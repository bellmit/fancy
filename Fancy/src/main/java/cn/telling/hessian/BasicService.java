/**
 * Project Desc:
 * Project Name:java_pattern
 * File Name:BaseService.java
 * Package Name:rmi
 * Date:2015-3-31下午4:55:43
 * Copyright (c) 2015, zhgo116.com All Rights Reserved.
 *
*/
package cn.telling.hessian;

/**
 * ClassName:BaseService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-3-31 下午4:55:43 <br/>
 * @author   操圣
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class BasicService implements BasicAPI
{

	private String _greeting = "Hello java, hello admin";

	public void setGreeting(String greeting)
	{
		_greeting = greeting;
		System.out.println("set greeting success:" + _greeting);
	}

	public String hello()
	{
		return _greeting;
	}

	public User getUser()
	{
		return new User("java", "admin");
	}
}
