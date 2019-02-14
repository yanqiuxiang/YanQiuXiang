<%@ page language="java" import="java.util.*,com.yqx.jurisdiction.entity.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>无标题文档</title>
	<link rel="stylesheet" type="text/css" href="<c:out value="${pageContext.request.contextPath}"/>/css/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="<c:out value="${pageContext.request.contextPath}"/>/css/themes/icon.css"/>
	<link href="<c:out value="${pageContext.request.contextPath}"/>/css/style.css" rel="stylesheet" type="text/css" />
	<script language="JavaScript" src="<c:out value="${pageContext.request.contextPath}"/>/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/js/locale/easyui-lang-zh_CN.js"></script>
	<script src="<c:out value="${pageContext.request.contextPath}"/>/BNfile/layer/layer.js" type="text/javascript"></script>
	<script src="<c:out value="${pageContext.request.contextPath}"/>/BNfile/laydate/laydate.js" type="text/javascript"></script>
	
	<style>
		.grid{font:12px arial,helvetica,sans-serif;border:1px solid #8DB2E3}
		.grid td{font:100% arial,helvetica,sans-serif;height:24px;}
		.grid{width:100%;border-collapse:collapse}
		.grid th{font-size:20px;background:#E7F3FE;height:27px;line-height:20px;border:1px solid #8DB2E3;padding-left:5px}
		.grid td{border:1px solid #8DB2E3;padding-left:5px;vertical-align:middle; text-align:center;}
	</style>
	<script>
		 $.extend($.fn.validatebox.defaults.rules, {
		        /*必须和某个字段相等*/
		        equalTo: { validator: function (value, param) { return $(param[0]).val() == value; }, message: '字段不匹配' }
       });
		
		$(function(){
		
			var now = new Date(),hour = now.getHours(); 
			if(hour < 6){
				$("#timeFrame").html("凌晨好！");
			}else if (hour < 9){
				$("#timeFrame").html("早上好！");
			}else if (hour < 12){
				$("#timeFrame").html("上午好！");
			}else if (hour < 14){
				$("#timeFrame").html("中午好！");
			}else if (hour < 17){
				$("#timeFrame").html("下午好！");
			}else if (hour < 19){
				$("#timeFrame").html("傍晚好！");
			}else if (hour < 22){
				$("#timeFrame").html("晚上好！");
			}else {
				$("#timeFrame").html("深夜了,早点休息保重身体");
			}
		});
		
		//注销
		function loginOut(){
			$.messager.confirm("操作提示", "您确定要退出并返回登录界面？", function (data) {  
	            if (data) {  
	               window.top.location.href="<c:out value="${pageContext.request.contextPath}"/>/loginOut";  
	            }  
	        }); 
	 	} 
	 	//修改个人信息
	 	function submitForm(){
	 		$('#ff').form('submit',{
				onSubmit:function(){
					return $(this).form('enableValidation').form('validate');
				},success:function(){
					var USER_ID = $("#USER_ID").val();
			 		var USER_NAME = $("#USER_NAME").val();
			 		var USER_MAIL = $("#USER_MAIL").val();
			 		var USER_ROLE = $("#USER_ROLE").val();
					var ROLE_ID = $("#ROLE_ID").val();	
					var USER_PHONE = $("#USER_PHONE").val();	
					var ALARM_PHONE = $("#ALARM_PHONE").val();
					var ALARM_SMS = $("#ALARM_SMS").val();
					var ALARM_MAIL = $("#ALARM_MAIL").val();
							
					$.ajax({
						url:"<c:out value="${pageContext.request.contextPath}"/>/adminUser/updUser",
						datatype:"json",
						type:"post",
						data:{USER_ID:USER_ID,USER_NAME:USER_NAME,USER_MAIL:USER_MAIL,USER_ROLE:USER_ROLE,ROLE_ID:ROLE_ID,
						USER_PHONE:USER_PHONE,ALARM_PHONE:$('#ALARM_PHONE').is(':checked')?1:0,ALARM_MAIL:$('#ALARM_MAIL').is(':checked')?1:0,ALARM_SMS:$('#ALARM_SMS').is(':checked')?1:0},
						success:function(data){
							if(data!=null){
								  var obj = eval("(" + data + ")");
								  var at="";
								  if(obj.status == 'true'){
									 	alert("修改成功,重新登录后才生效");
								  }else{
								  		alert("修改失败,失败原因"+obj.errorInfo);
								  }
								  $("#w").window("close");
							}
						}
					
					});
				}
				
			});	
	 	}
	 	
	 	
	 	//修改密码
	 	function updPwd(){
	 		$('#hh').form('submit',{
				onSubmit:function(){
					return $(this).form('enableValidation').form('validate');
				},success:function(){
					var OLD_PASSWORD = $("#OLD_PASSWORD").val();
					var USER_PASSWORD = $("#USER_PASSWORD").val();
					var USER_ID = $("#USER_ID").val();	
					$.ajax({
						url:"<c:out value="${pageContext.request.contextPath}"/>/adminUser/updPwd",
						datatype:"json",
						type:"post",
						data:{USER_ID:USER_ID,OLD_PASSWORD:OLD_PASSWORD,USER_PASSWORD:USER_PASSWORD},
						success:function(data){
							if(data!=null){
								  var obj = eval("(" + data + ")");
								  var at="";
								  if(obj.status == 'true'){
									 	alert("修改成功,重新登录后才生效");
									 	$("#p").window("close");
								  }else{
								  		alert("修改失败,失败原因"+obj.errorInfo);
								  }
								  
							}
						}
					
					});
				}
				
			});	
	 	}
</script>
</head>
<body>
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    	<li><a href="#">首页</a></li>
	    </ul>
    </div>
    
    <div class="mainindex">
	    <div class="welinfo">
		    <b><span style="color:#FF4500;font-size: 2 0px">${users.USER_ACCOUNT}&nbsp;&nbsp;&nbsp;</span><span id="timeFrame"></span>
		    	</b>&nbsp;[ &nbsp;${users.USER_MAIL} &nbsp;]
	    </div>
	    <div class="xline"></div>
	    <ul class="iconlist">
		    <li><img style="width: 32px;height: 32px" src="<c:out value="${pageContext.request.contextPath}"/>/images/ico/g1.png" /><p><a href="#" onclick="$('#w').window('open')">修改个人信息</a></p></li>
		    <li><img style="width: 32px;height: 32px" src="<c:out value="${pageContext.request.contextPath}"/>/images/ico/g10.png" /><p><a href="#" onclick="$('#p').window('open')">修改密码</a></p></li>
		    <li><img style="width: 32px;height: 32px" src="<c:out value="${pageContext.request.contextPath}"/>/images/ico/g4.png" /><p><a href="#" onclick="loginOut()">退出登录</a></p></li>
	    </ul>
	    <input type="hidden" value="${users.USER_ID}" id="loginUser"/>
	    <input type="hidden" value="${users.PERMISSION_ONE}" id="loginRole"/>
	    
	    <div class="xline"></div>
	    <div class="box"></div>
    </div>
    
    <div id="p" class="easyui-window" title="修改密码" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:420px;top:0px;display:none;">
		<div class="easyui-panel" title="" style="width:400px">
			<div style="padding:10px 60px 20px 60px">
			    <form id="hh" method="post" >
			    	<table cellpadding="5">
			    		<tr>
			    	    	<td class="pTop" align="left">原密码:</td>
			    	    	<td class="pTop"><input id="OLD_PASSWORD" name="OLD_PASSWORD" style="width:149px;" validType="length[4,32]" class="easyui-textbox" required="true" type="password" value=""/></td>
			    	    </tr>
			    		<tr style="">
			    			<td class="pTop" align="left">新密码:</td>
			    			<td class="pTop"><input id="USER_PASSWORD" name="USER_PASSWORD" style="width:149px;" validType="length[4,32]" class="easyui-textbox" required="true" type="password" value=""/></td>
			    		</tr>
			    		<tr  >
			    			<td class="pTop" align="left">确认新密码:</td>
			    			<td class="pTop"><input type="password" name="repassword" style="width:149px;" id="repassword" required="true" class="easyui-textbox"  validType="equalTo['#USER_PASSWORD']" invalidMessage="两次输入密码不匹配"/></td>
			    		</tr>
			    	</table>
			    	  	<div style="text-align:center;padding:5px">
					    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="updPwd()">提交</a>
					    </div>
			    </form>
		    </div>
		</div>
	</div>
    
    
    <div id="w" class="easyui-window" title="修改个人信息" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:470px;top:0px;display:none;">
		<div class="easyui-panel" title="" style="width:450px">
			<div style="padding:10px 60px 20px 60px">
			    <form id="ff" method="post" >
			    <input type="hidden" id="USER_ID" value="${users.USER_ID}"/>
			    <input type="hidden" id="ROLE_ID" value="${users.ROLE_ID}"/>
			    	<table cellpadding="5">
			    		<tr>
			    	    	<td class="pTop" align="left">账号:</td>
			    	    	<td class="pTop">${users.USER_ACCOUNT}</td>
			    	    </tr>
			    		<tr>
			    			<td class="pTop" align="left">用户名:</td>
			    			<td class="pTop"><input class="easyui-textbox" validType="length[2,32]"  value="${users.USER_NAME}" style="width: 200px" type="text" id="USER_NAME" name="USER_NAME" data-options="required:true"></input></td>
			    		</tr>
			    	    <tr>
			    	    	<td class="pTop" align="left">邮箱:</td>
			    	    	<td class="pTop"><input class="easyui-textbox" validType='email'    
                             invalidMessage="请输入正确的邮箱"  value="${users.USER_MAIL}" style="width: 200px" type="text" id="USER_MAIL" name="USER_MAIL" ></input></td>
			    	    </tr>
			    	    <tr>
			    			<td class="pTop" align="left">手机号码:</td>
			    			<c:if test="${users.USER_PHONE==0}">
							    	<td class="pTop" ><input type="text"  validType="phoneNum" name="USER_PHONE"   
		                             invalidMessage="请输入正确的手机号码" type="text"  value=""  style="width:149px;" id="USER_PHONE"  class="easyui-textbox"/></td>
							</c:if>
			    			<c:if test="${users.USER_PHONE!=0}">
							    	<td class="pTop" ><input type="text"  validType="phoneNum" name="USER_PHONE"   
		                             invalidMessage="请输入正确的手机号码" type="text"  value="${users.USER_PHONE}"  style="width:149px;" id="USER_PHONE"  class="easyui-textbox"/></td>
							</c:if>
			    			
			    		</tr>
			    		
			    		<tr>
			    			<td class="pTop" align="left">角色说明:</td>
			    			<td class="pTop"><textarea  style="width:200px;border: 1px" cols=25 rows=3  id="USER_ROLE">${users.USER_ROLE}</textarea></td>
			    		</tr>
			    	</table>
			    	  	<div style="text-align:center;padding:5px">
					    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
					    </div>
			    </form>
		    </div>
		</div>
	</div>
	
	<div id="s" class="easyui-window" title="短信发送记录" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:570px;height:400px;top:0px;display:none;">
		
		<table  class="grid">
	        <thead>
	            <tr>
	                <th width="150" align="center">手机号</th>
	                <th width="150" align="center">时间</th>
	                <th width="150" align="center">验证码</th>
	            </tr>
	        </thead>
	        <tbody id="smsCenter">
	                                        
	        </tbody>
	    </table>
	</div>
	
	
    <script type="text/JavaScript">
    	$(function(){
			var loginUser = $("#loginUser").val();
			var loginRole = $("#loginRole").val();
			
			if(loginUser==0){
			
				$('#shoWremindWriteLog').css('display','');
			}else if((loginRole&1)!=0){
			
				$('#shoWremindWriteLog').css('display','');
			}else{
			
				$('#shoWremindWriteLog').css('display','none');
			}
	    });

    	function remindWriteLog(){
		
			var iWidth = 1000;
			var iHeight = 450; 
			var iTop = (window.screen.availHeight - 30 - iHeight) / 2;
			var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;
			window.open("<c:out value="${pageContext.request.contextPath}"/>/remindWriteLogView?date="+new Date(),window, "width=" + iWidth + ", height=" + iHeight + ",top=" + iTop + ",left=" + iLeft + ",toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no,alwaysRaised=yes,depended=yes");			
		}
    	function sendSms(){
    		$("#smsCenter tr").remove();
    		 $.ajax({
					url:"<c:out value="${pageContext.request.contextPath}"/>/getSendSmsList",
					datatype:"json",
					type:"post",
					data:{},
					success:function(data){
						
						if(data!=null){

							var smsLen = data.split("\n").length; 
							var st = "";
							for(var i=0,j=smsLen;i<j;i++){
								if(data.split("\n")[i]!=''){
									var str="<tr><td>"+data.split("\n")[i].split(",")[0]+"</td>"+
									"<td>"+data.split("\n")[i].split(",")[1]+"</td>"+
									"<td>"+data.split("\n")[i].split(",")[2]+"</td></tr>";
									st+=str;
								}
							}
							$("#smsCenter").append(st);
							$('#s').window('open');
						}else{
						
							alert("没有查询到相关信息");
						}
					}
			})
    	}
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
</body>
</html>