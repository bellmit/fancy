<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="common/taglibs.jsp"%>

<html>
<head>
<title>Spring MVC Exception</title>
<script type="text/javascript">
function ajaxTestException()
{
/* 	$.ajax( {
		type : 'GET',
		url : '${basePath}/josonException.html',   
		async: false,//禁止ajax的异步操作，使之顺序执行。
		dataType : 'json',
		success : function(data,textStatus){
			alert(JSON.stringify(data));
		},
		error : function(data,textstatus){
			alert(data.responseText);
		}
	}); */
	$.get("${basePath}/josonException.html",function(data){
				alert(JSON.stringify(data));
		});
	}
function SystemException()
{
	 window.location.href=" <c:url value="/SystemException.html"/>";
}
function BusinessException()
{
	 window.location.href=" <c:url value="/BusinessException.html"/>";
}
	$(function(){
		var fields = $("select, :radio").serializeArray();
		jQuery.each( fields, function(i, field){
		  $("#results").append(field.value + " ");
		});
	});	
</script>
</head>
<body>
	<table>
	 <tr>
	  	 <td style="height:72px;">
	          <div>
            	<input type=button value="Ajax Exception Test" onclick="ajaxTestException();"></input>
           	 </div>
	      </td>
	      	 <td style="height:72px;">
	          <div>
            	<input type=button value="System Exception Test" onclick="SystemException();"></input>
           	 </div>
	      </td>
	      	 <td style="height:72px;">
	          <div>
            	<input type=button value="business Exception Test" onclick="BusinessException();"></input>
           	 </div>
	  </tr>
	</table>
	
	<p id="results"><b>Results:</b> </p>
<form>
  <select name="single">
    <option>Single</option>
    <option>Single2</option>
  </select>
  <select name="multiple" multiple="multiple">
    <option selected="selected">Multiple</option>
    <option>Multiple2</option>
    <option selected="selected">Multiple3</option>
  </select><br/>
  <input type="checkbox" name="check" value="check1"/> check1
  <input type="checkbox" name="check" value="check2" checked="checked"/> check2
  <input type="radio" name="radio" value="radio1" checked="checked"/> radio1
  <input type="radio" name="radio" value="radio2"/> radio2
</form>
</body>
</html>
