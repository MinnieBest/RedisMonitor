function ajaxGet(url , callback , data ,  opt) {
	var option = {
			url : url ,
			type:'GET' ,
			dataType : 'json' ,
			data : data , 
			success :function(data){
				callback(data) ;
			}	
		} ;
	if(typeof opt != 'undefined'){
		$.extend(option , opt) ;
	}
	
	$.ajax(option) ;
}

function ajaxPost(url , callback , data ,  opt) {
	var option = {
			url : url ,
			type:'Post' ,
			dataType : 'json' ,
			data : data , 
			success :function(data){
				callback(data) ;
			}	
		} ;
	if(typeof opt != 'undefined'){
		$.extend(option , opt) ;
	}
	
	$.ajax(option) ;
}