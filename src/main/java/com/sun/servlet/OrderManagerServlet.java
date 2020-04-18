package com.sun.servlet;

import com.sun.bean.Order;
import com.sun.service.OrderService;
import com.sun.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * 处理管理员的订单相关请求
 * @author sun
 */
public class OrderManagerServlet extends BaseServlet {

    OrderService orderService=new OrderServiceImpl();

    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> list=orderService.getAllOrder();
        //保存到域中
        request.setAttribute("orders",list);
        request.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(request,response);
    }


    /**
     * 订单发货
     * @param request request 请求
     * @param response response 信息
     * @throws IOException 读取错误
     */
    protected void deliver(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //从页面获取订单状态
        String orderId=request.getParameter("orderId");
        //修改状态
        orderService.updateStatus(orderId,"1");
        //回到页面
        response.sendRedirect(request.getHeader("referer"));
    }
}
