package cn.telling.common;

/**
 * @Title: CompareNumber.java
 * @Package cn.telling.common
 * @Description: (描述该文件做什么)
 * @author 操圣
 * @date 2015-11-5 下午1:06:32
 * @version V1.0
 */
public class Test {

	public static void main(String[] args) {
		StringBuffer bf = new StringBuffer();
		bf.append("               with allprov as (select count(areaid) num " + "                                  from areainfo " + "                                 where arealevel = '2') 　 " + "                 select  " + "                        s.name supplyname,  " + // --供应商名称
				"                        p.productid,  " + // --产品ID
				"                        p.productname,  " + // --产品名称
				"                        sa.priceretailonline priceretailonline,  " + // --产品价格
																						// 原价
				"                        sa.id said," + "                        to_char(sysdate, 'yyyy-MM-dd') || ' ' || " + "                        to_char(pmh.l_startTime, 'HH24:MI:SS') startTime, " + // --
																																																				// 活动开始时间
				"                        to_char(sysdate, 'yyyy-MM-dd') || ' ' || " + "                        to_char(pmh.l_endtime, 'HH24:MI:SS') endTime,  " + // --活动结束时间
				"                        to_char(pmh.endTime, 'yyyy-MM-dd') || ' ' || " + "                        to_char(pmh.l_endtime, 'HH24:MI:SS') tkEndTime,  " + // --活动周期结束时间
				"                        to_char(pmh.STARTTIME, 'yyyy-MM-dd') || ' ' || " + "                        to_char(pmh.l_startTime, 'HH24:MI:SS') tkstartTime,  " + // --活动周期开始时间
				"                        pdh.sprice activeprice, " + // --产品价格
																		// 活动价格
				"                        pp.picturepath1 picture,  " + // --产品图片
				"						pdh.id  active_id,  " + // 活动id
				"						decode(pmh.status,1,'1',2,'1',3,'0',5,'1',7,'0',8,'0' ) endflag ," + // 活动状态
				"                        decode(ssh.areacount - allprov.num, 0, '0', '1') areaflag, " + "                        decode(ssh.areacount - nvl(ssh2.freecount, 0), 0, '0', '1') freightflag ,'02' as order_type,round(pdh.sprice*10/ sa.priceretailonline, 1) as discount " + "                   from supply s ");

		bf.append("  inner join promotionm_hjh pmh " + "                     on pmh.supplyid = s.id and (pmh.show_telling='1' or pmh.show_telling is null) " + "                    and pmh.status in (" + GroupStatus.inProcess.index() + "," + GroupStatus.intime.index() + "," + GroupStatus.waitPulish.index() + "," + GroupStatus.finished.index() + ",7,8) " + "                    and pmh.endtime >= " + "                        to_date(to_char(sysdate, 'yyyy/MM/dd'), 'yyyy/MM/dd') " + "                  inner join promotiond_hjh pdh " + "                     on pmh.id = pdh.pid " + "                    and pdh.status in (" + GroupBuyStatus.inProcess.index() + "," + GroupBuyStatus.intime.index() + "," + GroupBuyStatus.waitPulish.index() + "," + GroupBuyStatus.finish.index()
				+ ",7,8) ");
		bf.append(" inner join shop_product sp " + "                    on pdh.productid = sp.id " + "                    and sp.del_flag = '1' " + "                    and sp.isvalid = '01' ");
		bf.append("  inner join product_main p " + "                     on p.productid = sp.productid " + "                    and p.del_flag = '1' ");
		bf.append("  inner join supply_area0 sa " + "                     on sp.id = sa.supplyproductid " + "                    and sa.status = '1' " + "                    and sa.del_flag = '1' " + "                    and sa.isvalid = '01' " + "                    and sa.areaid = 1");
		bf.append("  inner join sh_area_freight_rules shf1 " + "                     on shf1.active_id = pdh.id " + "                    and shf1.areaid = 1" + "                    and shf1.protype = '02' ");/*
																																																				 * add
																																																				 * by
																																																				 * wuzheng
																																																				 * :
																																																				 * 县包
																																																				 */
		bf.append("   inner join (select shf.active_id, " + "                                     count(shf.areaid) areacount " + "                                from sh_area_freight_rules shf " + "                               where shf.protype = '02' " + "                               group by shf.active_id) ssh " + "                     on ssh.active_id = pdh.id " + "                   left join (select shf.active_id, " + "                                     count(shf.areaid) freecount " + "                                from sh_area_freight_rules shf " + "                               where shf.protype = '02' " + "                                 and shf.type = 2 " + "                               group by shf.active_id) ssh2 "
				+ "                     on ssh2.active_id = pdh.id " + "                   left join product_pic_relation ppr " + "                     on ppr.product_id = p.productid " + "                   left join product_picture pp " + "                     on pp.pictureid = ppr.pictureid " + "                    and pp.del_flag = '1' " + "                     left join allprov " + "                      on 1 = 1 " + "                  where s.del_flag = '1' " + "                    and s.supply_status = '02'  " + "                    and s.supply_type = '16' ");

		bf.append("  order by pmh.starttime desc");
		System.out.println(bf.toString());
	}
}
