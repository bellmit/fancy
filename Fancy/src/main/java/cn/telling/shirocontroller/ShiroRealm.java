/**
 * 
 * Project Name:myweb-service
 * File Name:ShiroRealm.java
 * Package Name:cn.fancy.web
 * Date:2015-7-14
 *
 */

package cn.telling.shirocontroller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.telling.common.UserUtils;
import cn.telling.common.security.shiro.session.SessionDAO;
import cn.telling.common.uitl.Encodes;
import cn.telling.role.service.IRoleService;
import cn.telling.user.service.IUserService;
import cn.telling.user.vo.User;
import cn.telling.utils.LogUtils;
import cn.telling.utils.StringHelperTools;

/**
 * ClassName:ShiroRealm <br/>
 * 
 * @author caosheng
 */
@Service
public class ShiroRealm extends AuthorizingRealm {
	@Resource
	private IUserService userService;
	
	@Resource
	private IRoleService roleService;
	
	private static Logger logger = LoggerFactory.getLogger(ShiroRealm.class);
	
	@Resource
	SessionDAO sessionDao;
	/**
	 * 获得活动会话
	 * @return
	 */
	public Collection<Session> getActiveSessions(){
		return sessionDao.getActiveSessions(false);
	}
	//public ShiroRealm()
	//{
	    // 访问量小的情况下
		// 认证
		//super.setAuthenticationCacheName(Constants.authenticationCacheName);
		//super.setAuthenticationCachingEnabled(false); // 如果为true 密码会被缓存
		// 授权
		//super.setAuthorizationCacheName(Constants.authorizationCacheName);
		//super.setAuthorizationCachingEnabled(false);
		// super.setAuthorizationCachingEnabled(false); //测试的时候先关闭缓存
	//}

	/**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
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
		User psu = UserUtils.getUser();
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
		// 添加用户权限
		  sazi.addStringPermission("user");
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

	/**
     * 认证回调函数, 登录时调用
     */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException
	{
	  UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		User user = null;
		String userName = upToken.getUsername();
		try
		{
			user = userService.queryUserByName(userName);
		} catch (Exception e)
		{
			logger.error(e.getMessage());
		}
		System.out.println(sessionDao.getActiveSessions(false).size());
		if (user != null)
		{
			// 要放在作用域中的东西，请在这里进行操作
//			Session session = SecurityUtils.getSubject().getSession(false);
			byte[] salt = Encodes.decodeHex(user.getPassword().substring(0,16));
			AuthenticationInfo authinfo = new SimpleAuthenticationInfo(new Principal(user,upToken.isMobileLogin()), user.getPassword().substring(16),ByteSource.Util.bytes(salt),getName());
//			 Cache<Object, Object> cache =shiroCacheMan ager.getCache(GlobalStatic.authenticationCacheName);
//			 cache.put(GlobalStatic.authenticationCacheName+"-"+userName,session.getId());
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

		//setCredentialsMatcher(new CustomCredentialsMatcher());
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher("SHA-1");
		matcher.setHashIterations(1024);
		setCredentialsMatcher(matcher);
		// setCredentialsMatcher(matcher);
		// setCredentialsMatcher(new Sha256CredentialsMatcher());

		// setCacheManager(new MemoryConstrainedCacheManager());

		// HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(
		// HASH_ALGORITHMB);
		// matcher.setHashIterations(HASH_INTERATIONS);
		// setCredentialsMatcher(matcher);
	}
	@Override
    protected void checkPermission(Permission permission, AuthorizationInfo info) {
        authorizationValidate(permission);
        super.checkPermission(permission, info);
    }
    
    @Override
    protected boolean[] isPermitted(List<Permission> permissions, AuthorizationInfo info) {
        if (permissions != null && !permissions.isEmpty()) {
            for (Permission permission : permissions) {
                authorizationValidate(permission);
            }
        }
        return super.isPermitted(permissions, info);
    }
    
    @Override
    public boolean isPermitted(PrincipalCollection principals, Permission permission) {
        authorizationValidate(permission);
        return super.isPermitted(principals, permission);
    }
    
    @Override
    protected boolean isPermittedAll(Collection<Permission> permissions, AuthorizationInfo info) {
        if (permissions != null && !permissions.isEmpty()) {
            for (Permission permission : permissions) {
                authorizationValidate(permission);
            }
        }
        return super.isPermittedAll(permissions, info);
    }
	
	/**
     * 授权验证方法
     * @param permission
     */
    private void authorizationValidate(Permission permission){
        // 模块授权预留接口
    }
	/**
     * 授权用户信息
     */
    public static class Principal implements Serializable {

        private static final long serialVersionUID = 1L;
        
        private BigDecimal id; // 编号
        private String loginName; // 登录名
        private String name; // 姓名
        private boolean mobileLogin; // 是否手机登录
        
//      private Map<String, Object> cacheMap;

        public Principal(User user, boolean mobileLogin) {
            this.id = user.getId();
            this.loginName = user.getUsername();
            this.mobileLogin = mobileLogin;
        }

      
        
        public BigDecimal getId()
        {
          return id;
        }


        
        public void setId(BigDecimal id)
        {
          this.id = id;
        }


        
        public void setLoginName(String loginName)
        {
          this.loginName = loginName;
        }


        
        public void setName(String name)
        {
          this.name = name;
        }


        
        public void setMobileLogin(boolean mobileLogin)
        {
          this.mobileLogin = mobileLogin;
        }


        public String getLoginName() {
            return loginName;
        }

        public String getName() {
            return name;
        }

        public boolean isMobileLogin() {
            return mobileLogin;
        }

//      @JsonIgnore
//      public Map<String, Object> getCacheMap() {
//          if (cacheMap==null){
//              cacheMap = new HashMap<String, Object>();
//          }
//          return cacheMap;
//      }

        /**
         * 获取SESSIONID
         */
        public String getSessionid() {
            try{
                return (String) UserUtils.getSession().getId();
            }catch (Exception e) {
                return "";
            }
        }
    }
}
