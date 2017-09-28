package com.tamll.learn.entiy;

import java.util.List;

/**
 * 权限表
 */
public class Right {

    private Integer right_Id;

    private String right_Name;

    private String right_Describe;

    private List<Role> roles;

    public Integer getRight_Id() {
        return right_Id;
    }

    public String getRight_Name() {
        return right_Name;
    }

    public void setRight_Name(String right_Name) {
        this.right_Name = right_Name;
    }

    public String getRight_Describe() {
        return right_Describe;
    }

    public void setRight_Describe(String right_Describe) {
        this.right_Describe = right_Describe;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
