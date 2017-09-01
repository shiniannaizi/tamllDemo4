package com.tamll.learn.dao;

import com.tamll.learn.entiy.Product;

import java.util.List;

/**
 * Product数据库操作接口
 */
public interface ProductMapping {

    int insert(Product product);

    Product selectProductById(long productId);

    Product selectFullProductById(long productId);

    List<Product> selectAllProduct();

    int updateProductById(long productId);

    int deleteProductById(long productId);
}
