<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.LockedAccountException "%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>登录页</title>
	<script src="${ctx}/static/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
	<script src="${ctx}/static/jquery-validation/1.11.1/messages_bs_zh.js" type="text/javascript"></script>
	<link href="${ctx}/static/jquery-validation/1.11.1/validate.css" type="text/css" rel="stylesheet" />
</head>

<body>
	<h1>登录页</h1>
	<form id="loginForm" action="${ctx}/admin/login.action" method="post">
		<%
			String error = (String) request.getAttribute("error");
			if(error != null){
		%>
				<div class="alert alert-error controls input-large">
		<%
				out.print(error);
		%>		
				</div>
		<%
			}
		%>
		<div class="control-group">
			<label for="loginName" class="control-label">名称:</label>
			<div class="controls">
				<input type="text" id="loginName" name="loginName" value="${loginName}" class="input-medium required"/>
			</div>
		</div>
		<div class="control-group">
			<label for="password" class="control-label">密码:</label>
			<div class="controls">
				<input type="password" id="password" name="password" class="input-medium required"/>
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<label class="checkbox inline" for="rememberMe"> <input type="checkbox" id="rememberMe" name="rememberMe"/> 记住我</label>
				<input id="submit_btn" class="btn" type="submit" value="登录"/>
				<p class="help-block">(管理员：<b>admin/admin</b>, 普通用户：<b>user/user</b>)</p>
			</div>
		</div>
	</form>
	
	<script>
		$(document).ready(function() {
			$("#loginForm").validate();
		});
	</script>
</body>
</html>
