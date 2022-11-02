<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#tabs{
		display: flex;
		border-bottom : 1px solid #3367d6;
	}
	#tabs li{
		width : 140px;
		line-height: 40px;
		color : #3367d6;
		border : 1px solid #3667d6;
		border-bottom: none;
		cursor: pointer;
	}
</style>
</head>
<body>
<h3>사원정보분석</h3>
<div class='w-px1200' style='margin: 0 auto'>
	<ul id='tabs'>
		<li class='active'>부서원수</li>
		<li>채용인원수</li>
	</ul>
</div>
<div id='tab-content'>
	<div id="chart"></div>
</div>
</body>
</html>