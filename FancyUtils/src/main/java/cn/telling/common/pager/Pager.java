package cn.telling.common.pager;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;




/**
 * @author liufei
 *	pager function for pager
 *
 *	1.to use this function need to call getPager(); first to creat a new PageVo
 *	2.must set 2 value to PageVo pageNow and totalCount
 *  3.default message for page is 10 you can set to any number you want
 *  4.default page interval is 10 you can set to any number you like
 *  5.to use call the setPageerToPage function to request or Modelmap
 *  
 */


public class Pager{

	public Pager()
	{
		
	}
	
	public static PageVo getPager()
	{
		PageVo page = new PageVo();
		
		return page;
	}
	
	
	/**
	 * 
	 * @Description: TODO find the total page of the set
	 * @param		PageVo
	 * @return		PageVo
	 * @exception   null
	 * @see		    null
	 * @author      liu fei
	 * @date 2011-11-11 下午3:58:43 
	 * @version V1.0
	 */
	private static PageVo getTotalPage(PageVo page)
	{
		if(page.getMessageForPage()==0)
		{
			page.setMessageForPage(10);		//set default for message for page
		}
		
		//get the total page if 
		if(page.getTotalCount()%page.getMessageForPage()>0)
		{
			page.setTotalPage((page.getTotalCount()/page.getMessageForPage())+1);
		}else
		{
			page.setTotalPage((page.getTotalCount()/page.getMessageForPage()));
		}
		
		if(page.getPageNow()==1)
		{
			page.setLastPage(1);
		}else{
			page.setLastPage(page.getPageNow()-1);
		}
		if(page.getPageNow()==page.getTotalPage())
		{
			page.setNextPage(page.getPageNow());
		}else
		{
			page.setNextPage(page.getPageNow()+1);
		}
		
		return page;
	}
	
	/**
	 * 
	 * @Description: TODO find where is the page at
	 * @param		pageVo
	 * @return		pageVo
	 * @exception   null
	 * @see		         
	 * @author      liu fei
	 * @date 2011-11-11 下午3:03:18 
	 * @version V1.0
	 */
	private static PageVo findPageIntervalNow(PageVo page)
	{
		if(page.getPageInterval()==0)
		{
			if(page.getTotalCount()==0){
				page.setPageInterval(10);
			}else{
				page.setPageInterval(page.getTotalCount());
			}
		}
		
		if(page.getPageNow()==0)
		{
			page.setPageNow(1);
		}
		
		int rofeInterval = page.getPageNow()%page.getPageInterval(); //only basic interval
		if(rofeInterval == 0)
			{
				page.setPageIntervalNow(page.getPageNow()/page.getPageInterval());
			}else
			{
				page.setPageIntervalNow(page.getPageNow()/page.getPageInterval()+1);
			}
		
		return page;
	}
	
	/**
	 * 
	 * @Description: TODO private function for test if it is in the last interval
	 * @param		PageVo
	 * @return		PageVo
	 * @exception   null
	 * @see		    null
	 * @author      liu fei
	 * @date 2011-11-11 下午3:43:27 
	 * @version V1.0
	 */
	private static PageVo isInlastInterval(PageVo page)
	{
		int maxPage = page.getPageIntervalNow()*page.getPageInterval();
		
		if(page.getTotalPage()<maxPage && page.getPageNow()>(maxPage-page.getPageInterval()))
		{
			page.setInLastPageInteral(true);
		}else
		{
			page.setInLastPageInteral(false);
		}
		
		return page;
	}
	
	/**
	 * 
	 * @Description: TODO set oracle pager sql
	 * @param		PageVo,  sql
	 * @return		sql with right interval
	 * @exception   null
	 * @see		    null
	 * @author      liu fei
	 * @date 2011-11-11 下午3:05:32 
	 * @version V1.0
	 */
	public static String getPageDatas(PageVo pageVo, String sql) {
		// 拼装分页sql
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT * FROM (SELECT ROWNUM row_, t.* FROM (");
		sb.append(sql);
		sb.append(") t ) WHERE row_ <=");
		sb.append(pageVo.getPageNow()*pageVo.getMessageForPage());
		sb.append(" AND row_ >=");
		sb.append((pageVo.getPageNow()-1)*pageVo.getMessageForPage()+1);
		return sb.toString();
	}
	
	public static String getTotalPageSql(PageVo pageVo, String sql)
	{
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT count(*) as count FROM (");
		sb.append(sql);
		sb.append(") ");

		return sb.toString();		
	}
	
	/**
	 * 
	 * @Description: TODO for passing request to jsp
	 * @param		request, PageVo (have url, pagenow, total message)
	 * @return		request
	 * @exception   null
	 * @see		    null
	 * @author      liu fei
	 * @date 2011-11-11 下午3:33:41 
	 * @version V1.0
	 */
	public static HttpServletRequest setPageerToPage(HttpServletRequest request, PageVo page)
	{
		pageSetup(page);
		request.setAttribute("java_pager", page);
		
		return request;
	}
	
	/**
	 * 
	 * @Description: TODO 找到显示第一页与最后一页
	 * @param		参数说明
	 * @return		返回值
	 * @exception   异常描述
	 * @see		          需要参见的其它内容。（可选）
	 * @author      liu fei
	 * @date 2011-12-23 下午1:38:39 
	 * @version V1.0
	 */
	private static PageVo formStartPointAndEndPoint(PageVo page)
	{
		page.setCenterPage(page.getPageInterval()/2);
		
		if(page.getPageNow()<=page.getPageInterval())
		{
			if(page.getTotalPage()>=page.getPageInterval())
			{
				page.setStartPage(1);
				page.setEndPage(page.getPageInterval());
			}else
			{
				page.setStartPage(1);
				page.setEndPage(page.getTotalPage());
			}
			
			return page;
		}
		
		if(page.getPageNow()>=(page.getTotalPage()-page.getPageInterval()+1))
		{
			page.setStartPage(page.getTotalPage()-page.getPageInterval()+1);
			page.setEndPage(page.getTotalPage());
			
			return page;
		}
		

			page.setStartPage(page.getPageNow()-page.getCenterPage());
			page.setEndPage(page.getPageNow()+page.getCenterPage());
		
		return page;
		
	}
	
	/**
	 * 
	 * @Description: TODO 描述函数的功能、用途、对属性的更改，以及函数执行前后对象的状态。
	 * @param		参数说明
	 * @return		返回值
	 * @exception   异常描述
	 * @see		          需要参见的其它内容。（可选）
	 * @author      liu fei
	 * @date 2011-11-11 下午3:35:32 
	 * @version V1.0
	 */
	public static ModelMap setPageerToPage(ModelMap map, PageVo page)
	{
		pageSetup(page);
		map.put("java_pager", page);
		
		return map;
	}
	
	public static void pageSetup( PageVo page)
	{
		page = getTotalPage(page);
		page = findPageIntervalNow(page);	
		page = isInlastInterval(page);
		page = formStartPointAndEndPoint(page);
		
		if(page.getTotalCount()==0)
		{
			page.setStartPage(1);
			page.setEndPage(1);
			page.setNextPage(1);
		}
	}
	

}
