<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'down.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="<c:out value="${pageContext.request.contextPath}"/>/js/jquery-1.9.1.min.js" type="text/javascript"></script>
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		
		.footer{
			height: 30px;
			line-height: 50px;
			position: fixed;
			bottom: 0;
			width: 100%;
			text-align: center;
			font-family: Arial;
			font-size: 14px;
			letter-spacing: 1px;
		}
	</style>
	
  </head>
  
  <body style="background:#f0f9fd;">
  <input type="hidden" id="DATA_RIGHT1" value="${users.DATA_RIGHT1}">
  	<embed width="0" height="0">
		<audio id="music" src="<c:out value="${pageContext.request.contextPath}"/>/images/ico/2.mp3"></audio>
	</embed>
<!-- 	<embed src="http://www.w3school.com.cn//i/horse.mp3" loop="0" autostart="true" 
="true"></embed> -->
    <div>
    <input type="hidden" id="openid" value="${users.OPENID}">
    	<div class="footer" style="top: -5px;">©2018-2019  版权所有  
    	    	<a  onclick="return false;" href="javascript:void(0);">
    </div></div>
  </body>
</html>
