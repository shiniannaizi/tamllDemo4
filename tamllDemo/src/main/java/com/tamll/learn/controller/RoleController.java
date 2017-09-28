package com.tamll.learn.controller;

import com.tamll.learn.service.RoleService;
import com.tamll.learn.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/management/add/manager")
    public String addManagerPage(Model model){
        model.addAttribute("roles",roleService.getAllRoles());
        return "/back/user/addusermanager";
    }

    @RequestMapping(value = "/management/add/managerinfo")
    public String addManagerInfo(@RequestParam("username") String username,
                                 @RequestParam("password") String password,
                                 @RequestParam("userRole") String userRole,
                                 Model model){
        if (WebUtils.isNull(username)||WebUtils.isNull(password)||WebUtils.isNull(userRole)){
            model.addAttribute("errMsg","输入信息不能为空");
            return "/back/user/addusermanager";
        }
        roleService.insert(username,password,userRole);
        return "redirect:/management/userlist";
    }

    @RequestMapping(value = "/management/rolelist")
    public String allRolesPage(Model model){
        model.addAttribute("roles",roleService.getAllRoles());
        return "/back/user/rolelist";
    }

    @RequestMapping(value = "/management/add/addrole")
    public String addRolePage(Model model){
        model.addAttribute("rights",roleService.getAllRights());
        return "/back/user/addrole";
    }

    @RequestMapping(value = "/management/add/addroleinfo")
    public String addRoleInfo(@RequestParam("roleName") String roleName,
                              @RequestParam("rightName") String rightName){
        roleService.setRole(roleName,rightName);
        return "redirect:/management/rolelist";
    }

    @RequestMapping(value = "/management/add/addright")
    public String addRightPage(){
        return "back/user/addright";
    }

    @RequestMapping(value = "/management/add/addrightinfo")
    public String addRightInfo(@RequestParam("rightName") String rightName,
                               @RequestParam("rightDescribe") String rightDescribe){
        roleService.insertRight(rightName,rightDescribe);
        return "redirect:/management/rolelist";
    }
}
