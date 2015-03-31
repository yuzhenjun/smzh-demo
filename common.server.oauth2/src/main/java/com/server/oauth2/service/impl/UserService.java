package com.server.oauth2.service.impl;

import java.util.List;

import com.server.oauth2.entity.User;

/**
 * 
 */
public interface UserService {
    /**
     * 创建用户
     * @param user
     */
    public void createUser(User user);

    public void updateUser(User user);

    public void deleteUser(Long userId);

    /**
     * 修改密码
     * @param userId
     * @param newPassword
     */
    public void changePassword(Long userId, String newPassword);

    User findOne(Long userId);

    List<User> findAll();

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    public User findByUsername(String username);

}
