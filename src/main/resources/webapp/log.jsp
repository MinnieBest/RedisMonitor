<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/taglib/taglibs.jsp" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Angel</title>
	<pubTag:resource/>
	<script type="text/javascript" src="/resources/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="/resources/bootstrap/js/bootstrap-dropdown.js"></script>
	<script type="text/javascript" src="/resources/bootstrap/js/bootstrap-scrollspy.js"></script>
	<script type="text/javascript" src="/resources/bootstrap/js/bootstrap-button.js"></script>
</head>
<body>
	<pubTag:header/>
	<div class="container e-log"> 
		<div class="e-control">
			<div class="btn-group e-startstop" data-toggle="buttons-radio">
			  <button class="btn btn-primary">开始</button>
			  <button class="btn btn-primary">停止</button>
			</div>
			<div class="e-cache">
			 	<span class="help-inline" style="margin: 0 5px 0 20px;padding-bottom: 10px;">缓冲区</span><input type="text" value="1000" class="input-small" style="height:28px;">
			 </div>
			 <div class="e-assist">
			 	<button class="btn btn-primary">清除</button>
			 	<button class="btn btn-primary" data-toggle="button" id="scrollable">滚动</button>
			 </div>
		</div>
		<div class="container e-console" id="log">
			log----->>log1
		</div>
		<!--/row-->
	</div>
	<pubTag:footer/>
</body>
</html>