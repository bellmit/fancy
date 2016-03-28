<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=0">
<title>会员登录</title>
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<c:set var="request" value="${pageContext.request.contextPath }" />
</head>
<body>
	验证码：
	<img name="d" id="verifyCode" onclick="changeValidate()" src="${request}/validate.jpg">
	<script type="text/javascript">
		function changeValidate() {
			document.getElementById("verifyCode").src = '${request}/validate.jpg?t=' + Math.random(1000);
		}
	</script>
	<form method="post" action="mylogin">
		<div id="L_login">
			<div id="dingdan_nav">
				<img class="home" src="${pageContext.request.contextPath}/images/Lding_left.gif">
				<h2>会员登录</h2>
			</div>
			<div class="dengLu_cuoWu">
				<li style="list-style: none; color: red">${isSuccess}</li>
				<ul>
					<li><input name="userName" id="userName" type="text" placeholder="用户名" /></li>
					<!--<a class="diangdan_tab_border"></a>-->
					<li><input name="passWord" id="passWord" type="password" placeholder="密码" /></li>
				</ul>
				<div class="re_MenSou_Bu">
					<input type="submit" value="登录" />
				</div>
				<div class="re_MenSou_Text">提示：通过会员登录，可以把天联网账号与微信号进行绑定， 绑定后，会员可以收到天联网微信提醒信息，没有账号的用户请到天联网（ww.telling.cn）进行申请。</div>
			</div>
		</div>
	</form>
</body>
</html>