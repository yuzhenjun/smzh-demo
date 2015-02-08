package com.smzh.security.service;

import com.smzh.security.entity.DefaultUser;

public interface IUserService {

	/**
	 * 创建或者更新用户
	 * @param user
	 */
	public void saveOrUpdate(DefaultUser user);
	
	/**
	 * 判断登录名是否存在
	 * @param loginName
	 * @param id
	 * @return
	 */
	public boolean isExistLoginName(String loginName,long id);
	
	/**
	 * 判断邮箱是否存在
	 * @param loginName
	 * @param id
	 * @return
	 */
	public boolean isExistEmail(String email,long id);
	
	/**
	 * 判断电话是否存在
	 * @param phone
	 * @param id
	 * @return
	 */
	public boolean isExistPhone(String phone,long id);
	
	/**
	 * 根据登录查询用户
	 * @param loginName 登录名
	 * @return 
	 */
	public DefaultUser findUserByLoginName(String loginName);
}
