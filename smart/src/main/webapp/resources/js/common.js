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
	
	
	//선택한 파일을 미리보기되도록 이미지로 보이게 처리
	$('#attach-file').change(function(){
		console.log( this.files[0] );
		var attached = this.files[0];
		if( attached ){ //선택한 파일이 있는 경우
			$('#delete-file').css('display', 'inline');
			
			//미리보기 태그가 있으면 
			if( $('#preview').length > 0 ){
				//해당 파일이 이미지파일인지 확인
				if( isImage( attached.name ) ){
					$('#preview').html( '<img>' );
					var reader = new FileReader();
					reader.onload = function( e ){
						$('#preview img').attr('src', e.target.result );						
					}
					reader.readAsDataURL( attached );
				}else
					$('#preview').html('');
			}	
		}
	});
	
	$('#delete-file').click(function(){
		$('#attach-file').val('');		//선택한 file태그를 초기화
		$(this).css('display', 'none');	//파일삭제 이미지도 안보이게
		$('#preview').html('');//첨부된 이미지 미리보기 없애기
	});
	
});

//이미지파일인지 확인하는 함수
function isImage( filename ){
	//KOAS_SeatInfo.txt, abc.png, abc.jpg, abc.gif, ...
	var ext = filename.substring( filename.lastIndexOf('.')+1 );
	var imgs = [ 'png', 'jpg', 'jpeg', 'gif', 'bmp', 'webp' ];
	if( imgs.indexOf( ext ) == -1 ) return false;
	else 							return true;
}



//input 태그에 입력값이 있는지 확인
function emptyCheck(){
	var ok = true;
	$('.chk').each(function(){
		if( $(this).val()=='' ){
			var item = $(this).attr('placeholder')
					? $(this).attr('placeholder') : $(this).attr('title');
			alert(item + ' 입력하세요!');
			$(this).focus();
			ok = false;
			return ok;			
		}
	});
	return ok;
}





