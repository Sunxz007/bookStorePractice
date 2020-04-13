package com.sun.dao;


import com.sun.bean.Book;

import java.util.List;

/**
 * 图书bean与数据库交互的功能接口
 * @author sun
 */
public interface BookDao {

    /**
     * 获取一本图书
     * @param book 存储查询信息的book实例
     * @return 返回查询的一本book实例
     */
    public Book getBook(Book book);

    /**
     * 显示所有的图书
     * @return 返回图书list
     */
    public List<Book> getAllbook();

    /**
     * 添加图书
     * @param book 传入一个book
     * @return boolean 添加成功返回true，不成功false
     */
    public boolean addBook(Book book);

    /**
     * 删除图书
     * @param book 传入一个book类
     * @return boolean 添加成功返回true，不成功false
     */
    public boolean delBook(Book book);

    /**
     * 更新book信息
     * @param book 更新的book数据
     * @return boolean 修改成功返回true，不成功false
     */
    public boolean updateBook(Book book);

    /**
     * 分页查询图书的方法
     * @param index 开始索引
     * @param pageSize 每页的大小
     * @return 返回book对象合集
     */
    public List<Book> getPageList(int index,int pageSize);

    /**
     *
     * 获取书籍的总数
     * @return int 总数
     */
    public int getTotalCount();

}
