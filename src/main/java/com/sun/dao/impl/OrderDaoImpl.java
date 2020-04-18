package com.sun.dao.impl;

import com.sun.bean.Order;

import com.sun.dao.BaseDao;
import com.sun.dao.OrderDao;

import java.util.List;

/**
 * @author sun
 */
public class OrderDaoImpl extends BaseDao<Order> implements OrderDao {

    /**
     * 保存订单
     *
     * @param order 封装了要保存的订单信息的订单对象
     * @return 返回保存的行数
     */
    @Override
    public int saveOrder(Order order) {
        String sql ="insert into bs_order(order_id , create_date , total_money , status , user_id ) values(?,?,?,?,?)";
        return update(sql, order.getOrderId(), order.getCreateDate(), order.getTotalMoney(), order.getStatus(), order.getUserId());
    }

    /**
     * 修改订状态
     *
     * @param order 封装了要更改状态的订单id的订单对象
     * @return 返回成功修改的行数
     */
    @Override
    public int updateStatus(Order order) {
        String sql ="update bs_order set status=? where order_id=?";
        return update(sql ,order.getStatus(),order.getOrderId());
    }

    /**
     * 管理员查出所有订单
     *
     * @return orider对象的列表
     */
    @Override
    public List<Order> getOrderList() {
        String sql ="select  order_id as orderId, create_date as createDate,total_money as totalMoney,status,user_id as userId from bs_order";
        return getBeanList(sql);
    }

    /**
     * 查出某用户的所有订单
     *
     * @param userid 用户id
     * @return 用户的所有订单
     */
    @Override
    public List<Order> getOrderByUserId(Integer userid) {
        String sql ="select  `order_id` as orderId, create_date as createDate,total_money as totalMoney,status,user_id as userId from bs_order where user_id=?";
        return getBeanList(sql,userid);
    }
}
