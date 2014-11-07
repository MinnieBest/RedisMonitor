<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/taglib/taglibs.jsp" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>redisMonitor</title>
	<pubTag:resource/>
	<link href="/resources/json/s.css" rel="stylesheet">
	<script type="text/javascript" src="resources/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="resources/bootstrap/js/bootstrap-dropdown.js"></script>
	<script type="text/javascript" src="resources/bootstrap/js/bootstrap-scrollspy.js"></script>
	<script type="text/javascript" src="resources/bootstrap/js/bootstrap-button.js"></script>
	
	<script type="text/javascript" src="/resources/json/m.js"></script>
	<script type="text/javascript" src="/resources/json/c.js"></script>
	<script type="text/javascript" src="/resources/xml/formatXML.js"></script>
	<script src="/js/keys/keys.js"></script>
	<script>
		uuid = '${param.uuid}' ;
		$._messengerDefaults = {
				extraClasses: 'messenger-fixed messenger-theme-future messenger-on-bottom messenger-on-right'
			}
	</script>
</head>
<body>
	<pubTag:header/>
	<div class="container-fluid"> 
		<div class="row-fluid">
			<div class="span3 left">
				<div class="well sidebar-nav info keys">
					<div class="form-search e-search-form">
					  <input type="text" class="input-medium search-query" id="searchValue" style="width: 140px;">
					  <button class="btn btn-small btn-primary" id="search">搜索</button>
					  <input type="text" id="showNum" value="100" style="width: 50px;" title="显示数量"/>
					</div>
					
					<!-- <div class="btn-group type">
					  <a class="btn dropdown-toggle btn-info" data-toggle="dropdown" href="#">
						全部
					    <span class="caret"></span>
					  </a>
					  <ul class="dropdown-menu">
					    <li><a href="#two">String</a></li>
						<li class="divider">List</li>
						<li><a href="#">List</a></li>
						<li><a href="#three">Map</a></li>
						<li><a href="#three">Set</a></li>
					  </ul>
					</div> -->
					<table class="table table-hover table-condensed">
						<thead>
							<tr>
							</tr>
						</thead>
						<tbody id="keysBody">
							<tr><td>无匹配结果</td></tr>
						</tbody>
					</table>
				</div>
				<!--/.well -->
			</div>
			<!--/span-->
			
			<div class="span8"> 
				<div class="hero-unit span-content hide" id="listView">
					<table class="table table-hover table-condensed e-table-edit ">
						<thead>
							<th>操作</th><tr><th>index</th><th >Value</th></tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
				<div class="hero-unit span-content hide" id="mapView">
					<table class="table table-hover table-condensed e-table-edit">
						<thead>
							<tr>
								<th>操作</th><th>Key</th><th >Value</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
				<div class="hero-unit span-content e-value" id="stringView">
					<div class="control-group e-format">
						<button class="btn btn-primary " type="button" onclick="updateString()">修改</button>
						<button class="btn btn-primary " type="button" onclick="confirmDeleteString()">删除</button>
						<button class="btn btn-primary " type="button" onclick="Process()">Json</button>
						<button class="btn btn-primary"  type="button" onclick="formatXML()">XML</button>
						<input type="text" id="ttl" value="" style="width: 50px;height: 30px;margin: 10px 0 10px 0;" title="剩余生存时间"/><br/>
					</div>
					<textarea class=""></textarea>
					
				</div>
				<div class="hero-unit span-content e-value hide">
					<div id="Canvas" class="Canvas"></div>
				</div>
			</div>
			<!--/span-->
		</div>
		<!--/row-->
	</div>
	<pubTag:footer/>
</body>
</html>