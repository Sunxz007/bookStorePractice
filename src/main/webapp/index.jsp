<%@ page contentType="text/html;charset=UTF-8"  pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
</head>
<%@include file="/include/base.jsp"%>
<body>
	<jsp:forward page="/client/BookClientServlet?method=page">

	</jsp:forward>
</body>
</html>