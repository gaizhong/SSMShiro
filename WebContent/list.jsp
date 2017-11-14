<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
list<br><br>
welcome:<shiro:principal></shiro:principal><br><br>
<shiro:hasRole name="admin">
<a href="admin.jsp">admin</a><br><br>
</shiro:hasRole>

<shiro:hasRole name="user">
<a href="user.jsp">user</a><br><br>
</shiro:hasRole>

<shiro:hasRole name="user">
<a href="shiro/testAnnotation">testAnnotation</a><br><br>
</shiro:hasRole>



<a href="shiro/logout">logout</a><br><br>
</body>
</html>