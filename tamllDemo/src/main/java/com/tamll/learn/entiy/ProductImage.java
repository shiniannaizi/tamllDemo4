package com.tamll.learn.entiy;


/**
 * 商品图片实体类
 */
public class ProductImage {

    //商品图片ID
    private Integer product_Image_Id;

    //商品图片地址
    private String product_Image_Address;

    //与商品的一对多
    private Product product;

    public Integer getProduct_Image_Id() {
        return product_Image_Id;
    }

    public String getProduct_Image_Address() {
        return product_Image_Address;
    }

    public void setProduct_Image_Address(String product_Image_Address) {
        this.product_Image_Address = product_Image_Address;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
