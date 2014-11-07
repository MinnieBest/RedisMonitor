<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/taglib/taglibs.jsp" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>redisMonitor</title>
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
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span3">
				<div class="well sidebar-nav info">
					<table class="table table-hover">
						<thead>
							<tr>
								<th colspan="2">Infomation</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>Mark</td>
								<td>Otto</td>
							</tr>
							<tr>
								<td>Jacob</td>
								<td>Thornton</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!--/.well -->
			</div>
			<!--/span-->
			<div class="span9"> 
				<div class="hero-unit chart" >
					<div id="container" class="container"></div>
				</div>
				<div class="hero-unit chart">
					<div id="keysChart" class="container"></div>
				</div>
				<div class="hero-unit chart">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>First Name</th>
								<th>Last Name</th>
								<th>Username</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>Mark</td>
								<td>Otto</td>
								<td>@mdo</td>
							</tr>
							<tr>
								<td>Jacob</td>
								<td>Thornton</td>
								<td>@fat</td>
							</tr>
						</tbody>
					</table>
				</div>

			</div>
			<!--/span-->
		</div>
		<!--/row-->
	</div>
	<pubTag:footer/>
</body>
</html>