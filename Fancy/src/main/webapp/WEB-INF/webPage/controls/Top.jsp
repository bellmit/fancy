<%@page import="com.Common.UrlCommon"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="<%=UrlCommon.ContextPath(request) %>/css/header.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="<%=UrlCommon.ContextPath(request) %>/js/jquery.js"></script>
</head>
<body>
<div class="header">
  <div class="headerleft"><img src="<%=UrlCommon.ContextPath(request) %>/images/managerImages/b2blogo.jpg" width="212" height="86" alt="logo" /></div>
  <div class="headermid">
     <p><span class="c0d3s">张三</span><span class="c666s">，欢迎您！</span>2013-1-6 12：26 </p>
  </div>
  <div class="headerright">
    <a>退出</a>
  </div>
</div>
</body>
</html>