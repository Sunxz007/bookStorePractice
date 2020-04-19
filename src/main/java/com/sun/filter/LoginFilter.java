package com.sun.filter;

import com.sun.bean.User;
import com.sun.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("filter 启动");
        //验证用户是否登录，如果登录就放行，未登录跳转登录界面
        User loginUser = WebUtils.getLoginUser((HttpServletRequest) req);
        if(loginUser==null){
            req.setAttribute("msg","此操作需登录后使用");
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else{
            chain.doFilter(req, resp);
        }

}

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
