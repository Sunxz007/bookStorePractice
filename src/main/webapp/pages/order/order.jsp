<%@ page contentType="text/html;charset=UTF-8"  pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
	<%@include file="/include/base.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">我的订单</span>
			<%@include file="/include/user-info.jsp"%>
	</div>
	
	<div id="main">
		<c:if test="${empty requestScope.orders}">

			<div style="text-align: center;line-height: 120px">
				<h1>当前没有订单，赶紧去<a href="#" style="color: red"> 首页</a>下单吧</h1>
			</div>


		</c:if>
		<c:if test="${!empty requestScope.orders}">
			<table>
				<tr>
					<td>订单编号</td>
					<td>日期</td>
					<td>金额</td>
					<td>状态</td>
					<td>详情</td>
				</tr>
				<c:forEach items="${requestScope.orders}" var="order">
					<tr>
						<td>${order.orderId}</td>
						<td>${order.createDate}</td>
						<td>${order.totalMoney}</td>
						<c:choose>
							<c:when test="${order.status==0}">
								<td>待发货</td>
							</c:when>
							<c:when test="${order.status==1}">
								<td><a href="${pageContext.request.contextPath}/client/OrderClientServlet?method=receive&orderId=${order.orderId}">确认收货</a></td>
							</c:when>
							<c:when test="${order.status==2}">
								<td>已完成</td>
							</c:when>
						</c:choose>
						<td><a href="#">查看详情</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>

		
	
	</div>

	<%@include file="/include/bottom.jsp"%>
</body>
</html>