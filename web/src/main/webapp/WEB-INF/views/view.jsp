<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix='c'%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
<h3>화면</h3>
<div>오늘 날짜: ${today}</div>
<div>현재 시각: ${now}</div>
<div>지금: ${time}</div>

<div><a href='<c:url value="/"/>'>홈으로</a></div>
</body>
</html>