package com.smzh.security.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.smzh.security.bean.NodeState;
import com.smzh.security.bean.TreeNode;
import com.smzh.security.entity.DefaultPrivilege;

public interface IPrivilegeService {
	/**
	 * 根据code查询权限
	 * @param code
	 * @return
	 */
	public DefaultPrivilege findByCode(String code);
	
	/**
	 * 根据用户ID查询权限
	 * @param userId
	 * @return
	 */
	public List<DefaultPrivilege> findByUserId(long userId);
	
	/**
	 * 查询角色
	 * @param roleId
	 * @return
	 */
	public List<DefaultPrivilege> findByRoleId(long roleId);
	
	/**
	 * 根据父ID查询权限
	 * @param parentId
	 * @return
	 */
	public List<DefaultPrivilege> findByParentId(long parentId);
	
	
	/**
	 * 根据角色Id查询权限
	 */
	
	public Set<String> findPermissionsByRoleId(long roleId);
	/**
	 * 更根据userID查询用户权限
	 * @param userId
	 * @return
	 */
	public Set<String> findPermissionsByUserId(long userId);
	
	/**
	 * 获取用户的权限map
	 * @param roleId 角色 id
	 * @param parentId 
	 * @return
	 */
	public Map<String, NodeState> findTreeByRoleId(long roleId,long parentId);
	
	/**
	 * 获取权限map
	 * @param parentId
	 * @param treeMap
	 * @return
	 */
	public List<TreeNode> findAllTree(long parentId,Map<String, NodeState> treeMap);
	
	public List<TreeNode> findTree(long roleId,long parentId);
}
