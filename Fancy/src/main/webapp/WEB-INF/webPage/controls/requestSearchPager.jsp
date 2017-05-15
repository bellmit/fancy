<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.Common.UrlCommon"%>
<%@ page import="com.Common.Pager.PageVo"%>
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
<div class="page" style="width:1200px;">    
<ul class="clearfix">
	<li>
	<% if(pager.getLastPage()==1&&pager.getPageNow()==1){ %>
	<a class="big" href="javascript:void(0);">上一页</a>
	<% }else{ %>
	<a class="big" href="<%=pager.getPageUrl(pager.getLastPage())%>">上一页</a>
	<% } %>	
	</li>
<% 
	int counter = 0;
	for(int i=pager.getStartPage(); i<=pager.getEndPage(); i++){
		if(pager.getEndPage()-pager.getStartPage()>6&&counter>5){
			%>
			<li>...</li>
			<li><a href="<%=pager.getPageUrl(pager.getEndPage())%>"><%=pager.getEndPage()%></a></li>
			<%		
			break;
		}
		if(i==pager.getPageNow())
		{%>
			<li><a class="active" href="<%=pager.getPageUrl(i)%>"><%=i%></a></li>
		<%}else{%>		
			<li><a href="<%=pager.getPageUrl(i)%>"><%=i%></a></li>
		<%}
		counter++;
	}
%>	
	<li>
	<% if(pager.getNextPage()==pager.getTotalPage()&&pager.getPageNow()==pager.getTotalPage()){ %>
	<a class="big" href="javascript:void(0);">下一页</a>
	<% }else{ %>
	<a class="big" href="<%=pager.getPageUrl(pager.getNextPage())%>">下一页</a>
	<% } %>		
	</li>
</ul>
</div>
<% } %>		