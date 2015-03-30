/**
 * @title RetryLimitSimpleCredentialsMatcher.java
 * @package com.github.zhangkaitao.shiro.chapter17.credentials
 * @projectName shiro-example-chapter17-server
 * @author yzj
 * @date 2015年3月30日 下午4:34:31
 */
package com.server.oauth2.credentials;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

/**
 * portal登录验证
 */
public class RetryLimitSimpleCredentialsMatcher extends SimpleCredentialsMatcherWapper {
	private Cache<String, AtomicInteger> passwordRetryCache;

	public RetryLimitSimpleCredentialsMatcher(CacheManager cacheManager) {
		passwordRetryCache = cacheManager.getCache("passwordRetryCache");
	}

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String)token.getPrincipal();
        AtomicInteger retryCount = passwordRetryCache.get(username);
        if(retryCount == null) {
            retryCount = new AtomicInteger(0);
            passwordRetryCache.put(username, retryCount);
        }
        if(retryCount.incrementAndGet() > 5) {
            throw new ExcessiveAttemptsException();
        }

        boolean matches = super.doCredentialsMatch(token, info);
        if(matches) {
            passwordRetryCache.remove(username);
        }
        return matches;
	}
}
