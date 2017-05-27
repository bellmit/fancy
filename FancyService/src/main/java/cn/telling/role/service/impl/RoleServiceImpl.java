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

import cn.telling.common.pager.PageVo;
import cn.telling.menu.dao.IMenuDao;
import cn.telling.menu.vo.Menu;
import cn.telling.menu.vo.PusReRoleMenu;
import cn.telling.role.dao.IRoleDao;
import cn.telling.role.service.IRoleService;
import cn.telling.role.vo.Role;
import cn.telling.user.vo.ReturnUserVo;

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

	public Set<String> getRolesAsString(String userId) throws Exception
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

	private List<Menu> findAllMenuByUserId(String userId) throws Exception
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

	public Set<String> getPermissionsAsString(String userId) throws Exception
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
	/**
	 * @Description:  查询角色列表
	 * @param		参数说明
	 * @return		返回值
	 * @exception   异常描述
	 * @see		          需要参见的其它内容。（可选）
	 * @author      操圣
	 * @date 2017年3月17日 下午1:47:29 
	 * @version V1.0  
	*/
	@Override
	public ReturnUserVo queryUserPagesByname(String name, PageVo pages) {
		return roleDao.queryUserPagesByname(name, pages);
	}

	/* (non-Javadoc)
	 * @see cn.telling.role.service.IRoleService#getById(java.lang.Integer)
	 */
	@Override
	public Role getById(Integer id) {
		//  Auto-generated method stub
		return roleDao.getById(id);
	}

	/* (non-Javadoc)
	 * @see cn.telling.role.service.IRoleService#saveRole(cn.telling.menu.vo.PusReRoleMenu)
	 */
	@Override
	public int saveRole(List<Object[]> batch) {
		//  Auto-generated method stub
		return roleDao.saveRole(batch);
	}
}
