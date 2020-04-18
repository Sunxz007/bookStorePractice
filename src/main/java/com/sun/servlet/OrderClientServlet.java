package com.sun.servlet;

import com.mysql.cj.Session;
import com.sun.bean.Order;
import com.sun.bean.User;
import com.sun.service.OrderService;
import com.sun.service.impl.OrderServiceImpl;
import com.sun.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


public class OrderClientServlet extends BaseServlet {
    OrderService orderService=new OrderServiceImpl();

    protected void checkout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //验证用户是否登录
        User loginUser= WebUtils.getLoginUser(request);
        //如果存在user做说明登录成功
        if(loginUser!=null){
            //取出存储在session中的购物车内的信息并，进行结算
            String orderId = orderService.checkout(CartServlet.getCart(request), loginUser);
            request.getSession().setAttribute("orderId",orderId);
            response.sendRedirect(request.getContextPath()+"/pages/cart/checkout.jsp");
        }else{
            //未登录跳转到登录界面，并提示登录
            request.setAttribute("msg" ,"结算购物车需登录后操作");
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
        }
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
