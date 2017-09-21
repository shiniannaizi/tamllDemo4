package com.tamll.learn.service.impl;

import com.tamll.learn.dao.PropertyMapping;
import com.tamll.learn.entiy.Property;
import com.tamll.learn.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 属性服务层
 */
@Service
public class PropertyServiceImpl implements PropertyService{

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private PropertyMapping propertyMapping;

    /**
     * 获取所有属性
     * @return 返回属性列表
     */
    public List<Property> getAllProperty(){
        return propertyMapping.selectPropertyList();
    }

    /**
     * 通过名称获取属性
     * @param propertyName 属性名称
     * @return 返回一个属性对象
     */
    public Property getPropertyByName(String propertyName){
        return propertyMapping.selectPropertyByName(propertyName);
    }
}
