package cn.telling.common.select;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.telling.areas.service.IAreaInfoService;
import cn.telling.areas.vo.AreaInfoVo;
import cn.telling.common.enums.users.UsersCategory;
import cn.telling.utils.StringHelperTools;

import com.danga.MemCached.MemCachedClient;

/**
 * to be jsp:include by other page only thing needed is PageVo object within
 */
@Controller
public class SelectAction {

	@Resource
	private IAreaInfoService areaInfoService;

	@Resource
	private MemCachedClient memClient;
	/**
	 * 根据请求获得登录人的省级管辖区域，以及showLev层下拉菜单 showLev=1 省；2市；3区县；4乡镇；5最后一级，如有更多，再扩展
	 * 
	 * @param map
	 * @param request
	 * @return
	 */

	@RequestMapping(value = "/getmanagerareas", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getmanagerareas(ModelMap map, HttpSession session,HttpServletRequest request) {
		String showLev = request.getParameter("showLev");
		String classId = request.getParameter("classId");
		String from = request.getParameter("from");
		if(StringUtils.isBlank(classId)){
			classId="maselect";
		}
		String userId =  session.getAttribute("userId")+"";
		if("null".equals(userId)){
			userId="";
		}
		List<AreaInfoVo> lst = new ArrayList<AreaInfoVo>();
		//判断登陆员工是否是管理员
		String category=session.getAttribute("categoryId")+"";
		if(StringUtils.equals("home", from)){//用户前台客户注册时使用
			category="1";
		}
		boolean isAdmin=UsersCategory.Administrator.equals(UsersCategory.valueOf(Integer.parseInt(category+"")));
		lst = this.areaInfoService.queryManageProvinceAreaByUserId(userId+"@"+isAdmin);
//		if(isAdmin&&memClient.get("proviceAreaCache")!= null){//从缓存里取区域信息
//			lst=(List<AreaInfoVo>)memClient.get("proviceAreaCache");
//			if(lst==null||lst.size()<1){
//				lst = this.areaInfoService.queryManageProvinceAreaByUserId(userId+"@"+isAdmin);
//				memClient.set("proviceAreaCache", lst);//把区域信息放到缓存里,有效期是24小时
//			}
//		}else{
//			lst = this.areaInfoService.queryManageProvinceAreaByUserId(userId+"@"+isAdmin);
//			if(isAdmin){
//				memClient.set("proviceAreaCache", lst);//把区域信息放到缓存里,有效期是24小时
//			}
//		}
		String provinceSelectHtml="<select name=\"provinceId\" id=\"provinceId\" class=\""+classId+"\"><option value=\"\">---全部---</option>";//组装下拉框
		String citySelectHtml="";//组装下拉框
		String districtSelectHtml="";//组装下拉框
		String townSelectHtml="";//组装下拉框
		String villageSelectHtml="";//组装下拉框
		int iShowLev=2;//页面显示到几级菜单
		if(null!=showLev){//页面需要显示的菜单级别不为空时,
			if(!"".equals(StringHelperTools.nvl(showLev))){//输入值非空
				if(StringHelperTools.isNum(showLev)){//输入值为数字
					if(showLev.length()>1){//当输入参数过长时，自动截取第一位
						showLev=showLev.substring(0,1);
					}
					if(Integer.parseInt(showLev)<1){//过滤非法参数值，默认到省
						showLev="1";
					}
				}else{
					showLev="1";
				}
			}else{//输入值为空
				showLev="1";
			}
		}else{//当showLev为null时，显示该用户所有的管辖区域信息
			
		}
		iShowLev=Integer.parseInt(showLev)+1;//页面显示到几级菜单,默认显示到省，省的level是2，因此在此需要加一
		for(AreaInfoVo areaInfoVo:lst){
			int level=areaInfoVo.getAreaLevel().intValue();
			if(level==2){//显示省
				provinceSelectHtml+="<option value=\""+areaInfoVo.getAreaId()+"\">";
				provinceSelectHtml+=areaInfoVo.getAreaName();
				provinceSelectHtml+="</option>";
			}
			if(level==3&&level<=iShowLev){//市
				citySelectHtml="<select name=\"cityId\" id=\"cityId\" class=\""+classId+"\"><option value=''>---全部---</option>";//组装下拉框
			}else if(level==3&&null==showLev){// 当页面传入的参数不包括showLev时
				citySelectHtml="<select name=\"cityId\" id=\"cityId\" class=\""+classId+"\"><option value=''>---全部---</option>";//组装下拉框
			}
			if(level==4&&level<=iShowLev){//区/县
				districtSelectHtml="<select name=\"districtId\" id=\"districtId\" class=\""+classId+"\"><option value=''>---全部---</option>";//组装下拉框
			}else if(level==4&&null==showLev){// 当页面传入的参数不包括showLev时
				districtSelectHtml="<select name=\"districtId\" id=\"districtId\" class=\""+classId+"\"><option value=''>---全部---</option>";//组装下拉框
			}
			if(level==5&&level<=iShowLev){//乡镇
				townSelectHtml="<select name=\"townId\" id=\"townId\" class=\""+classId+"\"><option value=''>---全部---</option>";//组装下拉框
			}else if(level==5&&null==showLev){// 当页面传入的参数不包括showLev时
				townSelectHtml="<select name=\"townId\" id=\"townId\" class=\""+classId+"\"><option value=''>---全部---</option>";//组装下拉框
			}
			if(level==6&&level<=iShowLev){//下一级乡镇
				villageSelectHtml="<select name=\"villageId\" id=\"villageId\" class=\""+classId+"\"><option value=''>---全部---</option>";//组装下拉框
			}else if(level==6&&null==showLev){// 当页面传入的参数不包括showLev时
				villageSelectHtml="<select name=\"villageId\" id=\"villageId\" class=\""+classId+"\"><option value=''>---全部---</option>";//组装下拉框
			}
		}
		String selectHtml=provinceSelectHtml+"</select>";
		if(!"".equals(citySelectHtml)){
			selectHtml+=citySelectHtml+"</select>";
		}
		if(!"".equals(districtSelectHtml)){
			selectHtml+=districtSelectHtml+"</select>";
		}
		if(!"".equals(townSelectHtml)){
			selectHtml+=townSelectHtml+"</select>";
		}
		if(!"".equals(villageSelectHtml)){
			selectHtml+=villageSelectHtml+"</select>";
		}
		map.put("selectHtml", selectHtml);
		return "Controls/select";
	}

	/**
	 * 查询下级区域 根据上级areaId
	 * 
	 * @param req
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/QueryAreaById", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	List<AreaInfoVo> QueryAreaByAreaId(HttpSession session,
			HttpServletRequest req) throws SQLException {
		String areaId = req.getParameter("areaId");
		String userId = session.getAttribute("userId") + "";
		String from = req.getParameter("from");
		if("null".equals(userId)){
			userId="";
		}
		String category=session.getAttribute("categoryId")+"";
		if("null".equals(category)){//用户前台客户注册时使用
			category="1";
		}
		if(StringUtils.equals("home", from)){//用户前台客户注册时使用
			category="1";
		}
		//判断登陆员工是否是管理员
		boolean isAdmin=UsersCategory.Administrator.equals(UsersCategory.valueOf(Integer.parseInt(category)));
		List<AreaInfoVo> lst = new ArrayList<AreaInfoVo>();
		if (!"".equals(StringHelperTools.nvl(areaId))) {
			lst = this.areaInfoService.queryManageAreaByUserIdAndAreaid(userId+"@"+isAdmin,
							areaId);
//			if(isAdmin&&memClient.get(areaId+"AreaCache")!= null){//从缓存里取区域信息
//				lst=(List<AreaInfoVo>)memClient.get(areaId+"AreaCache");
//				if(lst==null||lst.size()<1){
//					lst = this.areaInfoService.queryManageAreaByUserIdAndAreaid(userId+"@"+isAdmin,
//							areaId);
//					memClient.set("AreaCache", lst);//把区域信息放到缓存里,有效期是24小时
//				}
//			}else{
//				lst = this.areaInfoService.queryManageAreaByUserIdAndAreaid(userId+"@"+isAdmin,
//						areaId);
//				if(isAdmin){
//					memClient.set(areaId+"AreaCache", lst);//有效期24小时
//				}
//			}
		}
		return lst;
	}

	public IAreaInfoService getAreaInfoService() {
		return areaInfoService;
	}

	public void setAreaInfoService(IAreaInfoService areaInfoService) {
		this.areaInfoService = areaInfoService;
	}

}
