/**
 * Project Desc:
 * Project Name:SpringMVC
 * File Name:UserDao.java
 * Package Name:com.fancy.paging.dao
 * Date:2015-4-27下午6:10:33
 * Copyright (c) 2015, zhgo116.com All Rights Reserved.
 *
 */
package cn.telling.user.dao;

import java.util.List;

import cn.telling.common.Pager.PageVo;
import cn.telling.menu.vo.Menu;
import cn.telling.user.vo.ReturnUserVo;
import cn.telling.user.vo.Users;

/**
 * ClassName:UserDao <br/>
 * Date: 2015-4-27 下午6:10:33 <br/>
 * 
 * @author 操圣
 * @version 1.0
 * @see
 */
public interface IUserDao {
	public List<Users> getUsers();

	public Users userTest(String uName);

	public Integer getUserId(String name);

	public Users userLogin(String uName, String pWd);

	public Users getUserInfo(Integer id);

	public List<Menu> queryMenuByRoleId(Integer mId);

	public Users queryUserByName(String uName);

	public Boolean saveUser(Users user);

	/**
	 * @Description:  描述函数的功能、用途、对属性的更改，以及函数执行前后对象的状态。
	 * @param		参数说明
	 * @return		返回值
	 * @exception   异常描述
	 * @see		          需要参见的其它内容。（可选）
	 * @author      操圣
	 * @date 2015-12-18 下午5:23:15 
	 * @version V1.0  
	 * @param users 
	*/
	
	public List<Users> queryUserPagesByAccount(String account, PageVo page);

	/**
	 * @Description:  描述函数的功能、用途、对属性的更改，以及函数执行前后对象的状态。
	 * @param		参数说明
	 * @return		返回值
	 * @exception   异常描述
	 * @see		          需要参见的其它内容。（可选）
	 * @author      操圣
	 * @date 2016年1月1日 下午4:57:43 
	 * @version V1.0  
	*/
	
	public ReturnUserVo queryUsers(String account, PageVo page);
}
