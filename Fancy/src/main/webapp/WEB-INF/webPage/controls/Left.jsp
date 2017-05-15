<%@page import="com.Common.UrlCommon"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="<%=UrlCommon.ContextPath(request) %>/css/left.css" type="text/css" rel="stylesheet" />
<script src="<%=UrlCommon.ContextPath(request) %>/js/sdmenu.js" type="text/javascript"></script>
<script type="text/javascript">
	// <![CDATA[
	var myMenu;
	window.onload = function() {
		myMenu = new SDMenu("my_menu");
		myMenu.init();
	};
	// ]]>
	</script>
</head>
<body>
<div id="my_menu" class="sdmenu leftbar">
      <div>
        <span>用户管理</span>
        <a href="/TellingB2B/Users/List.html">用户查询</a>
        <a href="/TellingB2B/Users/AddNew.html">新建用户</a>
      </div>
      <div>
        <span>员工管理</span>
        <a href="/TellingB2B/Staff/List.html">员工查询</a>
        <a href="/TellingB2B/Staff/AddNew.html">增加员工</a>
      </div>
      <div class="collapsed">
        <span>机构/区域管理</span>
        <a href="/TellingB2B/OrgManage/List/_1.html">组织机构管理</a>
        <a href="/TellingB2B/AreaManage/List/_1.html">区域管理</a>
      </div>
      <div>
        <span>个人资料</span>
        <a href="#">Current or not</a>
        <a href="#">Current or not</a>
        <a href="#">Current or not</a>
        <a href="#">Current or not</a>
      </div>
       <div>
        <span>会员中心</span>
        <a href="#">Current or not</a>
        <a href="#">Current or not</a>
        <a href="#">Current or not</a>
        <a href="#">Current or not</a>
      </div>
    </div>
</body>
</html>