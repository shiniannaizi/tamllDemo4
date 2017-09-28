package com.tamll.learn.realm;

import com.tamll.learn.entiy.Right;
import com.tamll.learn.entiy.Role;
import com.tamll.learn.entiy.User;
import com.tamll.learn.service.RoleService;
import com.tamll.learn.service.impl.UserServiceImpl;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * shiro权限过滤类
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RoleService roleService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> roles = userService.getRoleByName(userName);
        List<Right> rights = new ArrayList<Right>();
        Set<String> permissions = new HashSet<String>();
        for (String rolename:roles){
            Role role = (Role) roleService.getRightsByRoleName(rolename);
            rights = role.getRights();
        }
        for (Right right:rights){
            permissions.add(right.getRight_Name());
        }
        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        String userName = (String) authenticationToken.getPrincipal();
        User user = userService.getUserByName(userName);
        if (user!=null){
            AuthenticationInfo authcInfo  = new SimpleAuthenticationInfo(user.getUser_Name(),
                    user.getUser_Password(),"myRealm");
            return authcInfo ;
        }else {
            return null;
        }
    }
}
