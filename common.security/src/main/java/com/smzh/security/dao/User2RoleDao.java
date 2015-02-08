package com.smzh.security.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Repository;

import com.smzh.common.BaseDao;
import com.smzh.security.entity.DefaultUser;
import com.smzh.security.entity.User2Role;

/**
 * 用户与角色关系DAO
 * @author jiachen.tong
 *
 */
@Repository
public class User2RoleDao extends BaseDao<User2Role> implements IUser2RoleDao {

	public void bind(long userId, List<Long> roleIds) {
		// 待删除的关联实体列表
		List<User2Role> user2RolesToDelete = new ArrayList<User2Role>();
		// 待创建的角色 ID 列表，初始为传入的角色 ID 列表
		Set<Long> roleIdsToCreate = new HashSet<Long>(roleIds);
		List<User2Role> user2Roles = this.findByUserId(userId);
		if(null != user2Roles && user2Roles.size() > 0){
			// 遍历所有关联实体
			for (User2Role user2Role : user2Roles) {
				Long roleId = user2Role.getRoleId();
				if (roleIdsToCreate.contains(roleId)) {
					// 保留关联实体，将角色 ID 从待创建的角色 ID 列表中移除
					roleIdsToCreate.remove(roleId);
				} else {
					// 删除关联实体，添加到待删除的关联实体列表
					user2RolesToDelete.add(user2Role);
				}
			}
		}
		
		getHibernateTemplate().deleteAll(user2RolesToDelete);
		DefaultUser user = (DefaultUser) SecurityUtils.getSubject().getSession().getAttribute("user");
		for (Long roleId : roleIdsToCreate) {
			User2Role user2Role = new User2Role();
			user2Role.setUserId(userId);
			user2Role.setRoleId(roleId);
			user2Role.setCreator(user.getId());
			user2Role.setCreateTime(new Date());
			save(user2Role);
		}
	}

	public void deleteByUserId(long userId) {
		String hql = "delete from User2Role u2r where u2r.userId = ?";
		this.execute(hql, userId);
	}

	public void deleteByUserIdAndRoleId(long userId, long roleId) {
		String hql = "delete from User2Role u2r where u2r.userId = ? and u2r.roleId = ?";
		this.execute(hql, userId,roleId);
	}

	public List<User2Role> findByRoleId(long roleId) {
		String hql = "from User2Role u2r where u2r.roleId = ?";
		return this.find(hql, roleId);
	}

	public List<User2Role> findByRoleIds(List<Long> roleIds) {
		String hql = "from User2Role u2r where u2r.roleId in (:roleIds)";
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("roleIds", roleIds);
		return this.findByParams(hql, params);
	}

	public List<User2Role> findByUserId(long userId) {
		String hql = "from User2Role u2r where u2r.userId  = ?";
		return this.find(hql, userId);
	}

	public User2Role findByUserIdAndRoleId(long userId, long roleId) {
		String hql = "from User2Role u2r where u2r.userId = ? and u2r.roleId = ?";
		return this.findUnique(hql, userId,roleId);
	}

	

}
