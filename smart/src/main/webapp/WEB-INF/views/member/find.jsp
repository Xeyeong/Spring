<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/member.css" type="text/css" />
</head>
<body>
	<div class="center">
		<a href="<c:url value="/"/>"><img src="img/hanul.logo.png"
			alt="logo" /></a>
		<form action="reset" method="post">
			<div class="box">
				<ul>
					<li><input type="text" name="id" class="chk" placeholder="아이디" /></li>
					<li><input type="text" name="name" class="chk"
						placeholder="성명" /></li>
					<li><input type="text" name="email" class="chk"
						placeholder="이메일" /></li>
					<li><input type="reset" value="초기화" /></li>
					<li><input type="button" value="확인" id="find" /></li>
				</ul>
			</div>
		</form>
	</div>

	<script>
		// 		$('#find').click(function(){

		// 		})
		$('#find').on('click', function() {
			if (emptyCheck()) {

				$('form').submit();
			}
		});
	</script>
</body>
</html>





