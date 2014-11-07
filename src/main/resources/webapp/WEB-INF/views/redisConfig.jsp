<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/taglib/taglibs.jsp" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<pubTag:resource/>
	
	<link rel="stylesheet" type="text/css" href="/resources/jquery-easyui-1.3.4/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="/resources/jquery-easyui-1.3.4/themes/icon.css">
    <script type="text/javascript" src="/resources/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/resources/jquery-easyui-1.3.4/locale/easyui-lang-zh_CN.js"></script>
    
	<script type="text/javascript" src="/resources/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/resources/bootstrap/js/bootstrap-scrollspy.js"></script>
	<script type="text/javascript" src="/resources/bootstrap/js/bootstrap-button.js"></script>
	<script type="text/javascript" src="/resources/bootstrap/js/bootstrap-dropdown.js"></script>
	
	<!-- <link rel="stylesheet" type="text/css" href="/resources/fuelux/css/fuelux.min.css">
	<script type="text/javascript" src="/resources/fuelux/require.js"></script>
	<script type="text/javascript" src="/resources/fuelux/datasource.js"></script>
	<script type="text/javascript" src="/resources/fuelux/datagrid.js"></script> -->
	
	<title>Angel</title>
	<script type="text/javascript">
		function onLoadSuccess(data){
			/* console.log(data) ;
			var rowsspan = data.rows.length -1  ;
            $(this).datagrid('mergeCells',{
                index: 0 ,
                field: 'desc',
                rowspan: rowsspan
            }); */
		}
		function descFormatter(data){
			return decodeURIComponent(data) ;
		}
	</script>
</head>
<body>
	<pubTag:header/>
	<h2>Redis Server (redis.conf)</h2>
	<div class="row-fluid" style="margin:0 10px 0 10px;">
      <div class="span10">
   <div style="margin:10px 0;"></div>
	    <table id="datagrid" class="easyui-datagrid" style=""
	            data-options="nowrap:false,fitColumns:true,onLoadSuccess: onLoadSuccess,rownumbers:true,singleSelect:true,collapsible:true,url:'${pageContext.request.contextPath}/config/configDetail.htm',method:'get'">
	        <thead>
	            <tr>
	                <th data-options="field:'key',width:350,align:'center'">键</th>
	                <th data-options="field:'value',width:350,align:'center'">值</th>
	                <th data-options="field:'description',width:350,align:'center',formatter:descFormatter">描述</th>
	            </tr>
	        </thead>
	        <tbody>
	        </tbody>
	    </table>
	<pubTag:footer/>
</body>
</html>