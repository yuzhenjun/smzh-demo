package com.smzh.security.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.smzh.common.BaseDao;
import com.smzh.security.entity.DefaultPrivilege;

/**
 * 权限
 * @author zhenjun
 *
 */
@Repository
public class PrivilegeDao extends BaseDao<DefaultPrivilege> implements IPrivilegeDao {

	public DefaultPrivilege findByCode(String code) {
		String hql = "from DefaultPrivilege p where p.code = ?";
		return this.findUnique(hql, code);
	}

	public List<DefaultPrivilege> findByUserId(long userId) {
		String hql = "select distinct p from DefaultPrivilege p,User2Role u2r,Role2Privilege r2p where u2r.userId = ? and u2r.roleId = r2p.roleId and r2p.privilegeId = p.id";
		return this.find(hql, userId);
	}

	public List<DefaultPrivilege> findByRoleId(long roleId) {
		String hql = "select p from DefaultPrivilege p,Role2Privilege r2p where r2p.roleId = ? and r2p.privilegeId = p.id";
		return this.find(hql, roleId);
	}

	public List<DefaultPrivilege> findByParentId(long parentId) {
		String hql = "from DefaultPrivilege p where p.parentId = ?";
		return this.find(hql, parentId);
	}

	public List<DefaultPrivilege> findByRoleIdAndPid(Long roleId, long parentId) {
		String hql = "select p from DefaultPrivilege p ,Role2Privilege r2p where r2p.roleId = ? and p.parentId=? and r2p.privilegeId = p.id";
		return this.find(hql,roleId, parentId);
	}

	
	

	

}
