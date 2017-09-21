package com.tamll.learn.service;

import com.tamll.learn.entiy.ProductImage;

import java.util.List;

public interface ProdImageService {

    public void addProductImages(String imgurl,long prodId);

    public List<ProductImage> getProductImageByProductId(long productId);

    public void deleteProductImage(Integer prodImageId);

    public void deleteProductImageByProductId(long productId);
}
