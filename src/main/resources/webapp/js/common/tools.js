/**
 * +------------------------------------------------------------------------------
 * | 工具
 * | 
 * +------------------------------------------------------------------------------
 * | @date 2012-5-30 12:20:00
 * | @author Eden
 * | @version 1.0
 * +------------------------------------------------------------------------------
 */
var Tools = {
	jumpByPost:function(url , param , isPage){
		var forwardForm = $j("<form name='_forward'></form>") ;
		forwardForm.appendTo("body");
		forwardForm.attr('action' , url).attr('method' , 'POST');
		try{
			if(param){
				$j.each(param , function(k , v) {
					forwardForm.append($j("<input type='hidden' name='' value=''/>").attr("name" , k).attr("value" , v)) ;
				}) ;
			}
		} catch(e) {}
		
		if(isPage){
			try{
				if(typeof firstResult != 'undefined'){
					forwardForm.append($j("<input type='hidden' name='' value=''/>").attr("name" , "page.firstResult").attr("value" , firstResult) ) ;
				} 
				if(typeof maxResults != 'undefined'){
					forwardForm.append($j("<input type='hidden' name='' value=''/>").attr("name" , "page.maxResults" ).attr("value" ,  maxResults) ) ;
				} 
				if(typeof findType != 'undefined'){
					forwardForm.append($j("<input type='hidden' name='' value=''/>").attr("name" , "page.findType" ).attr("value" ,  findType) ) ;
				} 
				if(typeof findValue != 'undefined'){
					forwardForm.append($j("<input type='hidden' name='' value=''/>").attr("name" , "page.findValue" ).attr("value" ,  findValue) ) ;
				}
			} catch(e){
			}
		}
		forwardForm.submit();
	} ,
	
	/**
	 * 判断是否为数字
	 * @param num 
	 */
	isNum:function(num){
		return /^\d+$/.test(num) ;
	} ,
	
	/**
	 * 是否超过了java中int类型的最大值
	 * @param num
	 */
	isOverMaxInt : function(num){
		if(parseInt(num) > 2147483647){
			return true ;
		}
		return false ;
	} ,
	/**
	 * +------------------------------------------------------------------------------
	 * | 检查是否为邮箱格式
	 * | 如果是邮箱格式返回 true
	 * | 否则返回 false
	 * +------------------------------------------------------------------------------
	 * | @param email 邮箱地址
	 * +------------------------------------------------------------------------------
	 * | @return boolean
	 * +------------------------------------------------------------------------------
	 */
	isEmail : function(email){
	 		var emailReg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/ ;
	 		return emailReg.test(email) ;
		} ,
	isPhone : function(ph) {
		var phoneReg = 	/1\d{10}/ ;
		return phoneReg.test(ph) ;
	} ,
	/**
	 * | 检查是否为性别
	 * | 是返回 true
	 * | 否则返回 false
	 * | @param email 性别
	 * | @return boolean
	 */
	isSex : function(sex){
			return /^(男|女|(保密))$/.test(sex) ;
	} ,
	/**
	 * 去除字符串前面的0
	 * @return
	 */
	removeStartZero:function(text) {
		if(/^0+$/.test(text)){
			return '0' ;
		}
		//如果是整数的前面有0,则去掉
		if(/^0+\d+$/.test(text)){
			return text.replace(/^0+/ , '') ;
		}
		return text ;
	} , 
	 trim: function(str) {
		if(!str) return '' ;
		str=str.replace(/(^\s*)|(\s*$)/g,"");
		return str;
	} ,
	/**
	 * 返回字符串长度，一个汉字等于两个字节
	 */
	getStrCount :function(str) {
		var totalCount = 0;  
	     try {
			for ( var i = 0; i < str.length; i += 1) {
				var c = str.charCodeAt(i);
				if ((c >= 0x0001 && c <= 0x007e)
						|| (0xff60 <= c && c <= 0xff9f)) {
					totalCount += 1;
				} else {
					totalCount += 2;
				}
			}
		} catch (e) {
			return 0 ;
		}
		return totalCount; 
	} , 
	
	checkAll: function(parentId , childrenName) {
		var _parent = $j('#' + parentId) ;
		var _children = $j('input[name="' + childrenName + '"]');
		
		var isAllChecked = _parent.attr('checked') ;
		
		_children.each(function(){
			$j(this).attr('checked' , isAllChecked ) ;
		}) ;
	} , 
	
	checkOne : function(parentId , childrenName) {
		var _parent = $j('#' + parentId) ;
		var _children = $j('input[name="' + childrenName + '"]');
		
		var isAllChecked = true ;
		if(_children == null || _children.length == 0) {
			_parent.attr('checked' , false) ;
			return ;
		}
		_children.each(function(){
			if(!$j(this).attr('checked') ){
				isAllChecked = false ;
				return false  ;
			}
		} ) ;
		if(isAllChecked) {
			_parent.attr('checked' , true) ;
		} else {
			_parent.attr('checked' , false) ;
		}
	} ,
	
	escapeHTML:function(html){
		if(html == undefined) return html;
		var _html = html.replace(/</g , '&#60;');
		_html = _html.replace(/>/g , '&#62;');
		_html = _html.replace(/"/g , '&quot;');
		_html = _html.replace(/>/g , '&#39;');
		return _html ;
	} , 
	/**
	 * 字符串是否为空
	 * null = true 
	 * '' = true ;
	 * ' '= true ;
	 * [] = true ;
	 */
	isBlank : function(obj){
		if(obj==undefined) return true ;
		else if(typeof obj == "string"){
			if(this.trim(obj) == "")
				return true ;
		} else if(typeof obj == "object" && obj instanceof Array){
			if(obj.length == 0) return true ;
		}
		return false ;
	} ,
	/**
	 * 将对象组装成参数字符串，默认编码
	 * @param isEncode true 进行编码 false不编码
	 */
	serializeObj:function(param , isEncode){
		if(typeof param == "undefined") return '' ;
		if(isEncode == undefined) isEncode = true ;
		var _paramStr = '' ;
		for(key in param){
			var _value = param[key] ;
			if(isEncode){
				_paramStr += "&" + encodeURIComponent(key) +"=" + encodeURIComponent(_value) ; 
			} else {
				_paramStr += "&" + key +"=" + _value ; 
			}
		}
		return _paramStr ;
	} ,
	serialize:function(key , value) {
		return "&" + encodeURIComponent(key) +"=" + encodeURIComponent(value) ;
	} ,
	preview : function(content) {
		var newWindow = window.open('' , '' , '') ;
		newWindow.document.write(content) ;
		newWindow.document.close();
	} 
}