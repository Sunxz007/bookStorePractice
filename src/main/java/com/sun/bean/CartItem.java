package com.sun.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 购物车内的一个图书
 * @author sun
 */
public class CartItem implements Serializable {
    /**
     * 书籍，代表购物车内的同一书籍
     */
    private Book book;

    /**
     * 购买的数量
     */
    private int count;

    /**
     * 商品总价，通过计算得到
     */
    private double totalPrice;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getCount() {
        return count;
    }

    /**
     * 计算加入购物车内一本书的购买总价
     * @return 单一商品的总价
     */
    public double getTotalPrice() {
        //浮点数相加会产生精度问题，一般用bigDecimal来包裹浮点数，进行相加，为保证精度要使用string参数构造器
        BigDecimal price = new BigDecimal(getBook().getPrice() + "");
        BigDecimal count =new BigDecimal(getCount()+"");
        //使用doublevalue转为double
        return price.multiply(count).doubleValue();
    }

    public CartItem() {
    }

    public void setCount(int count) {
        this.count = count;
    }

    public CartItem(Book book, int count) {
        this.book = book;
        this.count = count;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "book=" + book +
                ", count=" + count +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
