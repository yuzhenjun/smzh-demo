package com.smzh.security.dao;

import java.util.List;

import com.smzh.common.IBaseDao;
import com.smzh.security.entity.User2Role;

/**
 * 用户与角色关系
 * @author zhenjun
 *
 */
public interface IUser2RoleDao extends IBaseDao<User2Role> {

	
	/**
	 * 绑定用户与角色的关系
	 * @param userId
	 * @param roleId
	 */
	public void bind(long userId,List<Long> roleId);
	
	/**
	 * 通过用户Id删除用户与角色的关系
	 * @param userId
	 */
	public void deleteByUserId(long userId);
	
	/**
	 * 通过用户ID与角色ID删除用户与角色的关系
	 * @param userId
	 * @param roleId
	 */
	public void deleteByUserIdAndRoleId(long userId,long roleId);
	
	/**
	 * 通过角色ID查询用户与角色关系
	 * @param roleId
	 * @return
	 */
	public List<User2Role> findByRoleId(long roleId);
	
	/**
	 * 通过角色ids查询用户与角色的关系
	 * @param roleIds
	 * @return
	 */
	public List<User2Role> findByRoleIds(List<Long> roleIds);
	
	/**
	 * 通过用户ID查询角色与用户的关系
	 * @param userId
	 * @return
	 */
	public List<User2Role> findByUserId(long userId);
	
	/**
	 * 通过用户ID和角色ID查询用户与角色的关系
	 * @param userId
	 * @param roleId
	 * @return
	 */
	public User2Role findByUserIdAndRoleId(long userId,long roleId);
}
