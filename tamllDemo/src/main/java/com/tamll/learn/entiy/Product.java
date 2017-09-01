package com.tamll.learn.entiy;

import java.util.Date;
import java.util.List;

/**
 * 商品实体类
 */
public class Product {

    //商品ID
    private long product_Id;

    //商品名称
    private String product_Name;

    //商品价格
    private Double product_Orignal_Price;

    //促销价
    private Double product_Promote_Price;

    //商品库存
    private int product_Stock;

    //更新时间
    private Date product_Update_Date;

    //创建时间
    private Date product_Create_Date;

    //商品简介
    private String product_Subtitle;

    //商品封面
    private String product_First_Image;

    //商品状态
    private Integer product_Status;//0 正常 1 促销 2 下架

    //与分类的一对多关系
    private Category category;

    //与属性值得多对一
    private List<PropertyValue> propertyValues;

    //与图片的多对一
    private List<ProductImage> productImages;

    public long getProduct_Id() {
        return product_Id;
    }

    public void setProduct_Id(long product_Id) {
        this.product_Id = product_Id;
    }

    public String getProduct_Name() {
        return product_Name;
    }

    public void setProduct_Name(String product_Name) {
        this.product_Name = product_Name;
    }

    public Double getProduct_Orignal_Price() {
        return product_Orignal_Price;
    }

    public void setProduct_Orignal_Price(Double product_Orignal_Price) {
        this.product_Orignal_Price = product_Orignal_Price;
    }

    public Double getProduct_Promote_Price() {
        return product_Promote_Price;
    }

    public void setProduct_Promote_Price(Double product_Promote_Price) {
        this.product_Promote_Price = product_Promote_Price;
    }

    public int getProduct_Stock() {
        return product_Stock;
    }

    public void setProduct_Stock(int product_Stock) {
        this.product_Stock = product_Stock;
    }

    public Date getProduct_Update_Date() {
        return product_Update_Date;
    }

    public void setProduct_Update_Date(Date product_Update_Date) {
        this.product_Update_Date = product_Update_Date;
    }

    public Date getProduct_Create_Date() {
        return product_Create_Date;
    }

    public void setProduct_Create_Date(Date product_Create_Date) {
        this.product_Create_Date = product_Create_Date;
    }

    public String getProduct_Subtitle() {
        return product_Subtitle;
    }

    public void setProduct_Subtitle(String product_Subtitle) {
        this.product_Subtitle = product_Subtitle;
    }

    public String getProduct_First_Image() {
        return product_First_Image;
    }

    public void setProduct_First_Image(String product_First_Image) {
        this.product_First_Image = product_First_Image;
    }

    public Integer getProduct_Status() {
        return product_Status;
    }

    public void setProduct_Status(Integer product_Status) {
        this.product_Status = product_Status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<PropertyValue> getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(List<PropertyValue> propertyValues) {
        this.propertyValues = propertyValues;
    }

    public List<ProductImage> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<ProductImage> productImages) {
        this.productImages = productImages;
    }
}
