<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User List</title>
<script type="text/javascript">
	function test(){
		alert("test");
	}
</script>
</head>
<body>
	<input type="button" value="button" onclick='test()'/>
    <a href="ctp/user/add">Add</a>
    <table>
        <tr>
            <td>ID</td>
            <td>Name</td>
        </tr>
        <c:forEach var="user" items="${userList }" >
            <tr>
                <td>${user.id }</td>
                <td>${user.name }</td>
                <td><a href="ctp/user/show/${user.id }">详细</a></td>
                <td><a href="ctp/user/edit/${user.id }">编辑</a></td>
                <td><a href="ctp/user/del/${user.id }">删除</a></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>