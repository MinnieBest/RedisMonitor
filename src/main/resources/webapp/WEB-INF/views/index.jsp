<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/taglib/taglibs.jsp" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>redisMonitor</title>
	<pubTag:resource/>
	<script type="text/javascript" src="/resources/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="/resources/bootstrap/js/bootstrap-dropdown.js"></script>
	<script type="text/javascript" src="/resources/bootstrap/js/bootstrap-scrollspy.js"></script>
	<script type="text/javascript" src="/resources/bootstrap/js/bootstrap-button.js"></script>
	<script src="/resources/highchart/highcharts.js"></script>
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
						  <c:forEach var="r" items="${rifList}">
						       <tr>
								  <td title="${r.key}:${r.desctiption }">${fn:substring(r.key, 0, 13)}</td> 
								  <td>${r.value}</td>
							   </tr>
						  </c:forEach> 
						</tbody>
					</table>
				</div>
				<!--/.well -->
			</div>
			<!--/span-->
			<div class="span9">
				<div class="hero-unit chart">
					<div id="container"></div>
				</div>
				<div class="hero-unit chart">
					<div id="keysChart"></div>
				</div>
				<div class="hero-unit chart info">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>id</th>
								<th>创建时间</th>
								<th>执行时间</th>
								<th>用时(毫秒)</th>
								<th>执行命令</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach var="op" items="${opList }">
						    <tr>
								<td>${op.id }</td>
								<td>${op.createTime }</td>
								<td>${op.executeTime }</td>
								<td>${op.usedTime }</td>
								
								<td><p style="width:600px;word-wrap: break-word;word-break: normal;">${op.args }</p></td>
							</tr>
						</c:forEach>
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
