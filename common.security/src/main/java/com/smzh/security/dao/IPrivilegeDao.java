package com.smzh.security.dao;

import java.util.List;

import com.smzh.common.IBaseDao;
import com.smzh.security.entity.DefaultPrivilege;

/**
 * 权限
 * @author zhenjun
 *
 */
public interface IPrivilegeDao extends IBaseDao<DefaultPrivilege> {

	/**
	 * 根据code查询权限
	 * 
	 * @param code
	 * @return
	 */
	public DefaultPrivilege findByCode(String code);

	/**
	 * 根据用户ID查询权限
	 * 
	 * @param userId
	 * @return
	 */
	public List<DefaultPrivilege> findByUserId(long userId);

	/**
	 * 查询角色
	 * 
	 * @param roleId
	 * @return
	 */
	public List<DefaultPrivilege> findByRoleId(long roleId);

	/**
	 * 根据父ID查询权限
	 * 
	 * @param parentId
	 * @return
	 */
	public List<DefaultPrivilege> findByParentId(long parentId);

	public List<DefaultPrivilege> findByRoleIdAndPid(Long roleId, long parentId);


}
