package com.smzh.security.shiro.credentials;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;

import com.smzh.security.entity.DefaultUser;
import com.smzh.security.service.IUserService;


/**
 * 登录验证<br>
 * 失败五次屏蔽十分钟<br>
 * @author zhenjun
 *
 */
public class RetryHashedCredentialsMatcher extends HashedCredentialsMatcher {  
	@Resource
	private IUserService userService;
	private static Logger logger=Logger.getLogger(RetryHashedCredentialsMatcher.class);
    private Cache<String, AtomicInteger> passwordRetryCache;
    public RetryHashedCredentialsMatcher(CacheManager cacheManager) {
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String)token.getPrincipal();
        logger.info("开始登录验证");
        AtomicInteger retryCount = passwordRetryCache.get(username);
        if(retryCount == null) {
            retryCount = new AtomicInteger(0);
            passwordRetryCache.put(username, retryCount);
        }
        if(retryCount.incrementAndGet() > 5) {
            //登录失败五次
            throw new ExcessiveAttemptsException();
        }
        //进行登录验证
        boolean matches = super.doCredentialsMatch(token, info);
        if(matches) {
            //登录成功清空缓存
         passwordRetryCache.remove(username);
         //更新用户最后登录信息
       	 String login_name = (String)token.getPrincipal();
		 DefaultUser user=userService.findUserByLoginName(login_name);
		 user.setLastLoginTime(new Date());
		 userService.saveOrUpdate(user);
		 
		Session session=SecurityUtils.getSubject().getSession();
		session.setAttribute("user", user);
        }
        return matches;
    }
}