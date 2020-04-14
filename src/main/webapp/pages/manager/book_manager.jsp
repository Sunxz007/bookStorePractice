<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
	<%@include file="/include/base.jsp"%>
</head>
<script type="text/javascript">
	$(function () {
		$(".bookDel").click(function () {
			const title= $(this).parent().parent().children(".title").text();
			if(!confirm("确认删除《"+ title+"》嘛")){
				return false;
			}
		})
	})
</script>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>
		<%@include file="/include/book-manger.jsp"%>
	</div>

	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>

				<c:forEach items="${requestScope.page.pageData}" var="book">
					<tr>
						<td class="title">${book.title}</td>
						<td class="price">${book.price}</td>
						<td class="author">${book.author}</td>
						<td class="sales">${book.sales}</td>
						<td class="stock">${book.stock}</td>
						<!--带上页码信息-->
						<td><a href="admin/BookManagerServlet?method=getBook&id=${book.id}&pn=${requestScope.page.pageNo}&pz=${requestScope.page.pageSize}">修改</a></td>
						<td><a class="bookDel" href="admin/BookManagerServlet?method=delete&id=${book.id}&pn=${requestScope.page.pageNo}&pz=${requestScope.page.pageSize}">删除</a></td>
					</tr>
				</c:forEach>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<!--带上总页信息和页码信息给编辑页，帮助跳转-->
				<td><a href="<c:url value="/pages/manager/book_edit.jsp?method=add&totalPage=${requestScope.page.totalPage}&pz=${requestScope.page.pageSize}"/>">添加图书</a></td>
			</tr>	
		</table>
		<%@include file="/include/page-nav.jsp"%>
	</div>

	<%@include file="/include/bottom.jsp"%>
</body>
</html>