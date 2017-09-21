package com.tamll.learn.service.impl;

import com.tamll.learn.constant.CommonConstant;
import com.tamll.learn.dao.OrderItemMapping;
import com.tamll.learn.dao.OrderMapping;
import com.tamll.learn.dao.ProductMapping;
import com.tamll.learn.entiy.Order;
import com.tamll.learn.entiy.OrderItem;
import com.tamll.learn.entiy.Product;
import com.tamll.learn.entiy.User;
import com.tamll.learn.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 订单服务层
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private OrderMapping orderMapping;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private OrderItemMapping orderItemMapping;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private ProductMapping productMapping;

    /**
     * 插入一条订单信息,同时插入与其关联的订单子项并更新包含的商品库存
     * @param orderReciveInfo 订单收货信息
     * @param map 包含商品信息和商品数量的map
     * @param user 购买的用户
     */
    public void insertOrder(String orderReciveInfo, Map<Product,Integer> map, User user){
        Order order = new Order();
        order.setOrder_Number(UUID.randomUUID().toString());
        double orderTotal = 0;
        int orderTotalNumber = 0;
        OrderItem orderItem = new OrderItem();
        for (Map.Entry<Product,Integer> entry:map.entrySet()){
            Product product = productMapping.selectProductById(entry.getKey().getProduct_Id());
            orderItem.setProduct(product);
            orderItem.setOrderItem_User_Id(user.getUser_Id());
            orderItem.setOrderItem_Product_Number(entry.getValue());
            orderItem.setOrder(order);
            orderItemMapping.insert(orderItem);
            product.setProduct_Stock(product.getProduct_Stock()-entry.getValue());
            productMapping.updateProductStockById(product);
            orderTotal += entry.getKey().getProduct_Orignal_Price()*entry.getValue();
            orderTotalNumber += entry.getValue();
        }
        order.setOrder_Create_Date(new Date());
        order.setOrder_Total(orderTotal);
        order.setOrder_Total_Number(orderTotalNumber);
        order.setOrder_Status(CommonConstant.UN_PAY);
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
     * 删除订单及其关联的订单子项,并更新商品库存
     * @param orderNumber 订单编号
     */
    public void deleteOrderByNumber(String orderNumber){
        List<OrderItem> orderItems = orderItemMapping.selectOrderItemByOrderNumber(orderNumber);
        for (OrderItem orderItem:orderItems){
            Product product = orderItem.getProduct();
            product.setProduct_Stock(product.getProduct_Stock()+orderItem.getOrderItem_Product_Number());
            productMapping.updateProductStockById(product);
        }
        orderItemMapping.deleteOrderItemByOrderNumber(orderNumber);
        orderMapping.deleteOrderByNumber(orderNumber);
    }

    /**
     * 通过订单编号更新订单
     * @param order 订单对象
     */
    public void updateOrderByNumber(Order order){
        orderMapping.updateOrderByNumber(order);
    }

    /**
     * 通过订单状态获取订单列表
     * @param status 订单状态
     * @return 返回一个订单列表
     */
    public List<Order> getOrderListByStatus(Integer status){
        return orderMapping.selectNoFinOrder(status);
    }

    /**
     * 分页查询订单列表
     * @param map 封装了分页查询条件的map
     * @return 返回一个订单列表
     */
    public List<Order> getPageOrderList(Map<String,Object> map){
        return orderMapping.selectPageOrder(map);
    }

    /**
     * 通过时间段获取订单列表
     * @param datesize 时间段
     * @return 返回一个订单列表
     */
    @Override
    public List<Order> getDateOrderList(Integer datesize) {
        return orderMapping.selectDateOrderList(datesize);
    }
}
