<%--
  Created by IntelliJ IDEA.
  User: sun
  Date: 2020/4/11
  Time: 8:24 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"  %>
<%--用户未登录，显示登录菜单，登录成功显示登录后的菜单--%>
<c:if test="${empty sessionScope.user}">
    <div>
        <a href="pages/user/login.jsp">登录</a> |
        <a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
        <a href="pages/cart/cart.jsp">购物车</a>
        <a href="pages/manager/manager.jsp">后台管理</a>
    </div>
</c:if>

<c:if test="${!empty sessionScope.user}">
    <div>
        <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
        <a href="${pageContext.request.contextPath}/pages/order/order.jsp">我的订单</a>
        <a href="userservlet?method=logout">注销</a>&nbsp;&nbsp;
        <a href="${pageContext.request.contextPath}/index.jsp">返回</a>
    </div>
</c:if>

