package com.tamll.learn.dao;


import com.tamll.learn.entiy.ProductImage;

import java.util.List;

/**
 * 商品图片数据库操作接口
 */
public interface ProductImageMapping {

    int insert(ProductImage productImage);

    List<ProductImage> selectProductImageByProductId(long productId);

    void deleteProductImageById(Integer productImageId);

    void deleteProductImageByProductId(long productId);
}
