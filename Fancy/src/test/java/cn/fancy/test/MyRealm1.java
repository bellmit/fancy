/**
 * 
 * Project Name:myweb-web
 * File Name:MyRealm1.java
 * Package Name:cn.fancy.test
 * Date:2015-7-13
 *
 */

package cn.fancy.test;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

/**
 * ClassName:MyRealm1 <br/>
 * 
 * @author caosheng
 */
public class MyRealm1 implements Realm {

	@Override
	public String getName() {

		return "myrealm1";
	}

	@Override
	public boolean supports(AuthenticationToken token) {

		return token instanceof UsernamePasswordToken;
	}

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		String password = new String((char[]) token.getCredentials());
		if (!"zhang".equals(username)) {
			throw new UnknownAccountException();
		}
		if (!"123".equals(password)) {
			throw new IncorrectCredentialsException();
		}

		return new SimpleAuthenticationInfo(username, password, getName());
	}

}
