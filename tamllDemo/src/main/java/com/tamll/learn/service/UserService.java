package com.tamll.learn.service;

import com.tamll.learn.entiy.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    User getUserById(Integer userId);

    User getUserByEmail(String email);

    User getUserByName(String userName);

    List<User> getFullUserList();

    void insertUser(String userName,String userSex,String userPassword,
                           String userAddress,String userEmail);

    void updateUser(Integer userId,String userSex,
                           String userAddress,String userEmail);

    int activeCodeByEmail(String email);

    void userChangePwd(Integer userId,String password);

    void deleteUser(Integer userId);

    User getFullUserById(Integer userId);

    Set<String> getRoleByName(String userName);
}
