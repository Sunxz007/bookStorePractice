<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>异常页面</title>
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
    <img class="logo_img" alt="" src="${pageContext.request.contextPath}/static/img/logo.gif" >
    <span class="wel_word">数据异常</span>
    <%@include file="/include/user-info.jsp"%>
</div>

<div id="main">

    <h1>数据异常，请联系管理员</h1>


</div>

<%@include file="/include/bottom.jsp"%>
</body>
</html>
