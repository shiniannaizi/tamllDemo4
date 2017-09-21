package com.tamll.learn.service;

import com.tamll.learn.entiy.Category;
import com.tamll.learn.entiy.Product;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ProductService {

    public void insertProduct(long prodId, String prodName, double prodOP, double prodPP, Integer prodStock,
                              Date prodCD, Date prodUD, String prodSubTitle, String prodFI, Category category,
                              Integer prodStatus);

    public Product getProductById(long productId);

    public List<Product> getAllProduct(Map<String,Object> map);

    public Product getFullProductById(long productId);

    public void updateProductById(long productId,String prodName, double prodOP, double prodPP, Integer prodStock,
                                  Date prodUD, String prodSubTitle, String prodFI ,Integer prodStatus,
                                  Category category);

    public void updateProductById(long productId,String prodName, double prodOP, double prodPP, Integer prodStock,
                                  Date prodUD, String prodSubTitle, Integer prodStatus, Category category);

    public void updateProductStock(Product product);

    public void deleteProductById(long productId);
}
