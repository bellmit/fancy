package cn.telling.product.dao.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cn.telling.common.vo.PageVo;
import cn.telling.product.dao.IProductDao;
import cn.telling.product.vo.ControlVo;
import cn.telling.product.vo.ProductAttrVo;
import cn.telling.product.vo.ProductDetailVo;
import cn.telling.product.vo.ProductInfoVo;
import cn.telling.product.vo.ProductMainVo;
import cn.telling.product.vo.ProductSortVo;
import cn.telling.product.vo.ProductSubVo;
import cn.telling.shop.vo.ShopInfoVo;
import cn.telling.utils.AutoInjection;
import cn.telling.utils.BaseUtil;

/**
 * 
 * @ClassName: ProductDaoImpl
 * 
 * @author xingle
 * @date 2015-7-31 下午1:23:04
 */
@Repository
public class ProductDaoImpl implements IProductDao{
	
	private static Logger logger = Logger.getLogger(ProductDaoImpl.class);
	
	@Resource
	private JdbcTemplate jdbcTemplate;

	/**
	 * 
	 * @Description: 
	 * @return
	 * @author xingle
	 * @data 2015-7-31 下午5:57:27
	 */
	@Override
	public List<ProductMainVo> getProductMainLs() {
		String sql = "select pm.productid, pm.productname,pp.mobile_img1 picturepath1," +
				"pb.brandname,pmd.modelname productpattern, pm.color,ps.sortname category," +
				"pm.saletype from product_main pm,product_brand pb,product_picture pp," +
				"product_pic_relation ppr,product_model pmd, product_sort ps where pm.del_flag = '1' " +
				"and pm.productid = ppr.product_id(+) and ppr.pictureid = pp.pictureid(+) " +
				"and pm.brandid = pb.brandid(+) and pm.productpattern = pmd.modelid(+) " +
				"and pm.sortid = ps.sortid(+) and rownum<10";
		/*and pm.productid in( 17170,17145) */
		logger.debug("查询产品主表基本信息:"+BaseUtil.logSQL(sql, new Object[]{}));
		List<ProductMainVo> ls = jdbcTemplate.query(sql,
				new RowMapper<ProductMainVo>() {
					@Override
					public ProductMainVo mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						ProductMainVo vo = new ProductMainVo();
						AutoInjection.Rs2Vo(rs, vo, null);
						return vo;
					}
				});
		return ls;
	}

	/**
	 * 
	 * @Description: 
	 * @param productId
	 * @return
	 * @author xingle
	 * @data 2015-7-31 下午6:09:15
	 */
	@Override
	public List<ProductAttrVo> getProductAttrLs(BigDecimal productId) {
		String sql = "select distinct pm.productid,ps.specificationid, ps.specificationname," +
				"psp.parametervalue from product_main pm,product_param pp," +
				"product_specification_paramval psp,product_specification ps " +
				"where pm.productid = pp.product_id(+) and pp.param_id = psp.valueid " +
				"and ps.specificationid = psp.specificationid " +
				"and ps.specificationid in ('164', '165', '172', '5') " +
				"and pm.productid = ? ";
		 Object[] args = new Object[]{productId};
		List<ProductAttrVo> ls = jdbcTemplate.query(sql,args,
				new RowMapper<ProductAttrVo>() {
					@Override
					public ProductAttrVo mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						ProductAttrVo vo = new ProductAttrVo();
						AutoInjection.Rs2Vo(rs, vo, null);
						return vo;
					}
				});
		return ls;
	}

	/**
	 * 
	 * @Description: 
	 * @param productId
	 * @return
	 * @author xingle
	 * @data 2015-7-31 下午6:14:43
	 */
	@Override
	public List<ProductSubVo> getProductSubByHjhLs(BigDecimal productId) {
		String sql = "select t1.*,t2.saleNum from (select t.spProductId,0 spId,0 saId,t.areaid," +
				"0 customerId,min(t.priceretailonline) priceretailonline,sum(t.overplusnumber) overplusnumber" +
				",sum(t.hits) hits,max(t.onshelftime) onshelftime,t.supplyId,t.supplyName,t.shopname " +
				"from (select sp.productid spProductId,sp.id spId,sa.id saId,sa.areaid areaid," +
				"'' customerId,sa.priceretailonline,sa.overplusnumber,nvl(sa.hits, 0) hits," +
				"sa.onshelftime,first_value(s.id) over(partition by pm.productid " +
				"order by sa.priceretailonline asc) as supplyid," +
				"first_value(s.name) over(partition by pm.productid order by sa.priceretailonline asc) as supplyname," +
				"first_value(si.shopname) over(partition by pm.productid order by sa.priceretailonline asc) as shopname " +
				"from product_main pm,supply_area0 sa,shop_product sp,supply s,shop_info si,areainfo ai " +
				"where pm.productid = sp.productid(+) and sp.id = sa.supplyproductid(+) " +
				"and sp.supplyid = s.id(+) and s.user_id = si.selleruserid(+) " +
				"and sa.areaid = ai.areaid(+) and sa.del_flag = '1' and sa.isvalid = '01' " +
				"and sp.del_flag = '1' and sp.isvalid = '01' and s.del_flag = '1' " +
				"and pm.productid = ? ) t group by t.spproductid,t.areaid,t.supplyid,t.supplyname,t.shopname) t1," +
				"(select od.product_id, sum(od.product_num) salenum from order_detail0 od " +
				"where od.product_id = ? and od.del_flag = '1' and od.order_status <> '05' group by od.product_id) t2 " +
				"where t1.spproductid = t2.product_id ";
		 Object[] args = new Object[]{productId,productId};
		List<ProductSubVo> ls = jdbcTemplate.query(sql,args,
				new RowMapper<ProductSubVo>() {
					@Override
					public ProductSubVo mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						ProductSubVo vo = new ProductSubVo();
						AutoInjection.Rs2Vo(rs, vo, null);
						return vo;
					}
				});
		return ls;
	}

	/**
	 * 
	 * @Description: 
	 * @param productId
	 * @return
	 * @author xingle
	 * @data 2015-7-31 下午6:19:04
	 */
	@Override
	public List<ProductSubVo> getProductSubLs(BigDecimal productId) {
		String sql = "select sp.productid spProductId,sp.id spId,sa.id saId,s.id supplyid," +
				"s.name supplyName,si.shopname shopname,sa.areaid areaid,nvl(sa.customid,0) customerId," +
				"sa.priceretailonline ,sa.overplusnumber,nvl(sa.hits,0) hits,nvl(t.saleNum,0) saleNum " +
				"from product_main pm ,supply_area0 sa,shop_product sp,supply s,shop_info si,areainfo ai," +
				"(select od.supply_area_id, sum(od.product_num) saleNum from order_detail0 od " +
				"where od.del_flag = '1' and od.order_status <> '05' and od.product_id = ? group by od.supply_area_id)t " +
				"where pm.productid = sp.productid(+) and sp.id = sa.supplyproductid(+) " +
				"and sp.supplyid = s.id(+) and s.user_id = si.selleruserid(+) and sa.areaid = ai.areaid(+) " +
				"and sa.id= t.supply_area_id(+) and sa.del_flag = '1' and sa.isvalid = '01' " +
				"and sp.del_flag = '1' and sp.isvalid = '01' and s.del_flag = '1' and pm.productid =? ";
		 Object[] args = new Object[]{productId,productId};
		List<ProductSubVo> ls = jdbcTemplate.query(sql,args,
				new RowMapper<ProductSubVo>() {
					@Override
					public ProductSubVo mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						ProductSubVo vo = new ProductSubVo();
						AutoInjection.Rs2Vo(rs, vo, null);
						return vo;
					}
				});
		return ls;
	}

	/**
	 * 
	 * @Description: 
	 * @return
	 * @author xingle
	 * @data 2015-8-6 上午10:55:46
	 */
	@Override
	public List<ProductInfoVo> getProductMainLs1() {
		String sql = "select t2.*,t4.minprice,t4.maxprice, to_char(t3.parametervalues) productAttribute from " +
				"(select pm.productid,pm.productname,pp.mobile_img1 picturepath1,pb.brandname brand," +
				"pmd.modelname productpattern,pm.color,ps.sortname category,pm.saletype " +
				"from product_main pm,product_brand pb,product_picture pp,product_pic_relation ppr," +
				"product_model pmd,product_sort ps where pm.del_flag = '1'  " +
				"and pm.productid = ppr.product_id(+) and ppr.pictureid = pp.pictureid(+) " +
				"and pm.brandid = pb.brandid(+) and pm.productpattern = pmd.modelid(+) " +
				"and pm.sortid = ps.sortid(+)) t2,(select t1.productid," +
				"wmsys.wm_concat(t1.parametervalues) parametervalues " +
				"from (select t.productid,'{' || t.specificationid || '} ' ||t.specificationname || ' ' || t.parametervalues ||' ####' parametervalues " +
				"from (select pm.productid,ps.specificationid,ps.specificationname," +
				"wmsys.wm_concat(psp.parametervalue) parametervalues " +
				"from product_main pm,product_param pp,product_specification_paramval psp," +
				"product_specification ps where pm.productid = pp.product_id(+) " +
				"and pp.param_id = psp.valueid and ps.specificationid = psp.specificationid " +
				"group by pm.productid,ps.specificationid,ps.specificationname) t) t1 " +
				"group by t1.productid) t3," +
				"(select pm.productid,CASE WHEN s.supply_type = '08' and s.supply_status = '02' " +
				"THEN min(sa.priceretailonline) ELSE min(sa.priceretailonline) END as minprice," +
				"CASE WHEN s.supply_type = '08' and s.supply_status = '02' " +
				"THEN min(sa.priceretailonline) ELSE max(sa.priceretailonline) END as maxprice " +
				"from product_main pm,supply_area0 sa,shop_product sp,supply s " +
				"where pm.productid = sp.productid(+) and sp.id = sa.supplyproductid(+) " +
				"and sp.supplyid = s.id(+) and sa.del_flag = '1' and sa.isvalid = '01' " +
				"and sp.del_flag = '1' and sp.isvalid = '01' and s.del_flag = '1' " +
				"and pm.del_flag = '1' group by pm.productid, s.supply_type, s.supply_status)t4 " +
				"where t2.productid = t4.productid and t4.productid = t3.productid(+)  ";
		logger.debug("***** lucene索引  查询产品基本信息开始:"+BaseUtil.logSQL(sql, new Object[]{}));
		List<ProductInfoVo> ls = jdbcTemplate.query(sql,
				new RowMapper<ProductInfoVo>() {
					@Override
					public ProductInfoVo mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						ProductInfoVo vo = new ProductInfoVo();
						AutoInjection.Rs2Vo(rs, vo, null);
						return vo;
					}
				});
		logger.debug("***** lucene索引   查询产品主表基本信息结束**************");
		return ls;
	}

	/**
	 * 
	 * @Description: 
	 * @param pls
	 * @param areaLs
	 * @param userid
	 * @return
	 * @author xingle
	 * @data 2015-8-11 上午11:30:28
	 */
	@Override
	public List<ProductDetailVo> getFilterProductLs(List<String> pls,
			List<String> areaLs, BigDecimal userid, PageVo pageVo, String rand,
			String isup,ControlVo isControl) {
		String sql = this.getFilterProductLsSQL(pls,areaLs,userid,rand,isup,isControl);
		if(pageVo!=null){
			// 拼装分页sql
			StringBuffer sb = new StringBuffer();
			sb.append(" SELECT * FROM (SELECT ROWNUM row_, t.* FROM (");
			sb.append(sql);
			sb.append(") t ) WHERE row_ <=");
			sb.append(pageVo.getPageNow()*pageVo.getMessageForPage());
			sb.append(" AND row_ >=");
			sb.append((pageVo.getPageNow()-1)*pageVo.getMessageForPage()+1);
			sql = sb.toString();
		}
		logger.debug("分页过滤产品sql:"+BaseUtil.logSQL(sql, new Object[]{}));
		List<ProductDetailVo> ls = jdbcTemplate.query(sql,
				new RowMapper<ProductDetailVo>() {
					@Override
					public ProductDetailVo mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						ProductDetailVo vo = new ProductDetailVo();
						AutoInjection.Rs2Vo(rs, vo, null);
						return vo;
					}
				});
		return ls;
	}

	/**
	 * 
	 * @param pls
	 * @param areaLs
	 * @param userid
	 * @return
	 * @author xingle
	 * @data 2015-8-13 下午12:06:11
	 */
	private String getFilterProductLsSQL(List<String> pls, List<String> areaLs,
			BigDecimal userid,String rand,String isup,ControlVo isControl) {
		StringBuffer sql = new StringBuffer();
		sql = sql.append("select * from (");
		sql = sql.append("select t1.*,nvl(decode(t1.hjhtype,'1',t2.salenum,t3.salenum),0) salenum from ( ");

		sql = sql.append("select distinct pm.productid,decode(s.supply_type,'08',decode(s.supply_status,'02','1','0') ,'0') hjhtype, ");
		sql = sql.append("decode(s.supply_type,'08',first_value(pt.feature) over(partition by pm.productid order by sa.priceretailonline asc),pt.feature)feature,");
		sql = sql.append("pm.productname,pm.color,pp.mobile_img1 picturepath1,pm.brandid,pb.brandname,");
		sql = sql.append(" pmd.modelid,pmd.modelname productpattern,pm.saletype,");
		
		sql = sql.append("decode(s.supply_type,'08',0,sa.id) saId,decode(s.supply_type,'08',0,sp.id) spId,sa.areaid,sa.customid,");
		sql = sql.append("first_value(s.id) over(partition by pm.productid order by sa.priceretailonline asc) as supplyid,");
		sql = sql.append("first_value(s.name) over(partition by pm.productid order by sa.priceretailonline asc) as supplyname,");
		sql = sql.append("first_value(si.shopname) over(partition by pm.productid order by sa.priceretailonline asc) as shopname,");
		sql = sql.append("first_value(sa.priceretailonline) over(partition by pm.productid order by sa.priceretailonline asc) as priceretailonline,");
		sql = sql.append("decode(s.supply_type,'08',sum(sa.overplusnumber) over(partition by pm.productid) ,sa.overplusnumber)overplusnumber,");
		sql = sql.append("first_value(sa.onshelftime) over(partition by pm.productid order by sa.onshelftime asc) as onshelftime,");
		sql = sql.append("decode(s.supply_type,'08',sum(sa.hits) over(partition by pm.productid) ,nvl(sa.hits,0)) hits");

		sql = sql.append(" from product_main pm, supply_area0 sa ,shop_product sp, supply s,shop_info si,");
		sql = sql.append("product_picture pp,product_pic_relation ppr,product_brand pb,product_model pmd,product_tag pt ");
				
		sql = sql.append("where pm.productid = sp.productid(+)");
		sql = sql.append("and sp.id = sa.supplyproductid(+)");
		sql = sql.append("and sp.productid = ppr.product_id(+)");
		sql = sql.append("and ppr.pictureid = pp.pictureid(+)");
		sql = sql.append("and sp.supplyid = s.id(+)");
		sql = sql.append("and s.user_id = si.selleruserid(+)");
		sql = sql.append("and pm.brandid = pb.brandid(+)");
		sql = sql.append("and pm.productpattern = pmd.modelid(+)");
		sql = sql.append("and sa.id = pt.supplyarea0(+)");

		sql = sql.append(" and ( pm.productid in (");
		for (int i = 0; i < pls.size(); i++) {
			String productId = pls.get(i);
			if (i % 900 == 0 && i>0) {
				sql = sql.append(")or pm.productid in (");
			}
			if (i == pls.size() - 1 ||((i+1) % 900 == 0 && i>0)) {
				sql = sql.append("'" + productId + "'");
			} else {
				sql = sql.append("'" + productId + "',");
			}
		}
		sql = sql.append(" ) )  ");
		
		sql = sql.append("and sa.del_flag = '1' and sa.isvalid = '01' and sa.status = '1' and sp.del_flag = '1' and sp.isvalid = '01'");
		if(null!=isControl){
			String productstandard = isControl.getProductstandard();
			if(null!= productstandard && !"".equals(productstandard)){
				sql = sql.append(" and pm.productbelongs = "+productstandard+" ");
			}
			String carrieroperator = isControl.getCarrieroperator();
			if(null!= carrieroperator && !"".equals(carrieroperator)){
				sql = sql.append("  and ((pm.operatorsver not in ("+carrieroperator+")) or (pm.operatorsver is null))");
			}
			String supplier = isControl.getSupplier();
			if(null!= supplier && !"".equals(supplier)){
				sql = sql.append("  and sp.supplyid = "+supplier+" ");
			}
		}
				
		if (areaLs.size()>0 && userid !=null){
			sql = sql.append(" and ( sa.areaid in ( ");
			for (int i = 0; i < areaLs.size(); i++) {
				String areaId = areaLs.get(i);
				if (i == areaLs.size() - 1) {
					sql = sql.append("'" + areaId + "'");
				} else {
					sql = sql.append("'" + areaId + "',");
				}
			}
			sql = sql.append(") or sa.customid  = "+userid+" )");
		}
		
		//如果userid 和 areaid 有一个没有，视为未登陆，值取好机汇的产品
		if(areaLs.size()==0||userid==null){
			sql = sql.append("and s.supply_type = '08' and s.supply_status = '02'");
		}
				
		sql = sql.append("group by pm.productid,pm.productname,s.supply_type,");
		sql = sql.append("s.supply_status,sa.id,sp.id,sa.areaid,sa.customid,s.id,sa.priceretailonline,");
		sql = sql.append("s.name,si.shopname,sa.overplusnumber,pm.color,pp.mobile_img1,pm.brandid,");
		sql = sql.append("pb.brandname,pmd.modelid,pmd.modelname,pm.saletype,pt.feature,sa.onshelftime,sa.hits");
		sql = sql.append(")t1, product_salenum t2, supply_salenum t3 ");
		sql = sql.append("where t1.productid = t2.product_id(+) ");
		sql = sql.append("and t1.said = t3.sa_id(+) ");
		sql = sql.append(")t ");
		
		if("1".equals(rand)||"".equals(rand)){
			if("2".equals(isup) || "".equals(isup))
				sql = sql.append("order by t.salenum desc");
			else
				sql = sql.append("order by t.salenum ");			
		}
		else if("2".equals(rand)){
			if("1".equals(isup) || "".equals(isup))
				sql = sql.append("order by t.priceretailonline ");
			else
				sql = sql.append("order by t.priceretailonline desc");			
		}
		else if("3".equals(rand)){
			if("1".equals(isup) || "".equals(isup))
				sql = sql.append(" order by t.onshelftime ");
			else
				sql = sql.append(" order by t.onshelftime desc");			
		}
		else if("4".equals(rand)){
			if("1".equals(isup) || "".equals(isup))
				sql = sql.append(" order by t.hits ");
			else
				sql = sql.append(" order by t.hits desc ");			
		}
			
		String sql1 = sql.toString();	
		return sql1;
	}

	/**
	 * 
	 * @Description: 
	 * @param areaid
	 * @return
	 * @author xingle
	 * @data 2015-8-11 上午9:15:39
	 */
	@Override
	public List<String> getAreaLs(BigDecimal areaid) {
		String sql = "select to_char(a.areaid) areaid from areainfo a where a.arealevel >= a.arealevel "
				+ "start with a.areaid = ? connect by prior a.parentid = a.areaid";
		Object[] args = new Object[] { areaid };
		return jdbcTemplate.query(sql, args, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				String vo = new String();
				vo = rs.getString("areaid");
				return vo;
			}
		});
	}

	/**
	 * 
	 * @Description: 
	 * @param total
	 * @return
	 * @author xingle
	 * @data 2015-8-12 上午9:32:03
	 */
	@Override
	public List<ProductInfoVo> getProductMainSegLs(int total, int current) {
//		synchronized(this){
//			Util.i ++;
//		}
		
		String sql = "select t2.*,t4.minprice,t4.maxprice, to_char(t3.parametervalues) productAttribute from " +
				"(select pm.productid,pm.productname,pp.mobile_img1 picturepath1,pb.brandname brand," +
				"pmd.modelname productpattern,pm.color,ps.sortname category,pm.saletype " +
				"from product_main pm,product_brand pb,product_picture pp,product_pic_relation ppr," +
				"product_model pmd,product_sort ps where pm.del_flag = '1' and  mod(pm.productid,?) = ? " +
				"and pm.productid = ppr.product_id(+) and ppr.pictureid = pp.pictureid(+) " +
				"and pm.brandid = pb.brandid(+) and pm.productpattern = pmd.modelid(+) " +
				"and pm.sortid = ps.sortid(+)) t2,(select t1.productid," +
				"listagg(t1.parametervalues,',')within group(order by 1) parametervalues " +
				"from (select t.productid,'{' || t.specificationid || '} ' ||t.specificationname || ' ' || t.parametervalues ||' ####' parametervalues " +
				"from (select pm.productid,ps.specificationid,ps.specificationname," +
				"listagg(psp.parametervalue,',')within group(order by 1) parametervalues " +
				"from product_main pm,product_param pp,product_specification_paramval psp," +
				"product_specification ps where pm.productid = pp.product_id(+) " +
				"and pp.param_id = psp.valueid and ps.specificationid = psp.specificationid " +
				"group by pm.productid,ps.specificationid,ps.specificationname) t) t1 " +
				"group by t1.productid) t3," +
				"(select pm.productid,CASE WHEN s.supply_type = '08' and s.supply_status = '02' " +
				"THEN min(sa.priceretailonline) ELSE min(sa.priceretailonline) END as minprice," +
				"CASE WHEN s.supply_type = '08' and s.supply_status = '02' " +
				"THEN min(sa.priceretailonline) ELSE max(sa.priceretailonline) END as maxprice " +
				"from product_main pm,supply_area0 sa,shop_product sp,supply s " +
				"where pm.productid = sp.productid(+) and sp.id = sa.supplyproductid(+) " +
				"and sp.supplyid = s.id(+) and sa.del_flag = '1' and sa.isvalid = '01' and sa.status = '1' " +
				"and sp.del_flag = '1' and sp.isvalid = '01' and s.del_flag = '1' " +
				"and pm.del_flag = '1' group by pm.productid, s.supply_type, s.supply_status)t4 " +
				"where t2.productid = t4.productid and t4.productid = t3.productid(+)  ";
		Object[] arg = new Object[]{total,current};
		logger.debug("***** lucene索引  查询产品基本信息开始,第"+current+"****"+BaseUtil.logSQL(sql, arg));
		List<ProductInfoVo> ls = jdbcTemplate.query(sql,arg,
				new RowMapper<ProductInfoVo>() {
					@Override
					public ProductInfoVo mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						ProductInfoVo vo = new ProductInfoVo();
						AutoInjection.Rs2Vo(rs, vo, null);
						return vo;
					}
				});
		logger.debug("***** lucene索引   查询产品主表基本信息结束**************");
		return ls;
	}

	/**
	 * 
	 * @Description: 
	 * @param pls
	 * @param areaLs
	 * @param userid
	 * @return
	 * @author xingle
	 * @data 2015-8-18 下午4:55:19
	 */
	@Override
	public List<ProductSortVo> getProductSort(List<String> pls,  List<String> areaLs,
			BigDecimal userid,ControlVo isControl) {
		String sql = this.getProductSortSQL(pls,areaLs,userid,isControl);
		logger.debug("查询产品返回分类类目信息:"+BaseUtil.logSQL(sql, new Object[]{}));
		List<ProductSortVo> ls = jdbcTemplate.query(sql,
				new RowMapper<ProductSortVo>() {
					@Override
					public ProductSortVo mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						ProductSortVo vo = new ProductSortVo();
						AutoInjection.Rs2Vo(rs, vo, null);
						return vo;
					}
				});
		return ls;
	}

	/**
	 * 
	 * @param pls
	 * @param areaLs
	 * @param areaLs2
	 * @return
	 * @author xingle
	 * @data 2015-8-18 下午4:58:26
	 */
	private String getProductSortSQL(List<String> pls, List<String> areaLs,
			BigDecimal userid,ControlVo isControl) {
		StringBuffer sql = new StringBuffer();
		sql = sql.append("select t.sortid, t.sortname,count(1) num from ( ");
		sql = sql.append("select distinct pm.productid,pm.sortid,ps.sortname, ");
		sql = sql.append("decode(s.supply_type,'08',0,sa.id) saId,sa.areaid,sa.customid,");
		sql = sql.append("first_value(s.id) over(partition by pm.productid order by sa.priceretailonline asc) as supplyid ");
		sql = sql.append("from product_main pm, supply_area0 sa ,shop_product sp, supply s,shop_info si,product_brand pb,product_sort ps ");
		sql = sql.append("where pm.productid = sp.productid(+)");
		sql = sql.append("and sp.id = sa.supplyproductid(+) ");
		sql = sql.append("and sp.supplyid = s.id(+) ");
		sql = sql.append("and s.user_id = si.selleruserid(+) ");
		sql = sql.append("and pm.brandid = pb.brandid(+) ");
		sql = sql.append("and pm.sortid = ps.sortid(+) ");
		sql = sql.append(" and ( pm.productid in (");
		for (int i = 0; i < pls.size(); i++) {
			String productId = pls.get(i);
			if (i % 900 == 0 && i>0) {
				sql = sql.append(")or pm.productid in (");
			}
			if (i == pls.size() - 1 ||((i+1) % 900 == 0 && i>0)) {
				sql = sql.append("'" + productId + "'");
			} else {
				sql = sql.append("'" + productId + "',");
			}
		}
		sql = sql.append(" ) )  ");
		sql = sql.append("and sa.del_flag = '1' and sa.isvalid = '01' and sp.del_flag = '1' and sp.isvalid = '01'");
		sql = sql.append("and sa.del_flag = '1' and sa.isvalid = '01' and sp.del_flag = '1' and sp.isvalid = '01'");
		
		if(null!=isControl){
			String productstandard = isControl.getProductstandard();
			if(null!= productstandard && !"".equals(productstandard)){
				sql = sql.append(" and pm.productbelongs = "+productstandard+" ");
			}
			String carrieroperator = isControl.getCarrieroperator();
			if(null!= carrieroperator && !"".equals(carrieroperator)){
				sql = sql.append("  and ((pm.operatorsver not in ("+carrieroperator+")) or (pm.operatorsver is null))");
			}
			String supplier = isControl.getSupplier();
			if(null!= supplier && !"".equals(supplier)){
				sql = sql.append("  and sp.supplyid = "+supplier+" ");
			}
		}		
		if (areaLs.size()>0 && userid !=null){
			sql = sql.append(" and ( sa.areaid in ( ");
			for (int i = 0; i < areaLs.size(); i++) {
				String areaId = areaLs.get(i);
				if (i == areaLs.size() - 1) {
					sql = sql.append("'" + areaId + "'");
				} else {
					sql = sql.append("'" + areaId + "',");
				}
			}
			sql = sql.append(") or sa.customid  = "+userid+" )");
		}
		
		//如果userid 和 areaid 有一个没有，视为未登陆，值取好机汇的产品
		if(areaLs.size()==0||userid==null){
			sql = sql.append("and s.supply_type = '08' and s.supply_status = '02'");
		}
		
		sql = sql.append("group by pm.productid,pm.sortid,pm.productname,s.supply_type,s.supply_status,");
		sql = sql.append("sa.id,sa.areaid,sa.customid,s.id,sa.priceretailonline,ps.sortname");
		sql = sql.append(" )t");
		sql = sql.append(" group by t.sortid, t.sortname");
		
		return sql.toString();
	}

	/**
	 * 
	 * @Description: 
	 * @return
	 * @author xingle
	 * @data 2015-8-24 上午11:33:52
	 */
	@Override
	public List<ShopInfoVo> getShopIndexLs() {
		String sql = " select si.id shopid,si.shopname,t1.id supplyid," +
				"si.selleruserid,t1.province supplyAreaId," +
				"t1.supply_type supplyType,si.shop_logo shoplogo,si.shop_introduction shopIntro," +
				"nvl(round(t3.goodNum / t2.num, 4) * 100,0) goodrate," +
				"nvl(ul.sellerlevel,0) shoplever " +
				"from shop_info si, " +
				"(select s.user_id userid, s.province_id province, s.id, s.supply_type " +
				"from supply s  where s.del_flag = '1' and s.supply_status = '02' " +
				" union all " +
				"select ts.userid, ts.province, ts.id, NULL supply_type " +
				"from tempsaler ts  where ts.status = '00') t1," +
				"(select count(s.ctoblevel) num, s.shop_id " +
				"from shop_assess s " +
				"where 1 = 1 and s.del_flag = '1'  " +
				"group by s.shop_id) t2," +
				"(select count(s.ctoblevel) goodNum, s.shop_id " +
				" from shop_assess s " +
				" where s.ctoblevel = '1'and s.del_flag = '1' " +
				" group by s.shop_id) t3, user_level ul " +
				" where si.del_flag = '1' and si.selleruserid = t1.userid(+) " +
				" and si.id = t2.shop_id(+) and t1.userid =  ul.userid(+) " +
				" and t2.shop_id = t3.shop_id(+) and ul.del_flag = '1'";
		logger.debug("***** lucene索引   查询店铺基本信息开始********"+BaseUtil.logSQL(sql, new Object[]{}));
		List<ShopInfoVo> ls = jdbcTemplate.query(sql,
				new RowMapper<ShopInfoVo>() {
					@Override
					public ShopInfoVo mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						ShopInfoVo vo = new ShopInfoVo();
						AutoInjection.Rs2Vo(rs, vo, null);
						return vo;
					}
				});
		logger.debug("***** lucene索引   查询店铺基本信息结束**************");
		return ls;
	}

	/**
	 * 
	 * @Description: 
	 * @return
	 * @author xingle
	 * @data 2015-8-25 下午2:18:24
	 */
	@Override
	public BigDecimal getProvinceIdByAreaid(String areaid) {
		String sql = "select  connect_by_root(ai.areaid) provinceid from areainfo ai "
				+ "where ai.areaid = ? start with ai.parentid = '2' connect by prior ai.areaid = ai.parentid";
		Object[] arg = new Object[] { areaid };
		return jdbcTemplate.queryForObject(sql, arg, BigDecimal.class);
	}

	/**
	 * 
	 * @Description: TODO
	 * @return
	 * @author xingle
	 * @data 2015-8-27 下午1:59:27
	 */
	@Override
	public void updateProductSaleNum() {
		String sql = "merge into product_salenum p " +
				"using (select od.product_id, " +
				"sum(od.product_num) salenum from order_detail0 od where od.del_flag = '1' " +
				"and od.order_status <> '05'  group by od.product_id) np " +
				"on (p.product_id = np.product_id) " +
				"when matched " +
				"then update set p.salenum = np.salenum " +
				"when not matched then " +
				"insert values (np.product_id, np.salenum)";
		logger.debug("定时任务   更新产品销量："+BaseUtil.logSQL(sql, new Object[]{}));
		jdbcTemplate.execute(sql);
	}

	/**
	 * 
	 * @Description: TODO
	 * @return
	 * @author xingle
	 * @data 2015-8-27 下午1:59:46
	 */
	@Override
	public void updatesSupplyProductSaleNum() {
		String sql = "merge into supply_salenum t " +
				"using (select sa.id said, 0 salenum  " +
				"from supply_area0 sa where sa.del_flag = '1' and sa.isvalid = '01' and sa.status = '1')nt " +
				"on (t.sa_id = nt.said) " +
				"when matched then " +
				"update set t.salenum = nt.salenum " +
				"when not matched then  " +
				"insert values (nt.said, nt.salenum) ";
		logger.debug("定时任务  更新供应商产品销量："+BaseUtil.logSQL(sql, new Object[]{}));
		jdbcTemplate.execute(sql);
	}

	

}
