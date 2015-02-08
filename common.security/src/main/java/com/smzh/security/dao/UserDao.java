package com.smzh.security.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.smzh.common.BaseDao;
import com.smzh.security.entity.DefaultUser;

/**
 * 用户
 * @author zhenjun
 *
 */
@Repository
public class UserDao extends BaseDao<DefaultUser> implements IUserDao {


	public DefaultUser findUserByLoginName(String loginName) {
		String hql ="from DefaultUser where loginName = ?";
		List<DefaultUser> users =  this.find(hql, loginName);
		if(null != users && users.size() == 1){
			return users.get(0);
		}else{
			return null;
		}
	}


	public DefaultUser findUserByEmail(String email) {
		String hql ="from DefaultUser where email = ?";
		List<DefaultUser> users =  this.find(hql, email);
		if(null != users && users.size() == 1){
			return users.get(0);
		}else{
			return null;
		}
	}


	public DefaultUser findUserByPhone(String phone) {
		String hql ="from DefaultUser where phone = ?";
		List<DefaultUser> users =  this.find(hql, phone);
		if(null != users && users.size() == 1){
			return users.get(0);
		}else{
			return null;
		}
	}
}
