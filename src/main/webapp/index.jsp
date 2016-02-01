<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User List</title>
<link href="/resources/lib/bootstrap.min.css" rel="stylesheet">
<link href="/resources/css/app.css" rel="stylesheet">

</head>
<body>
	<nav class="navbar navbar-default">
	<div class="navbar-header">
		<div class="navbar-brand">后台管理系统</div>
	</div>
	<ul class="nav navbar-nav navbar-left">
		<li><a id="index">首页</a></li>
		<li><a id="about">关于</a></li>
	</ul>
	</nav>
	<div id="content"></div>
</body>
<script type="text/javascript" src="/resources/lib/jquery-1.12.0.min.js"></script>
<script type="text/javascript">
$("#index").click(function(e){
	$("#content").load("/ctp/user/list",function(response,status,xhr){
	});
	
})
</script>
</html>