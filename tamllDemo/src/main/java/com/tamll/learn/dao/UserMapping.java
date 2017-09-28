package com.tamll.learn.dao;

import com.tamll.learn.entiy.User;

import java.util.List;
import java.util.Set;

/**
 * User的数据库操作接口
 */
public interface UserMapping {

    void deleteByUserId(int userId);

    int insert(User user);

    void insertManager(User user);

    User selectByUserName(String userName);

    User selectByUserId(int userId);

    List<User> selectFullUserList();

    Set<String> selectRoleByName(String userName);

    void updateByUserId(User user);

    User selectUserByEmail(String email);

    int updateStateByEmail(String email);

    User selectRecivesByUserId(int userId);
}
