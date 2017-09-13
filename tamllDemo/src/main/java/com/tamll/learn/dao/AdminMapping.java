package com.tamll.learn.dao;

import com.tamll.learn.entiy.Admin;

/**
 * 后台管理员数据库接口层
 */
public interface AdminMapping {

    Admin selectAdminByName(String adminName);
}
