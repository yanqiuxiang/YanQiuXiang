<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>节点路由控制</title>
	<link rel="stylesheet" type="text/css" href="<c:out value="${pageContext.request.contextPath}"/>/css/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<c:out value="${pageContext.request.contextPath}"/>/css/themes/icon.css">
	<link href="<c:out value="${pageContext.request.contextPath}"/>/css/style.css" rel="stylesheet" type="text/css" />
	<script src="<c:out value="${pageContext.request.contextPath}"/>/js/jquery-1.9.1.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/js/locale/easyui-lang-zh_CN.js"></script>
	 <style>
		.pTop{
			padding-top: 10px
		}
	</style>
</head>
<body>
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    	<li><a href="#">权限管理 >>节点管理</a></li>
	    </ul>
    </div>
	<div class="datagrid-toolbar">
		<table cellpadding="0" cellspacing="0">
			<tr>
				<td>
					<a id="" group="" class="l-btn l-btn-small l-btn-plain" href="javascript:void(0)" onclick="$('#w').window('open')" >
						<span class="l-btn-left l-btn-icon-left">
						<span class="l-btn-text">增加</span>
						<span class="l-btn-icon icon-add">&nbsp;</span></span>
					</a>
				</td>
				<td>
					<a id="" group="" class="l-btn l-btn-small l-btn-plain" href="javascript:void(0)" onclick="delNode()">
						<span class="l-btn-left l-btn-icon-left">
						<span class="l-btn-text">删除</span>
						<span class="l-btn-icon icon-cancel">&nbsp;</span></span>
					</a>
				</td>
				<td>
					<a id="" group="" class="l-btn l-btn-small l-btn-plain" href="javascript:void(0)" onclick="getSelected()">
						<span class="l-btn-left l-btn-icon-left">
						<span class="l-btn-text">修改</span>
						<span class="l-btn-icon icon-edit">&nbsp;</span></span>
					</a>
				</td>
				<td>
					<a id="" group="" class="l-btn l-btn-small l-btn-plain"  href="javascript:void(0)"  onclick="expandAll()">
						<span class="l-btn-left l-btn-icon-left">
						<span class="l-btn-text">展开</span>
						<span class="l-btn-icon icon-undo">&nbsp;</span></span>
					</a>
				</td>
			</tr>
		</table>
	</div>
	
	<table title="&nbsp;"  id="tg"  class="easyui-treegrid"  style="height:outo"
			data-options="
				url: '<c:out value="${pageContext.request.contextPath}"/>/adminNode/nodeTreeJson',
				method: 'post',
				lines: true,
				rownumbers: true,
				idField: 'id',
				treeField: 'name'
			">
		<thead>
			<tr >
				<th data-options="field:'name'"  width="220" align="center">节点名称</th>
				<th data-options="field:'icon'"  width="150" align="center">图标</th>
				<th data-options="field:'url'" width="350"  align="center">节点路径</th>
				<th data-options="field:'sort'" width="70" align="center">排序</th>
				<th data-options="field:'pName'" width="150" align="center">上级节点</th>
				<th  data-options="field:'status'" formatter="status_formatter"  width="150" align="center">状态</th>
				<th data-options="field:'remark'" width="200" align="center">节点说明</th>
			</tr>
		</thead>
	</table>
		<div id="w" class="easyui-window" title="新增节点" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:420px;top:0px;">
		<div class="easyui-panel" title="" style="width:400px">
		<div style="padding:10px 60px 20px 60px">
	    <form id="ff" method="post" >
	    	<table cellpadding="5">
	    		<tr>
	    			<td class="pTop" align="left">节点名称:</td>
	    			<td class="pTop"><input class="easyui-textbox" style="width: 200px" type="text" id="nodeTreeName" name="nodeTreeName" data-options="required:true"></input></td>
	    		</tr>
	    		<tr></tr>
	    		<br/>
	    		<tr>
	    			<td class="pTop" align="left">排序:</td>
	    			<td class="pTop"><input  class="easyui-numberbox" style="width: 200px" id="sort" max="30" size="8" maxlength="8"  type="text" name="sort" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td class="pTop" align="left">路径:</td>
	    			<td class="pTop"><input class="easyui-textbox" style="width: 200px" type="text" id="url" name="url" ></input></td>
	    		</tr>
	    		<tr>
	    			<td class="pTop" align="left">模块说明:</td>
	    			<td class="pTop"><input class="easyui-textbox" type="text" id="remark" name="remark" style="height:60px;width: 200px"></input></td>
	    		</tr>
	    	    <tr>
	    			<td class="pTop" align="left">父节点:</td>
	    			<td class="pTop">
	    				<select name="nodeTree" id="nodeTree" style="width: 200px"></select>
	    			</td>
	    		</tr>
	    	</table>
	    	  	<div style="text-align:center;padding:5px">
			    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
			    </div>
	    </form>
	  
	    </div>
	</div>
	</div>
	<script type="text/javascript">
		
		function status_formatter(value, row){
			 if (row.status == 0) {
                 return '<a href="javascript:void(0)" onClick="status_change(' + row.id + ',' + 1 + ')"><img src="<c:out value="${pageContext.request.contextPath}"/>/css/themes/icons/ok.png" width="16" height="16" style="border:none" title="启用"></a>';
             } else {
                 return '<a href="javascript:void(0)" onClick="status_change(' + row.id + ',' + 0 + ')"><img src="<c:out value="${pageContext.request.contextPath}"/>/css/themes/icons/lock.png" width="16" height="16" style="border:none" title="锁定"></a>';
             }
		}
		
		function status_change(id,value){
			var prompt ="";
			if(value=='1'){
			 	prompt = "锁定该节点,将会把所有该节点下的子节点同时锁定。是否继续操作?";	
			}else{
				prompt = "解锁该节点,将会把所有该节点下的子节点同时解锁。是否继续操作?";
			}
			
			$.messager.confirm("操作提示", prompt, function (data) {  
	            if (data) {  
		              $.ajax({
    				  url: "<c:out value="${pageContext.request.contextPath}"/>/adminNode/updNodeStatus",
					  datatype: 'json',
					  type: "post",
					  data:{node_id:id,status:value},
					  success: function (data) {
					  if(data!=null){
						  var obj = eval("(" + data + ")");
						  if(obj.status == 'true'){
							   alert("状态修改成功");
							   reloadPage();
						  }else{
						  		alert(obj.errorInfo);
						  }
						  
					  }
					}
		    	})
	          }  
	        }); 
		}
		
		//展开所有节点
		function expandAll(){
			$('#tg').treegrid('expandAll');
		}
		//修改节点树
		function getSelected(){
			var node = $('#tg').treegrid('getSelected');
			if (node){
				var s = node.id;
				var iWidth = 500;
				var iHeight = 260; 
				var iTop = (window.screen.availHeight - 30 - iHeight) / 2;
				var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;
				window.open("<c:out value="${pageContext.request.contextPath}"/>/adminNode/getEditNodeView?nodeId="+s,window, "width=" + iWidth + ", height=" + iHeight + ",top=" + iTop + ",left=" + iLeft + ",toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no,alwaysRaised=yes,depended=yes");
			}else{
				$.messager.alert("信息提示","请选择需要编辑的节点");
			}
		}
		
		//删除节点
		function delNode(){
			var node = $('#tg').treegrid('getSelected');
			if (node){
				var nodeId = node.id;
				$.messager.confirm("操作提示","删除节点,将影响系统的正常使用。是否确认删除?", function (data) {  
		            if (data) {  
			             $.ajax({
		    				  url: "<c:out value="${pageContext.request.contextPath}"/>/adminNode/delNode",
							  datatype: 'json',
							  type: "post",
							  data:{nodeId:nodeId},
							  success: function (data) {
							  if(data!=null){
								  var obj = eval("(" + data + ")");
								  if(obj.status == 'true'){
									   alert(obj.msg);
									   reloadPage();
								  }else{
								  		alert(obj.errorInfo);
								  }
								  
							  }
							}
				    	})
		            }  
		        });
			}else{
				$.messager.alert("信息提示","请选择需要删除的节点");
			}
		}
		
		 $(function() {
		 		$.ajax({
    				  url: "<c:out value="${pageContext.request.contextPath}"/>/adminNode/getSysNodeTree",
					  datatype: 'json',
					  type: "post",
					  success: function (data) {
					  if(data!=null){
						  var obj = eval("(" + data + ")");
						  var at="";
						  if(obj.status == 'true'){
							  $.each(obj.list,function(index, item){
							  	  at +="<option value='"+item.nodeId+"'>"+item.nodeTitle+"</option>";	
							  })
							    $("#nodeTree").append(at);   
						  }else{
						  		alert(obj.errorInfo);
						  }
						  
					  }
					}
		    	})
		 
		});
		 
		
		function submitForm(){
		
			$('#ff').form('submit',{
				onSubmit:function(){
					return $(this).form('enableValidation').form('validate');
				},success:function(){
					var nodeTreeName = $("#nodeTreeName").val();
					var icon = $("#icon").val();	
					var sort = $("#sort").val();	
					var url = $("#url").val();	
					var nodeTree = $("#nodeTree").val();	
					var remark = $("#remark").val();
					$.ajax({
						url:"<c:out value="${pageContext.request.contextPath}"/>/adminNode/addNode",
						datatype:"json",
						type:"post",
						data:{nodeTreeName:nodeTreeName,sort:sort,url:url,nodeTree:nodeTree,remark:remark},
						success:function(data){
							if(data!=null){
								  var obj = eval("(" + data + ")");
								  var at="";
								  if(obj.status == 'true'){
									 	alert("新增成功");
								  }else{
								  		alert("新增失败,失败原因"+obj.errorInfo);
								  }
								  $("#nodeTreeName").attr("value","");
								  $("#nodeTreeName").attr("value","");
								  $("#sort").attr("value","");
								  $("#url").attr("value","");
								  $("#remark").attr("value","");
								  $("#w").window("close");
								  reloadPage();
							}
						}
					
					})
				}
				
			});
		}
		
		function reloadPage(){
			window.location.reload();//刷新当前页面.
		}
	</script>
	
	
</body>


</html>
