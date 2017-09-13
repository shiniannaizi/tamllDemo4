package com.tamll.learn.entiy;

import java.io.Serializable;
import java.util.List;

/**
 * user实体类
 */
public class User implements Serializable{

    //用户id
    private int user_Id;

    //用户名
    private String user_Name;

    //用户性别
    private String user_Sex;

    //用户密码
    private String user_Password;

    //用户地址
    private String user_Address;

    //用户邮箱
    private String user_Email;

    //用户账号状态
    private int state;//0表示未激活,1表示激活

    public List<Recive> getRecives() {
        return recives;
    }

    //与Recive的多对一
    private List<Recive> recives;

    public User(){}

    public User(String user_Name, String user_Password, String user_Address,
                String user_Email,String user_Sex) {
        this.user_Name = user_Name;
        this.user_Password = user_Password;
        this.user_Sex = user_Sex;
        this.user_Address = user_Address;
        this.user_Email = user_Email;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + user_Name + '\'' +
                ", userAddress='" + user_Address + '\'' +
                ", password='" + user_Password + '\'' +
                '}';
    }

    public int getUser_Id() {
        return user_Id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getUser_Name() {
        return user_Name;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    public String getUser_Sex() {
        return user_Sex;
    }

    public void setUser_Sex(String user_Sex) {
        this.user_Sex = user_Sex;
    }

    public String getUser_Password() {
        return user_Password;
    }

    public void setUser_Password(String user_Password) {
        this.user_Password = user_Password;
    }

    public String getUser_Address() {
        return user_Address;
    }

    public void setUser_Address(String user_Address) {
        this.user_Address = user_Address;
    }

    public String getUser_Email() {
        return user_Email;
    }

    public void setUser_Email(String user_Email) {
        this.user_Email = user_Email;
    }

}
