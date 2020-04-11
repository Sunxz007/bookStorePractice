package com.sun.servlet;

import com.sun.bean.User;
import com.sun.service.UserService;
import com.sun.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author sun
 */
public class LoginServlet extends HttpServlet {

    private UserService us=new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = us.login(new User(null, username, password, null));
        if(user==null){
            //登录失败 返回登录页面
//            request.getRequestDispatcher("/pages/user/login.html").forward(request,response);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("<h1>登录失败<h1>");
        }else {
            response.sendRedirect(request.getContextPath()+"/pages/user/login_success.html");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            doPost(request,response);
    }

}
