package com.tamll.learn.service;

import com.tamll.learn.dao.AdminMapping;
import com.tamll.learn.entiy.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 后台管理员服务层
 */
@Service
public class AdminService {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private AdminMapping adminMapping;

    /**
     * 通过登录名获取后台管理员
     * @param adminName 后台管理员登录名
     * @return 返回一个后台管理员对象
     */
    public Admin getAdminByName(String adminName){
        return adminMapping.selectAdminByName(adminName);
    }

}
