package com.smzh.security.shiro.filter;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;

import com.smzh.security.service.IUserService;


public class SysUserFilter extends PathMatchingFilter {

	@Resource
	private IUserService userService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        String username = (String)SecurityUtils.getSubject().getPrincipal();
        request.setAttribute("user", userService.findUserByLoginName(username));
        return true;
    }
}
