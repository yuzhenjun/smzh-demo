package com.smzh.security.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smzh.security.dao.IUser2RoleDao;
import com.smzh.security.entity.User2Role;

@Service("user2RoleService")
public class User2RoleService implements IUser2RoleSrevice{
	@Resource
	private IUser2RoleDao user2RoleDao;
	public void bind(long userId, List<Long> roleId) {
		if(roleId!=null&&roleId.size()>0)
			user2RoleDao.bind(userId, roleId);
	}

	public void deleteByUserId(long userId) {
		user2RoleDao.deleteByUserId(userId);
	}

	public void deleteByUserIdAndRoleId(long userId, long roleId) {
		user2RoleDao.deleteByUserIdAndRoleId(userId, roleId);
		
	}

	public List<User2Role> findByRoleId(long roleId) {
		return findByRoleId(roleId);
	}

	public List<User2Role> findByRoleIds(List<Long> roleIds) {
		return user2RoleDao.findByRoleIds(roleIds);
	}

	public List<User2Role> findByUserId(long userId) {
		return user2RoleDao.findByUserId(userId);
	}

	public User2Role findByUserIdAndRoleId(long userId, long roleId) {
		return user2RoleDao.findByUserIdAndRoleId(userId, roleId);
	}

}
