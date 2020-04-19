package com.sun.servlet;


import com.sun.bean.Order;
import com.sun.bean.User;
import com.sun.service.OrderService;
import com.sun.service.impl.OrderServiceImpl;
import com.sun.utils.WebUtils;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


/**
 * @author sun
 */
public class OrderClientServlet extends BaseServlet {
    OrderService orderService=new OrderServiceImpl();

    protected void checkout(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        //验证用户是否登录
        User loginUser= WebUtils.getLoginUser(request);

        //取出存储在session中的购物车内的信息并，进行结算
        String orderId = orderService.checkout(CartServlet.getCart(request), loginUser);
        request.getSession().setAttribute("orderId",orderId);
        response.sendRedirect(request.getContextPath()+"/pages/cart/checkout.jsp");

    }

    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user=WebUtils.getLoginUser(request);
        List<Order> orders = orderService.getMyOrder(user.getId());
        //在request域中存储所有订单
        request.setAttribute("orders",orders);
        request.getRequestDispatcher("/pages/order/order.jsp").forward(request,response);
    }

    protected void receive(HttpServletRequest request, HttpServletResponse response) throws  IOException{
        String orderId = request.getParameter("orderId");
       orderService.updateStatus(orderId,"2");

       response.sendRedirect(request.getHeader("referer") );
    }
}
