<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link type='text/css' rel='stylesheet' 
	href='https://cdnjs.cloudflare.com/ajax/libs/c3/0.7.20/c3.min.css'>
<style>
#tabs { display: flex;  border-bottom: 1px solid #3367d6; }
#tabs li {
	width: 140px;  line-height: 40px; color: #3367d6;
	border: 1px solid #3367d6; border-bottom: none; cursor: pointer;
}
#tabs li:not(:first-child) { margin-left: 0; border-left:none;  }
#tabs li.active { color: #fff; background-color:#3367d6; }
#tab-content { width: 1200px; height: 550px; margin: 20px auto; }
.c3-axis,  .c3-text { font-size: 16px }

#legend { display: flex;  justify-content: center; }
#legend li { display: flex; align-items: center; }
#legend li:not(:first-child) { margin-left: 30px } 
.legend { width: 15px; height:15px; margin-right: 5px }
.tab { margin-bottom: 20px; }
</style>
</head>
<body>
<h3>사원정보분석</h3>
<div class='w-px1200' style='margin: 0 auto'>
	<ul id='tabs'>
		<li>부서원수</li>
		<li>채용인원수</li>
	</ul>
</div>
<div id='tab-content'>
	<div class='tab'>
		<label><input type='radio' name='graph' value='bar' checked>막대그래프</label>
		<label><input type='radio' name='graph' value='donut'>도넛그래프</label>		
	</div>
	<div class='tab'>
		<label><input type='checkbox' id='top3'>TOP3 부서</label>
		<label><input type='radio' name='unit' value='year' checked>년도별</label>
		<label><input type='radio' name='unit' value='month'>월별</label>		
	</div>
	<div id='chart'></div>
	<ul id='legend'>
		<li><span class='legend'></span><span>0~9명</span></li>
		<li><span class='legend'></span><span>10~19명</span></li>
		<li><span class='legend'></span><span>20~29명</span></li>
		<li><span class='legend'></span><span>30~39명</span></li>
		<li><span class='legend'></span><span>40~49명</span></li>
		<li><span class='legend'></span><span>50~59명</span></li>
		<li><span class='legend'></span><span>60명 이상</span></li>
	</ul>
</div>
<div class='loading center'><img src='img/loading.gif'></div>

<script src='https://cdnjs.cloudflare.com/ajax/libs/c3/0.7.20/c3.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/d3/5.16.0/d3.min.js'></script>
<script>

//채용인원수에 대한 년도별/월별 변경선택
$('[name=unit], #top3').change(function(){
	init();
	hirement();
});

//부서원수에 대한 그래프타입 변경선택
$('[name=graph]').change(function(){
	init();
	department();
})

$(function(){
	$('#tabs li').eq(1).trigger('click');
	$('.legend').each(function(idx){
		$(this).css('background-color', colors[idx]);
	});
})
$('#tabs li').click(function(){
 	if( $(this).hasClass('active') ) return; //현재 선택된 탭을 클릭
	$('#tabs li').removeClass();
	$(this).addClass('active');
	
	init();
	var idx = $(this).index();
	$('.tab').css('display', 'none');
	$('.tab').eq(idx).css('display', 'block');
	if( idx==0 )		department();
	else if( idx==1 )	hirement();
	
});

function init(){
	$('#chart').empty();
	$('#legend').css('display', 'none');
}

//부서별 사원수 시각화
function department(){
	loading(true);
	$.ajax({
		url: 'visual/department',
		success: function( response ){
// 			console.log( response )
			// [ ['부서원수', 30, 200, 100, 400, 150, 250] ]
			// ['cat1', 'cat2', 'cat3','cat9']
			var count = [ '부서원수' ], name = [ '부서명' ], info = [];
			$(response).each(function(){
				count.push( this.COUNT );
				name.push( this.DEPARTMENT_NAME );
// 				info.push( [ this.DEPARTMENT_NAME, this.COUNT ] );
				info.push( new Array( this.DEPARTMENT_NAME, this.COUNT ) );
			});
// 			console.log('info> ',[ name, count ])
			if( $('[name=graph]:checked').val()=='bar' )
				bar_chart( [ name, count ] ); // 선/막대그래프
				// [ ['부서원수', 30, 200, 100], ['부서명','A','B','C'] ]
			else
				donut_chart( info );		 		//파이/도넛그래프
				// [ ['영업부', 30], ['총무부', 120], ]
			//console.log('info> ', info)
			
			loading(false);
		},error: function(){
			loading(false);
		}
	});
	
}

//1.선그래프(기본)
function line_chart( info ){
	c3.generate({
		bindto: '#chart',
	    data: {
	        columns: info,
	        x : '부서명',
// 	        columns: [ ['부서원수', 30, 200, 100, 400, 150, 250] ]
	    },
	    axis: {
	    	  x: {
	    	    type: 'category',
// 	    	    categories: name,
	    	  }
    	},
	    size: { height: 450 },
	});
}

//2. 파이그래프
function pie_chart( info ){
	//[ ['영업부', 30], ['총무부', 120], ]
	c3.generate({
		bindto: '#chart',
		data: { columns: info, type: 'pie' },
		size: { height: 450 },
		pie: { 
			label: {
				format: function(value, ratio, id){
					return (ratio*100).toFixed(1) + '%('+value+'명)';
				}
			}
		},
		padding: { bottom:50 }, /* 차트와 범례간 여백 */
	});
}

//3. 도넛그래프
function donut_chart( info ){
	c3.generate({
		bindto: '#chart',
		data: { columns: info, type: 'donut' },
		size: { height: 450 },
		padding: { bottom: 50 },
		donut: {
			width: 100,
			title: '부서별 사원수',
			label: {
				format:function(v, r, id){
					return (r*100).toFixed(1) + '%('+ v +'명)';
				}
			}
		},
		
	});
	$('.c3-chart-arcs-title').css('font-size', '16px');
	$('#legend').css('display', 'none');
}

//4. 막대그래프
function bar_chart( info ){
	c3.generate({
		bindto: '#chart',
		data: { columns: info, x: info[0][0], type: 'bar', labels:true,
				color: function(color, data){
					//return colors[ data.index ];
					//인원수별로 색상을 지정
					return colors[ Math.floor(data.value/10) ];
				}
		},
		axis: { x: { type:'category', tick: { rotate:60 } },
				y: { label: { text: info[1][0], position:'outer-middle' } }
		},
		size: { height: 450 },
		bar: { width: 30 },   /* 막대굵기 */
		grid: { y: { show:true } },  /* y축 그리드선 보이게 */
		legend: { hide:true },/* 범례안보이게 */
	});
	$('#legend').css('display', 'flex');
}
/*
 0~9명: 0, 10~19명: 1,   20~29명: 2 : 인원수/10 
 */

var colors = [ '#d60217', '#d64c02', '#faee07', '#02a618', '#0210a6'
			, '#4202f2', '#be02f2', '#ff66f0', '#91faa1', '#73adfa'
			, '#9773fa', '#fa9b73', '#6e2202', '#1d4022', '#4a4a57' ];

function make_chart( info ){
	//1.선그래프(기본)
	//line_chart( info );
	
	//2. 파이그래프
	//pie_chart( info );
	
	//3. 도넛그래프
	//donut_chart( info );
	
	//4. 막대그래프
	bar_chart( info );
}

//년도/월별 채용인원수 시각화
function hirement(){
	if( $('#top3').prop('checked') )
		hirement_top3_chart(); //부서인원수 상위3위까지의 부서에 대해
	else
		hirement_chart(); //전체 사원에 대해
}

//상위3위까지의 부서의 년도/월별 채용인원수
function hirement_top3_chart(){
	loading(true);
	
	var unit = $("[name=unit]:checked").val();
	$.ajax({
		url: 'visual/hirement/top3/' + unit,
		success: function( response ){
			console.log( response )
			
			
			
			
			loading(false);
		},error: function(){
			loading(false);
		}
	});
	
}

//전체 사원에 대한 년도/월별 채용인원수
function hirement_chart(){
	loading(true);
	
	var unit = $('[name=unit]:checked').val();
	$.ajax({
		url: 'visual/hirement/'+ unit,
		success: function( response ){
			console.log( response );
			
			var name = [ unit ], count = [ '채용인원수' ];
			$(response).each(function(){
				name.push( this.UNIT );
				count.push( this.COUNT );
			});
			make_char_hirement( new Array(name, count) );
			
			loading(false);
		},error: function(){
			loading(false);
		}
	});
}

function make_char_hirement( info ){
	c3.generate({
		bindto: '#chart',
		data: {
			columns: info,  x: info[0][0],  type: 'bar', labels: true,
			color: function(c, d){ return colors[ Math.floor(d.value/10) ]; }
		},
		axis: { x:{ type:'category' }, 
				y:{label:{ text:info[1][0], position:'outer-top'} } 
		},
		size: { height: 450 },
		bar: { width:30 },
		grid: { y:{show:true} },
		legend: { hide:true },
	});
	$('#legend').css('display', 'flex');
}

</script>

</body>
</html>




