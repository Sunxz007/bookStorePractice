package com.sun.service;

import com.sun.bean.Book;
import com.sun.bean.Page;

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
    boolean add(Book book);

    /**
     * 更新图书信息
     * @param book 存储图书信息，根据bookid修改其他属性
     * @return boolean 是否修改成功
     */
    boolean update(Book book);

    /**
     * 删除
     * @param book 删除图书信息，根据 book id删除
     * @return Boolean 是否删除成功
     */
    boolean delete(Book book);

    /**
     * 获取一本图书
     * @param book 按照id获取图书
     * @return Book 返回图书对象
     */
    Book get(Book book);

    /**
     * 查询所有图书
     * @return 返回一个List<Book>
     */
    List<Book> getAll();

    /**
     * 获取分页数据
     * @param pageNo 前台传入的
     * @param pageSize 前台传入的每页数量
     * @return 返回page模型
     */
    Page<Book> getPage(String pageNo,String pageSize);

    /**
     * 更具价格获取对应的book信息
     * @param min 最低价，默认为0，不低于0
     * @param max 最高价，不低于最低价
     * @param pageNo 结果起始页
     * @param pagesize 每页结果大小
     * @return 在价格区间内的Book对象的list集合
     */
    Page<Book> getPageByPrice(String min, String max, String pageNo, String pagesize);

    /**
     * 更新书的数量和库存
     * @param bookId 书的id
     * @param stocks 库存信息
     * @param sales 销量信息
     */
    void updateStockAndSales(Integer bookId,Integer stocks,Integer sales);

}
