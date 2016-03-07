package cn.telling.action.dictionary;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.telling.common.Pager.PageVo;
import cn.telling.common.Pager.Pager;
import cn.telling.dictionary.service.DictionaryService;
import cn.telling.dictionary.vo.DictionaryConditionVo;
import cn.telling.dictionary.vo.DictionaryTypeVo;
import cn.telling.dictionary.vo.DictionaryVo;
import cn.telling.dictionary.vo.SelectVo;
import cn.telling.utils.StringHelperTools;

import com.danga.MemCached.MemCachedClient;

/**
 * 数据字典
 * @author Dylan Liu
 *
 */
@Controller
@RequestMapping("/dictionary")
public class DictionaryAction {
	@Resource
	private DictionaryService dictionaryService;
	

	// 缓存对象
	@Resource
	private MemCachedClient memClient;
	
	@RequestMapping(value="/type/{id}",method={RequestMethod.GET,RequestMethod.POST})
	public String getDictionaryTypeListByPage(@PathVariable(value="id") String id,HttpServletRequest request,ModelMap map){
		if("List".equals(id))
		{
			return "/dictionary/DictionaryTypeList";
		}else if ("Query".equals(id)){
			String type=StringHelperTools.nvl(request.getParameter("type"));
			int totalCount=dictionaryService.getDictionaryTypeTotalCountForPage(type);
			PageVo pageVo=Pager.getPager();
			pageVo.setTotalCount(totalCount);
			String pageNow=StringHelperTools.nvl(request.getParameter("page"));
			if("".equals(pageNow)){
				pageVo.setPageNow(1);
			}else{
				pageVo.setPageNow(Integer.parseInt(pageNow));
			}
			pageVo.setMessageForPage(10);
			pageVo.setUrl(request.getContextPath()+"/dictionary/Page/Query.html");
			List<DictionaryTypeVo> list=dictionaryService.getDictionaryTypeByPage(pageVo, type);
			map=Pager.setPageerToPage(map, pageVo);
			map.put("list", list);
			return "/dictionary/DictionaryTypeOutList";
		}
		return null;
	}
	
	@RequestMapping(value="/addDictionaryTypeIndex",method={RequestMethod.GET,RequestMethod.POST})
	public String addDictionaryTypeIndex(HttpServletRequest request,ModelMap map){
		return "/dictionary/addDictionaryType";
	}
	
	@RequestMapping(value="/addDictionaryType",method={RequestMethod.GET,RequestMethod.POST})
	public String addDictionaryType(HttpServletRequest request,ModelMap map){
		BigDecimal userid=(BigDecimal)request.getSession().getAttribute("userId");
		String total=StringHelperTools.nvl(request.getParameter("typeId"));
		String value=StringHelperTools.nvl(request.getParameter("typeValue"));
		// 判断新增的是否存在
		boolean flg = dictionaryService.queryDictionaryType(total);
		if(!flg){
			DictionaryTypeVo vo=new DictionaryTypeVo();
			vo.setDictionaryTypeText(value);
			vo.setDictionaryTypeValue(String.valueOf(total));
			dictionaryService.addDictionaryType(vo, userid);
			return "common/pubAddSuccess";
		}else{
			map.put("flag", "err");
			return "/dictionary/addDictionaryType";
		}
	}
	@RequestMapping(value="/deleteDictionaryType",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody ModelMap deleteDictionaryType(HttpServletRequest request,ModelMap map){
		BigDecimal userid=(BigDecimal)request.getSession().getAttribute("userId");
		String value=StringHelperTools.nvl(request.getParameter("typeValue"));
		int t=dictionaryService.deleteDictionaryType(value, userid);
		if(t==0){
			map.put("flag","false");
			return map;
		}
		map.put("flag","true");
		return map;
	}

	@RequestMapping(value="/modifyDictionaryType",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody ModelMap modifyDictionaryType(HttpServletRequest request,ModelMap map){
		BigDecimal userid=(BigDecimal)request.getSession().getAttribute("userId");
		String value=StringHelperTools.nvl(request.getParameter("typeText"));
		String key=StringHelperTools.nvl(request.getParameter("typeValue"));
		if("".equals(value)){
			map.put("flag", "false");
			return map;
		}
		DictionaryTypeVo vo=new DictionaryTypeVo();
		vo.setDictionaryTypeText(value);
		vo.setDictionaryTypeValue(key);
		int t=dictionaryService.updateDictionaryType(vo, userid);
		if(t==0){
			map.put("flag","false");
			return map;
		}
		map.put("flag","true");
		return map;
	}
	@RequestMapping(value="/page/{id}",method={RequestMethod.GET,RequestMethod.POST})
	public String getDictionaryByPage(@PathVariable(value="id") String id,HttpServletRequest request,ModelMap map){
		if("List".equals(id)){
			return "/dictionary/dictionaryList";
		}else if ("Query".equals(id)){
			String type=StringHelperTools.nvl(request.getParameter("dictionaryType"));
			String value=StringHelperTools.nvl(request.getParameter("dictionaryValue"));
			DictionaryConditionVo condition=new DictionaryConditionVo();
			condition.setDictionaryType(type);
			condition.setDictionaryValue(value);
			int totalCount=dictionaryService.getDicitonaryTotalCountForPage(condition);
			PageVo pageVo=Pager.getPager();
			pageVo.setTotalCount(totalCount);
			String pageNow=StringHelperTools.nvl(request.getParameter("page"));
			if("".equals(pageNow)){
				pageVo.setPageNow(1);
			}else{
				pageVo.setPageNow(Integer.parseInt(pageNow));
			}
			pageVo.setMessageForPage(10);
			pageVo.setUrl(request.getContextPath()+"/dictionary/Page/Query.html");
			List<DictionaryVo> list=dictionaryService.getDictionaryByPage(pageVo, condition);
			map=Pager.setPageerToPage(map, pageVo);
			map.put("list", list);
			return "/dictionary/dictionaryOutList";
		}
		return null;
	}
	@RequestMapping(value="/addDictionary",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody ModelMap addDictionary(HttpServletRequest request,ModelMap map){
		String dictionaryTypeKey=StringHelperTools.nvl(request.getParameter("dictionaryTypeKey"));
		String dictionaryTypeValue=StringHelperTools.nvl(request.getParameter("dictionaryTypeValue"));
		String dictionaryValue=StringHelperTools.nvl(request.getParameter("dictionaryValue"));
		String description =StringHelperTools.nvl(request.getParameter("description"));
		BigDecimal userid=(BigDecimal)request.getSession().getAttribute("userId");
		
		// 判断新增的是否存在
		boolean flg = dictionaryService.queryDictionaryIsAble(dictionaryTypeKey, dictionaryValue);
		if(flg){
			// 存在
			map.put("result","err");
			return map;
		}
		DictionaryVo vo=new DictionaryVo();
		vo.setAdd_user(userid.toString());
		vo.setDictionaryValue(dictionaryValue);
		vo.setDictionaryTypeValue(dictionaryTypeKey);
		vo.setDictionaryTypeText(dictionaryTypeValue);
		vo.setDescribtion(description);
		int t=dictionaryService.insertDictionary(vo);
		if(t>0){
			if(StringUtils.equals(dictionaryTypeKey, "channel_type")){
				List<SelectVo> channel_type=new ArrayList<SelectVo>();
					channel_type=dictionaryService.getDictionaryByType("channel_type");
					memClient.set("channel_type",channel_type);//更新缓存
			}
			map.put("result","true");
			return map;
		}
		map.put("result","false");
		return map;
	}
	@RequestMapping(value="/CU",method={RequestMethod.GET,RequestMethod.POST})
	public String CU(HttpServletRequest request){
		String id=StringHelperTools.nvl(request.getParameter("id"));
		if("".equals(id)){
			request.setAttribute("vo", new DictionaryVo());
			return "/dictionary/addDictionary";
		}
		DictionaryVo vo=dictionaryService.getDictionaryById(id);
		request.setAttribute("vo",vo);
		return "/dictionary/modifyDictionary";
	}
	@RequestMapping(value="/deleteDictionary",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody ModelMap deleteDictionary(HttpServletRequest request,ModelMap map){
		String id=StringHelperTools.nvl(request.getParameter("id"));
		
		int t=dictionaryService.deleteDictionary(id);
		if(t>0){
			map.put("result", "true");
			return map;
		}
		map.put("result", "false");
		return map;
	}
	@RequestMapping(value="/modifyDictionary",method={RequestMethod.GET,RequestMethod.POST})
	public ModelMap modifyDictionary(HttpServletRequest request,ModelMap map){
		String id=StringHelperTools.nvl(request.getParameter("id"));
		String dictionaryTypeKey=StringHelperTools.nvl(request.getParameter("dictionaryTypeKey"));
		String dictionaryTypeValue=StringHelperTools.nvl(request.getParameter("dictionaryTypeValue"));
		String dictionaryValue=StringHelperTools.nvl(request.getParameter("dictionaryValue"));
		String description =StringHelperTools.nvl(request.getParameter("description"));
		BigDecimal userid=(BigDecimal)request.getSession().getAttribute("userId");
		
		DictionaryVo vo=new DictionaryVo();
		vo.setDictionaryId(new BigDecimal(id));
		vo.setAdd_user(userid.toString());
		vo.setDictionaryValue(dictionaryValue);
		vo.setDictionaryTypeValue(dictionaryTypeKey);
		vo.setDictionaryTypeText(dictionaryTypeValue);
		vo.setDescribtion(description);
		dictionaryService.modifyDictionary(vo);
		map.put("result","true");
		return map;
	}
	@RequestMapping(value="/loadDictionaryType",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<DictionaryTypeVo> loadType(ModelMap map){
		List<DictionaryTypeVo> list=dictionaryService.getDictionaryType(null);
		return list;
	}
}
