package com.tamll.learn.controller;

import com.tamll.learn.service.AdminService;
import com.tamll.learn.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/backend/login")
    public String login(){
        return "/adminlogin";
    }

    @RequestMapping(value = "/backend/doLogin")
    public String doLogin(@RequestParam("adminname") String adminname,
                          @RequestParam("password") String password,
                          HttpServletRequest request){
        if (WebUtils.isNull(adminname) || WebUtils.isNull(password)){
            request.setAttribute("errMsg","输入信息不能为空");
            return "/adminlogin";
        }
        if (password.equals(adminService.getAdminByName(adminname).getAdmin_Password())){
            return "forward:/backend/manage.jsp";
        }else {
            request.setAttribute("errMsg","用户名密码错误");
            return "/adminlogin";
        }
    }
}
