<%--
  Created by IntelliJ IDEA.
  User: sun
  Date: 2020/4/11
  Time: 8:42 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<div>
    <a href="admin/BookManagerServlet?method=page">图书管理</a>
    <a href="admin/OrderManagerServlet?method=list">订单管理</a>
    <a href="${pageContext.request.contextPath}/index.jsp">返回商城</a>
</div>
