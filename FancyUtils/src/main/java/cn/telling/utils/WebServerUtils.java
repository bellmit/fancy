/*
 * @(#) WebServerUtils.java 2015年4月3日
 *
 * Copyright (c) 2014, SIMPO Technology. All Rights Reserved.
 * SIMPO Technology. CONFIDENTIAL
 */
package cn.telling.utils;

import java.util.Iterator;
import java.util.Set;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;


public class WebServerUtils {

	public static String DEFAULT_TOMCAT_PROTOCOL = "HTTP/1.1";
	public static String DEFAULT_TOMCAT_SCHEME = "http";

	public static int getPort(String protocol, String scheme) throws MalformedObjectNameException,
			AttributeNotFoundException, InstanceNotFoundException, MBeanException,
			ReflectionException {
		MBeanServer mBeanServer = null;
		if (MBeanServerFactory.findMBeanServer(null).size() > 0) {
			mBeanServer = (MBeanServer)MBeanServerFactory.findMBeanServer(null).get(0);
		}
		Set<ObjectName> names = mBeanServer.queryNames(new ObjectName("Catalina:type=Connector,*"),
				null);
		Iterator<ObjectName> it = names.iterator();
		ObjectName oname = null;
		while (it.hasNext()) {
			oname = (ObjectName)it.next();
			String pvalue = (String)mBeanServer.getAttribute(oname, "protocol");
			String svalue = (String)mBeanServer.getAttribute(oname, "scheme");
			if (protocol.equals(pvalue) && scheme.equals(svalue)) {
				return ((Integer)mBeanServer.getAttribute(oname, "port"));
			}
		}
		return 0;
	}
}
