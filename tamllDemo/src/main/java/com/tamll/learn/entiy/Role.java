package com.tamll.learn.entiy;

import java.util.List;

/**
 * 用户角色
 */
public class Role {

    private Integer role_Id;

    private String role_Name;

    private List<User> users;

    private List<Right> rights;

    public Integer getRole_Id() {
        return role_Id;
    }

    public String getRole_Name() {
        return role_Name;
    }

    public void setRole_Name(String role_Name) {
        this.role_Name = role_Name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Right> getRights() {
        return rights;
    }

    public void setRights(List<Right> rights) {
        this.rights = rights;
    }
}
