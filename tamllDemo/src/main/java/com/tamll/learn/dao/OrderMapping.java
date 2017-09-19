package com.tamll.learn.dao;

import com.tamll.learn.entiy.Order;

import java.util.List;
import java.util.Map;

/**
 * 订单数据库接口
 */
public interface OrderMapping {

    Order getFullOrderById(Integer orderId);

    Order getFullOrderByNumber(String orderNumber);

    Order getOrderByNumber(String orderNumber);

    List<Order> getOrderByUserId(Integer userId);

    int insert(Order order);

    void updateOrderByNumber(Order order);

    void deleteOrderById(Integer orderId);

    void deleteOrderByNumber(String orderNumber);

    List<Order> selectAllOrder();

    List<Order> selectNoFinOrder(Integer status);

    List<Order> selectPageOrder(Map<String,Object> map);
}
