package com.tamll.learn.service.impl;

import com.tamll.learn.dao.ReciveMapping;
import com.tamll.learn.dao.UserMapping;
import com.tamll.learn.entiy.Recive;
import com.tamll.learn.entiy.User;
import com.tamll.learn.service.ReciveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 收货信息服务层
 */
@Service
public class ReciveServiceImpl implements ReciveService{

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private ReciveMapping reciveMapping;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private UserMapping userMapping;

    /**
     * 插入一条用户收货信息记录
     * @param userId 用户ID
     * @param user_Recive_Name 收货人姓名
     * @param user_Recive_Phone 收货人手机号
     * @param user_Recive_Address 收货地址
     * @param user_Recive_Detail_Address 详细地址
     */
    public void insertRecive(Integer userId,String user_Recive_Name,String user_Recive_Phone,
                             String user_Recive_Address,String user_Recive_Detail_Address){
        Recive recive = new Recive();
        User user = userMapping.selectByUserId(userId);
        recive.setUser(user);
        recive.setUser_Recive_Name(user_Recive_Name);
        recive.setUser_Recive_Phone(user_Recive_Phone);
        recive.setUser_Recive_Address(user_Recive_Address);
        recive.setUser_Recive_Detail_Address(user_Recive_Detail_Address);
        reciveMapping.insert(recive);
    }

    /**
     * 查询用户所有的收货地址
     * @param userId 用户ID
     * @return 返回一个收货信息列表
     */
    public List<Recive> getAllRecives(Integer userId){
        return reciveMapping.selectListReciveByUserId(userId);
    }

    /**
     * 根据ID获取收货信息
     * @param reciveId 收货信息ID
     * @return 返回一个收货信息对象
     */
    public Recive getReciveById(Integer reciveId){
        return reciveMapping.selectReciveById(reciveId);
    }

    /**
     * 根据ID更新收货信息
     * @param recive_Id 收货信息ID
     * @param user_Recive_Name 收货人姓名
     * @param user_Recive_Phone 收货人联系方式
     * @param user_Recive_Address 收货地址
     * @param user_Recive_Detail_Address 详细地址
     */
    public void updateRecive(Integer recive_Id,String user_Recive_Name,String user_Recive_Phone,
                             String user_Recive_Address,String user_Recive_Detail_Address){
        Recive recive = reciveMapping.selectReciveById(recive_Id);
        recive.setUser_Recive_Name(user_Recive_Name);
        recive.setUser_Recive_Phone(user_Recive_Phone);
        recive.setUser_Recive_Address(user_Recive_Address);
        recive.setUser_Recive_Detail_Address(user_Recive_Detail_Address);
        reciveMapping.updateByReciveId(recive);
    }

    /**
     * 根据ID删除收货信息
     * @param recive_Id 收货信息ID
     */
    public void deleteRecive(Integer recive_Id){
        reciveMapping.deleteByReciveId(recive_Id);
    }
}
