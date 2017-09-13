package com.tamll.learn.service;

import com.tamll.learn.dao.ProductImageMapping;
import com.tamll.learn.dao.ProductMapping;
import com.tamll.learn.entiy.Product;
import com.tamll.learn.entiy.ProductImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdImageService {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private ProductImageMapping productImageMapping;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private ProductMapping productMapping;

    public void addProductImages(String imgurl,long prodId){
        Product product = productMapping.selectProductById(prodId);
        ProductImage productImage = new ProductImage();
        productImage.setProduct(product);
        productImage.setProduct_Image_Address(imgurl);
        productImageMapping.insert(productImage);
    }

    public List<ProductImage> getProductImageByProductId(long productId){
        return productImageMapping.selectProductImageByProductId(productId);
    }

    public void deleteProductImage(Integer prodImageId){
        productImageMapping.deleteProductImageById(prodImageId);
    }

    public void deleteProductImageByProductId(long productId){
        productImageMapping.deleteProductImageByProductId(productId);
    }
}
