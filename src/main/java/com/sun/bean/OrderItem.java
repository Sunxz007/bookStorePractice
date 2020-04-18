package com.sun.bean;

/**
 * orderItem 模型，存储数据库表bs_orderItem 数据表行记录
 * @author sun
 */
public class OrderItem {

    private Integer id;
    /**
     * 书名
     */
    private String title;

    /**
     * 购买数量
     */
    private int count;

    /**
     * 价格
     */
    private double price;
    /**
     * 单品总价
     */
    private double totalPrice;
    /**
     * 所属订单
     */
    private String orderId;

    public OrderItem() {
    }

    public OrderItem(String title, int count, double price, double totalPrice, String orderId) {
        this.title = title;
        this.count = count;
        this.price = price;
        this.totalPrice = totalPrice;
        this.orderId = orderId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "title='" + title + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}
