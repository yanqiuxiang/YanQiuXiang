<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%String path = request.getContextPath();String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
 
<title></title> 
	<link rel="stylesheet" type="text/css" href="<c:out value="${pageContext.request.contextPath}"/>/css/themes/gray/easyui.css">
	<link rel="stylesheet" type="text/css" href="<c:out value="${pageContext.request.contextPath}"/>/css/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<c:out value="${pageContext.request.contextPath}"/>/css/login.css">
	<script src="<c:out value="${pageContext.request.contextPath}"/>/js/jquery-1.9.1.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/js/locale/easyui-lang-zh_CN.js"></script>
	<script src="<c:out value="${pageContext.request.contextPath}"/>/js/login.js" type="text/javascript" charset="GBK"></script>
	<style>
		.login{
			background: rgb(0, 142, 173); 
			padding: 7px 10px; 
			border-radius: 4px; 
			border: 1px solid rgb(26, 117, 152); 
			border-image: none; 
			color: rgb(255, 255, 255); 
			font-weight: bold;
		}
		.inpDiv{
			height: 50px; 
			line-height: 50px; 
			border-top-color: rgb(231, 231, 231); 
			border-top-width: 1px; 
			border-top-style: solid;
		}
		.bigDiv{
			background: rgb(255, 255, 255); 
			margin: -100px auto auto; 
			border: 1px solid rgb(231, 231, 231); 
			border-image: none; 
			width: 400px; 
			height: 270px; 
			text-align: center;
		}
		.inp {
			width:260px;
			height: 30px;
			padding: 6px 12px; 
			font-size: 16px;
			color: #333;
			border:none;
		}
		.inpt{
			border: 1px solid #d3d3d3;
			padding: 10px 10px;
			width: 290px;
			border-radius: 4px;
			-webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
			box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
			-webkit-transition: border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
			-o-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
			transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s
		}	
		.dinp{
			border: 1px solid #CCC;
			border-radius: 5px;
		}
			
	</style>
<script>
	function verifyLogin(){
		var userName = $.trim($("#userName").val());//用户名
		var password = $.trim($("#password").val());//密码
		var userCode = $.trim($("#userCode").val());//验证码
		if(userName==''){
			 $("#reminder").html("");
			 $("#reminder").html("请输入用户名");
			 return false;
		}else if(password==''){
			 $("#reminder").html("");
			 $("#reminder").html("请输入密码");
			 return false;
		}else if(userCode==''){
			 $("#reminder").html("");
			 $("#reminder").html("请输入验证码");
			 return false;
		}
		return true;
	}
</script>
</head> 
<body>
	<div class="top_div"></div>
	<form  action="<c:out value="${pageContext.request.contextPath}"/>/realNameLogin" onsubmit="return verifyLogin()" method="post">
			<div class="bigDiv">
			<div style=" position: absolute;">
				<div class="tou"></div>
			</div>
			<P style="padding: 10px 0px 10px;height:10px">
			
				<span id="reminder" style="color:red">推荐使用360,谷歌浏览器</span>
				<span id="reminder" style="color:red">${reminder}</span>
			</P>
			<P style="padding: 30px 0px 10px; position: relative;">
				<span class="u_logo"></span>         
				<input class="ipt" type="text" placeholder="请输入用户名或邮箱" name="userName" id="userName" value=""> 
			</P>
			<P style="position: relative;">
				<span class="p_logo"></span>         
				<input class="ipt" id="password" type="password" name="passWord" placeholder="请输入密码" value="">   
			</P>
			<P style="padding: 10px 0px 10px; style="position: relative;">
				<span>验证码：<input class="inpt" title="验证码" name="userCode" id="userCode" type="text" style="width: 100px" /></span>
				<span style="margin-left: 13px;">
					<img id="imgObj"  onclick="changeImg()" alt="点击刷新" src="<c:out value="${pageContext.request.contextPath}"/>/code" />
				</span>			
			</P>
			
			<div class="inpDiv">
					<P style="margin: 0px 35px 20px 45px;">
					<!--  
						<span style="float: left;">
							<a style="color: rgb(204, 204, 204);" href="#" onclick="fpwd()">忘记密码?</a>
						</span> 
					--> 
					    <span style="float: right;">
						 	 <button type="submit" class="login" style="margin-top: 10px;">登 录</button>	
						</span>  
					</P>  
		    </div>
		</div>
	</form>

	
	<div style="text-align:center;"></div>
	<div id="win" style="display:none;text-align:center;">
		<P style="padding: 30px 10px 10px; position: relative;">
			<span>用户名：<input class="ipt" type="text" placeholder="请输入用户名" name="findUserName" id="findUserName" ></span>         
		</P>
		<button style="margin:30px 0;padding:8px 50px;border-radius: 4px;" onclick="getMail()">查找邮箱</button>
	</div>
<script type="text/javascript">
	function getMail(){
		 var findUserName  = $.trim($("#findUserName").val());
		 if(findUserName==''){
		 	   $.messager.alert("操作提示", "请先输入账户信息"); 
		 }else{
		 	$.ajax({
				url:"<c:out value="${pageContext.request.contextPath}"/>/adminUser/getMailByUserName",
				datatype:"json",
				type:"post",
				data:{findUserName:findUserName},
				success:function(data){
					  if(data!=null){
						  var obj = eval("(" + data + ")");
						  if(obj.status == "true"){
							 	if(obj.msg==''){
							 		$.messager.alert("操作提示", "该账户未配置邮箱信息,请通知系统管理员进行密码找回操作");  
							 	}else{
							 		$.messager.confirm("确认提示", "该账户设置的邮箱地址为【"+obj.msg+"】是否确认发送", function (r) {
								        if (r) {
								            $.ajax({
								            	url:"<c:out value="${pageContext.request.contextPath}"/>/adminUser/sendEmail",
								            	datatype:"json",
								            	type:"post",
								            	data:{userId:obj.userId,mail:obj.msg},
								            	success:function(data){
								            		var obj = eval("(" + data + ")");
								            		 if(obj.status == 'true'){
													 	$.messager.alert("操作提示", "发送成功,请登录邮箱进行密码修改");  
												    }else{
												    	$.messager.alert("操作提示", obj.error);
												   }     
								            	}
								            })
								        }
								    });
							 	}
						  }else{
						  		$.messager.alert("操作提示", obj.errorInfo);  
						  }
					 }
				}
			})
		 }
	}
	function fpwd(){
		$('#win').window({
			title:'找回密码',
		    width:400,
		    height:255,
		    draggable:false,
		    resizable:false,
		    collapsible:false,
		    minimizable:false,
		    maximizable:false,
		    modal:true,
		    closable:true
		});
	}
</script>	
</body>
</html>