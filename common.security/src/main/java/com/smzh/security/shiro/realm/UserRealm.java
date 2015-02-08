package com.smzh.security.shiro.realm;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.smzh.security.entity.DefaultUser;
import com.smzh.security.service.IPrivilegeService;
import com.smzh.security.service.IRoleServie;
import com.smzh.security.service.IUserService;

/**
 * 
 * 登录验证
 * @author zhenjun
 *
 */
public class UserRealm extends AuthorizingRealm {
	private static Logger logger=Logger.getLogger(UserRealm.class);      
	@Resource
	private IUserService userService;
	@Resource
	private IRoleServie roleService;
	@Resource
	private IPrivilegeService privilegeService;
	/**
	 * 权限信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		logger.info("AuthorizationInfo=====================================");
		 String login_name = (String)principals.getPrimaryPrincipal();
		 DefaultUser user=userService.findUserByLoginName(login_name);
         SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
         if(user!=null){
        	 authorizationInfo.setRoles(roleService.findCodeByUserId(user.getId()));
        	 authorizationInfo.setStringPermissions(privilegeService.findPermissionsByUserId(user.getId()));
         }
		return authorizationInfo;
	}

	 /**
	 * 首先验证账号信息,获取身份验证信息
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		 String login_name = (String)token.getPrincipal();
		 DefaultUser user=userService.findUserByLoginName(login_name);

		if (Boolean.TRUE.equals(user.hasLocked())) {
			throw new LockedAccountException(); // 帐号锁定
		}
		// 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				user.getLoginName(), user.getPasswordEncrypted(), // 加密密码
				ByteSource.Util.bytes(user.getLoginName() + user.getSalt()),// salt=login_name+salt
				getName() // realm name
		);
		return authenticationInfo;
	}
	
    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }
    
    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }

}
