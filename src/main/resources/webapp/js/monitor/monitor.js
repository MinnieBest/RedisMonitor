$(function(){
	count = 0 ;
	isStart = false ;
	bufferSize = $("#bufferSize").val() ;
	isScroll = true ;
	
	$('#switch').bootstrapSwitch('setSizeClass', '');
	
	$('.label-toggle-switch').on('switch-change', function (e, data) {
       if(data.value){
    	   start() ;
       } else {
    	   stop() ;
       }
    });
	/*$("#start").click(function(){
		if(!$(this).hasClass("active")) {
			start() ;
		}
	}) ;
	$("#stop").click(function(){
		if(!$(this).hasClass("active")) {
			stop() ;
		}
	}) ;*/
	$("#bufferSize").blur(function(){
		if($.isNumeric($(this).val())){
			bufferSize  = parseInt($(this).val()) ; 
		} 
		console.log(bufferSize) ;
	});
	$("#clear").click(function(){
		$("#log").empty() ;
		count = 0 ;
	}) ;
	$("#isScroll").click(function(){
		if($(this).hasClass("active")){
			$(this).removeClass("active") ;
			isScroll = false ; 
		}  else {
			$(this).addClass("active") ;
			isScroll = true ;
		}
	});
	
	function start(){
		$.getJSON('/monitor/start.htm?uuid=' + uuid , function(data){
			if(data.status == 0){
				refresh() ;
			} else {
				alert("开始失败") ;
			}
		}) ;
	}
	
	function stop(){
		$.getJSON('/monitor/stop.htm?uuid=' + uuid , function(data){
			if(data.status == 0){
				clearTimeout(window.monitorTimer) ; 
				isStart = false ;
			} else {
				alert("开始失败") ;
			}
		}) ;
		
	}
	
	function refresh(){
		isStart = true ;
		window.monitorTimer = setTimeout(function(){
			refresh() ;
		} , 1000 ) ;
		
		$.getJSON('/monitor/data.htm?uuid=' + uuid , function(data){
			$.each(data , function(i , v ){
				count++ ;
				while(count > bufferSize) {
					count-- ;
					var span = $("#log").children().get(0) ; 
					if(span!=null) span.remove() ;
				}
				$("#log").append("<span>" + v+"</span>") ;
				if(isScroll){
					$("#log")[0].scrollTop = $("#log")[0].scrollHeight ; 
				}
			}) ;
		}) ;
	}
	
	
}) ;