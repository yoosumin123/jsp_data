<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import= "java.sql.DriverManager" %>
<%@page import= "java.sql.Connection" %>
<%@page import= "java.sql.PreparedStatement" %>
<%!
	Connection conn=null;
 	PreparedStatement pstmt=null;
 	
 	String url = "jdbc:oracle:thin:@localhost:1521:XE";
 	String uid = "bg";
 	String pass = "bg";

 	String sql = "insert into member values(?, ?, ?, ?, ?, ?)";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
request.setCharacterEncoding("UTF-8");

String name= request.getParameter("name");
String userid= request.getParameter("userid");
String pwd= request.getParameter("pwd");
String email= request.getParameter("email");
String phone= request.getParameter("phone");
String admin= request.getParameter("admin");
try{
	//(1 단계) JDBC 드라이버 로드
	Class.forName("oracle.jdbc.driver.OracleDriver");
	
	//(2 단계) 데이터베이스 연결 객체인 Connection 생성
	conn = DriverManager.getConnection(url, uid, pass);
	
	//(3 단계) PreparedStatement 객체 생성하기
	pstmt = conn.prepareStatement(sql);
	
	//(4 단계) 바인딩 변수를 채운다
	pstmt.setString(1, name);
	pstmt.setString(2, userid);
	pstmt.setString(3, pwd);
	pstmt.setString(4, email);
	pstmt.setString(5, phone);
	pstmt.setInt(6, Integer.parseInt(admin));
	
	//(5 단계) SQL문을 실행하여 결과 처리
	
	//(1 단계) JDBC 드라이버 로드
}

%>
</body>
</html>