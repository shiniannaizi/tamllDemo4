package com.tamll.learn.service;

import com.tamll.learn.entiy.User;

import java.util.Set;

public interface UserService {

    public User getUserById(Integer userId);

    public User getUserByEmail(String email);

    public User getUserByName(String userName);

    public void insertUser(String userName,String userSex,String userPassword,
                           String userAddress,String userEmail);

    public void updateUser(Integer userId,String userSex,
                           String userAddress,String userEmail);

    public int activeCodeByEmail(String email);

    public void userChangePwd(Integer userId,String password);

    public void deleteUser(Integer userId);

    public User getFullUserById(Integer userId);

    public Set<String> getRoleByName(String userName);
}
