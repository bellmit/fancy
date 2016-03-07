package cn.telling.utils;

import java.io.Serializable;

/**   
* @Title: Paging.java 
* @Package com.fancy.paging.entity 
* @Description: (描述该文件做什么) 
* @author 操圣
* @date 2015-3-30 下午1:30:48 
* @version V1.0   
*/
public class Paging implements Serializable
{

	private static final long serialVersionUID = 8621749861441420212L;

    // 当前页
	private Integer nowPage = 1;

	// 页面要显示信息条数
	private Integer pageSize = 5;

	// 根据页面显示的条数计算总页数
	private Integer countPage = 0;

	// 根据传入的数据库查询数据库中的信息的条数
	private Integer total = 0;

	// 向数据库查询时的开始的下标
	private Integer startIndex = 0;

	// 向数据库查询时的查询条数
	private Integer endIndex = 0;

	public Integer getNowPage()
	{
		return nowPage;
	}

	public void setNowPage(Integer nowPage)
	{
		this.nowPage = nowPage;
	}

	public Integer getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(Integer pageSize)
	{
		this.pageSize = pageSize;
	}

	public Integer getCountPage()
	{
		return countPage;
	}

	public void setCountPage(Integer countPage)
	{
		this.countPage = countPage;
	}

	public Integer getTotal()
	{
		return total;
	}

	public void setTotal(Integer total)
	{
		this.total = total;
	}

	public Integer getStartIndex()
	{
		return startIndex;
	}

	public void setStartIndex(Integer startIndex)
	{
		this.startIndex = startIndex;
	}

	public Integer getEndIndex()
	{
		return endIndex;
	}

	public void setEndIndex(Integer endIndex)
	{
		this.endIndex = endIndex;
	}
}
