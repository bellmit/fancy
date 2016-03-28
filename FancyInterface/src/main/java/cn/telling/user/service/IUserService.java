/**
 * Project Desc:
 * Project Name:SpringMVC
 * File Name:UserDaoService.java
 * Package Name:com.fancy.paging.service
 * Date:2015-4-27下午6:22:20
 * Copyright (c) 2015, zhgo116.com All Rights Reserved.
 *
 */
package cn.telling.user.service;

import java.util.List;

import cn.telling.common.Pager.PageVo;
import cn.telling.user.vo.ReturnUserVo;
import cn.telling.user.vo.Users;

/**
 * ClassName:UserDaoService <br/>
 * Date: 2015-4-27 下午6:22:20 <br/>
 * 
 * @author 操圣
 * @version 1.0
 * @see
 */
public interface IUserService {

	public  abstract Users getUserInfo(Integer uId);

	Integer getUserId(String uName);

	Users queryUserByName(String uName);

	Users userLogin(String uName, String pWd);

	List<Users> getUsers();

	Boolean saveUser(Users user);

	List<Users> queryUserPagesByAccount(String account, PageVo page);

	ReturnUserVo queryUsers(String account, PageVo page);
}
