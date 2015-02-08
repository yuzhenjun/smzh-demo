package com.smzh.security.service;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smzh.security.dao.IUserDao;
import com.smzh.security.entity.DefaultUser;
/**
 * 
 * @author zhenjun
 *
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

	
	@Resource
	private IUserDao userDao;
	
	
	@Transactional
	public void saveOrUpdate(DefaultUser user){
		if(null != user){
			user.setLastModifyTime(new Date());
			if(user.getId() == 0){
				user.setCreateTime(new Date());
				this.userDao.save(user);
			}else{
				this.userDao.update(user);
			}
		}
	}

	
	public boolean isExistLoginName(String loginName, long id) {
		DefaultUser user = this.userDao.findUserByLoginName(loginName);
		return this.isExist(user, id);
	}
	
	public boolean isExistEmail(String email, long id) {
		DefaultUser user = this.userDao.findUserByEmail(email);
		return this.isExist(user, id);
	}

	public boolean isExistPhone(String phone, long id) {
		DefaultUser user = this.userDao.findUserByPhone(phone);
		return this.isExist(user, id);
	}
	
	/**
	 * 判断用户是否存在
	 * @param user 数据库查询出的用户
	 * @param id 需要比较的用户ID
	 * @return
	 */
	private boolean isExist(DefaultUser user,long id){
		if(null != user){
			if(id == 0){
				return true;
			}else if(user.getId() != id){
				return true;
			}
		}
		return false;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * 根据登录查询用户
	 * @param loginName 登录名
	 * @return 
	 */
	public DefaultUser findUserByLoginName(String loginName){
		DefaultUser user = this.userDao.findUserByLoginName(loginName);
		return user;
	}
	
	
	
}
