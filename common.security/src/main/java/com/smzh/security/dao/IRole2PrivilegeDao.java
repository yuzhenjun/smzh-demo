package com.smzh.security.dao;

import java.util.List;

import com.smzh.common.IBaseDao;
import com.smzh.security.entity.Role2Privilege;

/**
 * 权限与角色关系dao接口
 * @author jiachen.tong
 *
 */
public interface IRole2PrivilegeDao extends IBaseDao<Role2Privilege>{

	/**
	 * 在角色上绑定权限
	 * @param roleId
	 * @param privilegeIds
	 */
	public void bind(long roleId,List<Long> privilegeIds);
	
	/**
	 * 根据角色删除权限
	 * @param roleId
	 */
	public void deleteByRoleId(long roleId);
	
	/**
	 * 根据多个角色删除权限
	 * @param roleIds
	 */
	public void deleteByRoleIds(List<Long> roleIds);
	
	/**
	 * 根据角色ID查询角色与权限的关系
	 * @param roleId
	 * @return
	 */
	public List<Role2Privilege> findPrivilegesByRoleId(long roleId);
	
	
}
