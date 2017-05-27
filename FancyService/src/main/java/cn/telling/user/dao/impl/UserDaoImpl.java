/**
 * Project Desc:
 * Project username:SpringMVC
 * File username:UserDaoImpl.java
 * Package username:com.fancy.paging.dao.impl
 * Date:2015-4-27下午6:12:24
 * Copyright (c) 2015, zhgo116.com All Rights Reserved.
 *
 */
package cn.telling.user.dao.impl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cn.telling.common.AutoInjectionRowMapper;
import cn.telling.common.CommonBaseDao;
import cn.telling.common.pager.PageVo;
import cn.telling.menu.vo.Menu;
import cn.telling.user.dao.IUserDao;
import cn.telling.user.vo.ReturnUserVo;
import cn.telling.user.vo.User;
import cn.telling.utils.Common;
import cn.telling.utils.MySQLAutoInjection;
import cn.telling.utils.StringHelperTools;

/**
 * Classusername:UserDaoImpl <br/>
 * Date: 2015-4-27 下午6:12:24 <br/>
 * 
 * @author 操圣
 * @version 1.0
 * @see
 */
@Repository
public class UserDaoImpl extends CommonBaseDao implements IUserDao {
	private Logger logger = Logger.getLogger(UserDaoImpl.class);
	@Override
	public User userLogin(String uusername, String pWd)
	{
		try
		{
			String uLoginSql = "select u.id,u.username,u.email,u.realname,u.lasttime from Users u where u.username=? and u.password=?";
			logger.debug("用户登录:" + Common.logSQL(uLoginSql, new Object[]
			{ uusername, pWd }));
			List<User> uList = jdbcTemplate.query(uLoginSql, new Object[]
			{ uusername, pWd }, new RowMapper<User>() {

				@Override
				public User mapRow(ResultSet rs, int arg1) throws SQLException
				{

					User user = new User();
					MySQLAutoInjection.Rs2Vo(rs, user, null);
					return user;
				}
			});
			return uList.size() > 0 ? uList.get(0) : null;
		} catch (Exception e)
		{
			throw new RuntimeException("用户登录失败");
		}
	}

	@Override
	public Integer getUserId(String username)
	{
		try
		{
			String uLoginSql = "select id from Users u where u.username=?";
			logger.debug("根据用户名查询用户编号:" + Common.logSQL(uLoginSql, new Object[]
			{ username }));
			List<Integer> uList = jdbcTemplate.query(uLoginSql, new Object[]
			{ username }, new RowMapper<Integer>() {

				@Override
				public Integer mapRow(ResultSet rs, int arg1) throws SQLException
				{
					return rs.getInt("id");
				}
			});
			return uList.size() > 0 ? uList.get(0) : -1;
		} catch (Exception e)
		{
			throw new RuntimeException("用户登录失败");
		}
	}

	@Override
	public User userTest(String uusername)
	{
		try
		{
			String uLoginSql = "select id,username,password,realname from Users u where u.username=?";
			logger.debug("根据用户名查询用户Vo:" + Common.logSQL(uLoginSql, new Object[]
			{ uusername }));
			List<User> uList = jdbcTemplate.query(uLoginSql, new Object[]
			{ uusername }, new RowMapper<User>() {

				@Override
				public User mapRow(ResultSet rs, int arg1) throws SQLException
				{
					User user = new User();
					MySQLAutoInjection.Rs2Vo(rs, user, null);
					return user;
				}
			});
			return uList.get(0);
		} catch (Exception e)
		{
			throw new RuntimeException("用户登录失败");
		}
	}

	@Override
	public User getUserInfo(String id)
	{
		try
		{
			String uLoginSql = "select id,username,password from Users u where u.id=?";
			logger.debug("根据用户编号查询用户Vo:" + Common.logSQL(uLoginSql, new Object[]
			{ id }));
			List<User> uList = jdbcTemplate.query(uLoginSql, new Object[]
			{ id }, new RowMapper<User>() {

				@Override
				public User mapRow(ResultSet rs, int arg1) throws SQLException
				{
					User user = new User();
					MySQLAutoInjection.Rs2Vo(rs, user, null);
					return user;
				}
			});
			return uList.size() > 0 ? uList.get(0) : null;
		} catch (Exception e)
		{
			throw new RuntimeException("用户登录失败");
		}
	}

	// 查询所有，不要key,默认以方法名+参数值+内容 作为key

	public List<User> getUsers()
	{
		String userName = "";
		try
		{
			String uLoginSql = "select id,username from users where username like  ? ";

			List<User> uList = jdbcTemplate.query(uLoginSql, new Object[]
			{ userName.equals("") ? "%%" : "" }, new RowMapper<User>() {

				@Override
				public User mapRow(ResultSet rs, int arg1) throws SQLException
				{
					User user = new User();
					MySQLAutoInjection.Rs2Vo(rs, user, null);
					return user;
				}
			});
			return uList;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Menu> queryMenuByRoleId(Integer mId)
	{

		String sql = "SELECT m.createtime,m.description,m.id,m.menupid,m.`name`,m.pageurl,m.sortfiled,m.sortfiled,m.type,m.state,m.menupid FROM menu m,re_role_menu  re WHERE re.roleId=? AND re.menuid=m.id";
		return jdbcTemplate.query(sql, new Object[]
		{ mId }, new RowMapper<Menu>() {

			@Override
			public Menu mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				Menu m = new Menu();
				MySQLAutoInjection.Rs2Vo(rs, m, null);
				return m;
			}
		});
	}

	@Override
	public User queryUserByName(String uName)
	{

		String sql = "SELECT u.id, u.username, u.`password`, u.realname, u.email, u.lasttime, u.sex, u.phone, u.address, u.state, u.description, u.age, u.createtime,u.account FROM users AS u WHERE u.username =?";
		List<User> uList = jdbcTemplate.query(sql, new Object[]
		{ uName }, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				User u = new User();
				MySQLAutoInjection.Rs2Vo(rs, u, null);
				return u;
			}
		});
		return uList.size() > 0 ? uList.get(0) : null;
	}

	@Override
	public Boolean saveUser(User user)
	{

		return null;
	}

	/* (non-Javadoc)
	 * @see cn.telling.user.dao.IUserDao#queryUserPagesByAccount(java.lang.String, cn.telling.user.vo.Page)
	 */
	@Override
	public List<User> queryUserPagesByAccount(String account, PageVo page) {
		String sql="select u.id,u.username,u.account,r.name rolename,u.createtime from users u" +
				" left join re_user_role ur on u.id=ur.userid" +
				" left join role r on r.id=ur.roleid" +
				" where 1=1";
		Object params[]=new Object[]{};
		if(!"".equals(StringHelperTools.nvl(account)))
		{
			sql+=" and u.account=?";
			params[0]=account;
		}
		sql+=" order by u.createtime desc";
		
		List<User> userLs = queryByPage(sql, page,params ,new AutoInjectionRowMapper<User>(User.class));
		return userLs;
	}

	@Override
	public ReturnUserVo queryUsers(String account, PageVo page) {
		String sql="select u.id,u.username,u.account,r.name rolename,u.createtime from users u" +
				" left join re_user_role ur on u.id=ur.userid" +
				" left join role r on r.id=ur.roleid" +
				" where 1=1";
		Object params[]=new Object[]{};
		if(!"".equals(StringHelperTools.nvl(account)))
		{
			sql+=" and u.account=?";
			params[0]=account;
		}
		sql+=" order by u.createtime desc";
		List<User> userLs = queryByPage(sql, page,params ,new AutoInjectionRowMapper<User>(User.class));
		ReturnUserVo ruv=new ReturnUserVo();
		ruv.setTotalCount(page.getTotalCount());
		ruv.setUserLs(userLs);
		return ruv;
	}

}
