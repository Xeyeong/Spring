<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<form method='post' action='${url}'>
<input type='hidden' name='id' value='${id}'>
</form>
<script>
if( ${download} ){
	alert('다운로드할 파일이 없습니다!');
}
$('form').submit();
</script>
