package com.tamll.learn.controller;

import com.tamll.learn.constant.CommonConstant;
import com.tamll.learn.service.AdminService;
import com.tamll.learn.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * 后台管理员控制层
 */
@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 后台管理员登录页面
     * @return 转向管理员登录页
     */
    @RequestMapping(value = "/backend/login")
    public String login(){
        return "/adminlogin";
    }

    /**
     * 后台管理员登录
     * @param adminname 管理员登录名
     * @param password 管理员登录密码
     * @param request 请求参数 向前台返回错误信息
     * @return 验证通过,转向后台管理页面,否则,转向管理员登录页
     */
    @RequestMapping(value = "/backend/doLogin")
    public String doLogin(@RequestParam("adminname") String adminname,
                          @RequestParam("password") String password,
                          HttpServletRequest request){
        if (WebUtils.isNull(adminname) || WebUtils.isNull(password)){
            request.setAttribute("errMsg","输入信息不能为空");
            return "/adminlogin";
        }
        if (password.equals(adminService.getAdminByName(adminname).getAdmin_Password())){
            request.getSession().setAttribute(CommonConstant.ADMIN_CONTEXT,
                    adminService.getAdminByName(adminname));
            return "forward:/backend/manage.jsp";
        }else {
            request.setAttribute("errMsg","用户名密码错误");
            return "/adminlogin";
        }
    }
}
