package com.tamll.learn.entiy;

/**
 * 订单子项实体类
 */
public class OrderItem {

    //订单子项ID
    private int orderItem_Id;

    //订单子项用户ID
    private int orderItem_User_Id;

    //订单子项商品数目
    private int orderItem_Product_Number;

    //与订单的多对一
    private Order order;

    //保存的商品对象
    private Product product;

    public int getOrderItem_Id() {
        return orderItem_Id;
    }

    public int getOrderItem_User_Id() {
        return orderItem_User_Id;
    }

    public void setOrderItem_User_Id(int orderItem_User_Id) {
        this.orderItem_User_Id = orderItem_User_Id;
    }

    public int getOrderItem_Product_Number() {
        return orderItem_Product_Number;
    }

    public void setOrderItem_Product_Number(int orderItem_Product_Number) {
        this.orderItem_Product_Number = orderItem_Product_Number;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
