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
    Book getBook(Book book);

    /**
     * 显示所有的图书
     * @return 返回图书list
     */
    List<Book> getAllbook();

    /**
     * 添加图书
     * @param book 传入一个book
     * @return boolean 添加成功返回true，不成功false
     */
    boolean addBook(Book book);

    /**
     * 删除图书
     * @param book 传入一个book类
     * @return boolean 添加成功返回true，不成功false
     */
    boolean delBook(Book book);

    /**
     * 更新book信息
     * @param book 更新的book数据
     * @return boolean 修改成功返回true，不成功false
     */
    boolean updateBook(Book book);

    /**
     * 分页查询图书的方法
     * @param index 开始索引
     * @param pageSize 每页的大小
     * @return 返回book对象合集
     */
    List<Book> getPageList(int index,int pageSize);

    /**
     *
     * 获取书籍的总数
     * @return int 总数
     */
    int getTotalCount();

    /**
     * 更具价格获取对应的book信息
     * @param min 最低价，默认为0，不低于0
     * @param max 最高价，不低于最低价
     * @param index 结果起始页
     * @param pagesize 每页结果大小
     * @return 在价格区间内的Book对象的list集合
     */
    List<Book> getPageByPrice(double min,double max,int index ,int pagesize);

    /**
     * 根据价格区间获取对应的书籍数量
     * @param min 最高价
     * @param max 最低价
     * @return 价格区间内的书籍数量
     */
    int getCountByPrice(double min,double max);
}
