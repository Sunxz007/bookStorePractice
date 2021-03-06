package com.sun.dao.impl;

import com.sun.bean.OrderItem;
import com.sun.dao.BaseDao;
import com.sun.dao.OrderItemDao;

import java.util.List;

/**
 * @author sun
 */
public class OrederItemDaoImpl extends BaseDao<OrderItem> implements OrderItemDao {

    /**
     * 获取某个订单所有的订单项目
     *
     * @param orderId 订单的id
     * @return 某个order的包含的所有项目
     */
    @Override
    public List<OrderItem> getOrderItems(String orderId) {
        String sql ="select id ,title,count ,price,total_money as total_price, order_id as orderId from bs_order_item where orderId=?";
        return getBeanList(sql,orderId);
    }

    /**
     * 将数据保存到orderitem
     *
     * @param orderItem 封装了orderitem的数据的
     */
    @Override
    public void saveOrderItem(OrderItem orderItem) {
        String sql="insert into bs_order_item(title,count,price,total_price,order_id) values(?,?,?,?,?)";
        update(sql, orderItem.getTitle(), orderItem.getCount(), orderItem.getPrice(), orderItem.getTotalPrice(), orderItem.getOrderId());
    }

    /**
     * 将数据批量保存到orderitem
     *
     * @param orderItems 封装了orderitem的数据的s
     * @return 受影响的行数
     */
    @Override
    public int saveOrderItems(List<OrderItem> orderItems) {
        String sql="insert into bs_order_item(title,count,price,total_price,order_id) values(?,?,?,?,?)";
        Object[][] objs=new Object[orderItems.size()][];
        int count=0;
        for(OrderItem item :orderItems){
            objs[count]=new Object[]{item.getTitle(),item.getCount(),item.getPrice(),item.getTotalPrice(),item.getOrderId()};
            count++;
        }
        batch(sql,objs);
        return 1;
    }

}
