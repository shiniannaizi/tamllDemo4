package com.tamll.learn.dao;

import com.tamll.learn.entiy.Right;

import java.util.List;

public interface RightMapping {

    void insert(Right right);

    List<Right> selectAllRights();

    Right selectRightByName(String rightName);
}
