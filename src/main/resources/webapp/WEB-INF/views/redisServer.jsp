<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/taglib/taglibs.jsp" %>
<html>
<head>
	<pubTag:resource/>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="/resources/jquery-easyui-1.3.4/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="/resources/jquery-easyui-1.3.4/themes/icon.css">
    <link href="/resources/bootstrap-switch/bootstrap-switch.css" rel="stylesheet">
	<script type="text/javascript" src="/resources/bootstrap-switch/bootstrap-switch.js"></script>
    
    <script type="text/javascript" src="/resources/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/resources/bootstrap/js/bootstrap-scrollspy.js"></script>
	<script type="text/javascript" src="/resources/bootstrap/js/bootstrap-button.js"></script>
	
	<script type="text/javascript" src="/resources/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/resources/jquery-easyui-1.3.4/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/resources/jquery-easyui-1.3.4/plugins/jquery.dialog.js"></script>
	<title>Angel</title>
	
</head>
<body>
	<pubTag:header/>
	<h2>redis服务配置</h2>
    <table id="dg" title="redis服务列表" class="easyui-datagrid" style="width:700px;height:250px"
            url="/server/redisServerList.htm"
            toolbar="#toolbar" 
            rownumbers="true" fitColumns="true" singleSelect="true" fit="true">
        <thead>
            <tr>
                <th field="uuid" width="50">uuid</th>
                <th field="host" width="50">host</th>
                <th field="port" width="50">port</th>
                <th field="master" width="50">isMaster</th>
                <th field="slaveRedisServer" width="50">slaves</th>
                <th field="name" width="50">名称</th>
                <th field="description" width="50">描述</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
       <a href="#myModal" role="button" class="btn btn-small btn-primary" data-toggle="modal">添加</a>
       <button class="btn btn-small btn-primary" data-toggle="modal"  id="updateRedis">修改</button>
       <button class="btn btn-small btn-primary" id="removeRedis">删除</button>
    </div>
    
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">Save</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$j('#dlg').dialog('close')">Cancel</a>
    </div>
    <script type="text/javascript">
        var url;
        function newRedis(){
            $j('#dlg').dialog('open').dialog('setTitle','New User');
            $j('#fm').form('clear');
            url = 'saveRedis.htm';
        }
        function editUser(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $('#dlg').dialog('open').dialog('setTitle','Edit User');
                $('#fm').form('load',row);
                url = 'updateRedis.htm?id='+row.id;
            }
        }
        function saveUser(){
            $('#newServer').submit();
        }
        
        $('#removeRedis').click(function(){
        	var selectRow = $('#dg').datagrid('getSelections');
        	if (selectRow.length > 0) {
        		$.messager.confirm('确认','您是否要删除当前选中的记录?',function(r){
        			if (r) {
        				var uuid = selectRow[0].uuid;
        				$.ajax({
        					url : '/server/removeServer.htm',
        					data : {'uuid' : uuid},
        					dataType : 'json',
        					method : 'get',
        					async : false,
        					success : function(result) {
        							$('#dg').datagrid('reload');
        							$('#dg').datagrid('clearSelections');
        							window.location.href = '/server/redisServer.htm';
        						//	$.messager.show({'title' : '提示','msg':'删除成功!'});
        					}
        				});
        			}
        		});
        	} else {
        		$.messager.alert('提示','请选中要删除的记录!');
        	}
        });
        
        $('#updateRedis').click(function(){
        	var selectRow = $('#dg').datagrid('getSelections');
        	if (selectRow.length > 0) {
        		$('#myModal').modal('show');
        		var rowObj = selectRow[0];
        		for (var key in rowObj) {
        			$('#newServer').form('clear');
        			var formAttr = "#newServer " + "#" +key;
        			$(formAttr).attr("placeholder",rowObj[key]);
        		}
        	} else {
        		$.messager.alert('提示','请选中要更新的记录!');
        	}
        });
        
        function destroyUser(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $.messager.confirm('Confirm','Are you sure you want to destroy this user?',function(r){
                    if (r){
                        $.post('destroy_user.php',{id:row.id},function(result){
                            if (result.success){
                                $('#dg').datagrid('reload');    // reload the user data
                            } else {
                                $.messager.show({    // show error message
                                    title: 'Error',
                                    msg: result.errorMsg
                                });
                            }
                        },'json');
                    }
                });
            }
        }
    </script>
    <style type="text/css">
        #fm{
            margin:0;
            padding:10px 30px;
        }
        .ftitle{
            font-size:14px;
            font-weight:bold;
            padding:5px 0;
            margin-bottom:10px;
            border-bottom:1px solid #ccc;
        }
        .fitem{
            margin-bottom:5px;
        }
        .fitem label{
            display:inline-block;
            width:80px;
        }
    </style>
	<pubTag:footer/>
	
          <div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
              <h5 id="myModalLabel">新增</h5>
            </div>
            <div class="modal-body">
            <form class="form-horizontal e-form" action="/server/newServer.htm" method="post" id="newServer">
			  <div class="control-group">
			    <label class="control-label" for="uuid">uuid</label>
			    <div class="controls">
			      <input type="text" id="uuid" name="uuid" placeholder="uuid">
			    </div>
			  </div>
			  <div class="control-group">
			    <label class="control-label" for="host">host</label>
			    <div class="controls">
			      <input type="text" id="host" name="host" placeholder="host">
			    </div>
			  </div>
			  <div class="control-group">
			    <label class="control-label" for="port">port</label>
			    <div class="controls">
			      <input type="text" id="port" name="port" placeholder="port">
			    </div>
			  </div>
			  <div class="control-group">
			    <label class="control-label" for="isMaster ">isMaster</label>
			    <div class="controls">
			      <input id="isMaster" name="isMaster" type="radio" checked="checked" value="" />true
                  <input id="isMaster" name="isMaster" type="radio" value="" />false
			    </div>
			  </div>
			  <div class="control-group">
			    <label class="control-label" for="slaves">slaves</label>
			    <div class="controls">
			      <input type="text" id="slaves" name="slaves" placeholder="slaves ">
			    </div>
			  </div>
			  <div class="control-group">
			    <label class="control-label" for="name">名称 </label>
			    <div class="controls">
			      <input type="text" id="name" name="name" placeholder="名称 ">
			    </div>
			  </div>
			  <div class="control-group">
			    <label class="control-label" for="description">描述 </label>
			    <div class="controls">
			      <input type="text" id="description" name="description" placeholder="描述 ">
			    </div>
			  </div>
			</form>
            </div>
            <div class="modal-footer">
              <button class="btn" data-dismiss="modal">关闭</button>
              <button class="btn btn-primary" onclick="saveUser()">保存</button>
            </div>
          </div>
          
</body>
</html>