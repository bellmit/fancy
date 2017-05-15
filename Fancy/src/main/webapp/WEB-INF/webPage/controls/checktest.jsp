<%@page import="com.ordermanager.util.OrderConstantValues"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="com.Common.UrlCommon"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/checkBoxSelect/style.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/checkBoxSelect/jquery-ui.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/checkBoxSelect/jquery.multiselect.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/checkBoxSelect/prettify.css"/>

<script src= "<%=UrlCommon.ContextPath(request)%>/js/checkBoxSelect/jquery.js" type="text/javascript"></script>
<%-- <script src= "<%=UrlCommon.ContextPath(request)%>/js/checkBoxSelect/jquery-ui.min.js" type="text/javascript"></script> --%>
<!--控制下拉复选框-->
<script src= "<%=UrlCommon.ContextPath(request)%>/js/checkBoxSelect/jquery.multiselect.js" type="text/javascript"></script>
<script src= "<%=UrlCommon.ContextPath(request)%>/js/checkBoxSelect/prettify.js" type="text/javascript"></script>
<script type="text/javascript">

	//添加用戶信息
 	$(function(){
 		$("#provinceId").multiselect({
 			selectedList: 4
 		});
 		
 	});
 	$("#provinceId").multiselect({ 
 	   selectedText: function(numChecked, numTotal, checkedItems){ 
 	      return numChecked + ' of ' + numTotal + ' checked'; 
 	   } 
 	});
 </script>
</head>
<body>
<jsp:include page="/getcheckboxselect.html">
	<jsp:param name="showLev" value="2"></jsp:param> 
</jsp:include>
</body>
</html>