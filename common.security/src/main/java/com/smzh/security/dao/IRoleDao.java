package com.smzh.security.dao;

import java.util.List;

import com.smzh.common.IBaseDao;
import com.smzh.security.entity.Role;
import com.smzh.util.SearchResult;

/**
 * 角色接口
 * @author zhenjun
 *
 */
public interface IRoleDao extends IBaseDao<Role>{
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
     * 根据我code查询role
     * @param code
     * @return
     */
    public Role findByCode(String code);
    
    
    public List<Role> findAll();
    

    /**
     *查询分页结果
     * @param firstResult
     * @param maxResults
     * @param values
     * @return
     */
    public SearchResult<Role> findRoles(int firstResult,int maxResults);
}
