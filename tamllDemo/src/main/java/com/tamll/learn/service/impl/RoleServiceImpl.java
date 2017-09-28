package com.tamll.learn.service.impl;

import com.tamll.learn.dao.RightMapping;
import com.tamll.learn.dao.RoleMapping;
import com.tamll.learn.dao.UserMapping;
import com.tamll.learn.entiy.Right;
import com.tamll.learn.entiy.Role;
import com.tamll.learn.entiy.User;
import com.tamll.learn.service.RoleService;
import com.tamll.learn.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private RoleMapping roleMapping;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private UserMapping userMapping;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private RightMapping rightMapping;

    @Override
    public Role getRightsByRoleName(String roleName) {
        return roleMapping.selectRightsByRoleName(roleName);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleMapping.selectAllRoles();
    }

    @Override
    public Role getRoleByName(String roleName) {
        return roleMapping.selectRoleByName(roleName);
    }

    @Override
    public void insert(String manName,String manPwd,String roleName) {
        User user = new User();
        user.setUser_Name(manName);
        user.setUser_Password(WebUtils.md5(manPwd));
        user.setRole(roleMapping.selectRoleByName(roleName));
        userMapping.insertManager(user);
    }

    @Override
    public void setRole(String roleName, String rightName) {
        Role role = new Role();
        role.setRole_Name(roleName);
        roleMapping.insert(role);
        roleMapping.setRight(roleMapping.selectRoleByName(roleName).getRole_Id(),
                rightMapping.selectRightByName(rightName).getRight_Id());
    }

    @Override
    public void insertRight(String rightName,String rightDescribe) {
        Right right = new Right();
        right.setRight_Name(rightName);
        right.setRight_Describe(rightDescribe);
        rightMapping.insert(right);
    }

    @Override
    public List<Right> getAllRights() {
        return rightMapping.selectAllRights();
    }
}
