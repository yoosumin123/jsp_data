<%@page import="java.net.URLEncoder" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String id="sm";
String pwd="1234";
String name="유수민";
request.setCharacterEncoding("utf-8");
if(id.equals(request.getParameter("id")) &&
		pwd.equals(request.getParameter("pwd")) ) {
	response.sendRedirect("main.jsp?name="+URLEncoder.encode(name,"utf-8"));
} else{
	response.sendRedirect("p193_login.jsp");
}
%>
</body>
</html>