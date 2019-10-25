<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>板块管理</title>

	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/jquery-easyui-1.7.0/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/jquery-easyui-1.7.0/themes/icon.css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/jquery-easyui-1.7.0/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/jquery-easyui-1.7.0/locale/easyui-lang-zh_CN.js"></script>
</head>

<body>

	<!-- 表格定义 -->
	<table class="easyui-datagrid" id="dg" title="板块管理"
			data-options="rownumbers:true,
			singleSelect:true,
			url:'../board.s?op=query',
			method:'get',
			toolbar:'#tb',
			pagination:true,		<%--pagination:true,时每次请求都会带两个参数 page rows--%>
			pageList:[5,10,15,20,25]<%--可选择每页的记录条数 --%>
			">
		<thead>
			<tr>
				<th data-options="field:'boardid'">Item ID</th>
				<th data-options="field:'boardname',width:200">版块名称</th>
				<th data-options="field:'parentid',width:200">父板块</th>
				<th data-options="field:'boardid1',width:200 ,formatter:fmtOp">操作</th>
			</tr>
		</thead>
	</table>
	<!-- 工具栏 -->
	<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true"></a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true"></a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true"></a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>
		</div>
		
		<div>
			板块名: <input  id="boardName" class="easyui-textbox" style="width:150px">
			父板块: 
			<select id="parentBoard" class="easyui-combobox" panelHeight="auto" style="width:100px"
			data-options="
					url:'../board.s?op=query&parentBoard=0',
					valueField:'boardid',    <%--下拉列表的真实值 --%> 
					textField:'boardname'     <%--下拉列表的显示值 数据库字段 --%> 
			">
			</select>
			<a href="#" class="easyui-linkbutton" iconCls="icon-search"
			 onclick="query()">查询</a>
			 <a href="#" class="easyui-linkbutton" iconCls="icon-add"
			 onclick="add()">新增</a>
		</div>
	</div>
	<%-- 新增操作的窗口 --%>
	<div id="dlg" class="easyui-dialog" title="板块信息编辑" 
	style="width:400px;height:200px;padding:10px"
			data-options="
				iconCls: 'icon-save',
				closed:true,
				buttons: [{
					text:'保存',
					iconCls:'icon-ok',
					handler:function(){
						save();
					}
				},{
					text:'取消',
					handler:function(){
						$('#dlg').dialog('close');
					}
				}]
			">
	<form id ="ff" action="../board.s">
			 <input name="boardid" type="hidden" id="boardid">
			 <input  name="boardname" class="easyui-textbox" style="width:250px"
			label="板块名称"><br>
			<select name="parentid" class="easyui-combobox" panelHeight="auto" style="width:250px"
			data-options="
					url:'../board.s?op=query&parentBoard=0',
					valueField:'boardid',    <%--下拉列表的真实值 --%> 
					textField:'boardname',     <%--下拉列表的显示值 数据库字段 --%> 
					label:'父板块：'
			">
			</select>
		</form>	
	</div>
	<script type="text/javascript">
function query(){
	$('#dg').datagrid('load',{
		boardName: $("#boardName").textbox('getValue'),
		parentBoard:$("#parentBoard").combobox('getValue')
	});
}
function save() {
	
	<%-- ajax 回调函数--%>
 	$('#ff').form('submit',{
		onSubmit: function(param){
			//拓展参数 官方的api 没有描述清楚
			param.op="save";
		},
		success:function(data){
			//data 是服务器返回的字符串 ===json
			eval(" var d="+data+";");//eval可以实现 json 转js对象
			//alert(d.msg);
			if(d.code==1){
				$.messager.show({
					title:'系统提示',
					msg:d.msg,
					timeout:2000,
					showType:'slide'
					
				});
				$("#dlg").dialog("close");//关闭窗口
				$("#dg").datagrid("reload");//刷新表格	
			}else{
				//失败则继续编辑不
				$.messager.alert('系统提示',d.msg,'error');
			}
		}
	}); 
	
}
<%-- 给没一行添加修改按钮--%>
function fmtOp(value,row,index){
	return "<button onclick='mod("+index+")'>修改</button>";
}

function mod(index) {
	//修改对应的记录
	var rows=$("#dg").datagrid("getRows");//获取所有的行
	var board=rows[index];//获取当前的行
	//将板块对象的属性填写到表单中
	$('#ff').form("load",board);
	$("#dlg").dialog("open");//打开窗口
}
function add() {
	//easyui 的reset 只会reset esayui的控件
	$('#ff').form("reset");
	//清空boardid的值
	$("#boardid").val("");
	$("#dlg").dialog("open");//打开窗口
}
</script>
</body>
</html>