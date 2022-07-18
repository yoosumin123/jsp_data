<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 표현언어(EL, Expression Language : 이렇게 값을 가져오면 편함 )-->

${"Hello"}<br><!--  표현어  -->

<%= "Hello" %><br> <!-- 표현식 --> 

<%out.println("Hello"); %> <!-- 스크립트릿 -->

</body>
</html>