
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  <c:set var="ctx" value="${pageContext.request.contextPath}" />
 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="kiben" content="no-cache" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" /><!-- 为了保证浏览器统一渲染，务必保留这段话！！！ -->
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<meta http-equiv="content-language" content="zh-CN" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />

<title>测试图片上传</title>

<link rel="stylesheet" type="text/css" href="${ctx}/css/Int.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/common.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/base.css"/>
<script type="text/javascript">var ctx = "${ctx}";</script>
<script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/ajaxfileupload.js"></script>

<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/js/ajax_common.js"></script>

<script type="text/javascript" src="${ctx}/js/vote/active_operate.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.colorpicker.js"></script>
</head>
<body >

<div style="height: 95%;overflow: auto;">

<div style="align:left;">
<form id="addForm" class="form-horizontal" role="form">
  <table >
   
     <tr>
      <td height="80" ><pre>*</pre><b>主题封面图:</b></td>
      <td>
      			 <div class="imghoverstyle100" style="position:relative;">
      			<img src="http://pic.wenwen.soso.com/p/20110624/20110624235550-746018327.jpg" width="100px" height="100px"/> </div>
	      	 <div class="imghoverstyle100" style="position:relative;">
	      	  <i class="delImg" onclick="deleteImage(this);">x</i> 
			      	  <a class="add-img"> <span>+</span> 
			      	  <img class="uploadImg">
			          <input type="hidden" name="cover_pic" id="cover_pic" value="${ vote.cover_pic}">
			          <input type="file" name="imageFile" id="imageFile" class="filePrew100" onchange="uploadImage(this);">
			          </a>
			      </div>
      </td>
    </tr>
    <tr>
      <td height="71" ><pre>*</pre><b>请选择背景颜色值:</b></td>
      <td >
      		<input name="back_color" id="cp1" type="text"  value="${vote.back_color}" />
      </td>
    </tr>
    <tr style="height: 330px">
      <td><input type="button" class="bluebut" style="float: right;margin: 30px 20px 0px 5px;" value="保存"  onclick="doUpdate()"></td>
      <td><input type="button" class="Gaybut" style="float: left;margin: 30px 20px 0px 5px;" value="返回"  onclick="window.history.back()"></td>
    </tr>
    </table>

</form>
  
</div>

</div>
	<script type="text/javascript">
		$(function()
				{
			$('#cp1').colorPicker();
				});
	</script>
</body>
</html>