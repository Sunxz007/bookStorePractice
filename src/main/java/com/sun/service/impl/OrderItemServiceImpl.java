package com.sun.service.impl;

import com.sun.bean.OrderItem;
import com.sun.dao.OrderItemDao;
import com.sun.dao.impl.OrederItemDaoImpl;
import com.sun.service.OrderItemService;

import java.util.List;

/**
 *
 * @author sun
 */
public class OrderItemServiceImpl implements OrderItemService {
    OrderItemDao orderItemDao=new OrederItemDaoImpl();

    /**
     * 保存订单项目
     *
     * @param orderItem 封装了订单的单个项目的所有信息
     */
    @Override
    public void saveItem(OrderItem orderItem) {
        orderItemDao.saveOrderItem(orderItem);
    }

    /**
     * 获取订单的所有订单项
     *
     * @param orderId order的id
     * @return 订单的所有项目
     */
    @Override
    public List<OrderItem> getOrderItems(String orderId) {
        return orderItemDao.getOrderItems(orderId);
    }

    /**
     * 批量保存orderItem
     *
     * @param orderItems orderItem的列表
     */
    @Override
    public void saveOrederItems(List<OrderItem> orderItems) {

        for (OrderItem item: orderItems){
            orderItemDao.saveOrderItem(item);
        }
    }
}
