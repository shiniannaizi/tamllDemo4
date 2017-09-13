package com.tamll.learn.dao;

import com.tamll.learn.entiy.Product;

import java.util.List;
import java.util.Map;

/**
 * Product数据库操作接口
 */
public interface ProductMapping {

    int insert(Product product);

    Product selectProductById(long productId);

    Product selectFullProductById(long productId);

    List<Product> selectAllProduct(Map<String,Object> map);

    void updateProductById(Product product);

    void updateProductStockById(Product product);

    void deleteProductById(long productId);
}
