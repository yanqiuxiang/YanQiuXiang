 <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>角色管理</title> 
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
		.grid{font:12px arial,helvetica,sans-serif;border:1px solid #8DB2E3}
		.grid td{font:100% arial,helvetica,sans-serif;height:24px;padding:5px}
		.grid{width:100%;border-collapse:collapse}
		.grid th{font-size:20px;background:#E7F3FE;height:27px;line-height:27px;border:1px solid #8DB2E3;padding-left:5px}
		.grid td{border:1px solid #8DB2E3;padding-left:5px;vertical-align:middle; text-align:center;}
	</style>
</head>
<body>
 	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    	<li><a href="#">权限管理 >>角色管理</a></li>
	    </ul>
    </div>
    
	<div class="datagrid-toolbar"  >
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
					<a id="" group="" class="l-btn l-btn-small l-btn-plain" href="javascript:void(0)" onclick="deleteRole()">
						<span class="l-btn-left l-btn-icon-left">
						<span class="l-btn-text">删除</span>
						<span class="l-btn-icon icon-cancel">&nbsp;</span></span>
					</a>
				</td>
				<td>
					<a id="" group="" class="l-btn l-btn-small l-btn-plain" href="javascript:void(0)" onclick="updRoleInfo()">
						<span class="l-btn-left l-btn-icon-left">
						<span class="l-btn-text">修改</span>
						<span class="l-btn-icon icon-edit">&nbsp;</span></span>
					</a>
				</td>
			</tr>
		</table>
	</div>
	
	
	<table class="easyui-datagrid" title="" id="tg"
			data-options="singleSelect:true,
			collapsible:true,
			rownumbers:true,
			url:'<c:out value="${pageContext.request.contextPath}"/>/adminRole/getRoleInfo',method:'post'">
		<thead>
			<tr>
				<th data-options="field:'rolename',width:100,halign:'center'">角色名称</th>
				<th data-options="field:'roleaccess'" width="500", align="left">可访问资源 </th>
				<th data-options="field:'roleremark'">角色说明</th>
				<th data-options="field:'rolestatus',width:80,align:'right',halign:'center'" formatter="status_formatter">角色状态</th>
			</tr>
		</thead>
	</table>
	
	<div id="w" class="easyui-window" title="增加权限" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:420px;top:0px;">
		<div class="easyui-panel" title="" style="width:400px">
		<div style="padding:10px 10px 20px 10px">
	    <form id="ff" method="post" >
	    	<table cellpadding="5" class="grid">
	    		<tr>
	    			<td class="pTop" align="left">角色名称:</td>
	    			<td class="pTop"><input class="easyui-textbox" validType="length[1,15]"  style="width: 200px" type="text" id="role_name" name="role_name" data-options="required:true"></input></td>
	    		</tr>
	    	    <tr>
	    	    	<td class="pTop" align="left">可访问资源:</td>
	    	    	<td >
						<select id="cc" class="easyui-combotree" 
							data-options="url:'<c:out value="${pageContext.request.contextPath}"/>/adminRole/getRoleTree',method:'post',
							required:true,cascadeCheck:false" multiple  style="width:200px;"></select>
				 	</td>
	    	    </tr>
	    		<tr>
	    			<td class="pTop" align="left">角色说明:</td>
	    			<td class="pTop"><input class="easyui-textbox" validType="length[0,200]"   id="role_remark" type="text" style="height:60px;width: 200px" name="role_remark" ></input></td>
	    		</tr>
	    		<tr>
	    			<td class="pTop" align="left">允许推送忘记日志和分配客服用户权限</td>
	    			<td class="pTop">
	    				<input name="receive" type="checkbox"  id="receive"/>
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
		
		//增加
		function submitForm(){
			$('#ff').form('submit',{
				onSubmit:function(){
					return $(this).form('enableValidation').form('validate');
				},success:function(){
				    var nodeIds = $("#cc").combotree("getValues");//所有选中节点的ID
				    var role_access = $("#cc").combotree("getText");//所有选中节点的文本内容
					var role_name = $("#role_name").val();
					var role_remark = $("#role_remark").val();
					var receive = $("#receive").is(':checked')?1:0;
					$.ajax({
						url:"<c:out value="${pageContext.request.contextPath}"/>/adminRole/addRole",
						datatype:"json",
						type:"post",
						data:{nodeIds:nodeIds.toString(),role_name:role_name,role_access:role_access,role_remark:role_remark,receive:receive
							 },
						success:function(data){
							if(data!=null){
								  var obj = eval("(" + data + ")");
								  var at="";
								  if(obj.status == 'true'){
									 	alert("新增成功");
								  }else{
								  		alert("新增失败,失败原因"+obj.errorInfo);
								  }
								  reloadPage();
							}
						}
					
					})
				}
				
			});
		}
		
		//删除
		function deleteRole(){
			var node = $('#tg').treegrid('getSelected');
			if (node!=null){
				var roleId = node.roleid;
				$.messager.confirm("操作提示","删除后,该角色下的所有用户无法正常使用,是否继续?", function (data) {  
		            if (data) {  
			              $.ajax({
							url:"<c:out value="${pageContext.request.contextPath}"/>/adminRole/delRole",
							datatype:"json",
							type:"post",
							data:{roleId:roleId},
							success:function(data){
								  if(data!=null){
									  var obj = eval("(" + data + ")");
									  var at="";
									  if(obj.status == 'true'){
										 	alert(obj.msg);
									  }else{
									  		alert("删除失败,失败原因"+obj.errorInfo);
									  }
									  reloadPage();
								 }
							}
					})
		            }  
		        });
			}else{
				$.messager.alert("信息提示","请选择需要删除的数据");
			}	
		}
		//修改
		function updRoleInfo(){
			var node = $('#tg').treegrid('getSelected');
			if (node!=null){
				var roleId = node.roleid;
				var iWidth = 600;
				var iHeight = 350; 
				var iTop = (window.screen.availHeight - 30 - iHeight) / 2;
				var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;
				window.open("<c:out value="${pageContext.request.contextPath}"/>/adminRole/editRoleView?roleId="+roleId+"&date="+new Date(),window, "width=" + iWidth + ", height=" + iHeight + ",top=" + iTop + ",left=" + iLeft + ",toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no,alwaysRaised=yes,depended=yes");			
			}else{
				$.messager.alert("信息提示","请选择需要修改的数据");
			}
		}
		//修改角色状态
		function status_formatter(value, row){
			 if (row.rolestatus == 0) {
                 return '<a href="javascript:void(0)" onClick="status_change(' + row.roleid + ',' + 1 + ')"><img src="<c:out value="${pageContext.request.contextPath}"/>/css/themes/icons/ok.png" width="16" height="16" style="border:none" title="启用"></a>';
             } else {
                 return '<a href="javascript:void(0)" onClick="status_change(' + row.roleid + ',' + 0 + ')"><img src="<c:out value="${pageContext.request.contextPath}"/>/css/themes/icons/lock.png" width="16" height="16" style="border:none" title="锁定"></a>';
             }
		}
		
		function status_change(id,value){
			var prompt ="";
			if(value=='1'){
			 	prompt = "禁用角色将使该角色下的所有用户无法正常使用。是否继续操作?";	
			}else{
				prompt = "是否启用该角色。是否继续操作?";
			}
			
			$.messager.confirm("操作提示", prompt, function (data) {  
	            if (data) {  
		              $.ajax({
	    				  url: "<c:out value="${pageContext.request.contextPath}"/>/adminRole/updRoleStatus",
						  datatype: 'json',
						  type: "post",
						  data:{roleId:id,status:value},
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
		function reloadPage(){
			window.location.reload();//刷新当前页面.
		}
	</script>
	
	
</body>


</html>
