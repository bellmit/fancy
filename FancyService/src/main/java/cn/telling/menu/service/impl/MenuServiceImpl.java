/**
 * 
 * Project Name:myweb-service
 * File Name:MenuServiceImpl.java
 * Package Name:com.fancy.menu.service.impl
 * Date:2015-7-24
 *
 */

package cn.telling.menu.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.telling.menu.dao.IMenuDao;
import cn.telling.menu.service.IMenuService;
import cn.telling.menu.vo.Menu;
import cn.telling.role.dao.IRoleDao;
import cn.telling.role.vo.Role;

/**
 * ClassName:MenuServiceImpl <br/>
 * 
 * @author caosheng
 */
@Service
public class MenuServiceImpl implements IMenuService {

	@Autowired
	private IMenuDao menuDao;

	@Autowired
	private IRoleDao roleDao;

	public List<Menu> findMenuByUserId(BigDecimal userid) {

		return menuDao.findMenuByUserId(userid);
	}

	public List<Menu> findAllMenuByUserId(BigDecimal userId) throws Exception {
		if (userId == null) {
			return null;
		}

		List<Role> roles = roleDao.queryRoleByUserId(userId);
		if (CollectionUtils.isEmpty(roles)) {
			return null;
		}
		List<Menu> list = new ArrayList<Menu>();
		for (Role role : roles) {
			List<Menu> menus = menuDao.queryMenuByRoleId(role.getId());
			if (CollectionUtils.isEmpty(menus)) {
				continue;
			}
			list.addAll(menus);
		}

		return list;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.telling.menu.service.IMenuService#getById(java.lang.BigDecimal)
	 */
	@Override
	public Menu getById(BigDecimal menupId) {
		return menuDao.getById(menupId);
	}

    /* (non-Javadoc)
     * @see cn.telling.menu.service.IMenuService#findMenus(java.lang.String)
     */
    @Override
    public List<Menu> findMenus(String mname) {
        return menuDao.findMenus(mname);
    }
    
    
    public Menu getPmById(BigDecimal id)
    {
        return menuDao.getPmById(id);
        
    }
}
