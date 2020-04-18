package com.sun.service.impl;

import com.sun.bean.Book;
import com.sun.bean.Page;
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

    /**
     * 获取分页数据
     *
     * @param pageNo   前台传入的
     * @param pageSize 前台传入的每页数量
     * @return 返回page模型
     */
    @Override
    public Page<Book> getPage(String pageNo, String pageSize) {

        Page<Book> page = new Page<>();
        //1. 将前台传入的数据转型为int
        //设置默认值，以防转换时报
        int pn= 1;
        //获取page类默认的页面大小
        //设置默认值
        int pz= page.getPageSize();

        try {
            pn = pageNo==null ? pn:Integer.parseInt(pageNo);
            pz = pageSize==null? pz:Integer.parseInt(pageSize);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }


        //2. 设置页面展示数量
        page.setPageSize(pz);
        //3. 获取总记录数
        int totalcount = bd.getTotalCount();
        //设置总页面数
        page.setTotalCount(totalcount);
        //这样可以计算出正确的totalpage
        page.setPageNo(pn);

        //查询分页数据并封装
        List<Book> list=bd.getPageList(page.getIndex(),page.getPageSize());
        page.setPageData(list);
        return page;
    }

    /**
     * 更具价格获取对应的book信息
     *
     * @param minPrice      最低价，默认为0，不低于0
     * @param maxPrice      最高价，不低于最低价
     * @param pageNo    结果起始页
     * @param pageSize 每页结果大小
     * @return 在价格区间内的Book对象的list集合
     */
    @Override
    public Page<Book> getPageByPrice(String minPrice, String maxPrice, String pageNo, String pageSize) {
        //建立page对象封装数据
        Page<Book> page = new Page<>();
        //将servlet传入的字符串数据转为对应对象类型
        double min = 0.0;
        double max = Double.MAX_VALUE;
        int pn = 1;
        int pz = page.getPageSize();
        try {
            min = minPrice==null?min:Double.parseDouble(minPrice);
            max = maxPrice==null ? max:Double.parseDouble(maxPrice);
            pn = pageNo==null ? pn:Integer.parseInt(pageNo);
            pz = pageSize==null? pz:Integer.parseInt(pageSize);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        //封装前台传入的页码数据

        page.setPageSize(pz);
        //获取记录数
        int count = bd.getCountByPrice(min, max);
        //封装记录数
        page.setTotalCount(count);
        //有了总数就可以验证当前页码是否符合要求
        page.setPageNo(pn);
        List<Book> pageByPrice = bd.getPageByPrice(min, max, pn, pz);
        page.setPageData(pageByPrice);

        return page;
    }

    /**
     * 更新书的数量和库存
     *
     * @param bookId 书的id
     * @param stocks 库存信息
     * @param sales  销量信息
     */
    @Override
    public void updateStockAndSales(Integer bookId, Integer stocks, Integer sales) {

    }
}
