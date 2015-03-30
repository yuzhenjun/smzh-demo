package com.server.oauth2.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.server.oauth2.dao.base.BaseDao;
import com.server.oauth2.entity.User;

@Repository
public class UserDaoImpl extends BaseDao<User> implements UserDao {

	@Override
	public void createUser(User user) {
		this.save(user);
	}

	@Override
	public void updateUser(User user) {
		this.update(user);
	}

	@Override
	public void deleteUser(Long userId) {
		User user=findById(userId);
		if(user!=null){
			this.delete(userId);
		}
	}

	@Override
	public User findOne(Long userId) {
		return this.findById(userId);
	}

	@Override
	public List<User> findAll() {
		return this.getAll();
	}

	@Override
	public User findByUsername(String username) {
		String hql="from User where username=?";
		return this.findUnique(hql, username);
	}

}
