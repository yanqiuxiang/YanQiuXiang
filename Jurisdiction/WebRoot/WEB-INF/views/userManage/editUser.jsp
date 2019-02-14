<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>修改用户信息</title>
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
		.validatebox-text{
			height: 20px !important;
			width: 280px !important;
			
		}
	</style>
</head>
<body>
	<div class="easyui-panel" title="" style="width:480px;height:330px">
		<div style="padding:10px 60px 20px 60px">
	    <form id="ff" method="post" >
	    	<table cellpadding="5" class="grid">
	    		<tr>
	    			<td>账号</td>
	    			<input type="hidden" value="${USER_ID}" id="USER_ID">
	    			<td>${USER_ACCOUNT}</td>
	    		</tr>
	    		<tr>
	    			<td>用户名</td>
	    			<td><input style="width:160px"  value="${USER_NAME}" validType="length[2,32]" id="USER_NAME" name="USER_NAME" data-options="required:true"></td>
	    		</tr>
	    		 <tr>
	    	    	<td>角色</td>
	    	    	<td>
						<input id="cc" class="easyui-combobox"  style="width:160px;"
							data-options="url:'<c:out value="${pageContext.request.contextPath}"/>/adminRole/getRoleByStatus',method:'post',
							required:true,valueField:'id',textField:'text', value:${ROLE_ID},editable:true"/>
				 	</td>
	    	    </tr>
	    	    
	    	    <tr>
	    			<td>用户说明</td>
	    			<td>
	    				<textarea  id="USER_ROLE" cols=25 rows=3 style="width:160px;" data-options="required:true" id="USER_ROLE" >${USER_ROLE}</textarea>
	    			</td>
	    		</tr>
	    		<tr>
	    	    	<td>邮箱</td>
	    	    	<td>
						<input type="text" class="easyui-textbox" style="width:160px;height: 20px" value="${USER_MAIL}" validType='email' name="email"   
                             invalidMessage="请输入正确的邮箱"  id="USER_MAIL" name="USER_MAIL" data-options="required:true">
				 	</td>
	    	    </tr>
	    	    <tr>
	    			<td>手机号码</td>
	    			<td><input  validType="phoneNum"  value="${USER_PHONE}" name="USER_PHONE"   
                             invalidMessage="请输入正确的手机号码"   style="width:160px;" id="USER_PHONE"  data-options="required:true" class="easyui-numberbox"/></td>
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
<script type="text/JavaScript">

		$.extend($.fn.validatebox.defaults.rules, {    
		    phoneNum: { //验证手机号   
		        validator: function(value, param){ 
		         return /^1[3-8]+\d{9}$/.test(value);
		        },    
		        message: '请输入正确的手机号码。'   
		    },
		    
		    telNum:{ //既验证手机号，又验证座机号
		      validator: function(value, param){ 
		          return /(^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$)|(^((\d3)|(\d{3}\-))?(1[358]\d{9})$)/.test(value);
		         },    
		         message: '请输入正确的电话号码。' 
		    }  
		});
	
	</script>
<script>
		
	function submitForm(){
		
		$('#ff').form('submit',{
				onSubmit:function(){
					return $(this).form('enableValidation').form('validate');
				},success:function(){
					 var USER_PHONE = $("#USER_PHONE").val();//邮箱
					 if(USER_PHONE.length==11){
					 		var USER_ID = $("#USER_ID").val();//用户编号
							var USER_NAME = $("#USER_NAME").val();//用户名
							var ROLE_ID = $("#cc").combotree("getValue");//角色ID
						    var USER_ROLE = $("#USER_ROLE").val();//角色说明
						    var USER_MAIL = $("#USER_MAIL").val();//邮箱
						    var addRealName = $("#addRealName").is(":checked")?1:0;
					
							$.ajax({
								url:"<c:out value="${pageContext.request.contextPath}"/>/adminUser/updUser",
								datatype:"json",
								type:"post",
								data:{USER_ID:USER_ID,USER_NAME:USER_NAME,ROLE_ID:ROLE_ID,USER_ROLE:USER_ROLE,
								USER_MAIL:USER_MAIL,USER_PHONE:USER_PHONE,addRealName:addRealName},
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
					 }else{
					 
					 	 alert("手机号码应为11位");
					 }
					
				}
				
			});	
	}
</script>

</html>
