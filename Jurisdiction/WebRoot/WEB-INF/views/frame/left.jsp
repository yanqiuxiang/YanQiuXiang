<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="<c:out value="${pageContext.request.contextPath}"/>/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="<c:out value="${pageContext.request.contextPath}"/>/js/jquery-1.9.1.min.js"></script>

<script type="text/javascript">
$(function(){	
	//导航切换
	$(".menuson li").click(function(){
		$(".menuson li.active").removeClass("active")
		$(this).addClass("active");
	});
	
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('ul').slideUp();
		if($ul.is(':visible')){
			$(this).next('ul').slideUp();
		}else{
			$(this).next('ul').slideDown();
		}
	});
})	
</script>


</head>

<body style="background:#f0f9fd;">
	 <div class="lefttop"><span></span><cite></cite><a href="<c:out value="${pageContext.request.contextPath}"/>/index" target="rightFrame">主页</a></div>
    <dl class="leftmenu">
         <c:forEach items="${rmCompetenceNodes}" var="item" varStatus="status">
      		
      		<dd>
	      		<c:if test="${item.NODE_PID ==1}">
	      		
	 				<div class="title">
	         			<span><img src="<c:out value="${pageContext.request.contextPath}"/>/images/ico/leftico01.png" /></span>${item.NODE_TITLE} 
	    			</div>
	    			
	         		<ul class="menuson">
		         		<c:forEach items="${rmCompetenceNodes}" var="it">
		         			<c:if test="${it.NODE_PID ==item.NODE_ID}">
		         				<c:if test="${it.NODE_ID ==251}">
			         				<li ><cite></cite><a href="<c:out value="${pageContext.request.contextPath}"/>${it.NODE_URL}" target="_blank">${it.NODE_TITLE}</a><i></i></li> 
		         				</c:if>
		         				<c:if test="${it.NODE_ID !=251}">
			         				<li ><cite></cite><a href="<c:out value="${pageContext.request.contextPath}"/>${it.NODE_URL}" target="rightFrame">${it.NODE_TITLE}</a><i></i></li> 
		         				</c:if>
		         			</c:if>
		         		</c:forEach>
	         		</ul>
	         		
	         	</c:if>
         	</dd>
         </c:forEach>
    </dl>
</body>
</html>
