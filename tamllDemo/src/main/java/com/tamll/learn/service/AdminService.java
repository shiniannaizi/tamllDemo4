package com.tamll.learn.service;

import com.tamll.learn.dao.AdminMapping;
import com.tamll.learn.entiy.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private AdminMapping adminMapping;

    public Admin getAdminByName(String adminName){
        return adminMapping.selectAdminByName(adminName);
    }

}
