<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webPage/include/taglib.jsp"%>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML>
<html>
<head>
<link rel="shortcut icon" type="image/x-icon" href="${ctx}/images/favicon.png?t=1451964198000" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎登录${fns:getConfig('productName')}</title>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" /> 
<style type="text/css">
	.c{
		  float: right;
		  margin-top: 42px;
		  margin-right: 126px;
		  font-size: 14px;
		  color: red;
	}
	.bg{
	 style="background-color: #1c77ac;background-image: url(${ctx}/images/light.png); 
	  background-repeat: no-repeat;
	  background-position: center top; 
	 overflow: hidden;" 
		}
</style> 
<script type="text/javascript">var ctx="${ctx}";</script>
</head>
	<body style="background-color: #1c77ac;background-image: url(${ctx}/images/light.png); 
			  background-repeat: no-repeat;
			  background-position: center top; 
			 overflow: hidden;" >
		<div id="mainBody">
			<div id="cloud1" class="cloud"></div>
			<div id="cloud2" class="cloud"></div>
		</div>
		<div class="logintop">
			<span>欢迎登录后台管理界面平台</span>
			<ul>
				<li><a href="/">回首页</a></li>
				<li><a href="#">帮助</a></li>
				<li><a href="#">关于</a></li>
			</ul>
		</div>
		<div class="loginbody">
			<span class="systemlogo"></span>
			<div class="loginbox">
			 <span class="c">${message}</span>
			 <c:if test="${isValidateCodeLogin}">
			 	<div class="validateCode">
					<label class="input-label mid" for="validateCode">验证码</label>
					<sys:validateCode name="validateCode" inputCssStyle="margin-bottom:0;"/>
				</div>
			 </c:if>
					<form method="post" action="${ctx}/login">
						<ul>
							<li><input name="username" autocomplete="off" type="text" class="loginuser" value="admin" placeholder="请输入登录名" onclick="JavaScript:this.value=''" /></li>
							<li><input name="password" autocomplete="off" type="password" class="loginpwd" value="123qwe" placeholder="请输入密码" onclick="JavaScript:this.value=''" /></li>
							<li class="test">
			                    <img alt="验证码" onclick="changeValidate()" id="verifyCode" src="${ctx }/validate.html">&nbsp;&nbsp;&nbsp;
			                    <input name="validate" type="text" class="validate" /></li>
							<li>
							<input name="" type="submit" class="loginbtn" value="登录" /> 
							<label  for="rememberMe"><input name="rememberMe" id="rememberMe" type="checkbox" ${rememberMe ? 'checked' : ''} />记住密码</label>
							<label><a href="javascript:">忘记密码？</a></label>
							</li>
							<input type="hidden" name="remembered" value="true">
						</ul>
					</form>
			</div>
	
		</div>
		<div class="loginbm">
			版权所有 ${fns:getConfig('copyrightYear')} 
			<a>${fns:getConfig('productName')}</a> 仅供学习交流，勿用于任何商业用途  ${fns:getConfig('version')} --在线人数：${online }
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