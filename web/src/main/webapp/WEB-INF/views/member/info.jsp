<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix='c'%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table { width: 500px }
table th { width: 140px }
</style>
</head>
<body>
<h3>회원가입 정보 - ${usage}</h3>

<table border='1'>
<tr><th>성명</th>
	<td>${name}</td>
</tr>
<tr><th>성별</th>
	<td>${gender }</td>
</tr>
<tr><th>이메일</th>
	<td>${email}</td>
</tr>
<tr><th>전화번호</th>
	<td>${phone }</td>
</tr>
</table>
<hr>
<table border='1'>
<tr><th>성명</th>
	<td>${vo.name}</td>
</tr>
<tr><th>성별</th>
	<td>${vo.gender }</td>
</tr>
<tr><th>이메일</th>
	<td>${vo.email}</td>
</tr>
<tr><th>전화번호</th>
	<td>${vo.phone }</td>
</tr>
</table>
<div><a href='<c:url value="/member"/>'>회원가입화면</a></div>
</body>
</html>