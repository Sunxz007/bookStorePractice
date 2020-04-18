package com.sun.dao;

import com.sun.bean.Order;

import java.util.List;

/**
 * order的dao，实现对数据库表bs_order的修改
 * @author sun
 */
public interface OrderDao {

    /**
     * 保存订单
     * @param order 封装了要保存的订单信息的订单对象
     * @return 返回保存的行数
     */
    int saveOrder(Order order);

    /**
     * 修改订状态
     * @param order 封装了要更改状态的订单id的订单对象
     * @return int 修改的行数
     */
    int updateStatus(Order order);

    /**
     * 管理员查出所有订单
     * @return orider对象的列表
     */
    List<Order> getOrderList();

    /**
     * 查出某用户的所有订单
     * @param userid 用户id
     * @return 用户的所有订单
     */
    List<Order> getOrderByUserId(Integer userid);

}
