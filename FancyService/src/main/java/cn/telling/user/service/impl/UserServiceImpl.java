/**
 * Project Desc:
 * Project Name:SpringMVC
 * File Name:UserServiceImpl.java
 * Package Name:com.fancy.paging.service
 * Date:2015-4-27下午6:22:59
 * Copyright (c) 2015, zhgo116.com All Rights Reserved.
 *
 */
package cn.telling.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.telling.common.Pager.PageVo;
import cn.telling.role.dao.IRoleDao;
import cn.telling.user.dao.IUserDao;
import cn.telling.user.service.IUserService;
import cn.telling.user.vo.ReturnUserVo;
import cn.telling.user.vo.User;

/**
 * ClassName:UserServiceImpl <br/>
 * Date: 2015-4-27 下午6:22:59 <br/>
 * 
 * @author 操圣
 * @version 1.0
 * @see
 */
@Service
public class UserServiceImpl implements IUserService {

	@Resource
	private IUserDao userDao;
	
	@Resource
	IRoleDao roleDao;

	@Override
	public User userLogin(String uName, String pWd)
	{
		return userDao.userLogin(uName, pWd);
	}

	// 通过ID删除
	@Cacheable(value = "serviceCache", key = "#name")
	public Integer getUserId(String name)
	{
		return userDao.getUserId(name);
	}

	// 根据ID查询，ID 我们默认是唯一的
	@Cacheable(value = "serviceCache", key = "#id")
	public User getUserInfo(String id)
	{
		return userDao.getUserInfo(id);
	}

	@Cacheable(value = "serviceCache")
	public List<User> getUsers()
	{
		return userDao.getUsers();
	}

	@Override
	public User queryUserByName(String uName)
	{
	    User user=userDao.queryUserByName(uName);;
		return user;
	}

	@Override
	public Boolean saveUser(User user)
	{

		return null;
	}

	/* (non-Javadoc)
	 * @see cn.telling.user.service.IUserService#queryUserPagesByAccount(java.lang.String, cn.telling.user.vo.Page)
	 */
	@Override
	public List<User> queryUserPagesByAccount(String account, PageVo page) {
		return userDao.queryUserPagesByAccount(account,page);
	}

	/* (non-Javadoc)
	 * @see cn.telling.user.service.IUserService#queryUsers(java.lang.String, cn.telling.common.Pager.PageVo, cn.telling.user.vo.Users)
	 */
	@Override
	public ReturnUserVo queryUsers(String account, PageVo page) {
		return userDao.queryUsers(account,page);
	}
}
