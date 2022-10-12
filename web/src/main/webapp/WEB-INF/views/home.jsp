<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<div><a href='login'>로그인</a></div>
<div><a href='member'>회원가입</a></div>
<div><a href='first'>실습1</a></div>
<div><a href='second'>실습2</a></div>
<div><a href='third'>실습3</a></div>
</body>
</html>
