package com.smzh.security.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smzh.security.entity.DefaultUser;
import com.smzh.security.service.IUserService;
import com.smzh.security.utils.PasswordUtils;

/**
 * 修改密码
 * 
 * @author zhenjun
 *
 */
@Controller
public class ChangePasswordController {
	private static Logger logger=Logger.getLogger(ChangePasswordController.class);      
	@Resource
	private IUserService userService;

	@RequestMapping(value = "/changePassword")
	public @ResponseBody String changePassword(HttpServletRequest request,Model model) {
		
		String oldpassword = request.getParameter("oldpassword");
		String newpassword = request.getParameter("newpassword");
		String againpassword = request.getParameter("againpassword");
		
		if (StringUtils.isBlank(newpassword)
				|| StringUtils.isBlank(againpassword)) {
			return "密码不能为空";
		} else if (!StringUtils.equals(newpassword, againpassword)) {
			return "密码不一致";
		}
		
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		DefaultUser user = userService.findUserByLoginName(username);
		user.setPassword(oldpassword);
		if (!PasswordUtils.judgedPassword(user)) {
			return "密码错误";
		}
		user.setPassword(newpassword);
		PasswordUtils.encryptPassword(user);
		userService.saveOrUpdate(user);// 更新密码
		logger.info(user.getName()+"["+user.getLoginName()+"]修改了密码");
		return "修改成功";
	}
}
