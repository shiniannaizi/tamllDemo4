package com.tamll.learn.service;

import com.tamll.learn.dao.OrderMapping;
import com.tamll.learn.entiy.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 订单服务层
 */
@Service
public class OrderService {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private OrderMapping orderMapping;

    /**
     * 插入一条订单信息
     * @param orderNumber 订单编号
     * @param orderCreateDate 订单生成日期
     * @param orderTotal 订单总金额
     * @param orderTotalNumber 订单商品总数
     * @param orderStatus 订单状态
     * @param orderReciveInfo 订单收货信息
     */
    public void insertOrder(String orderNumber, Date orderCreateDate,double orderTotal,
                            Integer orderTotalNumber,Integer orderStatus,String orderReciveInfo){
        Order order = new Order();
        order.setOrder_Number(orderNumber);
        order.setOrder_Create_Date(orderCreateDate);
        order.setOrder_Total(orderTotal);
        order.setOrder_Total_Number(orderTotalNumber);
        order.setOrder_Status(orderStatus);
        order.setOrder_Recive_Info(orderReciveInfo);
        orderMapping.insert(order);
    }

    /**
     * 查询所有的订单并关联其子项对象
     * @return 返回订单列表
     */
    public List<Order> getAllOrder(){
        return orderMapping.selectAllOrder();
    }

    /**
     * 通过用户ID获取订单列表
     * @param userId 用户ID
     * @return 返回用户的订单列表
     */
    public List<Order> getOrderListByUserId(Integer userId){
        return orderMapping.getOrderByUserId(userId);
    }

    /**
     * 通过订单编号获取订单
     * @param orderNumber 订单编号
     * @return 返回订单对象
     */
    public Order getOrderByNumber(String orderNumber){
        return orderMapping.getOrderByNumber(orderNumber);
    }

    /**
     * 通过订单编号删除订单
     * @param orderNumber 订单编号
     */
    public void deleteOrderByNumber(String orderNumber){
        orderMapping.deleteOrderByNumber(orderNumber);
    }

    /**
     * 通过订单编号更新订单
     * @param order 订单对象
     */
    public void updateOrderByNumber(Order order){
        orderMapping.updateOrderByNumber(order);
    }
}
