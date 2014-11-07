(function($){
	
	$.select = function(option) {
		install(option) ;
	} ;
	
	function install(option){
		var defaultOption = {
				url:'' ,
				selectId:'' , 
				parentId:'' ,
				initText:'请选择' , 
				initValue:'' ,
				selectedValue:'' ,
				valuePropertyName : "code" ,
				textPropertyName:'name' 
		} ;

		$.extend(defaultOption , option) ;
		initSelect(defaultOption) ;
	}

	function hasParent(defaultOption){
		return !$.isEmptyObject(defaultOption.parentId) ;
	}
	
	function refresh(defaultOption , data , isSelected){
		var select = $(defaultOption.selectId) ;
		//用于清空间隔一级以上的select值
		select.val('') ;
		select.change() ;
		var option = '<option value="' + defaultOption.initValue + '">' + defaultOption.initText + '</option>' ;
		select.empty().append(option) ;
		
		if($.isEmptyObject(defaultOption.parentId) || (!$.isEmptyObject(defaultOption.parentId) && !$.isEmptyObject(data) ) ){
			var url = defaultOption.url ;
			url = url.replace(/\{.+\}/, data ) ; 
			
			$.ajax({
				url:url ,
				type:'post' ,
				dataType:'json' ,
				success:function(data){
					$.each(data , function( k , v){ 
						if(isSelected && defaultOption.selectedValue == v[defaultOption.valuePropertyName] ){
							option = '<option value="' + v[defaultOption.valuePropertyName] + '" selected="selected">' 
							+ v[defaultOption.textPropertyName] + '</option>' ;
						} else {
							option = '<option value="' + v[defaultOption.valuePropertyName] + '">' 
							+ v[defaultOption.textPropertyName] + '</option>' ;
						}
						select.append(option) ;
					}) ;
				}
			}) ;
		}
	}
	
	function initSelect(defaultOption){
		var select = $(defaultOption.selectId) ;
		var option = '<option value="' + defaultOption.initValue + '">' + defaultOption.initText + '</option>' ;
		select.empty().append(option) ;
		
		var data = {} ;
		if(hasParent(defaultOption)) {
			data =  $(defaultOption.parentId).val() ;
			$(defaultOption.parentId).change(function(){
				data =  $(defaultOption.parentId).val() ;
				refresh(defaultOption , data ,  false) ;
			}) ;
		}
		refresh(defaultOption , data ,  true) ;
	}
})(jQuery) ;