package cn.telling.action.areas;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.telling.areas.service.IAreaInfoService;
import cn.telling.areas.vo.AreaConditionVo;
import cn.telling.areas.vo.AreaInfoVo;
import cn.telling.common.Enums.Status;
import cn.telling.common.Pager.PageVo;
import cn.telling.common.Pager.Pager;

@Controller
@RequestMapping(value="AreaManage")
public class AreaManageAction {

	@Resource
	private IAreaInfoService areaService;

	public IAreaInfoService getAreaService() {
		return areaService;
	}

	public void setAreaService(IAreaInfoService areaService) {
		this.areaService = areaService;
	}
	
	/**
	 * 新增
	 * @param parentId
	 * @param map
	 * @return
	 */
	@RequestMapping(value="New/{parentId}.html",method={RequestMethod.GET,RequestMethod.POST})
	public String AddNew(@PathVariable(value="parentId") String parentId,ModelMap map)
	{
		if (parentId.equals(""))
		{
			return "Areas/AreaList";
		}
		map.put("parentId", parentId);
		map.put("status", Status.values());
		return "Areas/AreaInfoEdit";
		
	}
	
	/**
	 * 修改
	 * @param areaId
	 * @param map
	 * @return
	 */
	@RequestMapping(value="Edit/{areaId}.html",method={RequestMethod.GET,RequestMethod.POST})
	public String Edit(@PathVariable(value="areaId") BigDecimal areaId,ModelMap map)
	{
		AreaInfoVo area = areaService.GetById(areaId);
		map.put("area", area);
		map.put("status", Status.values());
		map.put("parentId", area.getParentId());
		return "Areas/AreaInfoEdit";
	}
	/**
	 * 保存
	 * @param area
	 * @param map
	 * @return
	 */
	@RequestMapping(value="Save.html",method={RequestMethod.GET,RequestMethod.POST})
	public String Save(AreaInfoVo area,ModelMap map)
	{
		if (area.getParentId() == null) area.setParentId(new BigDecimal(0));
		//area.setEnumStatus(Status.valid.value());
		area.setEnumStatus(Integer.parseInt(area.getStatus()));
		int effRows = areaService.Save(area);
		map.put("area",area);
		if (effRows > 0){
			map.put("tips", "保存成功!");
			map.put("parentId", area.getParentId());
		}
		else
		{
			map.put("tips", "保存失败!区域名称或编码已经存在！");
		}
		
		return "Areas/AreaInfoEdit";
		//return "redirect:List/"+area.getParentId()+"_1.html";
	}
	
	@RequestMapping(value="/Del/{areaId}.html",method={RequestMethod.GET,RequestMethod.POST})
	public String DelOrg(@PathVariable(value="areaId") BigDecimal areaId,ModelMap map,HttpServletRequest request)
	{
		areaService.DeleteById(areaId);
		String parentId = request.getParameter("parentId");
		return "redirect:/AreaManage/List/"+ parentId +"_1.html";
	}
	
	@RequestMapping(value="/List/{parentId}_{page}.html",method={RequestMethod.GET,RequestMethod.POST})
	public String ListByParentId(@PathVariable(value="parentId") BigDecimal parentId,
			@PathVariable(value="page") String page,ModelMap map,HttpServletRequest request)
	{
		AreaConditionVo condition = new AreaConditionVo();
		condition.setParentId(parentId);
		
		String url = String.format(request.getContextPath()+ "/AreaManage/List/%1$s_",parentId);
		int totalRecordCount = areaService.GetTotalCount(condition);
		PageVo pageVo = Pager.getPager();
		pageVo.setPageNow(Integer.parseInt(page));
		pageVo.setTotalCount(totalRecordCount);
		pageVo.setMessageForPage(10);
		pageVo.setUrl(url);
		
		map = Pager.setPageerToPage(map, pageVo);
		
		List<AreaInfoVo> areas = areaService.GetByPage(condition, pageVo);
		AreaInfoVo parArea = areaService.GetById(parentId);
		if (parArea != null)
		{
			map.put("parentId2", parArea.getParentId());
		}
		map.put("areas", areas);
		map.put("parentId",parentId);
		
		return "Areas/AreaList";
	}
}
