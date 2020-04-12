<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员登录页面</title>
	<%@include file="/include/base.jsp"%>
	<script !src="">
		$(function(){
			$("#sub_btn").click(function(){
				// 验证用户名：必须由字母，数字下划线组成，并且长度为5到12位
				//1 获取用户名输入框里的内容
				const username=$("#username").val();
				//2 创建正则表达式对象
				const usernamePatt = /^\w{5,12}$/;
				//3 使用test方法验证
				if(!usernamePatt.test(username)){
					$("span.errorMsg").text("用户名不合法，请重新输入");
					return false;
				}
				const password=$("#password").val();
				if (!usernamePatt.test(password)){
					$("span.errorMsg").text("用户密码不合法，请重新输入");
					return false;
				}
			})
		})
	</script>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎登录</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>尚硅谷会员</h1>
								<a href="/pages/user/regist.jsp">立即注册</a>
							</div>
							<div class="msg_cont">
								<b></b>
								<span class="errorMsg">
									${msg==null ? "请输入用户名密码" : msg}
<%--									<%=request.getAttribute("msg")==null?"请输入用户名密码":request.getAttribute("msg")%>--%>
								</span>

							</div>
							<div class="form">
								<form action="userservlet" method="post">
									<!--get方法会覆盖?后的参数，所以为了保险起见，一般用隐藏的表单信息提交方法-->
									<input type="hidden" name="method" value="login">
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" value="${param.username}" id="username"/>
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" id="password"/>
									<br />
									<br />
									<input type="submit" value="登录" id="sub_btn" />
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<%@include file="/include/bottom.jsp"%>
</body>
</html>