package cn.telling.shirocontroller;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.springframework.stereotype.Component;

import cn.telling.utils.LogUtils;
import cn.telling.utils.TCPIPUtil;

@Component("frameperms")
public class FramePermissionsAuthorizationFilter extends PermissionsAuthorizationFilter {

	@Override
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws IOException
	{
		HttpServletRequest req = (HttpServletRequest) request;
		Subject subject = getSubject(request, response);
		String uri = req.getRequestURI();
		String requestURL = req.getRequestURL().toString();//带协议的全访问地址
		String contextPath = req.getContextPath();//请求项目名称 /Fancy/
		
		int i = uri.indexOf(contextPath);
		if (i > -1)
		{
			uri = uri.substring(i + contextPath.length());
		}
		if (StringUtils.isNotEmpty(uri))
		{
			uri = "/";
		}
		boolean permitted = false;
		if ("/".equals(uri))
		{
			permitted = true;
		} else
		{
			permitted = subject.isPermitted(uri);
		}
		String isqx = "否";
		if (permitted)
		{
			isqx = "是";
		}
		// 写日志
		String ip = TCPIPUtil.getIpAddr(req);
		LogUtils.info("访问地址:"+requestURL.replace("localhost", ip)+",是否通过:"+isqx);
		return permitted;
	}
}
