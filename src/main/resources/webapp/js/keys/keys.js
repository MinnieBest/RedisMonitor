$(function(){
	$("#search").click(searchListener) ;
	$("#searchValue").keypress(function(e){
		if(e.keyCode==13){
			searchListener() ;
		}
	}) ;
	function searchListener(e){
		var pattern = $.trim($("#searchValue").val()) ;
		var showNum = $.trim($("#showNum").val()) ;
		if($.isNumeric(showNum)){
			showNum = parseInt(showNum) ;
		} else {
			showNum = 100 ;
		}
		var url = '/keys/getByPattern.htm';
		$.getJSON(url ,{patternKey:pattern , uuid:uuid , showNum:showNum } , function(data){
			$("#keysBody").empty() ;
			if(data == null || data.length < 1){
				$("#keysBody").append("<tr><td>无匹配结果</td></tr>") ; 
				return false ;
			}
			$.each(data , function(index , value){
				var show = value ;
				if(value.length > 30){
					show = value.substring(0 , 30) + "..." ;
					$("#keysBody").append('<tr><td><a title="'+value+'" href="#" onclick=\'getValue("'+value+'")\'>' + show + '</a></td></tr>') ;
				} else {
					$("#keysBody").append('<tr><td><a href="#" onclick=\'getValue("'+value+'")\'>' + value + '</a></td></tr>') ;
				}
				
			}) ;
		}) ;
		return false ;
	}
}) ;

function getValue(key){
	$("#Canvas").parent().hide() ; 
	window.key = key ;
	
	console.log('get value:' + key) ;
	var url = '/keys/value.htm?key=' + key +'&uuid=' + uuid ;
	$.getJSON(url , function(data){
		$("#stringView").hide().find("textarea").val("") ;
		$("#mapView").hide().find("tbody").empty() ;
		$("#listView").hide().find("tbody").empty();
		
		if(data.type == 'string'){
			$("#stringView").show().find("textarea").val(data.value) ;
			$("#ttl").val(data.ttl) ;
		} else if(data.type == "map"){
			$.each(data.value , function(key , value){
				var operate = '<td><p><button type="button" class="btn btn-mini btn-link" href="#" onclick="confirmDeleteMapEntry("'+key+'")">删除</button><button type="button" class="btn btn-mini btn-link" href="#" onclick="updateMapEntry("'+key+'")">修改</button></p></td>' ;
				$("#mapView").show().find("tbody").append("<tr>"+operate+"<td><p style=\"width:200px;\">"+ key +"</p></td><td><p style=\"width:600px;word-wrap: break-word;word-break: normal;\">"+ value +"</p></td></tr>") ;
			}) ;
		} else if(data.type == "list") {
			$.each(data.value , function(index , value){
				var operate = '<td><p><button type="button" class="btn btn-mini btn-link" href="#" onclick="confirmDeleteMapEntry("'+key+'")">删除</button><button type="button" class="btn btn-mini btn-link" href="#" onclick="updateMapEntry("'+key+'")">修改</button></p></td>' ;
				$("#listView").show().find("tbody").append("<tr>"+operate+"<td><p style=\"width:200px;\">"+ index +"</p></td><td><p style=\"width:600px;word-wrap: break-word;word-break: normal;\">"+ value +"</p></td></tr>") ;
			}) ;
		}
		console.log(data) ;
	}) ;
}

function formatXML(){
	var xml = $("#stringView").find("textarea").val();
	xml_formatted = formatXml(xml);
	xml_escaped = xml_formatted.replace(/&/g,'&amp;').replace(/</g,'&lt;').replace(/>/g,'&gt;').replace(/ /g, '&nbsp;').replace(/\n/g,'<br />');
	$("#Canvas").empty().html("<PRE class='CodeContainer'>"+xml_escaped+"</PRE>").parent().show();
}

function updateString(){
	if(typeof key == 'undefined'){
		message('没有要修改的key') ;
		return ;
	}
	var val = $("#stringView").find("textarea").val() ;
	$.post('/keys/updateString.htm' , {key:key , value:val} , function(data){
		console.log(data) ;
		if(data.status == 0){
			message('修改成功') ;
		} else {
			message('修改失败') ; 
		}
		
	} , "json") ;
}

function confirmDeleteString(){
	if(typeof key == 'undefined'){
		message('没有要删除的key');
		return ;
	}
	
	var confirm = $.scojs_confirm({content:'确认要删除吗?' , action:deleteString});
	confirm.show() ;
}

function deleteString(){
	$.post('/keys/deleteString.htm' , {key:key} , function(data){
		console.log(data) ;
		if(data.status == 0){
			message('删除成功') ; 
		} else {
			message('删除失败') ; 
		}
		
	} , "json") ;
}