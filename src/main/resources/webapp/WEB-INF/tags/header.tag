<%@tag import="com.redis.monitor.RedisJedisPool"%>
<%@tag pageEncoding="utf-8" isELIgnored="false" description="header" body-content="empty"%>
<%@include file="/WEB-INF/jsp/taglib/taglibs.jsp" %>
<script>
	$(function(){$('.dropdown-toggle').dropdown() ;});
	function confirmFlushAll(){
		var confirm = $.scojs_confirm({content:'确认要执行flushAll操作吗?' , action:flushAll});
		confirm.show() ;
	}
	function flushAll(){
		console.log("flush all") ;
		$.getJSON('/flushall.htm' , function(data){
			if(data.statu.toLowerCase() == 'ok'){
				message('flushAll操作成功') ;
			} else {
				message('flushAll操作失败') ;
			}
		}) ;
	}
	function confirmFlushDB(){
		var confirm = $.scojs_confirm({content:'确认要执行flushDB操作吗?' , action:flushDB});
		confirm.show() ;
	}
	function flushDB(){
		console.log("flush db") ;
		$.getJSON('/flushDb.htm' , function(data){
			if(data.statu.toLowerCase() == 'ok'){
				message('flushDB操作成功') ;
			} else {
				message('flushDB操作失败') ;
			}
		}) ;
	}
</script>
<div class="navbar navbar-inverse">
	<div class="navbar-inner">
		<div class="container">
			<a class="btn btn-navbar" data-toggle="collapse"
				data-target=".nav-collapse"> <span class="icon-bar"></span> <span
				class="icon-bar"></span> <span class="icon-bar"></span>
			</a> <a class="brand" href="/">Angel</a>
			<div class="nav-collapse">
				<ul class="nav">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">redis服务 <b class="caret"></b></a>
						<ul class="dropdown-menu">
						   <c:forEach var="rs" items="${redisServerList }">
						     <li class=""><a href="/index.htm?uuid=${rs.uuid }">${rs.description }</a></li>
						   </c:forEach>
							<!-- <li class=""><a href="#one">飞路快用户读服务</a></li>
							<li><a href="#two">飞路快用户写服务</a></li>
							<li class="divider"></li>
							<li><a href="#three">飞路快poi写服务</a></li>
							<li><a href="#three">飞路快poi读服务</a></li> -->
						</ul></li>
					<li><a href="/keys.htm">keys</a></li>
					<li><a href="/config/toConfigDetail.htm">redis-config.xml</a></li>
					<li><a href="/server/redisServer.htm">redis节点管理</a></li>
					<li><a href="/monitor.htm">redis实时监控</a></li>
				</ul>
				<ul class="nav pull-right">
					<li class="active"><a href="#"><font size="5">redis://${host }:${port }</font></a></li>
					<li class="divider-vertical"></li>
					<li><button class="btn btn-small btn-primary" id="flushall" onclick="confirmFlushAll()" value="${id }">flushall</button></li>
					<li class="divider-vertical"></li>
					<li>
						<button class="btn btn-small btn-primary" id="flushdb" onclick="confirmFlushDB()" value="${id }">flushDB</button>
					</li>
				</ul>
			</div>
			<!-- /.nav-collapse -->
		</div>
	</div>
	<!-- /navbar-inner -->
</div>
