package com.tamll.learn.entiy;

import java.io.Serializable;
import java.util.List;

/**
 * 商品分类实体类
 */
public class Category implements Serializable {

    //分类ID
    private int category_Id;

    //分类名称
    private String category_Name;

    //与商品的多对一
    private List<Product> products;

    public int getCategory_Id() {
        return category_Id;
    }

    public String getCategory_Name() {
        return category_Name;
    }

    public void setCategory_Name(String category_Name) {
        this.category_Name = category_Name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}