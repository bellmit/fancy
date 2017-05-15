<%@page import="com.Common.UrlCommon"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@page import="com.Common.Pager.PageVo"%>

<%
PageVo pager;
if(request.getAttribute("java_pager")!=null)
{
	 pager = (PageVo)request.getAttribute("java_pager");
	 int pageNow = pager.getPageNow();
	 if(pageNow>=6){
		 if(pager.getEndPage()-pageNow<=2){
			 pager.setStartPage(pager.getEndPage()-6);
		 }else{
			 pager.setStartPage(pageNow-4);
		 }
	 }else{
		 pager.setStartPage(1);
	 }
%>
<script type="text/javascript" src="<%=UrlCommon.ContextPath(request) %>/js/Utility.js"></script> 
<script type="text/javascript">
<!--
	$(document).ready(function(){
		$("#btnJump").click(function(){
			var page = $.trim($("#txtJumpPage").val());
			if (page == '' ||!$.isNumeric(page)|| page<1 || page><%=pager.getEndPage()%>){
				alert('无效的页码！');
				$("#txtJumpPage").val("");
			}
			else{
				var url = '<%=pager.getRestfulPageUrl(1)%>';
				window.open(url.replace('1.html',page+'.html'),'_self');
			}
			
			return false;
		});
	});
//-->
</script>
<div class="fanye">
	<ul>
	
		<li>
			<% if(pager.getLastPage()==1&&pager.getPageNow()==1){ %>
			上一页
			<% }else{ %>
			<a class="big" href="<%=pager.getRestfulPageUrl(pager.getLastPage())%>">上一页</a>
			<% } %>		
		</li>
		
<% 
	int counter = 0;
	for(int i=pager.getStartPage(); i<=pager.getEndPage(); i++)
	{
		if(pager.getEndPage()-pager.getStartPage()>6&&counter>5){
			%>
			<li>...</li>
			<li><a href="<%=pager.getRestfulPageUrl(pager.getEndPage())%>"><%=pager.getEndPage()%></a></li>
			<%		
			break;
		}
		if(i==pager.getPageNow())
		{%>
			<li><a class="active" href="<%=pager.getRestfulPageUrl(i)%>"><%=i%></a></li>
		<%}else{%>		
			<li><a href="<%=pager.getRestfulPageUrl(i)%>"><%=i%></a></li>
		<%}
		counter++;
		
	}
%>	

<li>	
<% if(pager.getNextPage()==pager.getTotalPage()&&pager.getPageNow()==pager.getTotalPage()){ %>
下一页
<% }else{ %>
<a class="big" href="<%=pager.getRestfulPageUrl(pager.getNextPage())%>">下一页</a>
<% } %>		
</li>

	</ul></div>
      <div class="jump">
	      <font>共<%=pager.getTotalPage() %>页 跳转到&nbsp;</font>
	      <input name="" type="text" class="kuang" id="txtJumpPage" />
	      <font>&nbsp;页&nbsp;</font>
	      <input name="" type="image" id="btnJump" src="<%=UrlCommon.ContextPath(request) %>/images/managerImages/go.jpg" />
      </div>


<%} %>


