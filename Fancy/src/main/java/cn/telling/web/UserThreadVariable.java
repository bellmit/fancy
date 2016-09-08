package cn.telling.web;

import cn.telling.user.vo.User;

/**
 * CMS线程变量
 */
public class UserThreadVariable
{

	/**
	 * 当前用户线程变量
	 */
	private static ThreadLocal<User> userUserVariable = new ThreadLocal<User>();

	/**
	 * 当前站点线程变量
	 */
	private static ThreadLocal<User> userSiteVariable = new ThreadLocal<User>();

	/**
	 * 获得当前用户
	 * 
	 * @return
	 */
	public static User getUser()
	{
		return userUserVariable.get();
	}

	/**
	 * 设置当前用户
	 * 
	 * @param user
	 */
	public static void setUser(User user)
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
	public static User getSite()
	{
		return userSiteVariable.get();
	}

	/**
	 * 设置当前站点
	 * 
	 * @param site
	 */
	public static void setSite(User site)
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
