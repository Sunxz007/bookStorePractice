package com.sun.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 用于所有的Servlet都是通过反射来调用相应的方法，所以可以抽取出一个BaseServlet，子类通过集成方法，就可以调用父类方法来根据method参数进行反射调用
 * @author sun
 */
public class BaseServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method=request.getParameter("method");
        /* System.out.println(method);
        if ("regist".equals(method)){
            regist(request,response);
        }else if ("login".equals(method)){
            login(request,response);
        }*/

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
    }
}