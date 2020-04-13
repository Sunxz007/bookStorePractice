package com.sun.servlet;

import com.sun.bean.Book;
import com.sun.service.BookService;
import com.sun.service.impl.BookServiceImpl;
import com.sun.utils.WebUtils;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * bookServlet 接受来自图书页面的图书处理请求
 * @author sun
 */
public class BookManagerServlet extends BaseServlet {

    private BookService bs= new BookServiceImpl();

    public BookManagerServlet() {
        super();
    }

    /**
     * 获取图书列表请求
     * @param request request请求
     * @param response response 请求
     * @throws ServletException
     * @throws IOException
     */
    protected void list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        //获取book列表集合
        List<Book> bookList = bs.getAll();
        //添加book属性
        request.setAttribute("list",bookList);
        //转发到展示列表
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }

    /**
     * 添加图书
     * @param request request对象
     * @param response response 请求
     */
    protected void add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        //将提交的图书信息封装为一个图书对象
        Book book= WebUtils.param2bean2(request,new Book());
        System.out.println(book);
        //将图书信息保存到数据库
        boolean b = bs.add(book);
            //保存成功，返回列表
            response.sendRedirect(request.getContextPath()+"/admin/BookManagerServlet?method=list");
    }

    /**
     * 删除图书
     * @param request request对象
     * @param response response对象
     * @throws IOException
     */
    protected void delete(HttpServletRequest request,HttpServletResponse response) throws IOException {
        Book book= WebUtils.param2bean2(request,new Book());
        System.out.println(book);
        boolean b=bs.delete(book);
        response.sendRedirect(request.getContextPath()+"/admin/BookManagerServlet?method=list");
    }

    /**
     * 获取数据信息
     * @param request 根据id信息获取书籍
     * @param response response
     * @throws ServletException
     * @throws IOException
     */
    protected void getBook(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        Book book =WebUtils.param2bean2(request,new Book());
        Book b=bs.get(book);
        request.setAttribute("book",b);
        System.out.println(book);
        request.getRequestDispatcher("/pages/manager/book_edit.jsp?").forward(request,response);
    }

    /**
     * 修改图书的信息
     * @param request 根据id修改书籍的其他信息
     * @param response
     * @throws IOException
     */
    protected void update(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //由于添加和修改操作，封装的book id有差别，所以可以直接判断id是否为0来判断是否为修改
        Book book= WebUtils.param2bean2(request,new Book());

        System.out.println(book);
        if(book.getId()==0){
            //添加图书
            bs.add(book);
        }else{
            //修改图书
             bs.update(book);
        }
        response.sendRedirect(request.getContextPath()+"/admin/BookManagerServlet?method=list");
    }
}
