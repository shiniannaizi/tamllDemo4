package com.tamll.learn.entiy;


import java.io.Serializable;

/**
 * 商品属性值实体类
 */
public class PropertyValue implements Serializable{

    //属性值ID
    private Integer property_Value_Id;

    //属性值内容
    private  String property_Value_Value;

    //与属性的一对多
    private Property property;

    //与商品的一对多
    private Product product;

    public Integer getProperty_Value_Id() {
        return property_Value_Id;
    }

    public String getProperty_Value_Value() {
        return property_Value_Value;
    }

    public void setProperty_Value_Value(String property_Value_Value) {
        this.property_Value_Value = property_Value_Value;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
