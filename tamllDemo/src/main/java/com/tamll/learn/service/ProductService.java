package com.tamll.learn.service;

import com.tamll.learn.dao.ProductMapping;
import com.tamll.learn.entiy.Category;
import com.tamll.learn.entiy.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 商品服务层
 */
@Service
public class ProductService {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private ProductMapping productMapping;

    public void insertProduct(long prodId, String prodName, double prodOP, double prodPP, Integer prodStock,
                              Date prodCD, Date prodUD, String prodSubTitle, String prodFI, Category category,
                              Integer prodStatus){
        Product product = new Product();
        product.setProduct_Id(prodId);
        product.setProduct_Name(prodName);
        product.setProduct_Orignal_Price(prodOP);
        product.setProduct_Promote_Price(prodPP);
        product.setProduct_Stock(prodStock);
        product.setProduct_Create_Date(prodCD);
        product.setProduct_Update_Date(prodUD);
        product.setProduct_Subtitle(prodSubTitle);
        product.setProduct_First_Image(prodFI);
        product.setCategory(category);
        product.setProduct_Status(prodStatus);

        productMapping.insert(product);
    }

    public Product getProductById(long productId){
        return productMapping.selectProductById(productId);
    }

    public List<Product> getAllProduct(){
        return productMapping.selectAllProduct();
    }

    public Product getFullProductById(long productId){
        return productMapping.selectFullProductById(productId);
    }
}
