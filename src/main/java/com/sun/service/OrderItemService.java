package com.sun.service;

import com.sun.bean.OrderItem;

import java.util.List;

public interface OrderItemService {

    /**
     * 保存订单项目
     * @param orderItem 封装了订单的单个项目的所有信息
     */
    void saveItem(OrderItem orderItem);

    /**
     * 获取订单的所有订单项
     * @param orderId order的id
     * @return 订单的所有项目
     */
    List<OrderItem> getOrderItems(String orderId);

    /**
     * 批量保存orderItem
     * @param orderItems orderItem的列表
     */
    void saveOrederItems(List<OrderItem> orderItems);
}
