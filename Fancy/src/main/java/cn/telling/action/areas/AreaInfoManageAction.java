package cn.telling.action.areas;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.telling.areas.service.IAreaInfoManageService;
import cn.telling.areas.vo.AreaInfoManageVo;
import cn.telling.areas.vo.AreaInfoVo;
import cn.telling.utils.StringHelperTools;

@Controller
public class AreaInfoManageAction {
	/**
	 * 调用区域管理接口
	 */
	@Resource
	private IAreaInfoManageService areaInfoManageService;
	
	/**
	 * @Description: 区域列表页面
	 * @return 返回值
	 * @author wukailun
	 * @date 2014-5-23上午8:57:21
	 * @version V1.0
	 */
	@RequestMapping(value = "/1index.html", method = { RequestMethod.GET, RequestMethod.POST })
	public String index(ModelMap map, HttpServletRequest req) {
		// 取得所有的地区
		List<AreaInfoManageVo> allList= areaInfoManageService.queryAllAreaInfo();
		// 返回类型json的数组
		String tree = areaInfoManageService.getAllTree(allList, null);
		map.put("ztree", tree);
		map.put("isSuccess", req.getParameter("isSuccess"));
		return "areamanage/areainfomanageList";
	}
	
	/**
	 * @Description: 区域列表新增页面
	 * @return 返回值
	 * @author wukailun
	 * @date 2014-5-23上午8:57:21
	 * @version V1.0
	 */
	@RequestMapping(value = "/AreaInfoInsert.html", method = { RequestMethod.GET, RequestMethod.POST })
	public String AreaInfoInsert(ModelMap map, HttpServletRequest req) {
		map.put("areaId", StringHelperTools.nvl(req.getParameter("areaid")));
		AreaInfoManageVo vo = areaInfoManageService.queryAreaInfoByAreaId(new BigDecimal(req.getParameter("areaid")));
		map.put("areaNameInfo", vo.getAreaName());
//		String provinceId =  areaInfoManageService.getParentByAreaId(StringHelperTools.nvl(req.getParameter("areaid")));
//		map.put("cityId", StringHelperTools.nvl(req.getParameter("areaid")));
//		map.put("provinceId", provinceId);
		return "areamanage/areainfomanageadd";
	}
	
	/**
	 * @Description: 区域列表新增页面保存
	 * @return 返回值
	 * @author wukailun
	 * @date 2014-5-23上午8:57:21
	 * @version V1.0
	 */
	@RequestMapping(value = "/AreaInfoInsertSave.html", method = { RequestMethod.GET, RequestMethod.POST })
	public String AreaInfoInsertSave(ModelMap map, HttpServletRequest req) {
		String parentId = StringHelperTools.nvl(req.getParameter("areaId"));
		String areaName = StringHelperTools.nvl(req.getParameter("areaName"));
		String areaCode = StringHelperTools.nvl(req.getParameter("areaCode"));
		String NC_PK_AREACL = StringHelperTools.nvl(req.getParameter("NC_PK_AREACL"));
		String NC_PK_CORP = StringHelperTools.nvl(req.getParameter("NC_PK_CORP"));
		String NC_DEF1 = StringHelperTools.nvl(req.getParameter("NC_DEF1"));
		String NC_PK_FATHERAREA = StringHelperTools.nvl(req.getParameter("NC_PK_FATHERAREA"));
		AreaInfoManageVo vo = new AreaInfoManageVo();
		vo.setParentId(new BigDecimal(parentId));
		vo.setAreaName(areaName);
		vo.setAreaCode(areaCode);
		vo.setNC_PK_AREACL(NC_PK_AREACL);
		vo.setNC_PK_CORP(NC_PK_CORP);
		vo.setNC_DEF1(NC_DEF1);
		vo.setNC_PK_FATHERAREA(NC_PK_FATHERAREA);
		boolean flag = areaInfoManageService.insertAreaInfo(vo);
		if (flag) {
			map.put("isSuccess", "insertsuccess");
		} else {
			map.put("isSuccess", "inserterror");
		}
		return "redirect:/AreaInfoManage/index.html";
	}
	
	/**
	 * @Description: 区域列表修改页面
	 * @return 返回值
	 * @author wukailun
	 * @date 2014-5-23上午8:57:21
	 * @version V1.0
	 */
	@RequestMapping(value = "/AreaInfoUpdate.html", method = { RequestMethod.GET, RequestMethod.POST })
	public String AreaInfoUpdate(ModelMap map, HttpServletRequest req) {
		String areaId = StringHelperTools.nvl(req.getParameter("areaid"));
		AreaInfoManageVo vo = areaInfoManageService.queryAreaInfoByAreaId(new BigDecimal(areaId));
		map.put("areaId", areaId);map.put("areaLevel", vo.getAreaLevel());
		map.put("areaName", vo.getAreaName());map.put("areaCode", vo.getAreaCode());
		map.put("NC_PK_AREACL", vo.getNC_PK_AREACL());map.put("NC_PK_CORP", vo.getNC_PK_CORP());
		map.put("NC_DEF1", vo.getNC_DEF1());map.put("NC_PK_FATHERAREA", vo.getNC_PK_FATHERAREA());
		
		if("4".equals(vo.getAreaLevel().toString())){
			// 县区级别
			String cityId =  areaInfoManageService.getParentByAreaId(areaId);
			String provinceId =  areaInfoManageService.getParentByAreaId(cityId);
			map.put("cityId", cityId);map.put("provinceId", provinceId);
		}else if("5".equals(vo.getAreaLevel().toString())){
			// 乡镇级别
			String areaId1 =  areaInfoManageService.getParentByAreaId(areaId);
			String cityId1 =  areaInfoManageService.getParentByAreaId(areaId1);
			String provinceId1 =  areaInfoManageService.getParentByAreaId(cityId1);
			map.put("areaId1", areaId1);
			map.put("cityId1", cityId1);
			map.put("provinceId1", provinceId1);
		}
		return "areamanage/areainfomanageedit";
	}
	
	/**
	 * @Description: 区域列表修改页面保存
	 * @return 返回值
	 * @author wukailun
	 * @date 2014-5-23上午8:57:21
	 * @version V1.0
	 */
	@RequestMapping(value = "/AreaInfoUpdateSave.html", method = { RequestMethod.GET, RequestMethod.POST })
	public String AreaInfoUpdateSave(ModelMap map, HttpServletRequest req) {
		String areaName = StringHelperTools.nvl(req.getParameter("areaName"));
		String areaCode = StringHelperTools.nvl(req.getParameter("areaCode"));
		String NC_PK_AREACL = StringHelperTools.nvl(req.getParameter("NC_PK_AREACL"));
		String NC_PK_CORP = StringHelperTools.nvl(req.getParameter("NC_PK_CORP"));
		String NC_DEF1 = StringHelperTools.nvl(req.getParameter("NC_DEF1"));
		String NC_PK_FATHERAREA = StringHelperTools.nvl(req.getParameter("NC_PK_FATHERAREA"));
		AreaInfoManageVo vo = new AreaInfoManageVo();
		String areaLevel = StringHelperTools.nvl(req.getParameter("areaLevel"));
		if("4".equals(areaLevel)){
			String areaId = StringHelperTools.nvl(req.getParameter("areaId"));
			String parentId = StringHelperTools.nvl(req.getParameter("cityId"));
			vo.setAreaId(new BigDecimal(areaId));
			vo.setParentId(new BigDecimal(parentId));
		}else if("5".equals(areaLevel)){
			String areaId = StringHelperTools.nvl(req.getParameter("areaId"));
			String parentId = StringHelperTools.nvl(req.getParameter("areaId1"));
			vo.setAreaId(new BigDecimal(areaId));
			vo.setParentId(new BigDecimal(parentId));
		}
		vo.setAreaName(areaName);
		vo.setAreaCode(areaCode);
		vo.setNC_PK_AREACL(NC_PK_AREACL);
		vo.setNC_PK_CORP(NC_PK_CORP);
		vo.setNC_DEF1(NC_DEF1);
		vo.setNC_PK_FATHERAREA(NC_PK_FATHERAREA);
		boolean flag = areaInfoManageService.updateAreaInfo(vo);
		if (flag) {
			map.put("isSuccess", "updatesuccess");
		} else {
			map.put("isSuccess", "updateerror");
		}
		return "redirect:/AreaInfoManage/index.html";
	}
	
	/**
	 * @Description: 通过areaid来判断这个是不是子节点
	 * @return 返回值
	 * @author wukailun
	 * @date 2014-5-23上午8:57:21
	 * @version V1.0
	 */
	@RequestMapping(value = "/AreaInfoChild.html", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String AreaInfoChild(ModelMap map, HttpServletRequest req) {
		String areaId = StringHelperTools.nvl(req.getParameter("areaId"));
		// true : 说明有子节点
		boolean flag = areaInfoManageService.getAreaInfoChild(areaId);
		if (flag) {
			// 有子节点时，返回err，不让其他进行删除操作
			return "err";
		} else {
			return "success";
		}
	}
	
	/**
	 * @Description: 通过areaid来删除区域
	 * @return 返回值
	 * @author wukailun
	 * @date 2014-5-23上午8:57:21
	 * @version V1.0
	 */
	@RequestMapping(value = "/AreaInfoDelete.html", method = { RequestMethod.GET, RequestMethod.POST })
	public String AreaInfoDelete(ModelMap map, HttpServletRequest req) {
		String areaid = StringHelperTools.nvl(req.getParameter("areaid"));
		// 删除区域，物理删除
		boolean flag = areaInfoManageService.deleteAreaInfo(new BigDecimal(areaid));
		if (flag) {
			map.put("isSuccess", "deletesuccess");
		} else {
			map.put("isSuccess", "deleteerror");
		}
		return "redirect:/AreaInfoManage/index.html";
	}
	
	/**
	 * @Description: 省级
	 * @author wukailun
	 * @date 2014-7-27 上午9:12:51
	 * @version V1.0
	 */
	@RequestMapping(value = "/QueryProvince", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	List<AreaInfoVo> QueryProvince() throws SQLException {
		return areaInfoManageService.getAreaInfoByLevel(new BigDecimal(2));
	}
	
	/**
	 * @Description: 市级
	 * @author wukailun
	 * @date 2013-7-27 上午9:12:51
	 * @version V1.0
	 */
	@RequestMapping(value = "/QueryCity", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	List<AreaInfoVo> QueryCity(HttpServletRequest req) throws SQLException {
		String provinceId = req.getParameter("provinceId");
		return areaInfoManageService.getAreaInfoByAreaId(provinceId);
	}
}
