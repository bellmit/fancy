package cn.telling.shop.dao.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cn.telling.common.vo.PageVo;
import cn.telling.shop.dao.IShopSearchDao;
import cn.telling.shop.vo.ShopInfoVo;
import cn.telling.shop.vo.ShopProductVo;
import cn.telling.utils.AutoInjection;

/**
 * 
 * @ClassName: ShopSearchDaoImpl
 * TODO
 * @author xingle
 * @date 2015-8-26 下午4:07:20
 */
@Repository
public class ShopSearchDaoImpl implements IShopSearchDao{
	
private static Logger logger = Logger.getLogger(ShopSearchDaoImpl.class);
	
	@Resource
	private JdbcTemplate jdbcTemplate;

	
	/**
	 * 
	 * @Description: TODO
	 * @param shopLs
	 * @return
	 * @author xingle
	 * @data 2015-8-26 下午4:16:00
	 */
	@Override
	public List<ShopProductVo> getShopProductLs(List<ShopInfoVo> shopLs) {
		StringBuffer sb = new StringBuffer(); 
		sb.append("select distinct sp.shopid,sp.productid,pm.productname,sa.id sa_id,");
		sb.append("sa.overplusnumber,sa.priceretailonline,pp.mobile_img1 picturepath1,pt.feature ");
		sb.append("from shop_product sp,supply_area0 sa,product_main pm,product_picture pp,");
		sb.append("product_pic_relation ppr,product_tag pt ");
		sb.append("where  sp.id = sa.supplyproductid and sp.productid = pm.productid ");
		sb.append("and pm.productid = ppr.product_id(+) and ppr.pictureid = pp.pictureid(+) ");
		sb.append("and sa.id = pt.supplyarea0(+) ");
		sb.append("and sp.del_flag = '1' and sp.isvalid = '01' ");
		sb.append("and sa.del_flag = '1' and sa.isvalid = '01' and sa.status = '1' and pm.del_flag = '1' ");		
		sb.append(" and ( sp.shopid in (");	
		for (int i = 0; i < shopLs.size(); i++) {
			String shopid = shopLs.get(i).getShopid();
			if (i % 900 == 0 && i>0) {
				sb = sb.append(") or sp.shopid in (");
			}
			if (i == shopLs.size() - 1 ||((i+1) % 900 == 0 && i>0)) {
				sb = sb.append("'" + shopid + "'");
			} else {
				sb = sb.append("'" + shopid + "',");
			}
		}
		sb = sb.append(" ) )  ");
		sb.append(" order by sp.shopid");	
		String sql = sb.toString();	
		logger.debug("查询分页店铺的所有产品sql："+sql);
		return jdbcTemplate.query(sql, new RowMapper<ShopProductVo>() {

			@Override
			public ShopProductVo mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				ShopProductVo vo = new ShopProductVo();
				AutoInjection.Rs2Vo(rs, vo, null);
				return vo;
			}
		}); 
		
	}


	/**
	 * 
	 * @Description: TODO
	 * @param shopls
	 * @param page
	 * @param isBuy
	 * @param userid
	 * @param areaid
	 * @param rand
	 * @param isup
	 * @return
	 * @author xingle
	 * @data 2015-8-26 下午4:18:49
	 */
	@Override
	public List<ShopInfoVo> getShopInfoLs(List<String> shopls, PageVo pageVo,
			boolean isBuy, BigDecimal userid, String areaid, String rand,
			String isup,List<String> areaIdLs) {
		List<BigDecimal> parmLs = new ArrayList<BigDecimal>();		
		String sql = this.getFilterShopLsSQL(shopls, isBuy, userid, areaid,
				rand, isup, parmLs,areaIdLs);	
		
		if(pageVo!=null){
			// 拼装分页sql
			StringBuffer sb = new StringBuffer();
			sb.append(" SELECT * FROM (SELECT ROWNUM row_, tt.* FROM (");
			sb.append(sql);
			sb.append(") tt ) WHERE row_ <=");
			sb.append(pageVo.getPageNow()*pageVo.getMessageForPage());
			sb.append(" AND row_ >=");
			sb.append((pageVo.getPageNow()-1)*pageVo.getMessageForPage()+1);
			sql = sb.toString();
		}	
		Object[] args = parmLs.toArray();
		logger.debug("分页过滤查询店铺信息sql："+sql);
		return jdbcTemplate.query(sql, args, new RowMapper<ShopInfoVo>() {

			@Override
			public ShopInfoVo mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				ShopInfoVo vo = new ShopInfoVo();
				AutoInjection.Rs2Vo(rs, vo, null);
				return vo;
			}
		}); 
	}


	/**
	 * TODO
	 * @param shopls
	 * @param isBuy
	 * @param userid
	 * @param areaid
	 * @param rand
	 * @param isup
	 * @param parmLs
	 * @return
	 * @author xingle
	 * @data 2015-8-26 下午4:20:33
	 */
	private String getFilterShopLsSQL(List<String> shopls, boolean isBuy,
			BigDecimal userid, String areaid, String rand, String isup,
			List<BigDecimal> parmLs,List<String> areaIdLs) {
		StringBuffer sql = new StringBuffer(); 
		sql = sql.append("with t1 as ");
		sql = sql.append(" (select s.user_id userid, s.province_id province, s.id, s.supply_type ");
		sql = sql.append(" from supply s ");
		sql = sql.append(" where s.del_flag = '1'and s.supply_status = '02' ");
		sql = sql.append("union all ");
		sql = sql.append(" select ts.userid, ts.province, ts.id, null supply_type ");
		sql = sql.append("from tempsaler ts where ts.status = '00') ");
		sql = sql.append("select distinct t.* ");
		sql = sql.append("from (select t3.*, nvl(t2.salenum, 0) salenum ");
		if(isBuy && null!= userid){
			sql = sql.append(",decode(t4.shop_id, '', '0', '1') attenFlg, decode(t5.supply_user_id, '', '0', '1') orderFlg ");
		}
		sql = sql.append(" from (select si.id shopid,");
		sql = sql.append(" si.shopname,t1.id  supplyid,si.selleruserid,t1.province supplyAreaId,");
		sql = sql.append("  t1.supply_type supplyType,si.shop_logo shoplogo, si.shop_introduction shopIntro,ul.sellerlevel shoplever");
		sql = sql.append(" from shop_info si, user_level ul, t1 ");
		sql = sql.append("where si.del_flag = '1' and ul.del_flag = '1' ");
		sql = sql.append("and si.selleruserid = ul.userid ");
		sql = sql.append("and si.selleruserid = t1.userid) t3, ");
		sql = sql.append("(select od.supply_user_id, count(od.product_num) salenum ");
		sql = sql.append("from order_detail0 od ");
		sql = sql.append("where od.del_flag = '1' and od.order_status <> '05' and od.add_date > sysdate - 30 ");
		sql = sql.append("group by od.supply_user_id) t2 ");
		if(isBuy && null!= userid){
			sql = sql.append(",(select distinct a.shop_id from attention_shop a ");
			sql = sql.append("where a.del_flag = '1' and a.user_id = ? ");
			parmLs.add(userid);
			sql = sql.append(" ) t4,");
			sql = sql.append(" (select distinct od.supply_user_id, od.supply_id ");
			sql = sql.append("from order_info oi, order_detail0 od ");
			sql = sql.append("where oi.id = od.order_id(+) ");
			sql = sql.append("and oi.customer_user_id = ? and oi.order_status = '02' ");
			parmLs.add(userid);
			sql = sql.append("and od.del_flag = '1')t5 ");
		}
		sql = sql.append("where t3.selleruserid = t2.supply_user_id(+) ");
		
		if(null!=areaid && !"".equals(areaid)){//所在区域id 有值的话，省包和县包的供应商根据销售区域过滤显示
			sql = sql.append("and ((exists ");
			sql = sql.append("(select 1 from SUPPLY_AREA sa  ");
			sql = sql.append(" where sa.supply_id = t3.supplyid ");
			sql = sql.append("and sa.area_id in ");
			sql = sql.append("(select ?  from dual ");
			parmLs.add(new BigDecimal(areaid));
			sql = sql.append("union all ");
			sql = sql.append("select provinceid from areainfo where areaid = ? )) and ");
			parmLs.add(new BigDecimal(areaid));
			sql = sql.append("t3.supplyType in ('06', '16')) ");
			sql = sql.append(" or(t3.supplyType not in ('06', '16'))) ");
		}
		else{//所在区域id 为空的话，只查好机汇的店铺
			sql = sql.append(" and t3.supplytype = '08' ");
		}		
		
		if(isBuy && null!= userid){
			sql = sql.append("and t3.shopid = t4.shop_id(+) and t2.supply_user_id = t5.supply_user_id(+) ");
		}
		sql = sql.append(" ) t ");
		sql = sql.append(" where 1 = 1 ");
		sql = sql.append(" and ( t.shopid in ( ");
		
		for (int i = 0; i < shopls.size(); i++) {
			String shopid = shopls.get(i);
			if (i % 900 == 0 && i>0) {
				sql = sql.append(") or t.shopid in (");
			}
			if (i == shopls.size() - 1 ||((i+1) % 900 == 0 && i>0)) {
				sql = sql.append("'" + shopid + "'");
			} else {
				sql = sql.append("'" + shopid + "',");
			}
		}
		sql = sql.append(" ) )  ");
		
		if (null!= areaIdLs && areaIdLs.size()>0 ){
			sql = sql.append(" and t.supplyareaid in ( ");
			for (int i = 0; i < areaIdLs.size(); i++) {
				String areaId = areaIdLs.get(i);
				if (i == areaIdLs.size() - 1) {
					sql = sql.append("'" + areaId + "'");
				} else {
					sql = sql.append("'" + areaId + "',");
				}
			}
			sql = sql.append(") ");
		}
		
		
		if(isBuy && null!= userid){
			sql = sql.append(" and (t.attenFlg = '1' or t.orderFlg = '1') ");
		}
			
		if("1".equals(rand)||"".equals(rand)){//按销量
			if("2".equals(isup) || "".equals(isup))
				sql = sql.append("order by t.salenum desc");
			else
				sql = sql.append("order by t.salenum ");			
		}
		else if("2".equals(rand)){//按信用
			if("1".equals(isup) || "".equals(isup))
				sql = sql.append("order by t.shoplever ");
			else
				sql = sql.append("order by t.shoplever desc");			
		}		
		return sql.toString();
	}



}
