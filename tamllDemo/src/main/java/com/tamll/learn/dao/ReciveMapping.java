package com.tamll.learn.dao;

import com.tamll.learn.entiy.Recive;

import java.util.List;

/**
 * Recive的数据库操作接口
 */
public interface ReciveMapping {

    int insert(Recive recive);

    int updateByReciveId(Recive recive);

    int deleteByReciveId(Integer reciveId);

    Recive selectReciveByUserId(Integer userid);

    Recive selectReciveById(Integer reciveId);

    List<Recive> selectListReciveByUserId(Integer userid);
}
