package cn.telling.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**   
 * @Title: TellingConstance.java 
 * @Package com.Common.Uitl 
 * @Description: TODO(常量表) 
 * @author 沙海   
 * @date 2013-3-28 下午3:15:23 
 * @version V1.0   
 */
public class TellingConstance {
	/**有效标识(删除)*/
	public static final String DEL_FLAG_1 = "1";
	/**无效标识(删除)*/
	public static final String DEL_FLAG_0 = "0";
	/**有效标识*/
	public static final String IS_VALID_01 = "01";
	/**无效标识*/
	public static final String IS_VALID_02 = "02";
	/**必填标识*/
	public static final String IS_REQUEIRED1 = "1";
	/**非必填标识*/
	public static final String IS_REQUEIRED2 = "0";
	/**父类型*/
	public static final String ROOT = "0";
	
	/**原类型产品*/
	public static final String SALETYPE_1 = "1";
	/**通信市场类产品*/
	public static final String SALETYPE_2 = "2";
	
	/**产品可销售*/
	public static final String isSales1 = "1";
	/**产品不可销售*/
	public static final String isSales2 = "0";
	
	/**上传产品图片默认值 */
	public static final String FILETYPE = "01";  //文件类型
	public static final String ISDEFAULT = "01"; //是否默认
	public static final String PICREMARK = "";   //图片备注
	

	
	/**分货*/
	public static final String FENG = "01";
	/**不分货*/
	public static final String NOFENG = "02";
	/**竞价模式*/
	public static final String BIDMODE = "01";
	/**指定供应商模式*/
	public static final String APPOINTMODE = "02";
	/**通讯市场模式*/
	public static final String TELMARKETMODE = "03";
	/**产品属性类型*/
	public static final Map<String,String> SPETYPE = new LinkedHashMap<String ,String>();
	static{
		SPETYPE.put("2", "联通");
		SPETYPE.put("1", "移动");
		SPETYPE.put("3", "电信");
		SPETYPE.put("4", "通用");
		SPETYPE.put("5", "综合");
	}
	
	
	/**运营商属性*/
	public static final Map<String,String>  CUSTOMER_ATTR = new LinkedHashMap<String ,String>();
	static{
		CUSTOMER_ATTR.put("5", "综合");
		CUSTOMER_ATTR.put("1", "移动");
		CUSTOMER_ATTR.put("2", "联通");
		CUSTOMER_ATTR.put("3", "电信");
	}
	
	/**串号录入方式*/
	public static final Map<String,String>  IMEI_WAY = new LinkedHashMap<String ,String>();
	static{
		IMEI_WAY.put("1", "按订单录入串号");
		IMEI_WAY.put("2", "按产品录入串号");
	}
	
	
	/**产品属性类型*/
	public static final Map<String,String> SPECIFICATION = new LinkedHashMap<String ,String>();
	static{
		SPECIFICATION.put("1", "价格其他");
		SPECIFICATION.put("2", "型号");
		SPECIFICATION.put("3", "网络制式");
		SPECIFICATION.put("4", "内存");
		SPECIFICATION.put("5", "颜色");
		SPECIFICATION.put("6", "价格电信");
		SPECIFICATION.put("7", "价格移动");
		SPECIFICATION.put("8", "价格联通");
	}
	/**频道属性类型*/
	public static final Map<String,String> CHANNEL_TYPE = new HashMap<String ,String>();
	static{
		CHANNEL_TYPE.put("0", "首页");
		CHANNEL_TYPE.put("4", "裸机");
		CHANNEL_TYPE.put("5", "秒杀专区");
		CHANNEL_TYPE.put("6", "营销专题");
		CHANNEL_TYPE.put("7", "合约办理");
		CHANNEL_TYPE.put("8", "促销专区");
		CHANNEL_TYPE.put("9", "满减专区");
//		CHANNEL_TYPE.put("10", "4.2专区");
        CHANNEL_TYPE.put("11","好机汇秒杀专区");
        CHANNEL_TYPE.put("12","好机汇限时抢购专区");
        CHANNEL_TYPE.put("13","好机汇运营商专区");
        /*Andy 2014-12-08*/
        CHANNEL_TYPE.put("14","优质客户专属限时抢购频道");
	}
	
	/**版块类型*/
	public static final Map<String,String> BLOCK_TYPE = new HashMap<String ,String>();
	static{
		BLOCK_TYPE.put("1", "通栏广告");
		BLOCK_TYPE.put("2", "单独推荐");
		BLOCK_TYPE.put("3", "产品版块");
		BLOCK_TYPE.put("4", "优秀供应商展示");
		BLOCK_TYPE.put("5", "指定合约机销售");
		BLOCK_TYPE.put("6", "秒杀专区");
		BLOCK_TYPE.put("7", "活动规则描述");
//		BLOCK_TYPE.put("8", "顶部banner");
		BLOCK_TYPE.put("9", "主推版块");
		BLOCK_TYPE.put("0", "版块banner");
		BLOCK_TYPE.put("a", "送券版块");
		BLOCK_TYPE.put("b", "超值组合版块");
		BLOCK_TYPE.put("c", "满减版块");
		BLOCK_TYPE.put("d", "页脚广告版块");
		BLOCK_TYPE.put("e", "轮播推荐版块");
		BLOCK_TYPE.put("f", "限时抢购版块");
		BLOCK_TYPE.put("g", "厂家快报");
		BLOCK_TYPE.put("h", "对联广告");
//		BLOCK_TYPE.put("i", "顶部弹出广告");
		BLOCK_TYPE.put("j", "好机汇产品型号版块");
		BLOCK_TYPE.put("k", "好机汇优质供应商推荐");
		BLOCK_TYPE.put("l", "营销活动");
		BLOCK_TYPE.put("m", "运营商营销活动");
	}
	/**推荐活动类型*/
	public static final String ACTIVITY_TYPE1 = "1";//秒杀
	public static final String ACTIVITY_TYPE2 = "2";//送券
	public static final String ACTIVITY_TYPE3 = "3";//组合
	public static final String ACTIVITY_TYPE4 = "4";//搭售
	public static final String ACTIVITY_TYPE5 = "5";//满减
	public static final String ACTIVITY_TYPE6 = "6";//限时抢购
	public static final String ACTIVITY_TYPE7 = "7";//团购
	
//	public static final String ACTIVITY_TYPE5 = "5";//送券活动
//	public static final String ACTIVITY_TYPE6 = "6";//组合活动
//	public static final String ACTIVITY_TYPE7 = "7";//
//	public static final String ACTIVITY_TYPE8 = "8";//满减	
//	public static final String ACTIVITY_TYPE9 = "9";//限时抢购
	
	/**版块活动对应关系*/
	public static final Map<String,String> BLOCK_ACTIVE_RELATION = new HashMap<String ,String>();
	static{
		BLOCK_ACTIVE_RELATION.put("a", "2");//送券
		BLOCK_ACTIVE_RELATION.put("b", "3");//组合
		BLOCK_ACTIVE_RELATION.put("c", "5");//满减
		BLOCK_ACTIVE_RELATION.put("f", "6");//限时抢购
	}
	/**推荐类型*/
	public static final String RECOMMEND_TYPE1 = "1";//产品推荐
	public static final String RECOMMEND_TYPE2 = "2";//广告推荐
	public static final String RECOMMEND_TYPE3 = "3";//活动推荐
	public static final String RECOMMEND_TYPE4 = "4";//供应商推荐
	public static final String RECOMMEND_TYPE5 = "5";//规则类维护
	public static final String RECOMMEND_TYPEK = "k";//规则类维护
	
	/**版块内容属性*/
	public static final Map<String,String> BLOCK_ATTR = new HashMap<String ,String>();
	static{
		BLOCK_ATTR.put("1", "品牌");
		BLOCK_ATTR.put("2", "供应商");
		BLOCK_ATTR.put("3", "");
	}
	/**广告图片展示类型*/
	public static final Map<String,String> ADVERT_PIC_SHOW_TYPE = new HashMap<String ,String>();
	static{
		ADVERT_PIC_SHOW_TYPE.put("1", "轮播");
		ADVERT_PIC_SHOW_TYPE.put("2", "横向列表");
		ADVERT_PIC_SHOW_TYPE.put("3", "纵向列表");
		ADVERT_PIC_SHOW_TYPE.put("4", "方格");
		ADVERT_PIC_SHOW_TYPE.put("5", "Banner");
		ADVERT_PIC_SHOW_TYPE.put("6", "主推列表");
		ADVERT_PIC_SHOW_TYPE.put("7", "对联广告");
	}
	/**区域维护根节点*/
	public static final String  AREAROOT= "0";
	
	/**销售类型 竞价*/
	public static final String SALETYPE1 = "1";
	/**销售类型 非定向*/
	public static final String SALETYPE2 = "2";
	/**销售类型 定向到客户*/
	public static final String SALETYPE3 = "3";
	/**销售类型 定向到区域*/
	public static final String SALETYPE4 = "4";
	
	/**竞价模式 连续竞价*/
	public static final String CONTINUOUS = "1";
	/**竞价模式非连续竞价*/
	public static final String UNCONTINUOUS = "0";
	
	/**销售状态 审核中*/
	public static final String SALESTATUS1 = "1";
	/**销售状态 审核未通过*/
	public static final String SALESTATUS2 = "2";
	/**销售状态 审核通过*/
	public static final String SALESTATUS3 = "3";
	/**销售状态 二审中*/
	public static final String SALESTATUS4 = "4";
	
	/**供应商产品状态 上架*/
	public static final String STATUS_1 = "1";
	/**供应商产品状态 下架*/
	public static final String STATUS_2 = "2";
	
	/**订单错误提示 1      您订购的产品库存不足，请修改订购数量，重新下单！*/
	public static final String ORDER_RESULT_1 = "1";
	/**订单错误提示 2* 您订购的产品数量超过抢购限定数量，请修改订购数量，重新下单！**/
	public static final String ORDER_RESULT_2 = "2";
	/**订单错误提示 3* 您订购的产品数量超过抢购活动剩余库存，请修改订购数量，重新下单！**/
	public static final String ORDER_RESULT_3 = "3";
	/**订单错误提示 4* 您已参与过当前抢购活动，请勿重复抢购！**/
	public static final String ORDER_RESULT_4 = "4";
	/**订单错误提示集合*/
	public static final Map<String,String> ORDER_RESULT_MAP = new HashMap<String ,String>();
	static{
		ORDER_RESULT_MAP.put("1", "您订购的产品库存不足，请修改订购数量，重新下单！");
		ORDER_RESULT_MAP.put("2", "您订购的产品数量超过抢购限定数量，请修改订购数量，重新下单！");
		ORDER_RESULT_MAP.put("3", "您订购的产品数量超过抢购活动剩余库存，请修改订购数量，重新下单！");
		ORDER_RESULT_MAP.put("4", "您已参与过当前抢购活动，请勿重复抢购！");
	}
	/**图片后缀*/
	public static final List<String> PICTYPE = new ArrayList<String>();
	static{
		PICTYPE.add("jpg");
		PICTYPE.add("jpeg");
		PICTYPE.add("bmp");
		PICTYPE.add("gif");
		PICTYPE.add("png");
	}
	/**产品类型 01 活动*/
	public static final String PRODUCT_TYPE_01 = "01";
	/**产品类型 02非 活动*/
	public static final String PRODUCT_TYPE_02 = "02";
	
	/**活动类型 01 团购*/
	public static final String PROTYPE_1 = "1";
	/**活动类型 02抢购*/
	public static final String PROTYPE_2 = "2";
	
	/**天音供应商ID 361*/
	public static final String TY_SUPPLY_ID = "361";
	
	/**国内品牌下面的其他*/
	public static final String QITA = "其它";
	/**产品筛选特殊项*/
	public static final Map<String,String> SEARCH_FIXED = new HashMap<String ,String>();
	static{
		SEARCH_FIXED.put("01", "价格");
		SEARCH_FIXED.put("02", "主显示屏");
		SEARCH_FIXED.put("03", "运营商");
	}
	/**产品筛选值特殊项*/
	public static final Map<String,String> SEARCH_VALUE = new HashMap<String ,String>();
	static{
		SEARCH_VALUE.put("75", "移动");
		SEARCH_VALUE.put("76", "联通");
		SEARCH_VALUE.put("77", "电信");
	}
	/**产品搜索项来源*/
	public static final Map<String,String> SEARCH_TYPE = new HashMap<String ,String>();
	static{
		SEARCH_TYPE.put("1", "无");
		SEARCH_TYPE.put("2", "产品属性");
		SEARCH_TYPE.put("3", "品牌");
	}
	/**直辖市名称**/
	public static final String BIGCITY[] =  {"北京","上海","天津","重庆"};
	
	/**安徽省产品*/
	public static final String ANHUIPRODUCT="1047";
	
	/**频道链接*/
	public static final Map<String,String> CHANNEL_LINK = new HashMap<String ,String>();
	static{
		CHANNEL_LINK.put("0", "/TellingB2B/frontbuyer/tellingPage/index.html");
		CHANNEL_LINK.put("4", "/TellingB2B/frontbuyer/tellingPage/index.html");
		CHANNEL_LINK.put("5", "/TellingB2B/frontbuyer/tellingPage/sk.html");
		CHANNEL_LINK.put("6", "/TellingB2B/frontbuyer/tellingPage/market.html");
		CHANNEL_LINK.put("7", "/TellingB2B/frontbuyer/unicom/fanghao.html");
		CHANNEL_LINK.put("8", "/TellingB2B/frontbuyer/tellingPromotion/sp.html");
		CHANNEL_LINK.put("9", "/TellingB2B/frontbuyer/tellingfullcut/fullcut.html");
//		CHANNEL_LINK.put("10", "/TellingB2B/frontbuyer/tellingactivity/activity.html");
        CHANNEL_LINK.put("11","/TellingB2B/frontbuyer/haosecondk/sk.html");
        CHANNEL_LINK.put("12","/TellingB2B/frontbuyer/PanicBuying/query.html");
        CHANNEL_LINK.put("13","/TellingB2B/frontbuyer/tellingmobile/tellingmobil.html");
        /*Andy 2014-12-08*/
        CHANNEL_LINK.put("14","/TellingB2B/frontbuyer/PanicBuying/qualityList.html");
	}
	/**价格政策角色定义*/
	public static final Map<String,String> ROLE_TYPE = new HashMap<String ,String>();
	static{
		ROLE_TYPE.put("01", "总部总经理");
		ROLE_TYPE.put("02", "总部电商财务经理");
		ROLE_TYPE.put("03", "总部产品经理");
		ROLE_TYPE.put("04", "分公司电商财务经理");
		ROLE_TYPE.put("05", "分公司产品经理");
	}
	

	public static final Map<String,String> SALESTATUS = new HashMap<String ,String>();
	static{
		SALESTATUS.put("1", "总部总经理");
		SALESTATUS.put("2", "总部电商财务经理");
		SALESTATUS.put("3", "总部产品经理");
		SALESTATUS.put("4", "分公司电商财务经理");
		
	}
	
	
	/**价格政策角色审核权限*/
	public static final Map<String, String> ROLE_REVIEW = new HashMap<String,String>();
	static{
		ROLE_REVIEW.put("01", "0,2%7");
		ROLE_REVIEW.put("02", "1,2%1,5");
		ROLE_REVIEW.put("03", "1,2%1,0,5,6");
		ROLE_REVIEW.put("04", "2,3%0,2,3,4,5,6,7,8,9");
		ROLE_REVIEW.put("05", "2,3%0,2,3,4,5,6,7,8,9");


	}
	
	/**价格政策角色审核权限*/
	public static final Map<String, String> POLICY_STATUS = new HashMap<String,String>();
	static{
		POLICY_STATUS.put("0", "分公司产品经理申请");
		POLICY_STATUS.put("1", "总部产品经理申请");
		POLICY_STATUS.put("2", "分公司电商财务经理驳回");
		POLICY_STATUS.put("3", "分公司电商财务经理审核通过");
		POLICY_STATUS.put("4", "总部产品经理驳回");
		POLICY_STATUS.put("5", "总部产品经理审核通过");
		POLICY_STATUS.put("6", "总部电商财务经理驳回");
		POLICY_STATUS.put("7", "总部电商财务经理审核通过");
		POLICY_STATUS.put("8", "总部总经理驳回");
		POLICY_STATUS.put("9", "总部总经理审核");
	}
	
	
	/**指定-未审核*/
	public static final String ASSIGNEDNOAPPROVE = "01";
	/**删除-未审核*/
	public static final String ASSIGNEDDEL = "02";
	/**生效*/
	public static final String ASSIGNEDAPPROVE = "03";
	
	
	/**产品属性：销售模式*/
	public static final Map<String,String> SALE_TYPE = new LinkedHashMap<String ,String>();
	static{
		SALE_TYPE.put("1", "非通讯市场");
		SALE_TYPE.put("2", "通讯市场");
	}
	/**模板类型*/
	public static final Map<String,String> MOBAN_TYPE = new HashMap<String ,String>();
	static{
		MOBAN_TYPE.put("1", "营销模板-20");
		MOBAN_TYPE.put("2", "营销模板-30");
		MOBAN_TYPE.put("3", "店铺营销模板");
		MOBAN_TYPE.put("4", "突出店铺模板");
		MOBAN_TYPE.put("5", "营销模板-20新");
		MOBAN_TYPE.put("6", "核心供应商专题模板"); // new add  moban_type 
	}
	/**模板链接*/
	public static final Map<String,String> MOBAN_LINK = new HashMap<String ,String>();
	static{
		MOBAN_LINK.put("1", "/TellingB2B/frontbuyer/hjhmarket/moban1.html");
		MOBAN_LINK.put("2", "/TellingB2B/frontbuyer/hjhmarket/moban2.html");
		MOBAN_LINK.put("3", "/TellingB2B/frontbuyer/hjhmarket/moban3.html");
		MOBAN_LINK.put("4", "/TellingB2B/frontbuyer/hjhmarket/moban4.html");
		MOBAN_LINK.put("5", "/TellingB2B/frontbuyer/hjhmarket/moban5.html");
		MOBAN_LINK.put("6", "/TellingB2B/frontbuyer/hjhmarket/moban6.html");
	}
	/**
	 * 通讯市场（'08'）
	 */

	public static final String TELLING_SUPPLY_01 = "'08'";
	/**
	 * 天联在线（'17','18','19','20'）
	 */
	public static final String TELLING_SUPPLY_02 = "'17','18','19','20'";
	/**
	 * 天联在线&通讯市场
	 */
	public static final String TELLING_SUPPLY_03 = "'08','17','18','19','20'";
	/**
	 * 好机汇所用产品类型
	 */
	public static final String HAO_PRODUCT_TYPE = "'99','97'";
	/**
	 * 页面搜索词配置文本信息-动态文本
	 */
	/** 产品类型  */
	public static final String PRODUCT_TYPE = "[CPLX]";
	/** 产品品牌 */
	public static final String PRODUCT_BRAND = "[CPPP]";
	/** 产品型号 */
	public static final String PRODUCT_MODEL = "[CPXH]";
	/** 产品名称 */
	public static final String PRODUCT_NAME = "[CPMC]";
	/** 店铺名称 */
	public static final String SHOP_NAME = "[DPMC]";
	/** 搜索条件 */
	public static final String SEARCH_CONDITION = "[SSTJ]";
	/** 品牌 */
	public static final String BRAND = "[PP]";
	/** 店铺介绍*/
	public static final String SHOP_INTRODUTION  = "[DPJS]";
	/** 标题*/
	public static final String TITLE = "01";
	/** 关键字 */
	public static final String KEYWORD = "02";
	/** 描述 */
	public static final String DESCRIBE = "03";

	/** 页面类型 */
	public static final Map<String,String> PAGE_TYPE = new HashMap<String ,String>();
	static{
		PAGE_TYPE.put("1", "天联网首页");
		PAGE_TYPE.put("2", "产品型号详情页");
		PAGE_TYPE.put("3", "店铺产品详情页");
		PAGE_TYPE.put("4", "限购产品详情页");
		PAGE_TYPE.put("5", "秒杀产品详情页");
		PAGE_TYPE.put("6", "店铺首页");
		PAGE_TYPE.put("7", "产品搜索结果页");
		PAGE_TYPE.put("8", "智品汇首页");
	}
	
	/** 手机快豹 我要搜货  */
	/**全网搜索 */
	public static final String WHOLENETWORK = "01";
	/** 淘宝搜索*/
	public static final String TAOBAO = "02";
	/** 商家搜索*/
	public static final String SELLER = "03";
	/** 手机快豹  我要进货 */
	/** 通讯市场*/
	public static final String COMMUNICATION = "01";
	/** 零售电商*/
	public static final String RETAILONLINE = "02";
	/**全文检索 缓存变量名什么***/
	public static final String TELLING_SEARCH_COM = "TELLING_SEARCH_COM";
	
}