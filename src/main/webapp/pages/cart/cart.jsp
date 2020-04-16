<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%@include file="/include/base.jsp"%>
	<script >
		$(function () {
			$(".delete").click(function () {
				const title=$(this).parents("tr").children(".title").text();
				if(!confirm("是否从购物车移除【"+title+"】")){
					return false;
				}
			});
			$(".clear").click(function () {
				if(!confirm("是否立即清除购物车？")){
					return false;
				}
			});
			$(".itemTotalCount").change(function () {
				const id=$(this).attr("bookid");
				const value=$(this).val();
				if(value<=0){
					alert("购物车内的数量不能小于1");
					$(this).val(1);
					return false;
				}
				window.location.href="/client/CartServlet?method=update&id="+id+"&count="+value;
			});
		})
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
			<%@include file="/include/user-info.jsp"%>
	</div>
	
	<div id="main">

		<c:if test="${sessionScope.cart.totalCount==0}">

				<div style="text-align: center;line-height: 460px">
					购物车饿扁了，赶紧去<a href="#" style="color: red"> 首页</a>加入购物车吧
				</div>


		</c:if>
		<c:if test="${sessionScope.cart.totalCount!=0}">
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${sessionScope.cart.allItems}" var="item">
				<tr>
					<td class="title">${item.book.title}</td>
					<td><label>
						<input bookid="${item.book.id}" class="itemTotalCount" type="number" value="${item.count}" style="width: 30px">
					</label></td>
					<td>${item.book.price}</td>
					<td>${item.totalPrice}</td>
					<td><a class="delete" href="client/CartServlet?method=delete&id=${item.book.id}">删除</a></td>
				</tr>
			</c:forEach>
		</table>
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
				<span class="cart_span clear"><a href="client/CartServlet?method=clear">清空购物车</a></span>
				<span class="cart_span"><a href="pages/cart/checkout.jsp">去结账</a></span>
			</div>
		</c:if>
	</div>

	<%@include file="/include/bottom.jsp"%>
</body>
</html>