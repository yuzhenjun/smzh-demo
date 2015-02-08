package com.smzh.security.service;

import java.util.List;

import com.smzh.security.entity.User2Role;

/**
 * 用户和角色关系
 * @author zhenjun
 *
 */
public interface IUser2RoleSrevice {

	/**
	 * 绑定用户角色
	 * 
	 * @param userId 用户id
	 * @param roleId 角色id
	 */
	public void bind(long userId, List<Long> roleId);

	/**
	 * 根据userid删除用户绑定角色
	 * 
	 * @param userId
	 */
	public void deleteByUserId(long userId);
	
	/**
	 * 根据userid和roleId删除用户的绑定角色
	 * @param userId
	 * @param roleId
	 */
	public void deleteByUserIdAndRoleId(long userId,long roleId);
	
	
	/**
	 * 根据角色id查询
	 * @param roleId
	 * @return
	 */
	public List<User2Role> findByRoleId(long roleId);
	
	/**
	 * 根据角色ids查询
	 * @param roleId
	 * @return
	 */
	public List<User2Role> findByRoleIds(List<Long> roleIds);
	
	
	/**
	 * 根据用户userId查询查询绑定角色
	 * @param userId
	 * @return
	 */
	public List<User2Role> findByUserId(long userId); 
	
	/**
	 * 
	 */
	public User2Role findByUserIdAndRoleId(long userId,long roleId);
	
}
