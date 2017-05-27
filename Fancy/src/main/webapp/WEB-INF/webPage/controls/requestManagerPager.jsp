<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="cn.telling.common.UrlCommon"%>
<%@ page import="cn.telling.common.pager.PageVo"%>
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
<script type="text/javascript">
function checkPageNum(){
	var page = document.getElementById("txtJumpPage").value;
	if (page == '' ||!$.isNumeric(page)||page<1 || page><%=pager.getTotalPage()%>){
		alert('无效的页码！');
	}else{
		var url = '<%=pager.getPageUrl(1)%>';
		window.open(url.replace('page=1','page='+page),'_self');
	}
	return false;
}
</script>
<div class="fanye">
	<ul>
		<li>
			<% if(pager.getLastPage()==1&&pager.getPageNow()==1){ %>
			<span class="orbig">上一页</span>
			<% }else{ %>
			<a href="<%=pager.getPageUrl(pager.getLastPage())%>">上一页</a>
			<% } %>		
		</li>
		
<% 
	int counter = 0;
	for(int i=pager.getStartPage(); i<=pager.getEndPage(); i++)
	{
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
<span class="orbig">下一页</span>
<% }else{ %>
<a href="<%=pager.getPageUrl(pager.getNextPage())%>">下一页</a>
<% } %>		
</li>
	</ul></div>
      <div class="jump">
	      <font>共<%=pager.getTotalPage() %>页 跳转到&nbsp;</font>
	      <input name="" type="text" class="kuang" id="txtJumpPage" value="<%=pager.getPageNow()%>"/>
	      <font>&nbsp;页&nbsp;</font>
	      <input name="" type="image" id="btnJump" onclick="checkPageNum();" src="<%=UrlCommon.ContextPath(request) %>/images/managerImages/go.jpg" />
      </div>
<%} %>