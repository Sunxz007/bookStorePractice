<%@ page contentType="text/html;charset=UTF-8"  pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
</head>
<%@include file="/include/base.jsp"%>
<body>
<%--suppress CheckTagEmptyBody --%>
<jsp:forward page="/client/BookClientServlet?method=page"></jsp:forward>
</body>
</html>