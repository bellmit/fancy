/**
 * Project Desc:
 * Project Name:java_pattern
 * File Name:User.java
 * Package Name:hessian
 * Date:2015-3-31下午4:34:55
 * Copyright (c) 2015, zhgo116.com All Rights Reserved.
 *
*/
package cn.telling.hessian;

import java.io.Serializable;

/**
 * ClassName:User <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-3-31 下午4:34:55 <br/>
 * @author   操圣
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class User implements Serializable
{

	/**
	 * serialVersionUID:TODO
	 * @since JDK 1.6
	 */
	private static final long serialVersionUID = -7585327006329929502L;

	String userName = "snoopy";

	String password = "showme";

	public User(String user, String pwd)
	{
		this.userName = user;
		this.password = pwd;
	}

	public String getUserName()
	{
		return userName;
	}

	public String getPassword()
	{
		return password;
	}
}
