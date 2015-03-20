package com.smzh.security.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.smzh.beans.SearchResult;
import com.smzh.common.WapperBaseDao;
import com.smzh.security.entity.Role;

/**
 * 角色
 * @author zhenjun
 *
 */
@Repository
public class RoleDao extends WapperBaseDao<Role> implements IRoleDao{

	/**
	 * 创建角色
	 */
	public void createRole(Role role) {
		 this.save(role);
	}

	/**
	 * 更新
	 */
	public void updateRole(Role role) {
		this.update(role);
	}

	public void deleteRole(Long roleId) {
		String hql = "delete from Role r where r.id = ?";
		this.execute(hql, roleId);
	}

	public Role findByRoleId(Long roleId) {
		String hql = "from Role r where r.id =?";
		return this.findUnique(hql, roleId);
	}

	public List<Role> findByRoleIds(List<Long> roleIds) {
		String hql = "from Role r where r.id in (:roleIds)";
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("roleIds", roleIds);
		return this.findByParams(hql, params);
	}

	public List<Role> findByUserId(Long userId) {
		String hql = "select distinct r from User2Role u2r,Role r where u2r.userId = ? and u2r.roleId = r.id ";
		return this.find(hql, userId);
	}

	public Role findByCode(String code) {
		String hql = "from Role r where r.code =?";
		return this.findUnique(hql, code);

	}

	public List<Role> findAll() {
		String hql = "select r from Role r";
		return this.find(hql);
	}

	public SearchResult<Role> findRoles(int firstResult, int maxResults) {
		String hql = "select r from Role r";
		Object []object=null;
		List<Role> list=this.find(hql, firstResult, maxResults,object);
		int count=this.getCount(hql);
		SearchResult<Role> result=new SearchResult<Role>();
		result.setRows(list);
		result.setTotal(count);
		return result;
	}

}
