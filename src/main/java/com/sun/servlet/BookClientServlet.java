package com.sun.servlet;

import com.sun.bean.Page;
import com.sun.service.BookService;
import com.sun.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BookClientServlet extends BaseServlet {

    BookService bookService=new BookServiceImpl();

    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pn=request.getParameter("pn");
        String pz=request.getParameter("pz");
        Page page=bookService.getPage(pn,pz);
        System.out.println("客户端图书");
        System.out.println(page);
        request.setAttribute("page",page);
        //设置一个跳转路径，方便分页审核跳转对应请求
        request.setAttribute("requestPath","/client/BookClientServlet?method=page");
        request.getRequestDispatcher("/pages/book/booklist.jsp").forward(request,response);
    }
}
