package cn.telling.web;

import cn.telling.user.vo.Users;

/**
 * CMS线程变量
 */
public class UserThreadVariable
{

	/**
	 * 当前用户线程变量
	 */
	private static ThreadLocal<Users> userUserVariable = new ThreadLocal<Users>();

	/**
	 * 当前站点线程变量
	 */
	private static ThreadLocal<Users> userSiteVariable = new ThreadLocal<Users>();

	/**
	 * 获得当前用户
	 * 
	 * @return
	 */
	public static Users getUser()
	{
		return userUserVariable.get();
	}

	/**
	 * 设置当前用户
	 * 
	 * @param user
	 */
	public static void setUser(Users user)
	{
		userUserVariable.set(user);
	}

	/**
	 * 移除当前用户
	 */
	public static void removeUser()
	{
		userUserVariable.remove();
	}

	/**
	 * 获得当前站点
	 * 
	 * @return
	 */
	public static Users getSite()
	{
		return userSiteVariable.get();
	}

	/**
	 * 设置当前站点
	 * 
	 * @param site
	 */
	public static void setSite(Users site)
	{
		userSiteVariable.set(site);
	}

	/**
	 * 移除当前站点
	 */
	public static void removeSite()
	{
		userSiteVariable.remove();
	}
}
