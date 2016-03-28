<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<link type="text/css" href="${pageContext.request.contextPath }/css/main.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/ui/jquery-1.8.0.min.js"></script>
</head>
<body>
${request}
	欢迎你${userId eq null}========${userId }
	<a href="user/loginout.html">退出登录</a>
	<a href="user/test.html">查看hessian</a>
	<a href="user/myServletLogin.html">servletLogin</a>
	<%-- <div align="center">
		<table border="1">
			<tr>
				<td>编号</td>
				<td>姓名</td>
				<td>密码</td>
			</tr>
			<c:forEach items="${data}" var="s">
				<tr>
					<td>${s.id }</td>
					<td>${s.username}</td>
					<td>${s.password}</td>
				</tr>
			</c:forEach>
			
			
		</table>
		<div id="pageDiv" align="center">
			<span>当前页${page.nowPage}</span>&nbsp;
			<c:if test="${page.nowPage>1}">
				<a href="i.html?nowPage=${page.nowPage-1 }">上一页</a>&nbsp;&nbsp;</c:if>

			<c:if test="${page.countPage-page.nowPage>=1}">
				<a href="i.html?nowPage=${page.nowPage+1 }">下一页</a>
			</c:if>
			总页数:${page.countPage}&nbsp;&nbsp;
		</div>
	</div> --%>
	
	<div align="center">
		<table border="1">
			<tr>
				<td>编号</td>
				<td>姓名</td>
				<td>密码</td>
			</tr>
			<c:forEach  items="${userLs}" var="s" >
				<tr>
					<td>${s.id }</td>
					<td>${s.username}</td>
					<td>${s.password}</td>
				</tr>
			</c:forEach>
			
			
		</table>
			</div>
	<div style="height: 68px; width: 928px;">
								<jsp:include page="/javapager.html"></jsp:include>
							</div>
</body>
</html>