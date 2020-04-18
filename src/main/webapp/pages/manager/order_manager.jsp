<%@ page contentType="text/html;charset=UTF-8"  pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
	<%@include file="/include/base.jsp"%>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">订单管理系统</span>
			<%@include file="/include/book-manger.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>订单编号</td>
				<td>日期</td>
				<td>金额</td>
				<td>详情</td>
				<td>发货</td>
				
			</tr>		
			<c:forEach items="${requestScope.orders}" var="order">
				<tr>
					<td>${order.orderId}</td>
					<td>${order.createDate}</td>
					<td>${order.totalMoney}</td>
					<td><a href="#">查看详情</a></td>
					<c:choose>
						<c:when test="${order.status==0}">
							<td><a href="${pageContext.request.contextPath}/admin/OrderManagerServlet?method=deliver&orderId=${order.orderId}">点击发货</a></td>
						</c:when>
						<c:when test="${order.status==1}">
							<td>等待收货 </td>
						</c:when>
						<c:when test="2">
							<td>交易完成</td>
						</c:when>
					</c:choose>
				</tr>
			</c:forEach>

		</table>
	</div>

	<%@include file="/include/bottom.jsp"%>
</body>
</html>