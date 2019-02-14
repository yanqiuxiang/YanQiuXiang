<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>修改角色信息</title>
	<link rel="stylesheet" type="text/css" href="<c:out value="${pageContext.request.contextPath}"/>/css/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<c:out value="${pageContext.request.contextPath}"/>/css/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<c:out value="${pageContext.request.contextPath}"/>/css/custom.css">
	<script src="<c:out value="${pageContext.request.contextPath}"/>/js/jquery-1.9.1.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/js/locale/easyui-lang-zh_CN.js"></script>
	<style>
		body{  
		    overflow-x: hidden;  
		    overflow-y: hidden;  
		}  
	</style>
</head>
<body>

	<div  class="easyui-panel" title="" style="width:560px;height:330px">
		<div style="padding:10px">
	    <form id="ff" method="post" >
	    	<table cellpadding="5" class="grid">
	    		<tr>
	    			<td>角色名称:</td>
	    			<input type="hidden" value="${ROLE_ID}" id="ROLE_ID">
	    			<td><input style="width:300px;"  value="${ROLE_NAME}"  validType="length[1,15]" type="text" id="ROLE_NAME" name="ROLE_NAME" data-options="required:true"></input></td>
	    		</tr>
	    		 <tr>
	    	    	<td>可访问资源</td>
	    	    	<td>
						<select id="cc" class="easyui-combotree" 
							data-options="url:'<c:out value="${pageContext.request.contextPath}"/>/adminRole/getNodeByRole?ROLE_ID=${ROLE_ID}',method:'post',
							required:true,cascadeCheck:true" multiple  style="width:300px;height: 20px"></select>
				 	</td>
	    	    </tr>
	    		<tr>
	    			<td>角色说明:</td>
	    			<td><input style="width:200px;" value="${ROLE_REMARK}"  type="text" id="ROLE_REMARK" name="ROLE_REMARK" ></input></td>
	    		</tr>
	    		<tr>
	    			<td>具体权限说明:</td>
	    			<td>
	    				<textarea  style="width:300px;" validType="length[0,200]"  cols=25 rows=6  data-options="required:true" readonly="readonly">${ROLE_ACCESS}</textarea>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>
	    				允许推送忘记日志提醒
	    			<br/>允许分配客服用户权限</td>
	    			<td >
	    			<input type="hidden" value="${role_receive}" id="role_receive">
	    					<input name="receive" type="checkbox"  id="receive"/>
	    					
	    			</td>
	    		</tr>
	    		
	    		
	    	    <tr>
	    	    	<td style="text-align:center;" colspan="2">
	    	    		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()"><span style='color:blue'>修改</span></a>
	    	    	</td>
	    	    </tr>
	    	</table>
	    	  	
	    </form>
	  
	    </div>
	</div>
</body>

<script>


		$(function(){
		
			var role_receive = $("#role_receive").val();
			if((role_receive&1) !=0){
				 $("#receive").prop("checked",true);
			}
    	});
		
		function submitForm(){
		    
			$('#ff').form('submit',{
					onSubmit:function(){
						return $(this).form('enableValidation').form('validate');
					},success:function(){
						var roleId  = $("#ROLE_ID").val();
						var nodeIds = $("#cc").combotree("getValues");//所有选中节点的ID
					    var role_access = $("#cc").combotree("getText");//所有选中节点的文本内容
						var role_name = $("#ROLE_NAME").val();
						var role_remark = $("#ROLE_REMARK").val();
						var receive = $("#receive").is(':checked')?1:0;
						
						$.ajax({
							url:"<c:out value="${pageContext.request.contextPath}"/>/adminRole/updRoleInfo",
							datatype:"json",
							type:"post",
							data:{roleId:roleId,nodeIds:nodeIds.toString(),role_name:role_name,role_access:role_access,role_remark:role_remark,receive:receive},
							success:function(data){
								if(data!=null){
									  var obj = eval("(" + data + ")");
									  var at="";
									  if(obj.status == 'true'){
										 	alert("修改成功");
										 	window.close();
										 	window.opener.location.reload(); 
									  }else{
									  		alert("修改失败,失败原因"+obj.errorInfo);
									  }
									  
								}
							}
						
						})
					}
					
				});	
		}
</script>

</html>
