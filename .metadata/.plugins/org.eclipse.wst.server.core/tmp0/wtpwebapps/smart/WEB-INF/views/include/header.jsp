<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix='c'%>    
<link href='css/common.css?<%=new java.util.Date()%>' type='text/css' rel='stylesheet'> 
    
<header>
	<div class='category'>
		<ul>
			<li><a href="<c:url value='/'/>"><img src='img/hanul.logo.png'></a></li>
			<li><a href='list.cu'>고객관리</a></li>
			<li><a>사원관리</a></li>
			<li><a>공지사항</a></li>
			<li><a>방명록</a></li>
			<li><a>공공데이터</a></li>
			<li><a>데이터시각화</a></li>
		</ul>
	</div>
</header>
<style>
header {
	align-items: center;
	padding: 0 100px;
	width: calc(100% - 200px);
	display: flex;
	justify-content: space-between;
	border-bottom: 1px solid #aaa;  
}

header div.category ul {
	display: flex;
	font-size: 18px;
	font-weight: bold;
}

header div.category ul li:not(:first-child) { margin-left: 50px; }

header div.category ul li:hover { color: #0040ff; }

</style>