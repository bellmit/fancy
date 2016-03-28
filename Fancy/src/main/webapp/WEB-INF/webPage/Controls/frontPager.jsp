<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="cn.telling.common.vo.*"%>
<ul class="fenPage">
	<li <c:if test="${java_pager.pageNow eq 1 }">class="up"</c:if> >
		<c:choose>
			<c:when test="${java_pager.totalPage eq 1 or java_pager.pageNow eq 1 }">
				<a  class="g_meiFire" >上一页</a>
			</c:when>
			<c:otherwise>
				<a  class="g_meiFire"   onclick="searchFun(${java_pager.pageNow-1 })">上一页</a>
			</c:otherwise>
		</c:choose>
	</li>
	<c:choose>
		<c:when test="${java_pager.totalPage<=7 }">
			<c:forEach begin="1" end="${java_pager.totalPage }" varStatus="i">
				<li><a <c:if test="${i.index eq java_pager.pageNow }">class="g_dangAien"</c:if>   onclick="searchFun(${i.index})">${i.index}</a></li>
			</c:forEach>
		</c:when>
		<c:when test="${java_pager.totalPage eq java_pager.pageNow}">
			<c:forEach begin="1" end="4" varStatus="i">
				<li><a <c:if test="${i.index eq java_pager.pageNow }">class="g_dangAien"</c:if>   onclick="searchFun(${i.index})">${i.index}</a></li>
			</c:forEach>
			<li  class="zh_dian"><a  >...</a></li>
			<c:forEach begin="${java_pager.totalPage-1 }" end="${java_pager.totalPage }" varStatus="i">
				<li><a <c:if test="${i.index eq java_pager.pageNow }">class="g_dangAien"</c:if>   onclick="searchFun(${i.index})">${i.index}</a></li>
			</c:forEach>
		</c:when>
		<c:when test="${java_pager.pageNow+2 >java_pager.totalPage }">
			<c:forEach begin="1" end="3" varStatus="i">
				<li><a <c:if test="${i.index eq java_pager.pageNow }">class="g_dangAien"</c:if>   onclick="searchFun(${i.index})">${i.index}</a></li>
			</c:forEach>
			<li  class="zh_dian"><a  >...</a></li>
			<c:forEach begin="${java_pager.totalPage-2 }" end="${java_pager.totalPage }" varStatus="i">
				<li><a <c:if test="${i.index eq java_pager.pageNow }">class="g_dangAien"</c:if>   onclick="searchFun(${i.index})">${i.index}</a></li>
			</c:forEach>
		</c:when>
		<c:when test="${java_pager.pageNow-3 >1 }">
			<c:forEach begin="${java_pager.pageNow-3 }" end="${java_pager.pageNow+1 }" varStatus="i">
				<li><a <c:if test="${i.index eq java_pager.pageNow }">class="g_dangAien"</c:if>   onclick="searchFun(${i.index})">${i.index}</a></li>
			</c:forEach>
			<li  class="zh_dian"><a  >...</a></li>
			<li><a    onclick="searchFun(${java_pager.totalPage})">${java_pager.totalPage}</a></li>
		</c:when>
		<c:when test="${java_pager.pageNow-3 <=1 }">
			<c:forEach begin="1" end="5" varStatus="i">
				<li><a <c:if test="${i.index eq java_pager.pageNow }">class="g_dangAien"</c:if>   onclick="searchFun(${i.index})">${i.index}</a></li>
			</c:forEach>
			<li  class="zh_dian"><a  >...</a></li>
			<li><a    onclick="searchFun(${java_pager.totalPage})">${java_pager.totalPage}</a></li>
		</c:when>
	</c:choose>
	
	<li <c:if test="${java_pager.pageNow eq java_pager.totalPage }">class="down"</c:if> >
		<c:choose>
			<c:when test="${java_pager.totalPage eq 1 or java_pager.pageNow eq java_pager.totalPage }">
				<a class="g_lastNUf" >下一页</a>
			</c:when>
			<c:otherwise>
				<a class="g_lastNUf"  onclick="searchFun(${java_pager.pageNow+1 })">下一页</a>
			</c:otherwise>
		</c:choose>
	</li>
	
	<li>
		<span>共${java_pager.totalPage}页&nbsp;跳转到&nbsp;</span>
		<input type="text" id="txtJumpPage" value="${txtJumpPage }"/>
		<span>&nbsp;页&nbsp;</span>
		<a href="javascript:void(0)" id="actionJumpPage" class="z_go">GO</a>
	</li>
</ul>
<script>
//跳转
$("#actionJumpPage").click(function(){
	var page=$.trim($("#txtJumpPage").val());  
	var reg=/^[0-9]*[1-9][0-9]*$/;
	var totalPage=${java_pager.totalPage};
	if(!reg.test(page)||page>totalPage){
		alert("无效的页码");
		("#txtJumpPage").val("");
	}else{
		searchFun(page);
	}
});
</script>

