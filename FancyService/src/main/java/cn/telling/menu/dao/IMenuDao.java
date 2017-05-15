/**
 * 
 * Project Name:myweb-service
 * File Name:IMenuService.java
 * Package Name:com.fancy.menu.dao
 * Date:2015-7-24
 *
*/

package cn.telling.menu.dao;

import java.math.BigDecimal;
import java.util.List;

import cn.telling.menu.vo.Menu;

/**
 * ClassName:IMenuService <br/>
 * @author   caosheng
 */
public interface IMenuDao {
	public List<Menu> queryMenuByRoleId(BigDecimal mId);

	/**
	 * @Description:  描述函数的功能、用途、对属性的更改，以及函数执行前后对象的状态。
	 * @param		参数说明
	 * @return		返回值
	 * @exception   异常描述
	 * @see		          需要参见的其它内容。（可选）
	 * @author      操圣
	 * @date 2015-12-7 上午11:28:07 
	 * @version V1.0  
	*/
	
	public Menu getById(BigDecimal menupId);
	
	public List<Menu> findMenuByUserId(String userId);

    /**
     * @Description:  描述函数的功能、用途、对属性的更改，以及函数执行前后对象的状态。
     * @param		参数说明
     * @return		返回值
     * @exception   异常描述
     * @see		          需要参见的其它内容。（可选）
     * @author      操圣
     * @date 2016-1-29 上午11:47:22 
     * @version V1.0  
    */
    
    List<Menu> findMenus(String mname);

    /**
     * @Description:  描述函数的功能、用途、对属性的更改，以及函数执行前后对象的状态。
     * @param		参数说明
     * @return		返回值
     * @exception   异常描述
     * @see		          需要参见的其它内容。（可选）
     * @author      操圣
     * @date 2016-2-2 上午9:24:09 
     * @version V1.0  
    */
    
    Menu getPmById(BigDecimal id);

	/**
	 * @Description:  描述函数的功能、用途、对属性的更改，以及函数执行前后对象的状态。
	 * @param		参数说明
	 * @return		返回值
	 * @exception   异常描述
	 * @see		          需要参见的其它内容。（可选）
	 * @author      操圣
	 * @date 2017年3月17日 下午2:39:38 
	 * @version V1.0  
	*/
	
	public List<Menu> findMenuByRoleId(Integer id);

	/**
	 * @Description:  描述函数的功能、用途、对属性的更改，以及函数执行前后对象的状态。
	 * @param		参数说明
	 * @return		返回值
	 * @exception   异常描述
	 * @see		          需要参见的其它内容。（可选）
	 * @author      操圣
	 * @date 2017年3月17日 下午2:47:57 
	 * @version V1.0  
	*/
	
	public List<Menu> getPusMenuList();

	/**
	 * @Description:  描述函数的功能、用途、对属性的更改，以及函数执行前后对象的状态。
	 * @param		参数说明
	 * @return		返回值
	 * @exception   异常描述
	 * @see		          需要参见的其它内容。（可选）
	 * @author      操圣
	 * @date 2017年3月17日 下午3:07:38 
	 * @version V1.0  
	*/
	
	public void batchDelete(Integer roleid);
}

