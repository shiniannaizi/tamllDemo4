package com.tamll.learn.service;

import com.tamll.learn.dao.OrderItemMapping;
import com.tamll.learn.dao.ProductMapping;
import com.tamll.learn.entiy.Order;
import com.tamll.learn.entiy.OrderItem;
import com.tamll.learn.entiy.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单子项服务层
 */
@Service
public class OrderItemService {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private OrderItemMapping orderItemMapping;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private ProductMapping productMapping;

    /**
     * 插入一个订单子项
     * @param productId 商品ID
     * @param userId 用户ID
     * @param productNumber 商品数目
     * @param order 订单对象
     */
    public void insertOrderItem(long productId,Integer userId,Integer productNumber,
                                Order order){
        Product product = productMapping.selectProductById(productId);
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setOrderItem_User_Id(userId);
        orderItem.setOrderItem_Product_Number(productNumber);
        orderItem.setOrder(order);
        orderItemMapping.insert(orderItem);
    }

    /**
     * 通过订单编号获取订单子项列表
     * @param orderNumber 订单编号
     * @return 返回一个订单子项列表
     */
    public List<OrderItem> getOrderItemListByOrderNumber(String orderNumber){
        return orderItemMapping.selectOrderItemByOrderNumber(orderNumber);
    }

    /**
     * 通过订单编号删除订单子项
     * @param orderNumber 订单编号
     */
    public void deleteOrderItemByOrderNumber(String orderNumber){
        orderItemMapping.deleteOrderItemByOrderNumber(orderNumber);
    }
}
