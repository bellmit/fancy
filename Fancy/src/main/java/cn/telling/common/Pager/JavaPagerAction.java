package cn.telling.common.Pager;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *	to be jsp:include by other page
 *	only thing needed is PageVo object within
 */
@Controller
public class JavaPagerAction {
	/**
	 * @Description:前台分页处理
	 * @param		参数说明
	 * @return		返回值
	 * @author      薛松坛
	 * @date 2013-4-19 下午3:30:57 
	 * @version V1.0
	 */
	@RequestMapping(value = "/javafrontpager", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String javaFrontPager(ModelMap map,HttpServletRequest request) {
	
		if(request.getAttribute("java_pager")!=null)
		{
			return "Controls/requestFrontPager";
		}else
		{
			return "Controls/requestFrontPager";
		}
	}
	/**
	 * @Description:前台搜索结果分页处理
	 * @param		参数说明
	 * @return		返回值
	 * @author      刘志荣
	 * @date 2014-7-9 下午3:30:57 
	 * @version V1.0
	 */
	@RequestMapping(value = "/javasearchpager", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String javaSearchPager(ModelMap map,HttpServletRequest request) {
	
		if(request.getAttribute("java_pager")!=null)
		{
			return "Controls/requestSearchPager";
		}else
		{
			return "Controls/requestSearchPager";
		}
	}
	/**
	 * @Description:比价宝搜索
	 * @param		参数说明
	 * @return		返回值
	 * @author      薛松坛
	 * @date 2013-4-19 下午3:30:57 
	 * @version V1.0
	 */
	@RequestMapping(value = "/empfrontpager", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String empFrontPager(ModelMap map,HttpServletRequest request) {
	
		if(request.getAttribute("java_pager")!=null)
		{
			return "Controls/empfrontpager";
		}else
		{
			return "Controls/empfrontpager";
		}
	}
	@RequestMapping(value = "/javapager", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String javaPager(ModelMap map,HttpServletRequest request) {
	
		if(request.getAttribute("java_pager")!=null)
		{
			return "Controls/requestManagerPager";
		}else
		{
			return "Controls/requestManagerPager";
		}
	}
	
	@RequestMapping(value = "/ajaxpager", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String ajaxPager(ModelMap map,HttpServletRequest request) {
		
		if(request.getParameter("queryType")!=null)
		{
			map.put("queryType", request.getParameter("queryType"));
		}
		if(request.getParameter("divId")!=null)
		{
			map.put("divId", request.getParameter("divId"));
		}
		if(request.getAttribute("java_pager")!=null)
		{
			return "Controls/ajaxPager";
		}else
		{
			return "Controls/ajaxPager";
		}
	}
	/**
	 * 
	 * @Description: 前台ajax分页
	 * @param		参数说明
	 * @return		返回值
	 * @exception   异常描述
	 * @see		          需要参见的其它内容。（可选）
	 * @author      taoye
	 * @date 2014-4-17 上午11:47:44 
	 * @version V1.0
	 */
	@RequestMapping(value = "/frontAjaxPager", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String frontAjaxPager(ModelMap map,HttpServletRequest request) {
		
		if(request.getParameter("queryType")!=null)
		{
			map.put("queryType", request.getParameter("queryType"));
		}
		if(request.getParameter("divId")!=null)
		{
			map.put("divId", request.getParameter("divId"));
		}
		if(request.getAttribute("java_pager")!=null)
		{
			return "Controls/frontAjaxPager";
		}else
		{
			return "Controls/frontAjaxPagerr";
		}
	}
	
	/**
	 * 
	 * @Description: 前台ajax分页
	 * @param		参数说明
	 * @return		返回值
	 * @exception   异常描述
	 * @see		          需要参见的其它内容。（可选）
	 * @author      王天锦
	 * @date 2014-12-08 
	 * @version V1.0
	 */
	@RequestMapping(value = "/AjaxLoginPaper", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String ajaxLoginPaper(ModelMap map,HttpServletRequest request) {
		
		if(request.getParameter("queryType")!=null)
		{
			map.put("queryType", request.getParameter("queryType"));
		}
		if(request.getParameter("divId")!=null)
		{
			map.put("divId", request.getParameter("divId"));
		}
		if(request.getAttribute("java_pager")!=null)
		{
			return "Controls/AjaxLoginPaper";
		}else
		{
			return "Controls/AjaxLoginPaper";
		}
	}
	/**
	 * 
	 * @Description: 前台ajax分页
	 * @param		参数说明
	 * @return		返回值
	 * @exception   异常描述
	 * @see		          需要参见的其它内容。（可选）
	 * @author      taoye
	 * @date 2014-4-17 上午11:47:44 
	 * @version V1.0
	 */
	@RequestMapping(value = "/frontBigOrderAjaxPager", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String frontBigOrderAjaxPager(ModelMap map,HttpServletRequest request) {
		
		if(request.getParameter("queryType")!=null)
		{
			map.put("queryType", request.getParameter("queryType"));
		}
		if(request.getParameter("divId")!=null)
		{
			map.put("divId", request.getParameter("divId"));
		}
		if(request.getAttribute("java_pager")!=null)
		{
			return "Controls/frontBigOrderAjaxPager";
		}else
		{
			return "Controls/frontBigOrderAjaxPager";
		}
	}
	/**
	 * 根据请求获得restful风格的URL分页
	 * @param map
	 * @param request
	 * @return
	 */
	
	@RequestMapping(value = "/backendPager", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String backendPager(ModelMap map,HttpServletRequest request) {
	
		if(request.getAttribute("java_pager")!=null)
		{
			return "Controls/backendPager";
		}else
		{
			return "Controls/backendPager";
		}
	}
	
	/**
	 * 根据请求获得restful风格的URL分页
	 * @param map
	 * @param request
	 * @return
	 */
	
	@RequestMapping(value = "/pagination", method = { RequestMethod.GET,
			RequestMethod.POST })
			public String pager(ModelMap map,HttpServletRequest request) {
		
		if(request.getAttribute("java_pager")!=null)
		{
			return "Controls/pagination";
		}else
		{
			return "Controls/pagination";
		}
	}
	
}
