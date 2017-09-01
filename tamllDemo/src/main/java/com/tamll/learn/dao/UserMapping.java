package com.tamll.learn.dao;

import com.tamll.learn.entiy.User;

/**
 * User的数据库操作接口
 */
public interface UserMapping {

    int deleteByUserId(int userId);

    int insert(User user);

    User selectByUserName(String userName);

    User selectByUserId(int userId);

    int updateByUserId(User user);

    User selectUserByEmail(String email);

    int updateStateByEmail(String email);
}
