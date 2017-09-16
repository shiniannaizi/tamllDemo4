package com.tamll.learn.entiy;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 订单实体类
 */
public class Order implements Serializable {

    //订单ID
    private int order_Id;

    //订单编号
    private String order_Number;

    //订单生成日期
    private Date order_Create_Date;

    //订单支付日期
    private Date order_Pay_Date;

    //订单发货人
    private String order_Post_Name;

    //订单发货地址
    private String order_Post_Address;

    //收货时间
    private Date order_Confim_Date;

    //发货时间
    private Date order_Delivery_Date;

    //订单金额
    private double order_Total;

    //订单商品总数
    private int order_Total_Number;

    //订单状态
    private int order_Status;

    //订单收货地址
    private String order_Recive_Info;

    //与订单子项的一对多
    private List<OrderItem> orderItems;

    public int getOrder_Id() {
        return order_Id;
    }

    public String getOrder_Number() {
        return order_Number;
    }

    public void setOrder_Number(String order_Number) {
        this.order_Number = order_Number;
    }

    public Date getOrder_Create_Date() {
        return order_Create_Date;
    }

    public void setOrder_Create_Date(Date order_Create_Date) {
        this.order_Create_Date = order_Create_Date;
    }

    public Date getOrder_Pay_Date() {
        return order_Pay_Date;
    }

    public void setOrder_Pay_Date(Date order_Pay_Date) {
        this.order_Pay_Date = order_Pay_Date;
    }

    public String getOrder_Post_Name() {
        return order_Post_Name;
    }

    public void setOrder_Post_Name(String order_Post_Name) {
        this.order_Post_Name = order_Post_Name;
    }

    public String getOrder_Post_Address() {
        return order_Post_Address;
    }

    public void setOrder_Post_Address(String order_Post_Address) {
        this.order_Post_Address = order_Post_Address;
    }

    public Date getOrder_Confim_Date() {
        return order_Confim_Date;
    }

    public void setOrder_Confim_Date(Date order_Confim_Date) {
        this.order_Confim_Date = order_Confim_Date;
    }

    public Date getOrder_Delivery_Date() {
        return order_Delivery_Date;
    }

    public void setOrder_Delivery_Date(Date order_Delivery_Date) {
        this.order_Delivery_Date = order_Delivery_Date;
    }

    public double getOrder_Total() {
        return order_Total;
    }

    public void setOrder_Total(double order_Total) {
        this.order_Total = order_Total;
    }

    public int getOrder_Total_Number() {
        return order_Total_Number;
    }

    public void setOrder_Total_Number(int order_Total_Number) {
        this.order_Total_Number = order_Total_Number;
    }

    public int getOrder_Status() {
        return order_Status;
    }

    public void setOrder_Status(int order_Status) {
        this.order_Status = order_Status;
    }

    public String getOrder_Recive_Info() {
        return order_Recive_Info;
    }

    public void setOrder_Recive_Info(String order_Recive_Info) {
        this.order_Recive_Info = order_Recive_Info;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
