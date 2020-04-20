package com.sun.filter;

import com.sun.utils.JdbcUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 交易事务过滤器，处理数据库事务时，出现异常则回滚操作，防止异常
 * @author sun
 */
public class TransactionFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig)  {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException {
        //拿到当前线程的连接
        Connection connection = JdbcUtils.getConnection();

        try {
            //没有异常，提交事务
            chain.doFilter(req, resp);
            connection.commit();
        } catch (Exception e) {
            //抓到异常，回滚事务
            System.out.println("filter收到异常了..."+e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            HttpServletResponse repsonse = (HttpServletResponse) resp;
            HttpServletRequest request = (HttpServletRequest) req;
            repsonse.sendRedirect(request.getContextPath()+"/pages/exception.jsp");
        }finally {
            //关闭连接
            JdbcUtils.releaseConnection();
        }



    }


}
