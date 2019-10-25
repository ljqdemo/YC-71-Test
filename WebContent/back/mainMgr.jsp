<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台主页</title>
<%--引入esasyui --%>
	<link rel="stylesheet" type="text/css" href="../jquery-easyui-1.7.0/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../jquery-easyui-1.7.0/themes/icon.css">
	<script type="text/javascript" src="../jquery-easyui-1.7.0/jquery.min.js"></script>
	<script type="text/javascript" src="../jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="../jquery-easyui-1.7.0/locale/easyui-lang-zh_CN.js"></script>
</head>
<script type="text/javascript">
function openBoardMgr(){
	//创建面板前先判断面板是否已经打开 
	
	
	//添加一个新的标签页面板
	$('#tt').tabs('add',{
	    title:'板块管理',
	    href:'boardMgr.jsp',
	    closable:true
	});

}
</script>
<body id="cc" class="easyui-layout">
	
		<div data-options="region:'north',split:true" style="height:180px"title="North"></div>
		<div data-options="region:'south',split:true" style="height:180px;"title="South"></div>
		<div data-options="region:'east',split:true" title="East" style="width:180px;"></div>
		<div  class="easyui-accordion" data-options="region:'west',split:true" title="West" style="width:180px;">
				<div title="系统管理" style="padding:10px;">
					<a id="btn" href="#" class="easyui-linkbutton" onclick="openBoardMgr()"
					data-options="iconCls:'icon-add',width:150">板块管理</a>
					<a id="btn" href="#" class="easyui-linkbutton" 
					data-options="iconCls:'icon-search',width:150">帖子查询</a>
				</div>
				<div title="会员管理" data-options="selected:true" style="padding:10px;">
					
				</div>
				<div title="统计报表" style="padding:10px">

				</div>
		</div>
		
		<div  id="tt" class="easyui-tabs" data-options="region:'center',title:'Center'">
				<div title="Tab1" style="padding:20px;display:none;">
					tab1
			    </div>
			    <div title="Tab2" data-options="closable:true" style="overflow:auto;padding:20px;display:none;">
					tab2
		   		</div>
		   		<div title="Tab3" data-options="iconCls:'icon-reload',closable:true" style="padding:20px;display:none;">
					tab3
   				 </div>
		</div>
</body>
</html>