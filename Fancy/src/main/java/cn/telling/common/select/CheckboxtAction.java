package cn.telling.common.select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.telling.areas.service.IAreaInfoService;
import cn.telling.areas.vo.AreaInfoVo;
import cn.telling.common.enums.users.UsersCategory;
import cn.telling.utils.StringHelperTools;
@Controller
public class CheckboxtAction {


	@Resource
	private IAreaInfoService areaInfoService;

	/**
	 * 根据请求获得登录人的省级管辖区域，以及showLev层下拉菜单 showLev=1 省；2市；3区县；4乡镇；5最后一级，如有更多，再扩展
	 * 
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getcheckboxselect", method = { RequestMethod.GET,RequestMethod.POST })
	public String getmanagerareas(ModelMap map, HttpSession session,HttpServletRequest request) {
		Map<String,String> proMap=new HashMap<String,String>();
		String showLev = request.getParameter("showLev");
		String showName=request.getParameter("showName");
		String classId = request.getParameter("classId");
		String province_ids=request.getParameter("province_id");
		String county_ids=request.getParameter("county_id");
		if(StringUtils.isBlank(classId)){
			classId="maselect";
		}
		String userId =  session.getAttribute("userId")+"";
		List<AreaInfoVo> lst = new ArrayList<AreaInfoVo>();
		//判断登陆员工是否是管理员
		boolean isAdmin=UsersCategory.Administrator.equals(UsersCategory.valueOf(Integer.parseInt(session.getAttribute("categoryId")+"")));
		lst = this.areaInfoService.queryManageProvinceAreaByUserId(userId+"@"+isAdmin);
		String provinceSelectHtml="<p id=\"provinces\" style=\"float:left;font-size:14px;margin-top: 10px;\">"
				+"省：<select  name=\"province\" id=\"province\" multiple=\"multiple\" style=\"background:#FFF ;display:none; width:200px\">";//组装下拉框
		if(null!=showName&&!showName.equals("")){
		 provinceSelectHtml="<p id=\"provinces\" style=\"float:left;font-size:14px;margin-top: 10px;\">"
					+""+showName+"：<select  name=\"province\" id=\"province\" multiple=\"multiple\" style=\"background:#FFF ; display:none; width:200px\">";//组装下拉框
		}
		String citySelectHtml="";//组装下拉框
		String countySelectHtml="";//组装下拉框
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
		}
		
		if(!"".equals(province_ids)&&null!=province_ids){
			String[] province_id= province_ids.split(",");
			if(province_id.length>0){
				for(int i=0;i<province_id.length;i++){
					proMap.put(province_id[i], province_id[i]);
				}
			}
		}
		
		if(!"".equals(county_ids)&&null!=county_ids){
			String[] countyId= county_ids.split(",");
			if(countyId.length>0){
				for(int i=0;i<countyId.length;i++){
					proMap.put(countyId[i], countyId[i]);
				}
			}
		}
		
		iShowLev=Integer.parseInt(showLev)+1;//页面显示到几级菜单,默认显示到省，省的level是2，因此在此需要加一
		for(AreaInfoVo areaInfoVo:lst){
			int level=areaInfoVo.getAreaLevel().intValue();
			if(level==2){//显示省
				String areaId=areaInfoVo.getAreaId().toString();
				if(proMap.get(areaId)!=null&&proMap.get(areaId).equals(areaId)){
					provinceSelectHtml+="<option value=\""+areaInfoVo.getAreaId()+"\" selected=\"selected\">";
					provinceSelectHtml+=areaInfoVo.getAreaName();
					provinceSelectHtml+="</option>";
				}else{
					provinceSelectHtml+="<option value=\""+areaInfoVo.getAreaId()+"\">";
					provinceSelectHtml+=areaInfoVo.getAreaName();
					provinceSelectHtml+="</option>";
				}
			}
			if(level==3&&level<=iShowLev){//市
				citySelectHtml="<select name=\"city\" multiple=\"multiple\" style=\"width:225px\" id=\"city\">";//组装下拉框
			}
			if(level==4&&level<=iShowLev){//县
				countySelectHtml="<select name=\"county\" multiple=\"multiple\" style=\"width:225px\" id=\"county\">";//组装下拉框
			}
			else if(level==3&&null==showLev){// 当页面传入的参数不包括showLev时
				citySelectHtml="<select name=\"city\" multiple=\"multiple\" style=\"width:225px\" id=\"city\">";//组装下拉框
			}
		}
		String selectHtml=provinceSelectHtml+"</select></p>";
		if(!"".equals(citySelectHtml)){
			if(proMap.size()==1){
				selectHtml+="<p id=\"cities\" style=\"display:block;float:left;margin-left:50px;font-size:14px;margin-top: 10px;\">"+"市："+citySelectHtml+"</select></p>";
			}else{
				selectHtml+="<p id=\"cities\" style=\"display:none;float:left;margin-left:50px;font-size:14px;margin-top: 10px;\">"+"市："+citySelectHtml+"</select></p>";
			}
		}
		if(!StringHelperTools.nvl(countySelectHtml).equals("")){
			if(proMap.size()==1){
				selectHtml+="<p id=\"counties\" style=\"display:block;float:left;margin-left:80px;font-size:14px;margin-top: 10px;\">"+"县："+countySelectHtml+"</select></p>";
			}else{
				selectHtml+="<p id=\"counties\" style=\"display:none;float:left;margin-left:80px;font-size:14px;margin-top: 10px;\">"+"县："+countySelectHtml+"</select></p>";
			}
		}
		map.put("selectHtml", selectHtml);
		return "Controls/checkBoxSelect";
	}

	public IAreaInfoService getAreaInfoService() {
		return areaInfoService;
	}

	public void setAreaInfoService(IAreaInfoService areaInfoService) {
		this.areaInfoService = areaInfoService;
	}



}
