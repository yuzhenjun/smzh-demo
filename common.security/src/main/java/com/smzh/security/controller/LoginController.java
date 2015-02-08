package com.smzh.security.controller;


import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 登录通用
 * @author zhenjun
 *
 */
@Controller
@Scope("request")
public class LoginController {

    /**
     *
     */
    private static final String LOGIN_URI = "login/login";

    /**
     * 登陆成功界面
     */
    private static final String LOGIN_SUSS_URI = "main";
    
    private static final String HAS_LOGIN = "redirect:/main";

    /**
     * 进入登陆界面
     * @return
     */
    @RequestMapping("/login")
    public String toLoginPage(HttpServletRequest request, Model model){
		Subject subject = SecurityUtils.getSubject();
		String error = null;
		String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");

		if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
			error = "用户名/密码错误";
		} else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
			error = "用户名/密码错误";
		} else if (ExcessiveAttemptsException.class.getName().equals(exceptionClassName)) {
			error = "登录失败次数过多,请十分钟后登录";
		} else if (LockedAccountException.class.getName().equals(exceptionClassName)) {
			error = "账号被锁定 ,请联系管理员";
		} else if (exceptionClassName != null) {
			error = "登录失败";
		}
		model.addAttribute("error", error);
		if (subject.isAuthenticated()) {
			return HAS_LOGIN;
		}
		return LOGIN_URI;
    }


    /**
     * 登录成功
     * @return
     */
    @RequestMapping("/main")
    public String loginSuss(){
        return LOGIN_SUSS_URI;
    }



}