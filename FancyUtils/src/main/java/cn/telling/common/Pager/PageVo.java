package cn.telling.common.Pager;

import java.io.Serializable;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;



/**
 * 
 * @author liufei
 *	save all pager function needed information
 */
public class PageVo implements Serializable {

	private static final long serialVersionUID = 1156364107449207777L;
	private int totalCount;				//总条数
	private int totalPage;				//总页数
	private int pageNow;				//当前页数
	private int messageForPage;			//每页多少条
	private int pageInterval;			//页面显示段
	private int pageIntervalNow;		//页面所在区间
	private boolean inLastPageInteral;	//在最后一个区域
	private int lastPage;				//上一页
	private int nextPage;				//下一页
	private int pageStartCount;			//当页起始数
	private int pageEndCount;			//当页结束数
	private int centerPage;				//中间页
	private int startPage;				//起始页
	private int endPage;				//结束页
	private String url;					//列表链接	
	
	/**
	 * @return the centerPage
	 */
	public int getCenterPage() {
		return centerPage;
	}

	/**
	 * @param centerPage the centerPage to set
	 */
	public void setCenterPage(int centerPage) {
		this.centerPage = centerPage;
	}

	/**
	 * @return the startPage
	 */
	public int getStartPage() {
		return startPage;
	}

	/**
	 * @param startPage the startPage to set
	 */
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	/**
	 * @return the endPage
	 */
	public int getEndPage() {
		return endPage;
	}

	/**
	 * @param endPage the endPage to set
	 */
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	/**
	 * @return the pageStartCount
	 */
	public int getPageStartCount() {
		return pageStartCount;
	}

	/**
	 * @param pageStartCount the pageStartCount to set
	 */
	public void setPageStartCount(int pageStartCount) {
		this.pageStartCount = pageStartCount;
	}

	/**
	 * @return the pageEndCount
	 */
	public int getPageEndCount() {
		return pageEndCount;
	}

	/**
	 * @param pageEndCount the pageEndCount to set
	 */
	public void setPageEndCount(int pageEndCount) {
		this.pageEndCount = pageEndCount;
	}

	/**
	 * @return the lastPage
	 */
	public int getLastPage() {
		return lastPage;
	}

	/**
	 * @param lastPage the lastPage to set
	 */
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	/**
	 * @return the nextPage
	 */
	public int getNextPage() {
		return nextPage;
	}

	/**
	 * @param nextPage the nextPage to set
	 */
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	/**
	 * @return the inLastPageInteral
	 */
	public boolean isInLastPageInteral() {
		return inLastPageInteral;
	}

	/**
	 * @param inLastPageInteral the inLastPageInteral to set
	 */
	public void setInLastPageInteral(boolean inLastPageInteral) {
		this.inLastPageInteral = inLastPageInteral;
	}

	/**
	 * @return the pageIntervalNow
	 */
	public int getPageIntervalNow() {
		return pageIntervalNow;
	}

	/**
	 * @param pageIntervalNow the pageIntervalNow to set
	 */
	public void setPageIntervalNow(int pageIntervalNow) {
		this.pageIntervalNow = pageIntervalNow;
	}



	/**
	 * @return the totalCount
	 */
	public int getTotalCount() {
		
//		if(totalCount==0)
//		{
//			totalCount=1;
//		}
		
		return totalCount;
	}

	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		if(this.pageNow>0&&this.totalCount>0){
			this.countFunction();
		}
		Pager.pageSetup(this);
	}

	/**
	 * @return the totalPage
	 */
	public int getTotalPage() {
		return totalPage;
	}

	/**
	 * @param totalPage the totalPage to set
	 */
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	/**
	 * @return the pageNow
	 */
	public int getPageNow() {
		if(pageNow<=0)
		{
			pageNow =1;
		}
		return pageNow;
	}

	/**
	 * @param pageNow the pageNow to set
	 */
	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
		
		if(this.pageNow>0&&this.totalCount>0){
			this.countFunction();
		}
	}

	/**
	 * @return the messageForPage
	 */
	public int getMessageForPage() {
		if(messageForPage==0)
		{
			this.messageForPage=10;
		}
		return messageForPage;
	}

	/**
	 * @param messageForPage the messageForPage to set
	 */
	public void setMessageForPage(int messageForPage) {
		this.messageForPage = messageForPage;
		
		if(this.pageNow>0&&this.totalCount>0){
			this.countFunction();
		}
	}

	/**
	 * @return the pageInterval
	 */
	public int getPageInterval() {
		return pageInterval;
	}

	/**
	 * @param pageInterval the pageInterval to set
	 */
	public void setPageInterval(int pageInterval) {
		this.pageInterval = pageInterval;
		
		if(this.pageNow>0&&this.totalCount>0){
			this.countFunction();
		}
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url, HttpServletRequest request) {
		
		Enumeration<String> names= request.getParameterNames();
		
		String tempUrl = "";
		String tempName ="";
		while(names.hasMoreElements())
		{
			tempName = (String) names.nextElement();
			if(!tempName.equals("page"))
			{
			tempUrl = tempUrl+tempName+"="+ request.getParameter(tempName)+"&";
				}
		}
		if(!tempUrl.equals(""))
		{
			tempUrl = tempUrl.substring(0,tempUrl.length()-1);
			if(url.indexOf("?")>0)
			{
				url = url+"&"+tempUrl;
			}else
			{
				url = url+"?"+tempUrl;
			}	
		}
		
		this.url = url;
	}
	
	
	private void countFunction()
	{
		this.pageStartCount=(this.pageNow-1)*this.messageForPage+1;
		this.pageEndCount=(this.pageNow*this.messageForPage);
		if(this.pageEndCount>this.totalCount)
		{
			this.pageEndCount=this.totalCount;
		}
	}
	
	
	public String getPageUrl(int page)
	{
		String returnUrl = "";
		
		if(this.url.indexOf("?")>0)
		{
			returnUrl = url+"&page="+page;
		}else
		{
			returnUrl = url+"?page="+page;
		}
		
		return returnUrl;
		
	}
	/**
	 * 得到restful风格的URL分页
	 * @param page
	 * @return
	 */
	public String getRestfulPageUrl(int page)
	{
		
		String returnUrl=this.url+page+".html";
		
		return returnUrl;
		
	}
	
}
