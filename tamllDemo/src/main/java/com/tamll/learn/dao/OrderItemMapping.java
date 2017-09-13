package com.tamll.learn.dao;

import com.tamll.learn.entiy.OrderItem;

import java.util.List;

/**
 * 订单子项数据库接口
 */
public interface OrderItemMapping {

    int insert(OrderItem orderItem);

    List<OrderItem> selectOrderItemByOrderNumber(String orderNum);

    void deleteOrderItemByOrderNumber(String orderNumber);
}
