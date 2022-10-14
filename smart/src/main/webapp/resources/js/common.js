/**
 * 공통으로 사용할 함수들
 */


$(function(){
	var today = new Date();
	var range = '1950:' + today.getFullYear();
	
	$.datepicker.setDefaults({
		dateFormat: 'yy-mm-dd',
		changeYear: true,
		changeMonth: true,
		showMonthAfterYear: true,
		monthNamesShort: [ '1월', '2월', '3월', '4월', '5월', '6월'
							, '7월', '8월', '9월', '10월', '11월', '12월' ],
		dayNamesMin: [ '일', '월', '화', '수', '목', '금', '토' ],
		maxDate: new Date(), 
		yearRange: range		
	});
	
});






