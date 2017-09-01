package com.tamll.learn.entiy;

import java.util.List;

/**
 * 商品属性实体类
 */
public class Property {

    //属性ID
    private Integer property_Id;

    //属性名
    private String property_Name;

    //与属性值的多对一
    private List<PropertyValue> propertyValues;

    public Integer getProperty_Id() {
        return property_Id;
    }

    public String getProperty_Name() {
        return property_Name;
    }

    public void setProperty_Name(String property_Name) {
        this.property_Name = property_Name;
    }
}
