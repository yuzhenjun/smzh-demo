package com.smzh.security.shiro.filter;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.util.WebUtils;



public class UserFilter extends org.apache.shiro.web.filter.authc.UserFilter {
	
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {
		saveRequestAndRedirectToLogin(request, response);
		return false;
	}
	
	@Override
	protected void saveRequestAndRedirectToLogin(ServletRequest request,
			ServletResponse response) throws IOException {
		if(!StringUtils.equals("XMLHttpRequest", WebUtils.toHttp(request).getHeader("X-Requested-With"))){
			saveRequest(request);
		}
		redirectToLogin(request, response);
	}

	@Override
	protected void redirectToLogin(ServletRequest request,
			ServletResponse response) throws IOException {
		if(StringUtils.equals("XMLHttpRequest", WebUtils.toHttp(request).getHeader("X-Requested-With"))){
			HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
			httpServletResponse.setHeader("reload-parent-login", "true");
			throw new RuntimeException("AXJA请求会话超时,需要重新登录");
		}else{
			super.redirectToLogin(request, response);
		}
	}

	
}
