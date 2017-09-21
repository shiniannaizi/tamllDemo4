package com.tamll.learn.service;

import com.tamll.learn.entiy.Order;
import com.tamll.learn.entiy.OrderItem;

import java.util.List;

public interface OrderItemService {

    public void insertOrderItem(long productId,Integer userId,Integer productNumber,
                                Order order);

    public List<OrderItem> getOrderItemListByOrderNumber(String orderNumber);

    public void deleteOrderItemByOrderNumber(String orderNumber);
}
