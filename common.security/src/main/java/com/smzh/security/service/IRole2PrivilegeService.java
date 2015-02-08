package com.smzh.security.service;

import java.util.List;

import com.smzh.security.entity.Role2Privilege;

/**
 * 角色与权限关系
 * @author zhenjun
 *
 */
public interface IRole2PrivilegeService {
	/**
	 * 给角色绑定权限
	 * @param roleId
	 * @param privilegeIds
	 */
	public void bind(long roleId,List<Long> privilegeIds);
	/**
	 * 删除角色的权限
	 * @param roleId
	 */
	public void deleteByRoleId(long roleId);
	
	/**
	 * 删除多个角色的权限
	 * @param roleIds
	 */
	public void deleteByRoleIds(List<Long> roleIds);
	
	/**
	 * 查询角色用户的权限
	 * @param roleId
	 * @return
	 */
	public List<Role2Privilege> findPrivilegesByRoleId(long roleId);
}
