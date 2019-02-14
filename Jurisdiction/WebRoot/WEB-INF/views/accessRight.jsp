<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0048)http://www.yelangsem.com/ -->
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=GBK">
<meta http-equiv="X-UA-Compatible" content="IE=7">
<title>无访问权限</title>
<style>
	body {
	
		margin: 0;
		padding: 20px;
		text-align:center;
		font-family:Arial, Helvetica, sans-serif;
		font-size:14px;
		color:#666666;
	}
	.error_page {
		width: 600px;
		padding: 50px;
		margin: auto;
	}
	.error_page h1 {
		margin: 20px 0 0;
	}
	.error_page p {
		margin: 10px 0;
		padding: 0;
	}
	a {
		color: #9caa6d;
		text-decoration:none;
	}
	a:hover {
		color: #9caa6d;
		text-decoration:underline;
	}
</style>
</head>

	<body class="login">
	    <div class="error_page"> <img alt="Kidmondo_face_sad" src="<c:out value="${pageContext.request.contextPath}"/>/images/timg.jpg">
	    </div>
	</body>
</html>