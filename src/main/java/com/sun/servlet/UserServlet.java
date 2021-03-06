package com.sun.servlet;

import com.google.code.kaptcha.Constants;
import com.sun.bean.User;
import com.sun.service.UserService;
import com.sun.service.impl.UserServiceImpl;
import com.sun.utils.WebUtils;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;



/**
 * 处理所有和用户有关的请求的Servlet implementation class UserServlet
 * 抽取BaseServlet以后
 * UserServlet只需编写相应的处理逻辑即可
 * @author sun
 */
public class UserServlet extends BaseServlet {
    private UserService us=new UserServiceImpl();

    public UserServlet() {
    }

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
        User userBean = WebUtils.param2bean2(request,new User());
        //用户登录，返回登录user信息
        User user = us.login(userBean);
        //将用户存入session域中
        HttpSession session = request.getSession();
        session.setAttribute("user",user);
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
        //获取用户输入的验证码
        String code=request.getParameter("code");
        //获取session中的验证码，kaptcha在生成图片后会在session中写入验证码的值
        String sessionCode = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        System.out.println(sessionCode);
        //验证验证码是否一致
        if (!sessionCode.equals(code)) {
            //验证码不同,返回错误信息
            request.setAttribute("msg","验证码错误");

            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
            return;
        }
        User user = WebUtils.param2bean2(request,new User());
        boolean res = us.register(user);
        if(res){
            //注册成功
            response.sendRedirect("/pages/user/regist_success.jsp");
        }else{
            //登录失败，
            request.setAttribute("msg","注册失败，用户已存在");
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
        }
    }


    protected void logout(HttpServletRequest request, HttpServletResponse response) throws  IOException{
        HttpSession session=request.getSession();
        //销毁session
        session.invalidate();
        //点击登出后返回商城首页
        response.sendRedirect(request.getContextPath()+"/index.jsp");

    }

    /**
     * 检查用户名是否可用
     *
     */
    protected void checkuser(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //获取username
        User user=WebUtils.param2bean2(request,new User());

        boolean b=us.checkUser(user);

        if(b){
            //可以注册
            response.getWriter().write("用户名可以注册");
        }else{
            response.getWriter().write("用户名不可用");
        }
    }
}
