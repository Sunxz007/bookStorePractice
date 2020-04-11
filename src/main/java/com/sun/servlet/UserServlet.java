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
 * 处理所有和用户有关的请求的Servlet implementation class UserServlet
 * 抽取BaseServlet以后
 * UserServlet只需编写相应的处理逻辑即可
 * @author sun
 */
public class UserServlet extends BaseServlet {
    private UserService us=new UserServiceImpl();

/*    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String method=request.getParameter("method");
        *//* System.out.println(method);
        if ("regist".equals(method)){
            regist(request,response);
        }else if ("login".equals(method)){
            login(request,response);
        }*//*

        //由于user的方法可能会累加起来，为避免一层层的if验证，可以通过反射来调用method对应的方法
        try {
            //通过反射获取请求的方法
            Method method1= this.getClass().getDeclaredMethod(method,HttpServletRequest.class,HttpServletResponse.class);
            //提高方法的权限
            method1.setAccessible(true);
            //调用方法
            method1.invoke(this,request,response);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }*/

    protected void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = us.login(new User(null, username, password, null));
        if(user==null){
            //登录失败 返回登录页面
            request.setAttribute("msg","用户名密码不匹配");
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
        }else {
            request.setAttribute("msg","用户名登录成功");
            response.sendRedirect(request.getContextPath()+"/pages/user/login_success.jsp");
        }
    }
    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        boolean res = us.register(new User(null, username, password, email));
        if(res){
            //注册成功
            response.sendRedirect("/pages/user/regist_success.jsp");
        }else{
            //登录失败，
            request.setAttribute("msg","注册失败，用户已存在");
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
        }
    }

}
