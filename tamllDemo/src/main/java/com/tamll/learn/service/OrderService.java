package com.tamll.learn.service;

import com.tamll.learn.entiy.Order;
import com.tamll.learn.entiy.Product;
import com.tamll.learn.entiy.User;

import java.util.List;
import java.util.Map;

public interface OrderService {

    public void insertOrder(String orderReciveInfo, Map<Product,Integer> map, User user);

    public List<Order> getAllOrder();

    public List<Order> getOrderListByUserId(Integer userId);

    public Order getOrderByNumber(String orderNumber);

    public void deleteOrderByNumber(String orderNumber);

    public void updateOrderByNumber(Order order);

    public List<Order> getOrderListByStatus(Integer status);

    public List<Order> getPageOrderList(Map<String,Object> map);

    List<Order> getDateOrderList(Integer datesize);
}
