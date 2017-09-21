package com.tamll.learn.service.impl;

import com.tamll.learn.dao.ProductImageMapping;
import com.tamll.learn.dao.ProductMapping;
import com.tamll.learn.entiy.Product;
import com.tamll.learn.entiy.ProductImage;
import com.tamll.learn.service.ProdImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdImageServiceImpl implements ProdImageService {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private ProductImageMapping productImageMapping;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private ProductMapping productMapping;

    /**
     * 插入一条商品图片信息
     * @param imgurl 商品图片地址
     * @param prodId 商品ID
     */
    public void addProductImages(String imgurl,long prodId){
        Product product = productMapping.selectProductById(prodId);
        ProductImage productImage = new ProductImage();
        productImage.setProduct(product);
        productImage.setProduct_Image_Address(imgurl);
        productImageMapping.insert(productImage);
    }

    /**
     * 通过商品ID获取商品图片列表
     * @param productId 商品ID
     * @return 返回一个商品图片列表
     */
    public List<ProductImage> getProductImageByProductId(long productId){
        return productImageMapping.selectProductImageByProductId(productId);
    }

    /**
     * 通过商品图片ID删除商品图片
     * @param prodImageId 商品图片ID
     */
    public void deleteProductImage(Integer prodImageId){
        productImageMapping.deleteProductImageById(prodImageId);
    }

    /**
     * 通过商品ID删除商品图片
     * @param productId 商品ID
     */
    public void deleteProductImageByProductId(long productId){
        productImageMapping.deleteProductImageByProductId(productId);
    }
}
