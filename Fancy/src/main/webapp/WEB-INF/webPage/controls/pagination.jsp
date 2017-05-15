<%@page import="com.Common.UrlCommon"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.Common.Pager.PageVo"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	PageVo pager;
	if (request.getAttribute("java_pager") != null) {
		pager = (PageVo) request.getAttribute("java_pager");
		int pageNow = pager.getPageNow();
		if (pageNow >= 6) {
			if (pager.getEndPage() - pageNow <= 2) {
				pager.setStartPage(pager.getEndPage() - 6);
			} else {
				pager.setStartPage(pageNow - 4);
			}
		} else {
			pager.setStartPage(1);
		}
%>
<script type="text/javascript">
$(document).ready(function(){
	$("#btnJump").click(function(){
		var page = $.trim($("#txtJumpPage").val());
		if (page == '' ||!$.isNumeric(page)|| page<1 || page><%=pager.getEndPage()%>){
			alert('无效的页码！');
			$("#txtJumpPage").val("");
		}
		else{
			goPage(page);
		}
		
		return false;
	});
});
	function prePage(i) {
		$("#curPage").val(parseInt(i) - 1);
		document.forms[0].submit();
	}
	function nextPage(i) {
		$("#curPage").val(parseInt(i) + 1);
		document.forms[0].submit();
	}
	function goPage(i) {
		$("#curPage").val(i);
		document.forms[0].submit();
	}
</script>

<div class="fanye" style="width: auto">
	<ul>

		<li>
			<%
				if (pager.getLastPage() == 1 && pager.getPageNow() == 1) {
			%> 上一页 <%
				} else {
			%>
			<a class="big" href="#" onclick='prePage("<%=pager.getPageNow()%>")'>上一页</a>
			<%
				}
			%>
		</li>

		<%
			int counter = 0;
				for (int i = pager.getStartPage(); i <= pager.getEndPage(); i++) {
					if (pager.getEndPage() - pager.getStartPage() > 6
							&& counter > 5) {
		%>
		<li>...</li>
		<li><a href='#' onclick='goPage("<%=pager.getEndPage()%>")'><%=pager.getEndPage()%></a></li>
		<%
			break;
					}
					if (i == pager.getPageNow()) {
		%>
		<li><a class="active" href="#" onclick="goPage(<%=i%>)"><%=i%></a></li>
		<%
			} else {
		%>
		<li><a href="#" onclick="goPage(<%=i%>)"><%=i%></a></li>
		<%
			}
					counter++;

				}
		%>

		<li>
			<%
				if (pager.getNextPage() == pager.getEndPage()
							&& pager.getPageNow() == pager.getEndPage()) {
			%>
			下一页 <%
 	} else {
 %> <a
			class="big" href="#" onclick='nextPage("<%=pager.getPageNow()%>")'>下一页</a>
			<%
				}
			%>
		</li>

<li>
		<font>共<%=pager.getTotalPage()%>页 跳转到&nbsp;
	</font> <input name="" type="text" class="kuang" id="txtJumpPage" style="width: 35px" /> <font>&nbsp;页&nbsp;</font>
	<input name="" type="image" id="btnJump"
		src="<%=UrlCommon.ContextPath(request)%>/images/managerImages/go.jpg" />
		</li>
	</ul>
</div>
<%-- <div class="jump">
	<font>共<%=pager.getTotalPage()%>页 跳转到&nbsp;
	</font> <input name="" type="text" class="kuang" id="txtJumpPage" /> <font>&nbsp;页&nbsp;</font>
	<input name="" type="image" id="btnJump"
		src="<%=UrlCommon.ContextPath(request)%>/images/managerImages/go.jpg" />
</div> --%>

<!-- hidden -->
<input type="hidden" id="curPage" name="curPage" />
<%
	}
%>