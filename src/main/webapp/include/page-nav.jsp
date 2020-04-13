<%@ page import="com.sun.bean.Page" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sun
  Date: 2020/4/13
  Time: 3:18 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    $(function () {
        $("#goPage").click(function () {
            const pageN = $("#pn_input").val();
            window.location.href="admin/BookManagerServlet?method=page&pn="+pageN+"&pz=${requestScope.page.pageSize}"
        })
    })
</script>

<div id="page_nav">
    <a href="/admin/BookManagerServlet?method=page">首页</a>
    <c:if test="${requestScope.page.hasPre}">
        <a href="/admin/BookManagerServlet?method=page&pn=${requestScope.page.pageNo-1}&pz=${requestScope.page.pageSize}">上一页</a>
    </c:if>
    <!--只显示就近的几页-->
    <c:forEach begin="${requestScope.page.pageNo-2<=1?1:requestScope.page.pageNo-2}" end="${requestScope.page.pageNo+2>=requestScope.page.totalPage?requestScope.page.totalPage:requestScope.page.pageNo+2}" var="n">
     <c:if test="${requestScope.page.pageNo==n}">
            【${requestScope.page.pageNo}】
     </c:if>
        <c:if test="${requestScope.page.pageNo!=n}">
            <a href="/admin/BookManagerServlet?method=page&pn=${n}&pz=${requestScope.page.pageSize}">${n}<a>
        </c:if>
    </c:forEach>
    <c:if test="${requestScope.page.hasNext}">
        <a href="/admin/BookManagerServlet?method=page&pn=${requestScope.page.pageNo+1}&pz=${requestScope.page.pageSize}">下一页</a>
    </c:if>
    <a href="/admin/BookManagerServlet?method=page&pn=${requestScope.page.totalPage}&pz=${requestScope.page.pageSize}">末页</a>
    共${requestScope.page.totalPage}页，${requestScope.page.totalCount}条记录
    到第<input value="${requestScope.page.pageNo}" name="pn" id="pn_input"/>页
    <input id="goPage"type="button" value="确定">
</div>
