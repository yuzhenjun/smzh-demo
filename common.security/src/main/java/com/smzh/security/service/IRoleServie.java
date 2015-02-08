package com.smzh.security.service;

import java.util.List;
import java.util.Set;

import com.smzh.security.entity.Role;
import com.smzh.util.SearchResult;

/**
 *角色service
 * @author zhenjun
 *
 */
public interface IRoleServie {
	/**
	 * 创建角色
	 * @param role
	 * @return
	 */
    public void createRole(Role role);
   /**
    * 更新角色
    * @param role
    * @return
    */
    public void updateRole(Role role);
    /**
     * 删除角色
     * @param roleId
     */
    public void deleteRole(Long roleId);

    /**
     * 查询角色
     * @param roleId
     * @return
     */
    public Role findByRoleId(Long roleId);
    
    public List<Role> findByRoleIds(List<Long> roleIds);
    
    /**
     * 根据userId查询角色
     * @param userId
     * @return
     */
    public List<Role> findByUserId(Long userId);
    
    /**
     * 根据用户id查询角色标识
     * @param userId
     * @return
     */
    public Set<String> findCodeByUserId(Long userId);
    
    
    public Role findByCode(String code);
    
    public List<Role> findAll();
    
    
    public SearchResult<Role> findRoles(int firstResult, int maxResults);
    
}
