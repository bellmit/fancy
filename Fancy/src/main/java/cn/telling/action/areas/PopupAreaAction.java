package cn.telling.action.areas;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.telling.areas.service.IAreaInfoService;
import cn.telling.areas.vo.AreaConditionVo;
import cn.telling.areas.vo.AreaInfoVo;

@Controller
@RequestMapping("AreaPop")
public class PopupAreaAction {

	@Resource
	private IAreaInfoService areaService;


	@RequestMapping("areaListAjax")
	public @ResponseBody List<AreaInfoVo> SearchArea(ModelMap map,
			@RequestParam(value="areaname",required=false) String areaName,
			@RequestParam(value="areacode",required=false) String areaCode)
	{
		AreaConditionVo condition = new AreaConditionVo();
		condition.setAreaName(areaName);
		condition.setAreaCode(areaCode);
		List<AreaInfoVo> list = areaService.GetByConditionFuzzy(condition);
		
		return list;
	}
}
