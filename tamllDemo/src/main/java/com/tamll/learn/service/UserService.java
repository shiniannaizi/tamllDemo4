package com.tamll.learn.service;

import com.tamll.learn.dao.UserMapping;
import com.tamll.learn.entiy.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User服务层
 */
@Service
public class UserService {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private UserMapping userMapping;

    /**
     * 通过id获取user
     * @param userId 用户ID
     * @return User 返回一个User对象
     */
    public User getUserById(Integer userId){
        return  userMapping.selectByUserId(userId);
    }

    /**
     * 通过邮箱查询用户
     * @param email 用户邮箱地址
     * @return 用户User
     */
    public User getUserByEmail(String email){
        return userMapping.selectUserByEmail(email);
    }

    /**
     * 通过name获取user
     * @param userName 用户名
     * @return User 返回一个User对象
     */
    public User getUserByName(String userName){
        return userMapping.selectByUserName(userName);
    }

    /**
     * 添加一个User
     * @param userName 用户名
     * @param userSex 用户性别
     * @param userPassword 用户密码
     * @param userAddress 用户地址
     * @param userEmail 用户邮箱
     */
    public void insertUser(String userName,String userSex,String userPassword,String userAddress,String userEmail){
        User user = new User();
        user.setUser_Name(userName);
        user.setUser_Sex(userSex);
        user.setUser_Password(userPassword);
        user.setUser_Address(userAddress);
        user.setUser_Email(userEmail);
        userMapping.insert(user);
    }

    /**
     * User更新
     * @param userId 用户ID
     * @param userSex 用户性别
     * @param userAddress 用户地址
     * @param userEmail 用户邮箱
     */
    public void updateUser(Integer userId,String userSex,
                           String userAddress,String userEmail){
        User user = userMapping.selectByUserId(userId);
        user.setUser_Sex(userSex);
        user.setUser_Address(userAddress);
        user.setUser_Email(userEmail);
        userMapping.updateByUserId(user);
    }

    /**
     * 根据邮箱激活账号
     * @param email 用户邮箱地址
     * @return 激活成功返回一个大于零的值
     */
    public int activeCodeByEmail(String email){
        return userMapping.updateStateByEmail(email);
    }

    /**
     * 用户更新密码
     * @param userId 用户ID
     * @param password 用户密码
     */
    public void userChangePwd(Integer userId,String password){
        User user = userMapping.selectByUserId(userId);
        user.setUser_Password(password);
        userMapping.updateByUserId(user);
    }

    /**
     * 删除User
     * @param userId 用户ID
     */
    public void deleteUser(Integer userId){
        userMapping.deleteByUserId(userId);
    }


}
