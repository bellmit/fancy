/**
 * 
 * Project Name:myweb-web
 * File Name:TestShiro.java
 * Package Name:cn.fancy.test
 * Date:2015-7-13
 *
 */

package cn.fancy.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

/**
 * ClassName:TestShiro <br/>
 * 
 * @author caosheng
 */
public class TestShiro {
	@Test
	public void test()
	{
		// 1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		// 2、得到SecurityManager实例 并绑定给SecurityUtils
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		// 3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zhang", "1123");

		try
		{
			// 4、登录，即身份验证
			subject.login(token);
		} catch (UnknownAccountException e)

		{
			System.out.println("错误账号");
		} catch (IncorrectCredentialsException e)
		{
			System.out.println("错误的密码");
		} catch (ExpiredCredentialsException e)
		{
			System.out.println("错误的用户名");
		} catch (AuthenticationException e)
		{
			// 5、身份验证失败
			System.out.println("验证失败");
			e.printStackTrace();
		}

		Assert.assertEquals(true, subject.isAuthenticated()); // 断言用户已经登录

		// 6、退出
		subject.logout();
	}
}
