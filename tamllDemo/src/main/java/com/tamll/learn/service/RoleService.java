package com.tamll.learn.service;

import com.tamll.learn.entiy.Right;
import com.tamll.learn.entiy.Role;
import com.tamll.learn.entiy.User;

import java.util.List;

public interface RoleService {

    Role getRightsByRoleName(String roleName);

    List<Role> getAllRoles();

    Role getRoleByName(String roleName);

    void insert(String manName,String manPwd,String roleName);

    void setRole(String roleName,String rightName);

    void insertRight(String rightName,String rightDescribe);

    List<Right> getAllRights();
}
