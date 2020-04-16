package com.sun.bean;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 购物车类,保存购物车信息，封装了购物车的操作方法
 * @author sun
 */
public class Cart implements Serializable {
    /**
     * 购物车内的商品集合
     */
    private Map<Integer,CartItem> items=new LinkedHashMap<>();



    /**
     * 总数量，计算得出
     */
    private int totalCount;

    /**
     * 购物车总金额
     */
    private double totalPrice;



    /**
     * 获取购物车内的商品总数
     * @return 购物车内的商品总数
     */
    public int getTotalCount() {
        //获取购物车所有商品
        List<CartItem> items = getAllItems();
        //遍历累加购物车内每一项的数量
        int count =0;
        for (CartItem cartItem:items){
            count += cartItem.getCount();
        }
        return count;
    }

    /**
     * 获取购物车内的商品总价
     * @return 购物车内的商品总价
     */
    public double getTotalPrice() {
        //获取购物车所有商品
        List<CartItem> items = getAllItems();
        //遍历累加购物车内每一项的总价
        BigDecimal totalPrice=new BigDecimal("0.0");
        for (CartItem cartItem:items){
            totalPrice=totalPrice.add(new BigDecimal(cartItem.getTotalPrice()+""));
        }
        return totalPrice.doubleValue();

    }



    /**
     * 获取所有的购物项
     * @return 购物项的集合
     */
    public List<CartItem> getAllItems(){
        return new ArrayList<>(items.values());

    }

    /**
     * 添加图书到购物车
     * @param book 要添加的图书
     */
    public void addBook2Cart(Book book){
        //判断当前购物车内有无当前图书，有就加1
        CartItem item = items.get(book.getId());
        if (item==null){
            //没有就添加一个项目
            items.put(book.getId(),new CartItem(book,1));
        }else{
            //有该项目就数量加1
            item.setCount(item.getCount()+1);
        }
    }

    /**
     * 根据书籍id删除购物车内的意向
     * @param bookId 书籍id
     */
    public void deleteItem(String bookId){
        int id =Integer.parseInt(bookId);
        items.remove(id);
    }

    /**
     * 更新购物车的某一项的数量
     * @param bookid 书籍的id
     * @param count 更改后的舒朗
     */
    public void  updateCount(String bookid ,String count){
        int id= 0;
        int num=1;
        try {
            id = bookid==null ?id :Integer.parseInt(bookid);
            num=count==null ? num:Integer.parseInt(count);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        num=num==0?1:num;
        CartItem cartItem=items.get(id);
        cartItem.setCount(num);
    }

    /**
     * 清空购物车
     */
    public  void clear(){
        //清空map
        items.clear();
    }
    @Override
    public String toString() {
        return "Cart{" +
                "items=" + items +
                ", totalCount=" + totalCount +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public Cart() {
    }

}
