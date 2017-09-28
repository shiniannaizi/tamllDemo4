package com.tamll.learn.entiy;

import java.util.Date;

public class Review {

    private Integer review_Id;

    private String review_Content;

    private Date review_Create_Date;

    private User user;

    private Product product;

    public Integer getReview_Id() {
        return review_Id;
    }

    public String getReview_Content() {
        return review_Content;
    }

    public void setReview_Content(String review_Content) {
        this.review_Content = review_Content;
    }

    public Date getReview_Create_Date() {
        return review_Create_Date;
    }

    public void setReview_Create_Date(Date review_Create_Date) {
        this.review_Create_Date = review_Create_Date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
