package com.tamll.learn.entiy;

/**
 * 后台管理员实体类
 */
public class Admin {

    //后台管理员ID
    private Integer admin_Id;

    //后台管理员登录名
    private String admin_Name;

    //后台管理员登录密码
    private String admin_Password;

    //后台管理员昵称
    private String admin_NickName;

    //后台管理员联系方式
    private String admin_Phone;

    //后台管理员邮箱
    private String admin_Email;

    //后台管理员地址
    private String admin_Address;

    //后台管理员员工编号
    private String admin_Emp_Id;

    public Integer getAdmin_Id() {
        return admin_Id;
    }

    public String getAdmin_Name() {
        return admin_Name;
    }

    public void setAdmin_Name(String admin_Name) {
        this.admin_Name = admin_Name;
    }

    public String getAdmin_Address() {
        return admin_Address;
    }

    public void setAdmin_Address(String admin_Address) {
        this.admin_Address = admin_Address;
    }

    public String getAdmin_Password() {
        return admin_Password;
    }

    public void setAdmin_Password(String admin_Password) {
        this.admin_Password = admin_Password;
    }

    public String getAdmin_NickName() {
        return admin_NickName;
    }

    public void setAdmin_NickName(String admin_NickName) {
        this.admin_NickName = admin_NickName;
    }

    public String getAdmin_Phone() {
        return admin_Phone;
    }

    public void setAdmin_Phone(String admin_Phone) {
        this.admin_Phone = admin_Phone;
    }

    public String getAdmin_Email() {
        return admin_Email;
    }

    public void setAdmin_Email(String admin_Email) {
        this.admin_Email = admin_Email;
    }

    public String getAdmin_Emp_Id() {
        return admin_Emp_Id;
    }

    public void setAdmin_Emp_Id(String admin_Emp_Id) {
        this.admin_Emp_Id = admin_Emp_Id;
    }
}
