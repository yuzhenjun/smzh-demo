package com.smzh.security.dao;

import com.smzh.common.IBaseDao;
import com.smzh.security.entity.DefaultUser;

/**
 * 用户信息
 * @author zhenjun
 *
 */
public interface IUserDao extends IBaseDao<DefaultUser>{

	
	
	
	/**
	 * 根据用户名查找用户
	 * @param userName
	 * @return
	 */
	public DefaultUser findUserByLoginName(String loginName);
	
	
	/**
	 * 根据Email查找用户
	 * @param email
	 * @return
	 */
	public DefaultUser findUserByEmail(String email);
	
	/**
	 * 根据电话查找用户
	 * @param phone
	 * @return
	 */
	public DefaultUser findUserByPhone(String phone);
}
