package com.tamll.learn.dao;

import com.tamll.learn.entiy.Admin;

public interface AdminMapping {

    Admin selectAdminByName(String adminName);
}
