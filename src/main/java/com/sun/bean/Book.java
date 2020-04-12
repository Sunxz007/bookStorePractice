package com.sun.bean;

public class Book {
    private Integer id;
    /**
     * 书名
     */
    private String title;
    /**
     * 图书作者
     */
    private String author;
    /**
     * 图书价格
     */
    private double price;
    /**
     * 图书销量
     */
    private Integer sales;
    /**
     * 图书库存
     */
    private Integer stock;
    /**
     * 封面路径,可指定默认路径
     */
    private String imgPath="static/img/default.jpg";

    public Book() { }

    public Book(Integer id, String title, String author, double price, Integer sales, Integer stock, String imgPath) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.sales = sales;
        this.stock = stock;
        this.imgPath = imgPath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        if(imgPath != null){
            this.imgPath = imgPath;
        }
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", titile='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", sales=" + sales +
                ", stock=" + stock +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }
}
