<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>修改节点信息</title>
	<link rel="stylesheet" type="text/css" href="<c:out value="${pageContext.request.contextPath}"/>/css/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<c:out value="${pageContext.request.contextPath}"/>/css/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<c:out value="${pageContext.request.contextPath}"/>/css/custom.css">
	<script src="<c:out value="${pageContext.request.contextPath}"/>/js/jquery-1.9.1.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/js/locale/easyui-lang-zh_CN.js"></script>
	<style type="text/css">
		body{  
		    overflow-x: hidden;  
		    overflow-y: hidden;  
		}
	</style>
	
</head>
<body>
	<div class="easyui-panel" title="" style="width:470px;height:245px">
		<div style="padding:10px 60px 20px 60px">
	    <form id="ff" method="post" >
	    	<table cellpadding="5" class="grid">
	    		<tr>
	    			<td align="right">节点名称</td>
	    			<input type="hidden" value="${nodeId}" id="nodeId">
	    			<td><input style="width:240px"  value="${title}"  type="text" id="nodeTreeName" name="nodeTreeName" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td align="right">排序</td>
	    			<td><input  style="width:240px"   value="${sort}" id="sort" max="30" size="8" maxlength="8"  type="text" name="sort" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td align="right">路径</td>
	    			<td><input style="width:240px"  value="${url}"  type="text" id="url" name="url" ></input></td>
	    		</tr>
	    		<tr>
	    			<td align="right">模块说明</td>
	    			<td><input style="width:240px"  value="${remark}" type="text" id="remark" name="remark" style="height:60px"></input></td>
	    		</tr>
	    	    <tr>
	    			<td align="right">父节点</td>
	    			<td>
	    				<input type="hidden" id="pid" value="${pid}">
	    					<select style="width:240px" name="nodeTree" id="nodeTree" >
	    					</select>
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
					      var pid = $("#pid").val();
					      $("#nodeTree").find("option[value="+pid+"]").attr("selected",true);
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
					var nodeTreeName = $("#nodeTreeName").val();//节点名称
					var nodeId = $("#nodeId").val();//  节点ID
					var sort = $("#sort").val();//  排序
					var url = $("#url").val();//路径
					var remark = $("#remark").val();//模块说明
					var nodeTree = $("#nodeTree").val();//父节点
					$.ajax({
						url:"<c:out value="${pageContext.request.contextPath}"/>/adminNode/updNode",
						datatype:"json",
						type:"post",
						data:{nodeTreeName:nodeTreeName,sort:sort,url:url,nodeTree:nodeTree,remark:remark,nodeId:nodeId},
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
