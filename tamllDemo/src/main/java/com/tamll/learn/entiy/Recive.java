package com.tamll.learn.entiy;

/**
 * 收货信息实体类
 */
public class Recive {

    //收货信息id
    private Integer user_Recive_Id;

    //收货人姓名
    private String user_Recive_Name;

    //收货人手机号
    private String user_Recive_Phone;

    //收货地址
    private String user_Recive_Address;

    //收货详细地址
    private String user_Recive_Detail_Address;

    //与User的一对多
    private User user;

    public Recive(){}

    public Recive(String user_Recive_Name, String user_Recive_Phone, String user_Recive_Address, String user_Recive_Detail_Address) {
        this.user_Recive_Name = user_Recive_Name;
        this.user_Recive_Phone = user_Recive_Phone;
        this.user_Recive_Address = user_Recive_Address;
        this.user_Recive_Detail_Address = user_Recive_Detail_Address;
    }

    public Integer getUser_Recive_Id() {
        return user_Recive_Id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUser_Recive_Name() {
        return user_Recive_Name;
    }

    public void setUser_Recive_Name(String user_Recive_Name) {
        this.user_Recive_Name = user_Recive_Name;
    }

    public String getUser_Recive_Phone() {
        return user_Recive_Phone;
    }

    public void setUser_Recive_Phone(String user_Recive_Phone) {
        this.user_Recive_Phone = user_Recive_Phone;
    }

    public String getUser_Recive_Address() {
        return user_Recive_Address;
    }

    public void setUser_Recive_Address(String user_Recive_Address) {
        this.user_Recive_Address = user_Recive_Address;
    }

    public String getUser_Recive_Detail_Address() {
        return user_Recive_Detail_Address;
    }

    public void setUser_Recive_Detail_Address(String user_Recive_Detail_Address) {
        this.user_Recive_Detail_Address = user_Recive_Detail_Address;
    }
}
