package com.sun.service.impl;

import com.sun.bean.*;
import com.sun.dao.OrderDao;
import com.sun.dao.impl.OrderDaoImpl;
import com.sun.service.BookService;
import com.sun.service.OrderItemService;
import com.sun.service.OrderService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author sun
 */
public class OrderServiceImpl implements OrderService {
    OrderDao orderDao=new OrderDaoImpl();
    OrderItemService orderItemService=new OrderItemServiceImpl();
    BookService bookService=new BookServiceImpl();
    /**
     * 结账并保存订单
     *
     * @param cart 购物车的信息
     * @return 返回订单号
     */
    @Override
    public String checkout(Cart cart, User user) {
        //保存购物车内的信息到orderitem中并一一保存
        //1. 封装订单对象
        Order order = new Order();
        order.setCreateDate(new Date());
        //生成orderid 使用时间戳加用户id的方法
        long millis=System.currentTimeMillis();
        String orderId=millis+""+user.getId();
        order.setOrderId(orderId);
        order.setTotalMoney(cart.getTotalPrice());
        order.setStatus(0);
        order.setUserId(user.getId());
        //将cart中的每项抽取出来封装为orderitem
        List<CartItem> allItems=cart.getAllItems();
        List<OrderItem> orderitems=new ArrayList<>();
        for (CartItem cartItem:allItems){
            //封装一个orderitem
            OrderItem item=new OrderItem(cartItem.getBook().getTitle(),cartItem.getCount(),cartItem.getBook().getPrice(),cartItem.getTotalPrice(),orderId);
           orderitems.add(item);
        }
        //3. 保存订单到数据库
        orderDao.saveOrder(order);
        //4. 保存所有单项
        orderItemService.saveOrederItems(orderitems);

        //5修改书籍库存
        for(CartItem cartItem:allItems){
            //获取详细信息
            Book book = cartItem.getBook();
            //获取购买的数量
            int count=cartItem.getCount();
            //修改数量
            book.setStock(book.getStock()-count);
            book.setSales(book.getSales()+count);

            //更新图书信息
            bookService.update(book);
        }

        return orderId;
    }


    /**
     * 更新订单状态
     *
     * @param orderId  要更新的订单的id
     * @param status 要更改的订单状态
     */
    @Override
    public void updateStatus(String orderId, String status) {
        Order order = new Order();
        order.setOrderId(orderId);
        int statusInt=0;
        try {
            statusInt =Integer.parseInt(status);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        order.setStatus(statusInt);
        orderDao.updateStatus(order);
    }

    /**
     * 获取所有订单
     *
     * @return 数据库内所有的订单
     */
    @Override
    public List<Order> getAllOrder() {
        return orderDao.getOrderList();
    }

    /**
     * 获取用户订单
     *
     * @param userId 用户id
     * @return 用户的所有订单的集合
     */
    @Override
    public List<Order> getMyOrder(Integer userId) {
        return  orderDao.getOrderByUserId(userId);
    }
}
