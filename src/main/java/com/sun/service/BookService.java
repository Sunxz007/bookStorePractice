package com.sun.service;

import com.sun.bean.Book;

import java.util.List;

/**
 * @author sun
 */
public interface BookService {
    /**
     * 添加图书
     * @param book 存储要添加的book信息
     * @return boolean 返回是否添加成功
     */
    public boolean add(Book book);

    /**
     * 更新图书信息
     * @param book 存储图书信息，根据bookid修改其他属性
     * @return boolean 是否修改成功
     */
    public boolean update(Book book);

    /**
     * 删除
     * @param book 删除图书信息，根据 book id删除
     * @return Boolean 是否删除成功
     */
    public boolean delete(Book book);

    /**
     * 获取一本图书
     * @param book 按照id获取图书
     * @return Book 返回图书对象
     */
    public Book get(Book book);

    /**
     * 查询所有图书
     * @return 返回一个List<Book>
     */
    public List<Book> getAll();
}
