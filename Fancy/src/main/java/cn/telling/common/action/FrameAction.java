package cn.telling.common.action;
/*package cn.telling.common.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.telling.Users.Vo.ConditionVo;
import cn.telling.Users.Vo.UsersVo;
import cn.telling.areas.Service.IAreaInfoService;
import cn.telling.common.Enums.Users.UsersCategory;
import cn.telling.common.Enums.logoManage.LogoTypeEnum;
import cn.telling.common.Enums.logoManage.OperatorTypeEnum;
import cn.telling.common.service.ICurrentUserService;
import cn.telling.product.vo.SupplyVo;
import cn.telling.shop.vo.ShopInfoVo;
import cn.telling.utils.StringHelperTools;
*//**   
 * @Title: FrameAction.java 
 * @Package com.Common.acion 
 * @Description: 用于集成页面通用部分。header、footer、lefeter
 * @author 李 欢   
 * @date 2013-3-26 上午11:12:26 
 * @version V1.0   
 *//*
@Controller
@RequestMapping("/frame")
public class FrameAction {
	private static Logger logger=Logger.getLogger("collectionLog");
	*//**
	 * 产品搜索
	 *//*
	@Autowired
	@Qualifier("tellingSearchService")
	private ITellingSearchService tellingSearchService;
	*//**
	 * 当前登录用户Service
	 *//*
	@Autowired
	@Qualifier("CurrentUserService")
	private ICurrentUserService CurrentUserService;
	*//**
	 * 买家会员中心
	 *//*
	@Autowired
	@Qualifier("orderManagerBuyerService")
	private IOrderManagerBuyerService orderManagerBuyerService;
	*//**
	 * 卖家会员中心
	 *//*
	@Autowired
	@Qualifier("orderManagerSellerService")
	private IOrderManagerSellerService orderManagerSellerService;
	*//**
	 * 调用卖家服务
	 *//*
	@Autowired
	@Qualifier("supplyService")
	private ISupplyService supplyservice;
	*//**
	 * 调用买家服务
	 *//*
	@Autowired
	@Qualifier("buyerService")
	private IBuyer buyerService;
	*//**
	 * 调用延迟付款服务
	 *//*
	@Autowired
	@Qualifier("orderDelayPayService")
	private IOrderDelayPayService orderDelayPayService;
	*//**
	 * 频道管理
	 *//*
	@Autowired
	@Qualifier("channelService")
	private IChannelService channelService;
	*//**
	 * 聚合页
	 *//*
	@Autowired
	@Qualifier("polymerozationService")
	private IPolymerozationService polymerozationService;
	*//**
	 * 帮助中心Service
	 *//*
	@Autowired
	@Qualifier("HelpCenterService")
	private IHelpCenterService helpCenterService;
	*//**
	 * 购物车
	 *//*
	@Autowired
	@Qualifier("CartService")
	private ICartService cartService;
	*//**
	 * 区域
	 *//*
	@Autowired
	@Qualifier("AreaService")
	private IAreaInfoService areaInfoService;
	*//**
	 * 热门关键字搜索
	 *//*
	@Autowired
	@Qualifier("hotSearchService")
	private IHotSearchService hotSearchService;
	*//**
	 * 客服电话服务
	 *//*
	@Autowired
	@Qualifier("CsTelService")
	private ICsTelService csTelService;
	*//**
	 * logo
	 *//*
	@Autowired
	@Qualifier("LogoManageService")
	private ILogoManageService logoManageService;
	*//**
	 * 普通买家
	 *//*
	@Autowired
	@Qualifier("commonBuyerServiceImpl")
	private ICommonBuyerService commonBuyerServiceImpl;
	*//**
	 * 店铺首页head
	 *//*
	@Autowired
	@Qualifier("ShopIndexService")
	private IShopIndexService shopIndexService;

	*//**
	 * 临时卖家服务
	 *//*
	@Autowired
	@Qualifier("tempSalerManageService")
	private ITempSalerManageService tempSalerManageService;
	*//**
	 * 店铺首页head
	 *//*
	@Autowired
	@Qualifier("SellerShopManageService")
	private ISellerShopManageService sellerShopManageService;
	*//**
	 * 页面顶部ban
	 *//*
    @Resource
    private IPopularizeService popularizeService;
	
	@Autowired
	@Qualifier("ShopAttentionService")
	private IShopAttentionService shopAttentionService;
    *//**获取友情链接**//*
    @Resource
    private ITellingLinksService tellingLinksService;
	*//**
	 * 用於獲得頂部頁面部份
	 * @param pageFlag
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 * @throws IOException
	 *//*
	@RequestMapping(value = "/{pageFlag}", method = {RequestMethod.GET, RequestMethod.POST })
	public String toTop(@PathVariable(value = "pageFlag") String pageFlag,HttpServletRequest request, HttpServletResponse response,ModelMap map) throws IOException{
		*//**
		 * 1、后期可以扩展 。比如将菜单、权限放入缓存中。
		 * 2、不用每次都查询数据库。
		 * 3、每个方法都可以进行相应的扩展，加入自己的相應的邏輯
		 *//*
		String pagePath="";
		if("all".equals(pageFlag)){

			pagePath="frame/testframe";
			logger.debug(" pageFlag="+pageFlag +" 测试页面");
		}else if("top".equals(pageFlag)){
			UsersVo manager = new UsersVo();
			manager.setUserId((BigDecimal)request.getSession().getAttribute("userId"));
			manager = CurrentUserService.GetCurrentUser(manager);
			//查询logo
			LogoVo logoVo= queryLogo(String.valueOf(LogoTypeEnum.ManageLogo.value()),"",null);

			map.put("curManager", manager);
			map.put("logoVo", logoVo);
			pagePath="frame/newheader";
			logger.debug(" pageFlag="+pageFlag+" 头部加载！");
		}else if("left".equals(pageFlag)){
			pagePath="frame/newlefter";
			logger.debug(" pageFlag="+pageFlag+" 左侧菜单加载！");
		}else if("footer".equals(pageFlag)){
			pagePath="frame/newfooter";
			logger.debug(" pageFlag="+pageFlag+" 页脚加载！");
		}else if("fronttopbeforelogin".equals(pageFlag)){
			Map<String,List<AttributeVo>> conditionMap = tellingSearchService.queryCondition("2","1");
			List<PolymerozationVo>queryPolymerozationlist=polymerozationService.queryPolymerozation();
			map.put("queryPolymerozationlist", queryPolymerozationlist);
			String Productkeyword="";
			String shopnamekeyword="";
				Productkeyword=StringHelperTools.nvl(hotSearchService.getShopnamekeyword("2","2"));
				shopnamekeyword=StringHelperTools.nvl(hotSearchService.getShopnamekeyword("2","3"));
				List<HotSearchVo> searchList = hotSearchService.queryToDisplay("2");
				map.put("searchList", searchList);
			map.put("Productkeyword", Productkeyword);
			map.put("shopnamekeyword", shopnamekeyword);
			map.put("conditionMap", conditionMap);
			//查询logo
			LogoVo logoVo=logoManageService.queryLogomoren();
			map.put("logoVo", logoVo);
			//顶部广告
			topBannerAd(map,request);
			//在线客服获取服务器端时间
//			Calendar c = Calendar.getInstance();
//			int serviceOnline_hour=c.get(Calendar.HOUR_OF_DAY);	
//			int ServiceStatus=1 ;//判断在线客服是否下线的参数
//			if(serviceOnline_hour>=8 && serviceOnline_hour<20){
//				ServiceStatus=1;
//			}else{
//				ServiceStatus=0;
//			}
			String channel_id = request.getParameter("channel_id");
//			map.put("ServiceStatus", ServiceStatus);
			map.put("channel_id", channel_id);
			logger.debug(" 前台买家头部加载 pageFlag="+pageFlag+" 页脚加载！");
			pagePath="frame/frontheaderbeforelogin";
		}else if("frontsellerheader".equals(pageFlag)){
			UsersVo manager = new UsersVo();
			manager.setUserId((BigDecimal)request.getSession().getAttribute("userId"));
			manager = CurrentUserService.GetCurrentUser(manager);
			SupplyVo supplyVo=new SupplyVo();
			supplyVo=supplyservice.findSupplyByUserId(String.valueOf(manager.getUserId()));
			
			*//*************************************添加判断临时卖家逻辑 jiangchengkai 2014-4-18************************************************************//*
			// 不是供应商
			if(supplyVo == null){
				TempSalerVo tempSalerVo = tempSalerManageService.getTempSalerInfoById(manager.getUserId());
				supplyVo = new SupplyVo();
				if(tempSalerVo!=null){
					supplyVo.setName(tempSalerVo.getSalerName());
					supplyVo.setSupply_status("02");
					supplyVo.setSupply_type("08");
				}else{
					supplyVo.setSupply_status("01");
				}
			}
			//查询logo
			LogoVo logoVo= queryLogo(String.valueOf(LogoTypeEnum.SupplyLogo.value()),"",null);

			map.put("curManagerLogtime", manager.getCreateTime());
			map.put("customerName", supplyVo.getName());
			map.put("supplyVo", supplyVo);
			map.put("logoVo", logoVo);
			//设置卖家订单数量
			String supply_status = StringHelperTools.nvl(supplyVo.getSupply_status());
			if(!"01".equals(supply_status)){
				querySellerOrderumByHeader(request,map);
			}
			boolean showHelp = !isSubDomain(request, "hb");
			map.put("showHelp", showHelp);
			pagePath="frame/frontsellerheader";
			logger.debug(" 前台卖家头部加载 pageFlag="+pageFlag+" 页脚加载！");
		}else if("frontbuyerheader".equals(pageFlag)){
			UsersVo manager = new UsersVo();
			BigDecimal userId=(BigDecimal)request.getSession().getAttribute("userId");
			manager.setUserId(userId);
			manager = CurrentUserService.GetCurrentUser(manager);
			BuyerVo buyerVo=new BuyerVo();
			buyerVo=buyerService.queryBuyerByUserId(manager.getUserId());
			String buyerstatus = "";
			if(buyerVo!=null){
				buyerstatus = StringHelperTools.nvl(buyerVo.getBuyerstatus());
			}
			String is_control = StringHelperTools.nvl(request.getSession().getAttribute("is_control"));//是否受控 值为1
			if(buyerVo==null||(buyerVo!=null&&StringUtils.equals("01", buyerVo.getBuyerstatus()))){//普通买家
				CommonBuyerVo commonBuyerVo=commonBuyerServiceImpl.getById(userId+"");
				if(commonBuyerVo!=null){
					buyerVo=new BuyerVo();
					buyerVo.setBuyerstatus(buyerstatus);
					buyerVo.setAreaid(commonBuyerVo.getDistrict());
					buyerVo.setShopname(commonBuyerVo.getShopName());
					buyerVo.setProvince(commonBuyerVo.getProvince());
				}
			}
			
			// 取得区域Id
			BigDecimal areaid = buyerVo.getAreaid();
			CsTelVo csTel = csTelService.GetCsTelByAreaId(areaid!=null?areaid.toString():"");
			if(csTel==null) csTel = csTelService.GetCsTelByAreaId("");
			map.put("curManagerLogtime", manager.getCreateTime());
			map.put("customerName",buyerVo.getShopname());
			map.put("buyerVo",buyerVo);
			map.put("curManager", manager);
			map.put("csTel", csTel);
			List<BuyerAttributeVo> list=buyerService.queryBuyerAttributeListByUserId(userId);//查询买家属性
			//获取客户属性
			String rattr = TellingUtil.findBuyerAttr(list); 
			String areaId = StringHelperTools.nvl(buyerVo.getAreaid());
			// 取得省Id
			String Province =StringHelperTools.nvl(buyerVo.getProvince());
			String iscommonbuyer =(String) request.getSession().getAttribute("iscommonbuyer");
			
			if(iscommonbuyer=="1"){//普通买家
				List<ChannelVo> channelList = channelService.channelList(areaId,"2");
				map.put("channelList", channelList);
			}else{
				List<ChannelVo> channelList;
				if("1".equalsIgnoreCase(is_control)){
//					// 取得地市Id
					String ctiy = StringHelperTools.nvl(buyerVo.getCity());
					channelList = channelService.shkchannelList(ctiy,rattr);
//					channelList = channelService.shkchannelList(areaId,rattr);
				}else{
					channelList = channelService.channelList(areaId,rattr);
				}
				map.put("channelList", channelList);
			}
			List<PolymerozationVo>queryPolymerozationlist=polymerozationService.queryPolymerozation();
			map.put("queryPolymerozationlist", queryPolymerozationlist);
			map.put("is_control", is_control);
			String Productkeyword="";
			String shopnamekeyword="";
			String channel_id = request.getParameter("channel_id");
			Map<String,List<AttributeVo>> conditionMap = tellingSearchService.queryCondition(Province,"1");
			if(!"".equals(areaId)){
				Productkeyword=StringHelperTools.nvl(hotSearchService.getShopnamekeyword(Province,"2"));
				shopnamekeyword=StringHelperTools.nvl(hotSearchService.getShopnamekeyword(Province,"3"));
				List<HotSearchVo> searchList = hotSearchService.queryToDisplay(Province);
				map.put("searchList", searchList);
			}
			map.put("Productkeyword", Productkeyword);
			map.put("shopnamekeyword", shopnamekeyword);
			//查询logo
			LogoVo logoVo= queryLogo(String.valueOf(LogoTypeEnum.BuyerLogo.value()),rattr,areaid);
			if(logoVo==null){
				logoVo=logoManageService.queryLogomoren();
			}
//			//查询logo
//			LogoVo logoVo = null;
//			if(areaid != null && ("1".equals(rattr) || "2".equals(rattr) || "3".equals(rattr))){//买家区域已经填写并且客户属性唯一
//				logoVo= queryLogo(String.valueOf(LogoTypeEnum.BuyerLogo.value()),rattr,areaid);
//			}
			map.put("conditionMap", conditionMap);
			map.put("channel_id", channel_id);
			
			map.put("areaId", areaId);
			map.put("customer_attr", rattr);
			map.put("logoVo", logoVo);
//			map.put("logoVot", logoVot);
			//添加购物车数量
			// added by guohui 2013/09/27 begin
			long cartProductNum = 0l;
			if (buyerVo != null && buyerVo.getAreaid() != null) {
				BigDecimal area_id = areaInfoService.GetAreaByIdAndLevel(buyerVo.getAreaid(),3);//地市区域ID
				// added by guohui 2013/09/27 end
				CartDisplayVo cartDisplayVo
				= cartService.GetCartInfoByCustId(userId.toString(), area_id);
				if (cartDisplayVo != null) {
					cartProductNum = cartDisplayVo.getProductNum();
				}
			}
			map.put("cartProductNum", cartProductNum);

			//设置买家订单数量
			if(!"01".equals(buyerstatus)){
				queryBuyerOrderumByHeader(request,map);
			}
			//顶部广告
			topBannerAd(map,request);
			//在线客服获取服务器端时间
//			Calendar c = Calendar.getInstance();
//			int serviceOnline_hour=c.get(Calendar.HOUR_OF_DAY);	
//			int ServiceStatus=1 ;//判断在线客服是否下线的参数
//			if(serviceOnline_hour>=8 && serviceOnline_hour<20){
//				ServiceStatus=1;
//			}else{
//				ServiceStatus=0;
//			}
//			map.put("ServiceStatus", ServiceStatus);
				
			boolean showHelp = !isSubDomain(request, "hb");
			map.put("showHelp", showHelp);
			logger.debug(" 前台买家头部加载 pageFlag="+pageFlag+" 页脚加载！");
			pagePath="frame/frontbuyerheader";
		}else if("frontfooter".equals(pageFlag)){
			//取得所有帮助信息
			List<HelpCenterVo> t_helps = helpCenterService.GetHelpCenter4Front();
			//将帮助信息组织成子父关系
			List<HelpCenterVo> helps = new ArrayList<HelpCenterVo>();
			if(t_helps!=null)
			{
				for(int i=0; i < t_helps.size(); i++)
				{
					if(t_helps.get(i).getParentId()==null||"0".equalsIgnoreCase(t_helps.get(i).getParentId()))
					{
						helps.add(t_helps.get(i));
						for(int j=0; j < t_helps.size(); j++)
						{
							if(t_helps.get(j).getParentId()!=null&&t_helps.get(j).getParentId().equals(t_helps.get(i).getId()))
							{
								t_helps.get(i).getChild().add(t_helps.get(j));
							}
						}
					}
				}
			}
			boolean showHelp = !isSubDomain(request, "hb");
            //Todo 获取友情链接
            List<TellingLinksVo> voList = tellingLinksService.getLinksList(8);
            map.put("friendLinds",voList);
			map.put("showHelp", showHelp);
			map.put("helps", helps);
			pagePath="frame/frontfooter";
			logger.debug(" 前台底部加载  pageFlag="+pageFlag+" 页脚加载！");
		}else if("frontbuyerlefter".equals(pageFlag)){
			BigDecimal userId=(BigDecimal)request.getSession().getAttribute("userId");
			BuyerVo vo = buyerService.queryBuyerByUserId(userId);
			String is_control = StringHelperTools.nvl(request.getSession().getAttribute("is_control"));//是否受控 值为1
			map.put("is_control",is_control);	
			boolean isCommonBuyer=false;
			*//**
			 * 新增判断是否是联通用户
			 *//*
			//根据USERID获得BUYERID
			if(vo==null){//兼容普通买家
				CommonBuyerVo commonBuyerVo=commonBuyerServiceImpl.getById(userId+"");
				if(commonBuyerVo!=null){
					isCommonBuyer=true;
					vo=new BuyerVo();
					vo.setBuyerid(commonBuyerVo.getGbuyerId());
					vo.setBuyerstatus("01");
					vo.setAreaid(commonBuyerVo.getProvince());
					vo.setShopname(commonBuyerVo.getShopName());
				}
			}
			BigDecimal buyerId=vo.getBuyerid();
			List<BuyerAttributeVo> buyerAttributeList=buyerService.queryBuyerAttributeList(buyerId);
			String IsESS = "f";
			if (buyerAttributeList != null) {
				for(BuyerAttributeVo buyerAttributeVo : buyerAttributeList)
				{
					if(buyerAttributeVo.getBuyerattributeId().equals(new BigDecimal("2"))){
						IsESS = "t";
					}
				}
			}
			map.put("IsESS",IsESS);	


			//设置买家左侧订单数量，由于前台IE6不兼容js加载 故在此设置
			String buyerstatus = StringHelperTools.nvl(vo.getBuyerstatus());
			if(isCommonBuyer||!"01".equals(buyerstatus)){
				queryBuyerOrderumByLefter(request,map);
			}
			map.put("buyervo", vo);
			pagePath="frame/frontbuyerlefter";
			logger.debug(" 前台买家菜单加载 pageFlag="+pageFlag+" 页脚加载！");
		}else if("frontsellerlefter".equals(pageFlag)){
			BigDecimal userId=(BigDecimal)request.getSession().getAttribute("userId");
			SupplyVo vo=new SupplyVo();
			vo=supplyservice.findSupplyByUserId(String.valueOf(userId));
			

			//设置卖卖家订单数量
			if(vo==null || !"01".equals(StringHelperTools.nvl(vo.getSupply_status()))){
				querySellerOrderumByLefter(request,map);
			}
			
			*//*************************************添加判断临时卖家逻辑 jiangchengkai 2014-4-18************************************************************//*
			// 不是供应商
			if(vo == null){
				vo = new SupplyVo();
				vo.setSupply_type("08");
				@SuppressWarnings("unchecked")
				List<Integer> userCategoryList = (List<Integer>) request.getSession().getAttribute("userCategoryList");
				String isTempSeller = (String) request.getSession().getAttribute("isTempSeller");
				if(userCategoryList!=null)
				{
					for (Integer userCategoryId : userCategoryList) {
						if(UsersCategory.tempSeller.value() == userCategoryId)
						{
							vo.setSupply_status("02");
						}
					}
					if(vo.getSupply_status()==null)
					{
						if("1".equals(isTempSeller)){
							vo.setSupply_status("03");
						}else{
							vo.setSupply_status("01");
						}
					}
				}else
				{
					vo.setSupply_status("01");
				}
				
			}
			*//**************************************添加判断临时卖家逻辑 jiangchengkai 2014-4-18***********************************************************//*
			
			map.put("supplyvo", vo);
			pagePath="frame/frontsellerlefter";
			logger.debug(" 前台卖家菜单加载 pageFlag="+pageFlag+" 页脚加载！");
		}else if("frontlogininfo".equals(pageFlag)){
			pagePath="frame/frontlogininfo";
			UsersVo manager = new UsersVo();
			manager.setUserId((BigDecimal)request.getSession().getAttribute("userId"));
			Object category=request.getSession().getAttribute("categoryId");//从session里取categoryId
			String categoryId="";
			if(category!=null){
				categoryId=(String)category;
			}
			manager = CurrentUserService.GetCurrentUser(manager);
			if(categoryId.equals(String.valueOf(UsersCategory.Buyer.value())))
			{
				BuyerVo buyerVo=new BuyerVo();
				buyerVo=buyerService.queryBuyerByUserId(manager.getUserId());
				map.put("curManagerLogtime", manager.getCreateTime());
				map.put("customerName", buyerVo.getShopname());
			}else if(categoryId.equals(String.valueOf(UsersCategory.Supply.value())))
			{
				SupplyVo supplyVo=new SupplyVo();
				supplyVo=supplyservice.findSupplyByUserId(String.valueOf(manager.getUserId()));
				map.put("curManagerLogtime", manager.getCreateTime());
				map.put("customerName", supplyVo.getName());
			}else if(categoryId.equals(String.valueOf(UsersCategory.commonBuyer.value())))
			{
				CommonBuyerVo common=commonBuyerServiceImpl.getById(manager.getUserId()+"");
				map.put("curManagerLogtime", manager.getCreateTime());
				map.put("customerName", common.getShopName());
			}
			logger.debug(" 前台登录信息 pageFlag="+pageFlag+" 页脚加载！");
		}else if("frontbuyershopheader".equals(pageFlag)){
			UsersVo manager = new UsersVo();
			BigDecimal userId=(BigDecimal)request.getSession().getAttribute("userId");
			manager.setUserId(userId);
			manager = CurrentUserService.GetCurrentUser(manager);
			BuyerVo buyerVo=new BuyerVo();
			buyerVo=buyerService.queryBuyerByUserId(manager.getUserId());
			if(buyerVo==null||(buyerVo!=null&&StringUtils.equals("01", buyerVo.getBuyerstatus()))){//普通买家
				CommonBuyerVo commonBuyerVo=commonBuyerServiceImpl.getById(userId+"");
				if(commonBuyerVo!=null){
					buyerVo=new BuyerVo();
					buyerVo.setBuyerstatus("01");
					buyerVo.setAreaid(commonBuyerVo.getProvince());
					buyerVo.setShopname(commonBuyerVo.getShopName());
				}
			}
			// 取得区域Id
			BigDecimal areaid = buyerVo.getAreaid();
			CsTelVo csTel = csTelService.GetCsTelByAreaId(areaid!=null?areaid.toString():"");
			if(csTel==null) csTel = csTelService.GetCsTelByAreaId("");
			map.put("curManagerLogtime", manager.getCreateTime());
			map.put("customerName",buyerVo.getShopname());
			map.put("buyerVo",buyerVo);
			map.put("curManager", manager);
			map.put("csTel", csTel);
			*//**多余代码清除**//*
//			List<BuyerAttributeVo> list=buyerService.queryBuyerAttributeListByUserId(userId);//查询买家属性
			//获取客户
//			String rattr = TellingUtil.findBuyerAttr(list); 
//			String areaId = StringHelperTools.nvl(buyerVo.getAreaid());
//			String iscommonbuyer =(String) request.getSession().getAttribute("iscommonbuyer");
//			if(iscommonbuyer=="1"){//普通买家
//				List<ChannelVo> channelList = channelService.channelList(areaId,"2");
//				map.put("channelList", channelList);
//			}else{
//				List<ChannelVo> channelList = channelService.channelList(areaId,rattr);
//				map.put("channelList", channelList);
//			}
//			String channel_id = request.getParameter("channel_id");
//			Map<String,List<AttributeVo>> conditionMap = tellingSearchService.queryCondition(StringHelperTools.nvl(userId),rattr,areaId,"1");
//			if(!"".equals(areaId)){
//			List<HotSearchVo> searchList = hotSearchService.queryToDisplay(areaId);
//			map.put("searchList", searchList);
//			}
			//查询logo
//			LogoVo logoVo = null;
//			if(areaid != null && ("1".equals(rattr) || "2".equals(rattr) || "3".equals(rattr))){//买家区域已经填写并且客户属性唯一
//				logoVo= queryLogo(String.valueOf(LogoTypeEnum.BuyerLogo.value()),rattr,areaid);
//			}

//			map.put("conditionMap", conditionMap);
//			map.put("channel_id", channel_id);
//			map.put("areaId", areaId);
//			map.put("customer_attr", rattr);
//			map.put("logoVo", logoVo);
			
			//设置买家订单数量
			String buyerstatus = StringHelperTools.nvl(buyerVo.getBuyerstatus());
			if(!"01".equals(buyerstatus)){
				queryBuyerOrderumByHeader(request,map);
			}
			boolean showHelp = !isSubDomain(request, "hb");
			map.put("showHelp", showHelp);
			
			//店铺信息
			// 访问方式不同：接收shopid或者接收shopsld(域名访问)
			String shopidParam = StringHelperTools.nvl(request.getParameter("shopid"));
			String shopSldParam = StringHelperTools.nvl(request.getParameter("shopsld"));
			String shopid = "";
			// 先根据域名关键词参数去查询shopid，如果没找到则使用shopid参数
			if(!"".equals(shopSldParam)) {
				ShopInfoVo shopinfo = shopIndexService.getShopInfoBySld(shopSldParam);
				shopid = shopinfo==null?"":shopinfo.getId();
			} else {
				shopid = shopidParam;
			}
			
			String aym = StringHelperTools.nvl(request.getParameter("aym"));
			String conStr = StringHelperTools.nvl(request.getParameter("conStr"));
			String headSearchType = StringHelperTools.nvl(request.getParameter("headSearchType"));
			String headSearchName = StringHelperTools.nvl(request.getParameter("headSearchName"));
			ShopInfoVo shopVo = shopIndexService.getShopInfoSimple(shopid);
			shopVo.setAttentionFlag(shopAttentionService.attentionShopFlag(userId, shopid));
			map.put("shopVo",shopVo);
			map.put("conStr",conStr);
			map.put("headSearchType",headSearchType);
			map.put("headSearchName",headSearchName);
			
			//获取该店铺后台配置的关键词
			ShopFastSearchVo shopsearchvo = sellerShopManageService.getShopFastSearchname(shopid);
			map.put("shopsearchvo", shopsearchvo);
			
			//该店铺已有的快速搜索
			List<ShopFastSearchVo> haslist = sellerShopManageService.getAllFastSearchByShopId(shopid);
			List<String> haslistScreenid= new ArrayList<String>();
			for(ShopFastSearchVo vo:haslist){
				haslistScreenid.add(vo.getSortName()==null?"":vo.getSortName());
			}
			for ( int i = 0 ; i < haslistScreenid.size() - 1 ; i ++ ) {
				String screenidone=haslistScreenid.get(i);
			     for ( int j = haslistScreenid.size() - 1 ; j > i; j -- ) {
			    	 String screenid=haslistScreenid.get(j);
			       if (screenid.equals(screenidone)) {
			    	   haslistScreenid.remove(j);
			       }
			     }
			}
			map.put("haslist", haslist);
			map.put("haslistScreenid", haslistScreenid);
//			List<SupplyQqVo> qqLs = supplyQqService.getSupplyQqVoById(shopVo.getSellerUserid());
//			String qqStr = "";
//			for(SupplyQqVo vo:qqLs){
//				if("".equals(qqStr)){
//					qqStr = vo.getQQ();
//				}else{
//					qqStr = qqStr + ","+vo.getQQ();
//				}
//	    	}
//			map.put("qqLs", qqStr);
			//店铺信息end
			logger.debug(" 店铺头部加载 pageFlag="+pageFlag+" 页脚加载！");
			if("1".equals(aym)) {
				pagePath="frame/frontbuyershopheaderAym";
			} else {
				
				*//** wangtianjin add qq替换*//*
				String product_id = StringHelperTools.nvl(request.getParameter("product_id"));
				map.put("product_id", product_id);
				pagePath="frame/frontbuyershopheader";
			}
		}else if("frontbuyerloginshopheader".equals(pageFlag)){
			//店铺信息
			// 访问方式不同：接收shopid或者接收shopsld(域名访问)
			String shopidParam = StringHelperTools.nvl(request.getParameter("shopid"));
			String shopSldParam = StringHelperTools.nvl(request.getParameter("shopsld"));
			String shopid = "";
			// 先根据域名关键词参数去查询shopid，如果没找到则使用shopid参数
			if(!"".equals(shopSldParam)) {
				ShopInfoVo shopinfo = shopIndexService.getShopInfoBySld(shopSldParam);
				shopid = shopinfo==null?"":shopinfo.getId();
			} else {
				shopid = shopidParam;
			}
			String conStr = StringHelperTools.nvl(request.getParameter("conStr"));
			String headSearchType = StringHelperTools.nvl(request.getParameter("headSearchType"));
			String headSearchName = StringHelperTools.nvl(request.getParameter("headSearchName"));
			ShopInfoVo shopVo = shopIndexService.getShopInfoSimple(shopid);
			map.put("shopVo",shopVo);
			map.put("conStr",conStr);
			map.put("headSearchType",headSearchType);
			map.put("headSearchName",headSearchName);
			//获取该店铺后台配置的关键词
			ShopFastSearchVo shopsearchvo = sellerShopManageService.getShopFastSearchname(shopid);
			map.put("shopsearchvo", shopsearchvo);
			
			//该店铺已有的快速搜索
			List<ShopFastSearchVo> haslist = sellerShopManageService.getAllFastSearchByShopId(shopid);
			List<String> haslistScreenid= new ArrayList<String>();
			for(ShopFastSearchVo vo:haslist){
				haslistScreenid.add(vo.getSortName()==null?"":vo.getSortName());
			}
			for ( int i = 0 ; i < haslistScreenid.size() - 1 ; i ++ ) {
				String screenidone=haslistScreenid.get(i);
			     for ( int j = haslistScreenid.size() - 1 ; j > i; j -- ) {
			    	 String screenid=haslistScreenid.get(j);
			       if (screenid.equals(screenidone)) {
			    	   haslistScreenid.remove(j);
			       }
			     }
			}
			map.put("haslistScreenid", haslistScreenid);
			map.put("haslist", haslist);
			
			
//			List<SupplyQqVo> qqLs = supplyQqService.getSupplyQqVoById(shopVo.getSellerUserid());
//			String qqStr = "";
//			for(SupplyQqVo vo:qqLs){
//				if("".equals(qqStr)){
//					qqStr = vo.getQQ();
//				}else{
//					qqStr = qqStr + ","+vo.getQQ();
//				}
//	    	}
//			map.put("qqLs", qqStr);
			//店铺信息end
			logger.debug(" 店铺头部加载 pageFlag="+pageFlag+" 页脚加载！");
			*//** wangtianjin add qq替换*//*
			String product_id = StringHelperTools.nvl(request.getParameter("product_id"));
			map.put("product_id", product_id);
			pagePath="frame/frontbuyerloginshopheader";
		}
		return pagePath;
	}
	private void topBannerAd(ModelMap map, HttpServletRequest req) {
		String pagetype = StringHelperTools.nvl(req.getParameter("pagetype"));
		List<PopularizeVo> advertLs = popularizeService.findAllInUseByTop();
		int picCount=popularizeService.picCount();
		map.put("picCount", picCount);
		map.put("pagetype", pagetype);
		map.put("advertLssize", advertLs.size());
		map.put("advertLs", advertLs);
	}
	*//**
	 * @Description:动态获取当前买家左侧菜单个状态订单数量
	 * @param		参数说明
	 * @return		返回值
	 * @author      薛松坛
	 * @date 2013-3-25 下午9:33:13 
	 * @version V1.0
	 *//*
	private void  queryBuyerOrderumByLefter(HttpServletRequest request,ModelMap map){
		Map<String,String> parameterMap = new HashMap<String, String>();
		String login_id = StringHelperTools.nvl(request.getSession().getAttribute("userId"));
		parameterMap.put("login_id", login_id);
		List<OrderSumVo> ls = orderManagerBuyerService.getBuyerOrderSumMap(parameterMap);
		String order_num_01 = "0";
		String order_num_02 = "0";
		String order_num_03 = "0";
		String order_num_04 = "0";
		String order_num_05 = "0";
		String order_num_06 = "0";
		if(ls!=null&&ls.size()>0){
			for (int i = 0; i < ls.size(); i++) {
				String order_status = StringHelperTools.nvl(ls.get(i).getOrder_status());
				String order_sum = StringHelperTools.nvl(ls.get(i).getOrder_sum());
				if(OrderConstantValues.ORDER_STATUS_01.equals(order_status)){
					order_num_01=order_sum;
				}else if(OrderConstantValues.ORDER_STATUS_02.equals(order_status)){
					order_num_02=order_sum;
				}else if(OrderConstantValues.ORDER_STATUS_03.equals(order_status)){
					order_num_03=order_sum;
				}else if(OrderConstantValues.ORDER_STATUS_04.equals(order_status)){
					order_num_04=order_sum;
				}else if(OrderConstantValues.ORDER_STATUS_05.equals(order_status)){
					order_num_05=order_sum;
				}else if(OrderConstantValues.ORDER_STATUS_06.equals(order_status)){
					order_num_06=order_sum;
				}
			}
		}
		map.put("order_status_01",order_num_01);
		map.put("order_status_02",order_num_02);
		map.put("order_status_03",order_num_03);
		map.put("order_status_04",order_num_04);
		map.put("order_status_05",order_num_05);
		map.put("order_status_06",order_num_06);
		
		int countDelayOrder = orderDelayPayService.getBuyerOrderInfoLsCount(login_id);
		map.put("countDelayOrder", countDelayOrder);

	}
	*//**
	 * @Description:动态获取当前买家左侧菜单个状态订单数量
	 * @param		参数说明
	 * @return		返回值
	 * @author      薛松坛
	 * @date 2013-3-25 下午9:33:13 
	 * @version V1.0
	 *//*
	private void  querySellerOrderumByLefter(HttpServletRequest request,ModelMap map){
		Map<String,String> parameterMap = new HashMap<String, String>();
		String login_id = StringHelperTools.nvl(request.getSession().getAttribute("userId"));
		parameterMap.put("login_id", login_id);
		List<OrderSumVo> ls = orderManagerSellerService.getSellerOrderSumMap(parameterMap);
		BigDecimal order_num_01 = BigDecimal.ZERO;
		BigDecimal order_num_02 = BigDecimal.ZERO;
		BigDecimal order_num_03 = BigDecimal.ZERO;
		BigDecimal order_num_04 = BigDecimal.ZERO;
		if(ls!=null&&ls.size()>0){
			for (int i = 0; i < ls.size(); i++) {
				String order_status = StringHelperTools.nvl(ls.get(i).getOrder_status());
				String ship_status=StringHelperTools.nvl(ls.get(i).getShip_status());
				BigDecimal order_sum = ls.get(i).getOrder_sum();
				if(OrderConstantValues.ORDER_STATUS_01.equals(order_status)){
					order_num_01=order_num_01.add(order_sum);
				}else if(OrderConstantValues.SHIP_STATUS_01.equals(ship_status)){
					order_num_02=order_num_02.add(order_sum);
				}else if(OrderConstantValues.SHIP_STATUS_02.equals(ship_status)){
					order_num_03=order_num_03.add(order_sum);
				}else if(OrderConstantValues.ORDER_STATUS_02.equals(order_status)&&OrderConstantValues.SHIP_STATUS_03.equals(ship_status)){
					order_num_04=order_num_04.add(order_sum);
				}
			}
		}
		map.put("order_status_01",order_num_01);
		map.put("order_status_02",order_num_02);
		map.put("order_status_03",order_num_03);
		map.put("order_status_04",order_num_04);
		
		int countDelayOrder = orderDelayPayService.getPayDelayOrdersCount(login_id);
		map.put("countDelayOrder", countDelayOrder);
		
	}
	*//**
	 * @Description:动态获取当前买家未付款、待发货、已发货对应总订单数
	 * @param		参数说明
	 * @return		返回值
	 * @author      薛松坛
	 * @date 2013-3-25 下午9:33:13 
	 * @version V1.0
	 *//*
	private int queryBuyerOrderumByHeader(HttpServletRequest request,ModelMap map){
		int buyer_order_num = 0;
		int novice_num = 0;
		
		String login_id = StringHelperTools.nvl(request.getSession().getAttribute("userId"));
		
		if(!"".equals(login_id)){
			Map<String,String> parameterMap = new HashMap<String, String>();
			parameterMap.put("login_id", login_id);
			List<OrderSumVo> ls = orderManagerBuyerService.getBuyerOrderSumMap(parameterMap);
			if(ls!=null&&ls.size()>0){
				for (int i = 0; i < ls.size(); i++) {
					String order_status = StringHelperTools.nvl(ls.get(i).getOrder_status());
					if(ls.get(i).getOrder_sum()!=null){
						int order_sum = ls.get(i).getOrder_sum().intValue();
						if(OrderConstantValues.ORDER_STATUS_01.equals(order_status)||OrderConstantValues.ORDER_STATUS_02.equals(order_status)||OrderConstantValues.ORDER_STATUS_03.equals(order_status)){
							buyer_order_num = buyer_order_num+order_sum;
						}
						此临时代码
						if(OrderConstantValues.ORDER_STATUS_01.equals(order_status)||OrderConstantValues.ORDER_STATUS_02.equals(order_status)||OrderConstantValues.ORDER_STATUS_03.equals(order_status)||OrderConstantValues.ORDER_STATUS_04.equals(order_status)||OrderConstantValues.ORDER_STATUS_05.equals(order_status)||OrderConstantValues.ORDER_STATUS_06.equals(order_status)){
							novice_num = novice_num + order_sum;
						}
						
					}
				}
			}
		}
		
		map.put("buyer_order_num",String.valueOf(buyer_order_num));
		return novice_num;
	}
	*//**
	 * @Description:动态获取当前买家未付款、待发货、已发货对应总订单数
	 * @param		参数说明
	 * @return		返回值
	 * @author      薛松坛
	 * @date 2013-3-25 下午9:33:13 
	 * @version V1.0
	 *//*
	private void  querySellerOrderumByHeader(HttpServletRequest request,ModelMap map) {
		int seller_order_num = 0;
		String login_id = StringHelperTools.nvl(request.getSession().getAttribute("userId"));
		if(!"".equals(login_id)){
			Map<String,String> parameterMap = new HashMap<String, String>();
			parameterMap.put("login_id", login_id);
			List<OrderSumVo> ls = orderManagerSellerService.getSellerOrderSumMap(parameterMap);
			if(ls!=null&&ls.size()>0){
				for (int i = 0; i < ls.size(); i++) {
					String order_status = StringHelperTools.nvl(ls.get(i).getOrder_status());
					if(ls.get(i).getOrder_sum()!=null){
						int order_sum = ls.get(i).getOrder_sum().intValue();
						if(OrderConstantValues.ORDER_STATUS_02.equals(order_status)||OrderConstantValues.ORDER_STATUS_03.equals(order_status)){
							seller_order_num = seller_order_num + order_sum;
						}
					}
				}
			}
		}
		map.put("seller_order_num",String.valueOf(seller_order_num));
	}

	*//**
	 * 客户属性对应
	 * @param attr
	 *//*
	private String setCustomerAttr(String attr){
		if("1".equals(attr)){
			attr = String.valueOf(OperatorTypeEnum.MoveType.value());
		}else if("2".equals(attr)){
			attr = String.valueOf(OperatorTypeEnum.LinkType.value());
		}else if("3".equals(attr)){
			attr = String.valueOf(OperatorTypeEnum.TelecomType.value());
		}else{
			attr = String.valueOf(OperatorTypeEnum.CommonLogo.value());
		}
		return attr;
	}

	*//**
	 * 查询logo
	 * @return
	 *//*
	private LogoVo queryLogo(String type,String attr,BigDecimal areaId){
		ConditionVo cVo = new ConditionVo();
		if(type != null && !"".equals(type)){
			cVo.setLogoType(type);	
		}
		if(attr != null && !"".equals(attr)){
			cVo.setOperator(setCustomerAttr(attr));
		}
		if(areaId != null){
			cVo.setProvince(StringHelperTools.nvl(areaId));
		}
		LogoVo logoVo= logoManageService.queryLogoForDisplay(cVo);
		return logoVo;
	}
	
	private boolean isSubDomain(HttpServletRequest request, String subDomain) {
		String addr = request.getServerName();
		logger.debug("当前访问域名为:"+addr);
		String subDoaminFull = subDomain + ".telling.cn";
		return subDoaminFull.equalsIgnoreCase(addr);
	}
}*/