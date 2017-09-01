package com.tamll.learn.filter;

import com.tamll.learn.constant.CommonConstant;
import com.tamll.learn.entiy.User;
import com.tamll.learn.service.UserService;
import com.tamll.learn.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        if (request.getSession().getAttribute(CommonConstant.USER_CONTEXT) == null){
            if (CookieUtils.getCookieValue(request,"autologin",true)!=null){
                String s = CookieUtils.getCookieValue(request,"autologin",true);
                String username = s.split(":")[0];
                String password = s.split(":")[1];
                User user = null;
                try {
                    user = userService.getUserByName(username);
                    if (!password.equals(user.getUser_Password())){
                        System.out.println("密码不正确");
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    throw new RuntimeException("没有这个用户");
                }
                if (user!=null){
                    request.getSession().setAttribute(CommonConstant.USER_CONTEXT,user);
                }
            }
        }
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
