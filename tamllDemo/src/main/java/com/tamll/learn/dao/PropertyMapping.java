package com.tamll.learn.dao;


import com.tamll.learn.entiy.Property;

import java.util.List;

/**
 * 属性数据库操作接口
 */
public interface PropertyMapping {

    int insert(Property property);

    List<Property> selectPropertyList();

    Property selectPropertyByName(String propertyName);

    int deletePropertyById(Integer propertyId);
}
