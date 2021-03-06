package com.sun.servlet;

import com.google.gson.Gson;
import com.sun.bean.Book;
import com.sun.bean.Cart;
import com.sun.bean.CartItem;
import com.sun.service.BookService;
import com.sun.service.impl.BookServiceImpl;
import com.sun.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基于session做购物车的处理
 * @author sun
 */
public class CartServlet extends BaseServlet {

    BookService bs=new BookServiceImpl();

    /**
     * 添加一项到购物车
     * @param request request 请求
     * @param response response 返回信息
     * @throws IOException 读取request对象错误
     */
    protected void add(HttpServletRequest request, HttpServletResponse response) throws  IOException {

        Book book=WebUtils.param2bean2(request,new Book());
        //获取图书完整信息
        book=bs.get(book);

        //获取session中的cart
        Cart cart = getCart(request);
        cart.addBook2Cart(book);

        //保存书名
        //request.getSession().setAttribute("bookTitle",book.getTitle());

        //请求来的路径
        //String path=request.getHeader("referer");
        //response.sendRedirect(path);

        //获取书名和购物车总数
        int totalCount =cart.getTotalCount();
        String title=book.getTitle();
        //方便js解析，转为json格式
        //将数据封装到map中
        Map<String,Object> map=new HashMap<>(2);
        map.put("totalCount",totalCount);
        map.put("title",title);

        Gson gson = new Gson();
        String json = gson.toJson(map);
        response.getWriter().write(json);
    }

    /**
     * 删除购物车中的一项内容
     * @param request request 请求
     * @param response response 返回信息
     * @throws IOException 读取request对象错误
     */
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        //获取session中的cart对象
        Cart cart = getCart(request);
        //获取id参数
        String id=request.getParameter("id");
        //根据id删除item
        cart.deleteItem(id);
        String path=request.getHeader("referer");
        response.sendRedirect(path);
    }


    /**
     * 清空购物车
     * @param request request 请求
     * @param response response 返回信息
     * @throws IOException 读取request对象错误
     */
    protected void clear(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        //获取session中的cart对象
        Cart cart = getCart(request);
        cart.clear();
        String path=request.getHeader("referer");
        response.sendRedirect(path);
    }

    protected List<Book> getItems(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }


    /**
     * 更新购物车中单项的数量
     * @param request request 请求
     * @param response response 返回信息
     * @throws IOException 读取request对象错误
     */
    protected void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取session中的cart对象
        Cart cart = getCart(request);
        //获取id参数
        String id=request.getParameter("id");
        //获取修改后的数量count参数
        String count=request.getParameter("count");
        cart.updateCount(id,count);

        //修改后的总数
        int totalCount=cart.getTotalCount();
        //购物车总价
        double totalPrice = cart.getTotalPrice();
        //被修改的购物车项
        CartItem cartItem=cart.getCartItem(id);
        double cartItemTotalPrice = cartItem.getTotalPrice();
        Map<String,Object> map=new HashMap<>(4);
        //封装数据
        map.put("totalCount",totalCount);
        map.put("totalPrice",totalPrice);
        map.put("itemTotalPrice",cartItemTotalPrice);

        Gson gson=new Gson();
        String json = gson.toJson(map);
        response.getWriter().write(json);
    }

    /**
     * 从session中读取cart对象
     * @param request request 请求
     * @return session 中的cart对象
     */
    public static Cart getCart(HttpServletRequest request){
        HttpSession session=request.getSession();
        //从session中取出cart
        Cart cart=(Cart) request.getSession().getAttribute("cart");
        //如果cart为空，说明是第一次添加购物车，新建一个cart对象
        if(cart==null){
            cart=new Cart();
            //保存cart
            session.setAttribute("cart",cart);
        }
        return cart;
    }

}
