package com.tamll.learn.dao;

import com.tamll.learn.entiy.Right;
import com.tamll.learn.entiy.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapping {

    void insert(Role role);

    void setRight(@Param("roleId") Integer roleId,
                  @Param("rightId") Integer rightId);

    Role selectRightsByRoleName(String roleName);

    Role selectRoleByName(String roleName);

    List<Role> selectAllRoles();
}
