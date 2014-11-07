function message(content , type){
	if(typeof type == 'undefined') type = 'info' ;
	$.globalMessenger().post({
		message: content ,
		type: type ,
		showCloseButton: true
	});
}