<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>공공데이터</h3>
<div class='btnSet api'>
	<a>약국조회</a>
	<a>유기동물조회</a>
</div>
<div id="data-list"></div>

<script>
$(function() {
	$('.api a').eq(0).trigger('click');	//클릭이벤트 강제발생
});

$('.api a').click(function(){
	$('.api a').removeClass();
	$(this).addClass('btn-fill');
	$('.api a').not( $(this) ).addClass( 'btn-empty' );
	
	if( $this.index()==0 )	 pharmacy_list();
	else					 animal_list();
});

//약국정보 요청
function pharmacy_list() {
	
}
//유기동물정보 요청
function animal_list() {
	
}



</script>
</body>
</html>








