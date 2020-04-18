package com.sun.service;

import com.sun.bean.Cart;
import com.sun.bean.Order;
import com.sun.bean.User;

import java.util.List;

/**
 * 订单的操作
 * @author sun
 */
public interface OrderService {

    /**
     * 结账并保存订单
     * @param cart 购物车的信息
     * @param user 购买的用户
     * @return 返回订单号
     */
    String checkout(Cart cart, User user);

    /**
     * 更新订单状态
     * @param orderId 要更新的订单的id
     * @param status 要更改的订单状态
     */
    void updateStatus(String orderId, String status);

    /**
     * 获取所有订单
     * @return 数据库内所有的订单
     */
    List<Order> getAllOrder();

    /**
     * 获取用户订单
     * @param userId 用户id
     * @return 用户的所有订单的集合
     */
    List<Order> getMyOrder(Integer userId);
}
