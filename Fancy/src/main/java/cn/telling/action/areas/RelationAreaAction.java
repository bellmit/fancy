package cn.telling.action.areas;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.telling.areas.service.IAreaInfoService;

@Controller
@RequestMapping(value="AreaRelation")
public class RelationAreaAction {
	@Resource
	private IAreaInfoService areaService;

	public IAreaInfoService getAreaService() {
		return areaService;
	}
	public void setAreaService(IAreaInfoService areaService) {
		this.areaService = areaService;
	}
	
	@RequestMapping(value="org2areaAjax")
	public @ResponseBody String RelaOrg2Area(
			@RequestParam(value="orgId",required=true) String orgId,
			@RequestParam(value="areaId",required=true) String areaId)
	{
		int effRows = areaService.RelationOrg2Area(orgId,areaId);
		return String.valueOf(effRows);
	}
	
}
