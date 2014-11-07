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
								<td><a href="#">province</a></td>
							</tr>
							<tr>
								<td>city</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!--/.well -->
			</div>
			<!--/span-->
			
			<div class="span9"> 
				<div class="hero-unit span-content">
					<table class="table table-hover e-table-edit">
						<thead>
							<tr>
								<th>Key</th>
								<th >Value</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>Otto</td>
								<td><textarea rows="" cols="">@mdo</textarea></td>
							</tr>
							<tr>
								<td>Thornton</td>
								<td><textarea class="">@fat@fat@fat@fat@fat@fat@fat@fat@fat@fat@fat@fat@fat@fat@fat@fat@fat@fat@fat@fat@fat@fat@fat@fat@fat@fat@fat@fat@fat@fat@fat@fat@fat@fat@fat</textarea></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="hero-unit span-content e-value">
					<div class="control-group e-format">
						<button class="btn btn-primary " type="button">Json</button>
						<button class="btn btn-primary"  type="button">XML</button><br/>
					</div>
					<textarea class="">大部分常用的表单控件、文本输入控件。包括所有HTML5所支持的：text、password、datetime、 datetime-local、date、 month、time、week、number、email、url、search、tel 和 color。</textarea>
					
				</div>
			</div>
			<!--/span-->
		</div>
		<!--/row-->
	</div>
	<pubTag:footer/>
</body>
</html>