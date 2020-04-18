package com.sun.dao;

import com.sun.bean.OrderItem;

import java.util.List;

/**
 * 操作订单项的 dao实现对order内项目的数据的增删改查
 * @author sun
 */
public interface OrderItemDao {

    /**
     * 获取某个订单所有的订单项目
     * @param orderId 订单的id
     * @return 某个order的包含的所有项目
     */
    List <OrderItem> getOrderItems(String orderId);

    /**
     * 将数据保存到orderitem
     * @param orderItem 封装了orderitem的数据的
     * @return 受影响的行数
     */
    int saveOrderItem(OrderItem orderItem);


}
