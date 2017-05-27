package cn.telling.action.main.service;

import java.util.List;

import cn.telling.common.pager.PageVo;
import cn.telling.user.vo.User;
import cn.telling.utils.Paging;



/**   
* @Title: PagingDao.java 
* @Package dao 
* @Description: (描述该文件做什么) 
* @author 操圣
* @date 2015-3-30 上午11:39:16 
* @version V1.0   
*/
public interface PagingService
{

	/****
	 * 
	 * @Description: 获取分页数据
	 * @param		参数说明
	 * @return		返回值
	 * @exception   异常描述
	 * @see		          需要参见的其它内容。（可选）
	 * @author      操圣
	 * @date 2015-3-30 下午1:26:09 
	 * @version V1.0
	 */
	public int getPageCount();

	/*****
	 * 
	 * @Description:获取分页数据 
	 * @param		参数说明
	 * @return		返回值
	 * @exception   异常描述
	 * @see		          需要参见的其它内容。（可选）
	 * @author      操圣
	 * @date 2015-3-30 下午1:26:31 
	 * @version V1.0
	 */
	public List<User> getPageData(Paging page);

    /**
     * @Description:  描述函数的功能、用途、对属性的更改，以及函数执行前后对象的状态。
     * @param		参数说明
     * @return		返回值
     * @exception   异常描述
     * @see		          需要参见的其它内容。（可选）
     * @author      操圣
     * @date 2016-2-2 上午10:54:55 
     * @version V1.0  
    */
    
    public List<User> getPageData(PageVo pageVo);
    
    public Integer getUserCount(PageVo pageVo);
}
