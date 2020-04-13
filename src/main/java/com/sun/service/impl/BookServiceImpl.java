package com.sun.service.impl;

import com.sun.bean.Book;
import com.sun.dao.BookDao;
import com.sun.dao.impl.BookDaoImpl;
import com.sun.service.BookService;

import java.util.List;

/**
 * 实现bookService接口
 * @author sun
 */
public class BookServiceImpl implements BookService {
    private BookDao bd=new BookDaoImpl();

    /**
     * 添加图书
     *
     * @param book 存储要添加的book信息
     * @return boolean 返回是否添加成功
     */
    @Override
    public boolean add(Book book) {
        return bd.addBook(book);
    }

    /**
     * 更新图书信息
     *
     * @param book 存储图书信息，根据bookid修改其他属性
     * @return boolean 是否修改成功
     */
    @Override
    public boolean update(Book book) {
        return bd.updateBook(book);
    }

    /**
     * 删除
     *
     * @param book 删除图书信息，根据 book id删除
     * @return Boolean 是否删除成功
     */
    @Override
    public boolean delete(Book book) {
        return bd.delBook(book);
    }

    /**
     * 获取一本图书
     *
     * @param book 按照id获取图书
     * @return Book 返回图书对象
     */
    @Override
    public Book get(Book book) {
        return bd.getBook(book);
    }

    /**
     * 查询所有图书
     *
     * @return 返回一个List<Book>
     */
    @Override
    public List<Book> getAll() {
        return bd.getAllbook();
    }
}
