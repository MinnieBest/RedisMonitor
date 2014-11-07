<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/taglib/taglibs.jsp" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Angel</title>
	<pubTag:resource/>
	<link href="/resources/bootstrap-switch/bootstrap-switch.css" rel="stylesheet">
	<script type="text/javascript" src="/resources/bootstrap-switch/bootstrap-switch.js"></script>
	<script type="text/javascript" src="/resources/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="/resources/bootstrap/js/bootstrap-dropdown.js"></script>
	<script type="text/javascript" src="/resources/bootstrap/js/bootstrap-scrollspy.js"></script>
	<script type="text/javascript" src="/resources/bootstrap/js/bootstrap-button.js"></script>
	<script type="text/javascript" src="/js/monitor/monitor.js"></script>
	<script>
		uuid = '${param.uuid}' ;
	</script>
</head>
<body>
	<pubTag:header/>
	<div class="container e-log"> 
		<div class="e-control">
			<div class="label-toggle-switch make-switch e-startstop">
		        <input type="checkbox" />
		    </div>
			<div class="e-cache">
			 	<span class="help-inline" style="margin: 0 5px 0 20px;padding-bottom: 10px;">缓冲区</span><input id="bufferSize" type="text" value="1000" class="input-small" style="height:28px;">
			 </div>
			 <div class="e-assist">
			 	<button class="btn btn-primary" id="clear">清除</button>
			 	<button class="btn btn-primary active" data-toggle="button" id="isScroll">滚动</button>
			 </div>
		</div>
		<div class="container e-console" id="log">
		</div>
		<!--/row-->
	</div>
	<pubTag:footer/>
</body>
</html>