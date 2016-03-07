/*
 * @(#) LocalServcieRouter.java 2015年4月3日
 *
 * Copyright (c) 2014, SIMPO Technology. All Rights Reserved.
 * SIMPO Technology. CONFIDENTIAL
 */
package cn.telling.rcb.router;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.stereotype.Component;

import cn.telling.utils.WebServerUtils;


@Component("cn.telling.rcb.router.LocalServiceRouter")
public class LocalServiceRouter implements ServiceRouter {

	@Override
	public URL getServiceURL(String servicename) {
		try {
			URL u= new URL("http", getServiceAddress(), getServicePort(), "");
			return u;
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String getServiceAddress() {
		return "localhost";
	}

	private int getServicePort() {
		int port = 0;
		try {
			port = WebServerUtils.getPort(WebServerUtils.DEFAULT_TOMCAT_PROTOCOL,
					WebServerUtils.DEFAULT_TOMCAT_SCHEME);
			port = port == 0 ? -2 : port;
			port = port   == 80 ? -1 : port;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return port;
	}
}
