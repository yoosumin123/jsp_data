<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리</title>
<script type="text/javascript" src="../chap09/script/member.js"></script>
</head>
<body>
	<h2>아이디 중복 확인</h2>
	<form action="idCheck.do" method="get" name="frm">
	아이디 	<input type="text" name="userid" value="${userid}">
		<input type="submit" value="중복 체크">
		<br>
		<c:if test="${result == 1}">
			<script type="text/javascript">
				opener.document.frm.userid.value="";
			</script>
			${userid}는 이미 사용 중인 아이디입니다.
		</c:if>
		<c:if test="${result==-1}">
			${userid}는 사용 가능한 아이디입니다.
			<input type="button" value="사용" class="cancle" onclick="idok()">
		</c:if>
	</form>
</body>
</html>