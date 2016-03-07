/**
 * 
 * Project Name:myweb-service
 * File Name:ShiroRealm.java
 * Package Name:cn.fancy.web
 * Date:2015-7-14
 *
 */

package cn.telling.shirocontroller;

import java.math.BigDecimal;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import cn.telling.bean.Constants;
import cn.telling.role.service.IRoleService;
import cn.telling.user.service.IUserService;
import cn.telling.user.vo.Users;
import cn.telling.utils.LogUtils;
import cn.telling.utils.StringHelperTools;

/**
 * ClassName:ShiroRealm <br/>
 * 
 * @author caosheng
 */
@Component("shiroRealm")
public class ShiroRealm extends AuthorizingRealm {
	@Resource
	private IUserService userService;
	
	@Resource
	private IRoleService roleService;
	
	private static Logger logger = LoggerFactory.getLogger(ShiroRealm.class);
	

	public ShiroRealm()
	{// 访问量小的情况下
		// 认证
		super.setAuthenticationCacheName(Constants.authenticationCacheName);
		super.setAuthenticationCachingEnabled(false); // 如果为true 密码会被缓存
		// 授权
		super.setAuthorizationCacheName(Constants.authorizationCacheName);
		super.setAuthorizationCachingEnabled(false);
		// super.setAuthorizationCachingEnabled(false); //测试的时候先关闭缓存
	}

	/***
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
	{
		// 因为非正常退出，即没有显式调用 SecurityUtils.getSubject().logout()
		// (可能是关闭浏览器，或超时)，但此时缓存依旧存在(principals)，所以会自己跑到授权方法里。
		if (!SecurityUtils.getSubject().isAuthenticated())
		{
			doClearCache(principals);
			SecurityUtils.getSubject().logout();
			return null;
		}
		Users psu = (Users) principals.getPrimaryPrincipal();
		// String userId = (String)
		// principalCollection.fromRealm(getName()).iterator().next();
		BigDecimal userId = psu.getId();
		if (StringHelperTools.nvl(userId).equals(""))
		{
			return null;
		}

		// 添加角色及权限信息
		SimpleAuthorizationInfo sazi = new SimpleAuthorizationInfo();
		try
		{
			sazi.addRoles(roleService.getRolesAsString(userId)); // 获取当前用户所有的角色,
			// //用于依据角色判断权限的shiro过滤器
			Set<String> sp = roleService.getPermissionsAsString(userId);
			sazi.addStringPermissions(sp); // 获取当前用户的所有权限,
			LogUtils.debug("拥有权限:" + sp);
			// 权限就是url,一个url的集合
		} catch (Exception e)
		{
			logger.error(e.getMessage());
		}

		return sazi;
	}

	// /认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException
	{

		UsernamePasswordToken upToken = (UsernamePasswordToken) token;

		// String pwd = new String(upToken.getPassword());
		// if (StringUtils.isNotBlank(pwd))
		// {
		// pwd = DigestUtils.md5Hex(pwd);
		// }

		// 调用业务方法
		Users user = null;
		String userName = upToken.getUsername();
		try
		{
			user = userService.queryUserByName(userName);
		} catch (Exception e)
		{
			logger.error(e.getMessage());
		}

		if (user != null)
		{
			// 要放在作用域中的东西，请在这里进行操作
			// SecurityUtils.getSubject().getSession().setAttribute("c_user",
			// user);
			// byte[] salt = EncodeUtils.decodeHex(user.getSalt());

			// Session session = SecurityUtils.getSubject().getSession(false);
			AuthenticationInfo authinfo = new SimpleAuthenticationInfo(new Users(user), user.getPassword(), getName());
			// Cache<Object, Object> cache =
			// shiroCacheMan ager.getCache(GlobalStatic.authenticationCacheName);
			// cache.put(GlobalStatic.authenticationCacheName+"-"+userName,
			// session.getId());
			return authinfo;
		}
		// 认证没有通过
		return null;
	}

	/**
	 * 设定Password校验.
	 */
	@PostConstruct
	public void initCredentialsMatcher()
	{
		// HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(
		// "Sha384");
		// matcher.setHashIterations(HASH_INTERATIONS);

		setCredentialsMatcher(new CustomCredentialsMatcher());

		// setCredentialsMatcher(matcher);
		// setCredentialsMatcher(new Sha256CredentialsMatcher());

		// setCacheManager(new MemoryConstrainedCacheManager());

		// HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(
		// HASH_ALGORITHMB);
		// matcher.setHashIterations(HASH_INTERATIONS);
		// setCredentialsMatcher(matcher);
	}
}
