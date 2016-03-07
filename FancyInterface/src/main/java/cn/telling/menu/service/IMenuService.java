/**
 * 
 * Project Name:myweb-service
 * File Name:IMenuService.java
 * Package Name:com.fancy.menu.service
 * Date:2015-7-24
 *
*/

package cn.telling.menu.service;

import java.math.BigDecimal;
import java.util.List;

import cn.telling.menu.vo.Menu;


/**
 * ClassName:IMenuService <br/>
 * @author   caosheng
 */
public interface IMenuService {
	public List<Menu> findAllMenuByUserId(BigDecimal userId) throws Exception;

	/**
	 * @Description:  描述函数的功能、用途、对属性的更改，以及函数执行前后对象的状态。
	 * @param		参数说明
	 * @return		返回值
	 * @exception   异常描述
	 * @see		          需要参见的其它内容。（可选）
	 * @author      操圣
	 * @date 2015-12-7 上午11:25:21 
	 * @version V1.0  
	*/
	
	public Menu getById(BigDecimal menupId);
	
	public List<Menu> findMenuByUserId(BigDecimal userId);
	
	public List<Menu> findMenus(String mname);
	
	Menu getPmById(BigDecimal id);
}

