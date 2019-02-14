<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>后台用户管理</title> 
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
	    	<li><a href="#">权限管理 >> 用户管理</a></li>
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
					<a id="" group="" class="l-btn l-btn-small l-btn-plain" href="javascript:void(0)" onclick="updUserInfo()">
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
			url:'<c:out value="${pageContext.request.contextPath}"/>/adminUser/getAllUser',method:'post'">
		<thead>
			<tr>
				<th data-options="field:'USER_ACCOUNT',width:100,halign:'center'">账号</th>
				<th data-options="field:'USER_NAME',width:100,halign:'center'">用户名</th>
				<th data-options="field:'USER_CREATDATE'" align="left">创建时间 </th>
				<th data-options="field:'USER_LASTDATE'">最后登录时间</th>
				<th data-options="field:'USER_STATUS',width:80,align:'center',halign:'center'" formatter="status_formatter">用户状态</th>
				<th data-options="field:'USER_ROLE',align:'left',halign:'center'" >用户说明</th>
				<th data-options="field:'USER_MAIL',align:'left',halign:'center'" >用户邮箱</th>
				<th data-options="field:'USER_PHONE',align:'left',halign:'center'" >手机号码</th>
			</tr>
		</thead>
	</table>
	 
	
	<div id="w" class="easyui-window" title="增加用户" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:420px;top:0px;">
		<div class="easyui-panel" title="" style="width:400px">
		<div style="padding:10px 60px 20px 60px">
	    <form id="ff" method="post" >
	    	<table cellpadding="5">
	    		<tr>
	    			<td class="pTop" align="left">账号:</td>
	    			<td class="pTop"><input class="easyui-textbox" validType="length[4,15]" type="text" id="USER_ACCOUNT" style="width:149px;" name="USER_ACCOUNT" data-options="required:true"></input></td>
	    		</tr>
	    		<tr style="">
	    			<td class="pTop" align="left">密码:</td>
	    			<td class="pTop"><input id="USER_PASSWORD" name="USER_PASSWORD" style="width:149px;" validType="length[4,32]" class="easyui-textbox" required="true" type="password" value=""/></td>
	    		</tr>
	    		<tr  >
	    			<td class="pTop" align="left">确认密码:</td>
	    			<td class="pTop"><input type="password" name="repassword" style="width:149px;" id="repassword" required="true" class="easyui-textbox"  validType="equalTo['#USER_PASSWORD']" invalidMessage="两次输入密码不匹配"/></td>
	    		</tr>
	    		<tr  >
	    			<td class="pTop" align="left">用户名:</td>
	    			<td class="pTop"><input type="text" name="USER_NAME" style="width:149px;" validType="length[2,32]" id="USER_NAME" required="true" class="easyui-textbox" validType="length[2,32]" /></td>
	    		</tr>
	    	    <tr>
	    	    	<td class="pTop" align="left">角色:</td>
	    	    	<td class="pTop">
						<input id="cc" class="easyui-combobox" 
							data-options="url:'<c:out value="${pageContext.request.contextPath}"/>/adminRole/getRoleByStatus',method:'post',
							required:true,valueField:'id',textField:'text',editable:false"  style="width:149px;"/>
				 	</td>
	    	    </tr>
	    	   
	    	    <tr>
	    			<td class="pTop" align="left">用户说明:</td>
	    			<td class="pTop"><input type="text" name="userExplain" style="width:149px;" id="userExplain"  class="easyui-textbox"  /></td>
	    		</tr>
	    		<tr>
	    			<td class="pTop" align="left">邮箱:</td>
	    			<td class="pTop" ><input type="text"  validType='email' name="email"   
                             invalidMessage="请输入正确的邮箱" type="text"  style="width:149px;" id="email" data-options="required:true"  class="easyui-textbox"/></td>
	    		</tr>
	    		<tr>
	    			<td class="pTop" align="left">手机号码:</td>
	    			<td class="pTop" >
                             <input type="text"  validType="phoneNum"  value="${USER_PHONE}" name="USER_PHONE"   
                             invalidMessage="请输入正确的手机号码" type="text"  style="width:149px;" id="USER_PHONE" data-options="required:true" class="easyui-textbox"/></td>
	    		</tr>
	    		
	    	
	    	</table>
	    	  	<div style="text-align:center;padding:5px">
			    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
			    </div>
	    </form>
	    </div>
	</div>
	</div>
	
	
	<script type="text/JavaScript">
		
		//$("#ct").combotree('setValue','$!{entity.EnvUnitCode}')
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
	<script type="text/javascript">
		 $.extend($.fn.validatebox.defaults.rules, {
		        /*必须和某个字段相等*/
		        equalTo: { validator: function (value, param) { return $(param[0]).val() == value; }, message: '字段不匹配' }
       });
		
		//增加
		function submitForm(){
		
	
			$('#ff').form('submit',{
				onSubmit:function(){
					return $(this).form('enableValidation').form('validate');
				},success:function(){
					
					var USER_ACCOUNT = $("#USER_ACCOUNT").val();//账号
					 
					$.ajax({
						url:"<c:out value="${pageContext.request.contextPath}"/>/adminUser/getCountUserByName",
						datatype:"json",
						type:"post",
						data:{USER_ACCOUNT:USER_ACCOUNT},
						success:function(data){
							if(data!=null){
								  var obj = eval("(" + data + ")");
								  if(obj.status == 'true'){
								  		var USER_PASSWORD = $("#USER_PASSWORD").val();//密码
										var USER_NAME = $("#USER_NAME").val();//经销商名称
									    var ROLE_ID = $("#cc").combotree("getValue");//角色ID
									    var userExplain = $("#userExplain").val();//角色说明
										var email = $("#email").val();//邮箱 
										var USER_PHONE = $("#USER_PHONE").val();//邮箱 
										var addRealName = $("#addRealName").is(":checked")?1:0;
										
										if(USER_PHONE.length==11){
											
											$.ajax({
												url:"<c:out value="${pageContext.request.contextPath}"/>/adminUser/addUserInfo",
												datatype:"json",
												type:"post",
												data:{USER_ACCOUNT:USER_ACCOUNT,USER_PASSWORD:USER_PASSWORD,USER_NAME:USER_NAME,ROLE_ID:ROLE_ID,userExplain:userExplain,USER_MAIL:email,USER_PHONE:USER_PHONE,addRealName:addRealName},
												success:function(data){
													if(data!=null){
														  var obj = eval("(" + data + ")");
														  var at="";
														  if(obj.status == 'true'){
														  		alert("新增成功");	
														  }else{
														  		$.messager.alert("信息提示","新增失败,失败原因"+obj.errorInfo);
														  }
														  reloadPage();
													}
												}
											
											})
										}else{
											
											 alert("手机号码应为11位");
										}
								  }else{
										alert(obj.errorInfo);	
								  }
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
				var USER_ID = node.USER_ID;
				$.messager.confirm("操作提示", "是否确认删除该用户?", function (data) {  
		            if (data) {  
			               $.ajax({
									url:"<c:out value="${pageContext.request.contextPath}"/>/adminUser/delUser",
									datatype:"json",
									type:"post",
									data:{USER_ID:USER_ID},
									success:function(data){
										  if(data!=null){
											  var obj = eval("(" + data + ")");
											  var at="";
											  if(obj.status == 'true'){
											  		alert("删除成功");
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
		function updUserInfo(){
			var user = $('#tg').treegrid('getSelected');
			if (user!=null){
				var userId = user.USER_ID;
 				var iWidth = 500;
				var iHeight = 350; 
				var iTop = (window.screen.availHeight - 30 - iHeight) / 2;
				var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;
				window.open("<c:out value="${pageContext.request.contextPath}"/>/adminUser/editUserView?userId="+userId+"&date="+new Date(),window, "width=" + iWidth + ", height=" + iHeight + ",top=" + iTop + ",left=" + iLeft + ",toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no,alwaysRaised=yes,depended=yes");			
			}else{
				$.messager.alert("信息提示","请选择需要修改的数据");
			}
		}
		//修改角色状态
		function status_formatter(value, row){
			 if (row.USER_STATUS == 0) {
                 return '<a href="javascript:void(0)" onClick="status_change(' + row.USER_ID + ',' + 1 + ')"><img src="<c:out value="${pageContext.request.contextPath}"/>/css/themes/icons/ok.png" width="16" height="16" style="border:none" title="启用"></a>';
             } else {
                 return '<a href="javascript:void(0)" onClick="status_change(' + row.USER_ID + ',' + 0 + ')"><img src="<c:out value="${pageContext.request.contextPath}"/>/css/themes/icons/lock.png" width="16" height="16" style="border:none" title="锁定"></a>';
             }
		}
		
		function status_change(id,value){
			 var prompt ="";
			if(value=='1'){
			 	prompt = "是否禁用该用户?";	
			}else{
				prompt = "是否启用该用户?";
			}
			$.messager.confirm("操作提示", prompt, function (data) {  
	            if (data) {  
		              $.ajax({
    				  url: "<c:out value="${pageContext.request.contextPath}"/>/adminUser/updUserStatus",
					  datatype: 'json',
					  type: "post",
					  data:{USER_ID:id,USER_STATUS:value},
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
