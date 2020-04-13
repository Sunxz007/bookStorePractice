package com.sun.dao.impl;

import com.sun.bean.Book;
import com.sun.dao.BaseDao;
import com.sun.dao.BookDao;

import java.util.List;

/**
 * 实现BookDao的增删改查操作
 * @author sun
 */
public class BookDaoImpl extends BaseDao<Book> implements BookDao {

    /**
     * 获取一本图书
     *
     * @param book 存储查询信息的book实例
     * @return 返回查询的一本book实例
     */
    @Override
    public Book getBook(Book book) {
        String sql="select id ,title,author,price,sales,stock,img_path as imgPath from bs_book where id=?";
        return getBean(sql,book.getId());
    }

    /**
     * 显示所有的图书
     *
     * @return 返回图书的list
     */
    @Override
    public List<Book> getAllbook() {
        String sql ="select id ,title,author,price,sales,stock,img_path as imgPath from bs_book";
        return getBeanList(sql);
    }

    /**
     * 添加图书
     *
     * @param book 传入一个book
     * @return boolean 添加成功返回true，不成功false
     */
    @Override
    public boolean addBook(Book book) {
        String sql ="insert into bs_book(title,author,price,sales,stock,img_path)"+
                "values(?,?,?,?,?,?)";
        int update = update(sql, book.getTitle(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath());
        return update>0;
    }

    /**
     * 删除图书
     *
     * @param book 传入一个book类
     * @return boolean 添加成功返回true，不成功false
     */
    @Override
    public boolean delBook(Book book) {
        String sql="delete from bs_book where id =?";
        int i = update(sql, book.getId());
        return i>0;
    }

    /**
     * 更新book信息
     *
     * @param book 更新的book数据
     * @return boolean 修改成功返回true，不成功false
     */
    @Override
    public boolean updateBook(Book book) {
        String sql="update bs_book set title=?,author=?,price=?,sales=?,stock=?,img_path=? where id =?";
        int i = update(sql, book.getTitle(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath(), book.getId());
        return i>0;
    }

    /**
     * 分页查询图书的方法
     *
     * @param index    开始索引
     * @param pageSize 每页的大小
     * @return 返回book对象合集
     */
    @Override
    public List<Book> getPageList(int index, int pageSize) {

        String sql ="select id ,title,author,price,sales,stock,img_path as imgPath from bs_book limit ?,?";
        return getBeanList(sql,index,pageSize);
    }

    /**
     * 获取书籍的总数
     *
     * @return int 总数
     */
    @Override
    public int getTotalCount() {
        String sql= "select count(*) from bs_book";
        Object value = getSingleValue(sql);

        //查询异常返回0
        int count = 0;
        try {
            count = Integer.parseInt(value.toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return count;
    }
}
