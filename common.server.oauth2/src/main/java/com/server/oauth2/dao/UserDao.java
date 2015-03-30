/**
 * @title UserDao.java
 * @package com.server.oauth2.dao
 * @projectName common.server.oauth2
 * @author yzj
 * @date 2015年3月30日 下午8:19:24
 */
package com.server.oauth2.dao;

import java.util.List;

import com.server.oauth2.entity.User;


public interface UserDao {
	public void createUser(User user);

	public void updateUser(User user);

	public void deleteUser(Long userId);

	User findOne(Long userId);

	List<User> findAll();

	User findByUsername(String username);

}
