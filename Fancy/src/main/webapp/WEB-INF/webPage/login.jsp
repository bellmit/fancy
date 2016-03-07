<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎登录后台管理系统</title>
<%-- <link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" /> --%>
 <style type="text/css">
	.c{
  float: right;
  margin-top: 42px;
  margin-right: 126px;
  font-size: 14px;
  color: red;
	}
</style> 
<script type="text/javascript">var ctx="${ctx}";</script>
</head>
<body 
<%-- style="background-color: #1c77ac;  --%>
<%-- background-image: url(${ctx}/images/light.png); --%>
<!--  background-repeat: no-repeat; -->
<!--   background-position: center top; -->
<%--    overflow: hidden;"  --%>
   >

	<div id="mainBody">
		<div id="cloud1" class="cloud"></div>
		<div id="cloud2" class="cloud"></div>
	</div>
	<div class="logintop">
		<span>欢迎登录后台管理界面平台</span>
		<ul>
			<li><a href="#">回首页</a></li>
			<li><a href="#">帮助</a></li>
			<li><a href="#">关于</a></li>
		</ul>
	</div>
 <form:input path="account" class="login_input"/>
	<div class="loginbody">
		<span class="systemlogo"></span>
		<div class="loginbox">
		 <span class="c">${message}</span> 
			<ul>
				<form method="post" action="${ctx }/loginValid.html">
					<li><input name="account" type="text" class="loginuser" value="admin" onclick="JavaScript:this.value=''" /></li>
					<li><input name="password" type="password" class="loginpwd" value="123qwe" onclick="JavaScript:this.value=''" /></li>
					<li class="test">
					<%-- <img alt="验证码" src="${ctx}/images/kaptcha.jpg" title="点击更换"  
                    id="img_captcha" onclick="javascript:refreshCaptcha();"> --%>
                    <img alt="验证码" onclick="changeValidate()" id="verifyCode" src="${ctx }/validate.html">&nbsp;&nbsp;&nbsp;<input name="validate"
						type="text" class="validate" onclick="JavaScript:this.value=''" /></li>
					<li><input name="" type="submit" class="loginbtn" value="登录" /> <label> <input name="" type="checkbox" value="" checked="checked" />记住密码
					</label><label><a href="#">忘记密码？</a></label></li>
					<input type="hidden"   name="remembered" value="true">
				</form>
			</ul>


		</div>

	</div>

	<div class="loginbm">
		版权所有 2013 <a href="http://www.uimaker.com">fancy</a> 仅供学习交流，勿用于任何商业用途
	</div>

	<script language="JavaScript" src="${ctx}/js/jquery-2.1.4.min.js"></script>
	<script src="${ctx}/js/cloud.js" type="text/javascript"></script>
	<script language="javascript">
		$(function() {
			$('.loginbox').css({
				'position' : 'absolute',
				'left' : ($(window).width() - 692) / 2
			});
			$(window).resize(function() {
				$('.loginbox').css({
					'position' : 'absolute',
					'left' : ($(window).width() - 692) / 2
				});
			});
		});
		function changeValidate() {
			document.getElementById("verifyCode").src = ctx+'/validate.html?t='
					+ Math.random(1000);
		}
		function refreshCaptcha(){  
		    document.getElementById("img_captcha").src="images/kaptcha.jpg?t=" + Math.random();  
		}  
	</script>
</body>

</html>
