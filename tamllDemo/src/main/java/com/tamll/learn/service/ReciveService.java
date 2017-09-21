package com.tamll.learn.service;

import com.tamll.learn.entiy.Recive;

import java.util.List;

public interface ReciveService {

    public void insertRecive(Integer userId,String user_Recive_Name,String user_Recive_Phone,
                             String user_Recive_Address,String user_Recive_Detail_Address);

    public List<Recive> getAllRecives(Integer userId);

    public Recive getReciveById(Integer reciveId);

    public void updateRecive(Integer recive_Id,String user_Recive_Name,String user_Recive_Phone,
                             String user_Recive_Address,String user_Recive_Detail_Address);

    public void deleteRecive(Integer recive_Id);
}
