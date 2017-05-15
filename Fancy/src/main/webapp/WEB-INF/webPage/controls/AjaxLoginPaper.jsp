<%@page import="com.Common.UrlCommon"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.Common.Pager.PageVo" %>

<%
PageVo pager;
if(request.getAttribute("java_pager")!=null)
{
	 pager = (PageVo)request.getAttribute("java_pager");
	 int pageNow = pager.getPageNow();
	 if(pageNow>=6){
		 if(pager.getEndPage()-pageNow<=2){
			 if(pager.getEndPage()-pageNow==0){
				 pager.setStartPage(pager.getEndPage()-5);
			 }else{
				 pager.setStartPage(pager.getEndPage()-6); 
			 }
		 }else{
			 pager.setStartPage(pageNow-4);
		 }
	 }else{
		 pager.setStartPage(1);
	 }
%>
<div class="my-seachpage">
   
<ul>
	<li class="uppage">
	<% if(pager.getLastPage()==1&&pager.getPageNow()==1){ %>
	<a  href="javascript:void(0);">上一页</a>
	<% }else{ %>
	<a  href="javascript:void(0);"  onclick="changePage(<%=pager.getLastPage()%>)" >上一页</a>
	<% } %>	
	</li>
<% 
	int counter = 0;
	for(int i=pager.getStartPage(); i<=pager.getEndPage(); i++){
		if(pager.getEndPage()-pager.getStartPage()>6&&counter>5){
			%>
			<li class="pnoboder ">...</li>
			<li class="pages"><a href="javascript:void(0);"  onclick="changePage(<%=pager.getEndPage()%>)" ><%=pager.getEndPage()%></a></li>
			<%		
			break;
		}
		if(i==pager.getPageNow())
		{%>
			<li class="pages"><a href="javascript:void(0);"  class="pageselect"   onclick = "changePage(<%=i%>);" ><%=i%></a></li>
		<%}else{%>		
			<li class="pages"><a href="javascript:void(0);"   onclick = "changePage(<%=i%>);" ><%=i%></a></li>
		<%}
		counter++;
	}
%>	
	<li class="nextpage">
	<% if(pager.getNextPage()==pager.getTotalPage()&&pager.getPageNow()==pager.getTotalPage()){ %>
	<a href="javascript:void(0);">下一页</a>
	<% }else{ %>
	<a href="javascript:void(0);"   onclick = "changePage(<%=pager.getNextPage()%>);">下一页</a>
	<% } %>		
	</li>
</ul>
</div>
<% } %>		