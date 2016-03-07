/**
 * 

 * Project Name:myweb-service
 * File Name:RoleServiceImpl.java
 * Package Name:com.fancy.role.service.impl
 * Date:2015-7-24
 *
 */

package cn.telling.role.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.telling.menu.dao.IMenuDao;
import cn.telling.menu.vo.Menu;
import cn.telling.role.dao.IRoleDao;
import cn.telling.role.service.IRoleService;
import cn.telling.role.vo.Role;

/**
 * ClassName:RoleServiceImpl <br/>
 * 
 * @author caosheng
 */
@Service
public class RoleServiceImpl implements IRoleService {
	@Resource
	IRoleDao roleDao;
	@Resource
	IMenuDao menuDao;

	@Override
	public List<Role> getRoles()
	{
		return roleDao.getRoles();
	}

	public Set<String> getRolesAsString(BigDecimal userId) throws Exception
	{
		List<Role> list = roleDao.queryRoleByUserId(userId);
		if (CollectionUtils.isEmpty(list))
		{
			return null;
		}
		Set<String> set = new HashSet<String>();
		for (Role r : list)
		{
			set.add(r.getName());
		}
		return set;
	}

	private List<Menu> findAllMenuByUserId(BigDecimal userId) throws Exception
	{
		if (userId == null)
		{
			return null;
		}

		List<Role> roles = roleDao.queryRoleByUserId(userId);
		if (CollectionUtils.isEmpty(roles))
		{
			return null;
		}
		List<Menu> list = new ArrayList<Menu>();
		for (Role role : roles)
		{
			List<Menu> menus = menuDao.queryMenuByRoleId(role.getId());
			if (CollectionUtils.isEmpty(menus))
			{
				continue;
			}
			list.addAll(menus);
		}

		return list;

	}

	public Set<String> getPermissionsAsString(BigDecimal userId) throws Exception
	{
		List<Menu> setMenu = findAllMenuByUserId(userId);
		if (CollectionUtils.isEmpty(setMenu))
		{
			return null;
		}
		Set<String> set = new HashSet<String>();
		for (Menu m : setMenu)
		{
			if (StringUtils.isEmpty(m.getPageUrl()))
			{
				continue;
			}
			set.add(m.getPageUrl());
		}
		return set;
	}
}
