<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
 
<title>密码找回</title> 
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
			height: 240px; 
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
		
		.dinp{
			border: 1px solid #CCC;
			border-radius: 5px;
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
	</style>
<script>
	function finadUpdPwd(){
		var USER_PASSWORD = $.trim($("#USER_PASSWORD").val());//用户名
		var repassword = $.trim($("#repassword").val());//密码
		if(USER_PASSWORD=='' || USER_PASSWORD.length < 4 || USER_PASSWORD.length > 32){
			  $("#reminder").html("");
			  $("#reminder").html("新密码不能为空,并且长度在4-32个字符之间");
			  return false;
		}else if(repassword=='' || repassword.length < 4 || repassword.length > 32){
			  $("#reminder").html("");
			  $("#reminder").html("确认密码不能为空,并且长度在4-32个字符之间");
			  return false;
		}else if(USER_PASSWORD!=repassword){
			  $("#reminder").html("");
			  $("#reminder").html("两次密码输入不一致,请先确认");
			  return false;
		}
		return true;
	}
</script>
</head> 
<body>
	<div class="top_div"></div>
	<form  action="<c:out value="${pageContext.request.contextPath}"/>/adminUser/finadUpdPwd" onsubmit="return finadUpdPwd()" method="post">
			<div class="bigDiv">
			<P style="padding: 10px 0px 10px;height:10px">
				<span id="reminder" style="color:red">${reminder}</span>
			</P>
			<input type="hidden" id="userId" name="userId" value="${userId}">
			<P style="padding: 0px 0px 10px; position: relative;">
				<span class="p_logo"></span>  
				<input class="ipt" id="USER_PASSWORD" name="USER_PASSWORD"  type="password"   name="passWord" placeholder="请输入新密码"  value="">   
			</P>
			<P style="position: relative;">
				<span class="p_logo"></span>    
				<input class="ipt"   id="repassword"  type="password" name="repassword" placeholder="请确认新密码" value="" >   
			</P>
			<P style="padding: 10px 0px 10px; style="position: relative;">
				<span>验证码：<input class="inpt" title="验证码" name="userCode" id="userCode" type="text" style="width: 100px" /></span>
				<span style="margin-left: 13px;">
					<img id="imgObj"  onclick="changeImg()" alt="点击刷新" src="<c:out value="${pageContext.request.contextPath}"/>/code" />
				</span>			
			</P>
			
			<div class="inpDiv">
					<P style="margin: 0px 35px 20px 45px;">
 					    <span style="float: right;">
						 	 <button type="submit" class="login" style="margin-top: 10px;">修改密码</button>	
						</span>  
					</P>  
		    </div>
		</div>
	</form>
	<div style="text-align:center;"></div>
</body>
</html>
