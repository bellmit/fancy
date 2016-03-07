<%@page import="com.fancy.hessian.BasicAPI"%>
<%@ pageimport="com.caucho.hessian.client.HessianProxyFactory,cn.fancy.hessian.BasicService"%>
<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%
	HessianProxyFactory factory = new HessianProxyFactory();
	String url =
			("http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/fancy");
	out.println(url + "=======" + request.getScheme() + "==========");
	BasicAPI basic = (BasicAPI) factory.create(BasicAPI.class, url);
	out.println("Hello: " + basic.hello());
	out.println("Hello: " + basic.getUser().getUserName());
	out.println("Hello: " + basic.getUser().getPassword());
%>