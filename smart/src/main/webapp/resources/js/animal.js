/**
 * 유기동물 관련처리 함수
 */
 
 
//동물사진 확대보기
$(document).on('click', '.animal img', function(){
	$('#popup, #popup-background').css('display', 'block');
	$('#popup').html( $(this).clone() );
});
 
 
//유기동물정보 목록보기
function animal_list_view( item ){
	
}

//유기동물정보 그리드보기
function animal_grid_view( item ){
	
}

//유기동물정보 요청
function animal_list( page ){
	$('#data-list').html('');
	$('.page-list').empty();
	
	var animal = new Object();
	animal.pageNo = page;
	animal.rows = pageList;
	animal.viewType = viewType;
	
	$.ajax({
		url: 'data/animal/list',
		data: JSON.stringify( animal ),
		type: 'post',
		contentType: 'application/json',		
		success: function( response ){
			console.log( response );
			$('#data-list').html( response );
			
		},error: function(req,text){
			alert(text+':'+req.status);
		}
	});
}