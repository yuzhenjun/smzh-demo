package com.smzh.security.service;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smzh.beans.SearchResult;
import com.smzh.security.dao.IRoleDao;
import com.smzh.security.entity.Role;

@Service("roleService")
public class RoleService implements IRoleServie {

	@Resource
	private IRoleDao roleDao;

	@Transactional
	public void createRole(Role role) {
		if (role != null) {
			role.setCreateTime(new Date());
			role.setLastModifyTime(new Date());
			roleDao.createRole(role);
		}

	}
	@Transactional
	public void updateRole(Role role) {
		if (role != null){
			roleDao.updateRole(role);
		}
	}

	public void deleteRole(Long roleId) {
		roleDao.deleteRole(roleId);

	}

	public Role findByRoleId(Long roleId) {
		return roleDao.findByRoleId(roleId);
	}

	public List<Role> findByRoleIds(List<Long> roleIds) {
		return roleDao.findByRoleIds(roleIds);
	}
	public List<Role> findByUserId(Long userId) {
		return roleDao.findByUserId(userId);
	}
	public Set<String> findCodeByUserId(Long userId) {
		List<Role> roles=findByUserId(userId);
		Set<String> role=new LinkedHashSet<String>();
		if(roles!=null&&roles.size()>0){
			for(Role r:roles)
				role.add(r.getCode());
		}
		return role;
	}
	public Role findByCode(String code) {
		return roleDao.findByCode(code);
	}
	public List<Role> findAll() {
		return roleDao.findAll();
	}
	public SearchResult<Role> findRoles(int firstResult, int maxResults) {
		SearchResult<Role> searchResult=roleDao.findRoles(firstResult, maxResults);
		return searchResult;
	}

}
