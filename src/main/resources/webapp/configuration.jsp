<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/taglib/taglibs.jsp" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Angel</title>
	<pubTag:resource/>
	<script type="text/javascript" src="resources/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="resources/bootstrap/js/bootstrap-dropdown.js"></script>
	<script type="text/javascript" src="resources/bootstrap/js/bootstrap-scrollspy.js"></script>
	<script type="text/javascript" src="resources/bootstrap/js/bootstrap-button.js"></script>
	<script src="http://code.highcharts.com/highcharts.js"></script>
	<script src="/js/index/index.js"></script>
</head>
<body>
	<pubTag:header/>
	<div class="container"> 
		<div class="row-fluid">
			<div class="span10">
				<div class="well sidebar-nav info keys">
					<form class="form-search e-search-form">
					  <input type="text" class="input-medium search-query">
					  <button type="submit" class="btn btn-primary">搜索</button>
					</form>
					<div class="btn-group type">
					  <a class="btn dropdown-toggle btn-info" data-toggle="dropdown" href="#">
						全部
					    <span class="caret"></span>
					  </a>
					  <ul class="dropdown-menu">
					    <li><a href="#two">String</a></li>
						<!-- <li class="divider">List</li> -->
						<li><a href="#">List</a></li>
						<li><a href="#three">Map</a></li>
						<li><a href="#three">Set</a></li>
					  </ul>
					</div>
					<table class="table table-hover">
						<thead>
							<tr>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><a href="#">name</a></td>
								<td><a href="#">value</a></td>
							</tr>
							<tr>
								<td>size</td>
								<td>100</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!--/.well -->
			</div>
			<!--/span-->
		</div>
		<!--/row-->
	</div>
	<pubTag:footer/>
</body>
</html>