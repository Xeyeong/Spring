<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<ul class='grid animal'>
<c:forEach items='${list.item}' var='vo'>
	<li>
		<div class='info'>
			<img src='${vo.popfile}'>
			<div>
				<span>${vo.age}</span>
				<span class='sw'>
					<span>${vo.sexCd}</span>
					<span>${vo.weight}</span>
				</span>
				<span>${vo.colorCd}</span>
				<span>${vo.processState}</span>
			</div>
		</div> 
		<div class='care'>
			<span>${vo.careNm}</span>
			<span>${vo.happenDt}</span>
		</div>
	</li>
</c:forEach>
</ul>
<script>
makePage(${list.count}, ${pageNo});
</script>
</body>
</html>