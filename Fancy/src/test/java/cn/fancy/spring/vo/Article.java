/**
 * 
 * Project Name:myweb-web
 * File Name:Article.java
 * Package Name:com.fancy.action.shop.vo
 * Date:2015-7-28
 *
*/

package cn.fancy.spring.vo;
/**
 * ClassName:Article <br/>
 * @author   caosheng
 */
public class Article {
	private String id;
	private String title;
	private String content;
	private Integer del_flag;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public Integer getDel_flag()
	{
		return del_flag;
	}

	public void setDel_flag(Integer del_flag)
	{
		this.del_flag = del_flag;
	}


}

