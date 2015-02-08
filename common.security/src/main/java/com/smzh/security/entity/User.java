package com.smzh.security.entity;

/**
 * 用户功能接口
 * @author zhenjun
 *
 */
public interface User {
	
	/**
	 * 用户是否过期
	 * @return
	 */
	public boolean hasExpirtation();
	
	/**
	 * 用户是否锁定
	 * @return
	 */
	public boolean hasLocked();
	
	

}
