/*
 * @(#) RemoteServiceServlet.java 2015年3月31日
 *
 * Copyright (c) 2014, SIMPO Technology. All Rights Reserved.
 * SIMPO Technology. CONFIDENTIAL
 */
package cn.telling.rsb.servlet;

import java.util.Map;

import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import cn.telling.rsb.reg.RegistClient;
import cn.telling.utils.WebServerUtils;


@SuppressWarnings("serial")
public class RemoteServiceServlet extends DispatcherServlet {

	private static String DEFAULT_IMPLEMENT_SURFIX = "Impl$";
	private static String DEFAULT_SERVICE_URL_SURFIX = "";

	public RemoteServiceServlet() {
		super();
	}

	public RemoteServiceServlet(WebApplicationContext webApplicationContext) {
		super(webApplicationContext);
	}

	@Override
	protected void initStrategies(ApplicationContext context) {
		initExporters(context);//在springmvc策略未开始时加载自定义服务
		super.initStrategies(context);
	}

	protected void initExporters(ApplicationContext context) {
		if (logger.isDebugEnabled()) {
			logger.debug("Custom exporters init begin");
		}
		int port = 0;
		try {
			port = WebServerUtils.getPort(WebServerUtils.DEFAULT_TOMCAT_PROTOCOL,
					WebServerUtils.DEFAULT_TOMCAT_SCHEME);
		}
		catch (Exception e) {
			logger.fatal("Get server port failed!Please check tomcat server configuration!");
			e.printStackTrace();
		}
		if (logger.isDebugEnabled()) {
			logger.debug("port--------:" + port);
		}
		RegistClient.REGINFO.setPort("" + port);

		ConfigurableWebApplicationContext cac = (ConfigurableWebApplicationContext)context;

		Map<String, Object> beanMap = cac.getParent().getBeansWithAnnotation(Service.class);

		String serviceName = cac.getServletContext().getServletContextName();
		if (logger.isDebugEnabled()) {
			logger.debug("注册服务名称:" + serviceName);
		}
		RegistClient.REGINFO.setServiceName(serviceName);

		/***
		 * 
		   HessianServiceExporter将普通bean导出成远程服务   类似于RmiServiceExporter
		     遍历所有service封装成远程服务
		 */
		for (String beanName : beanMap.keySet()) {
			Class<?> beanInterface = AopProxyUtils.proxiedUserInterfaces(beanMap.get(beanName))[0];

			BeanDefinitionBuilder builder = BeanDefinitionBuilder
					.genericBeanDefinition(HessianServiceExporter.class);
			builder.addPropertyReference("service", beanName);//需要封装的目标bean
			builder.addPropertyValue("serviceInterface", beanInterface);//Hessian服务的接口-
			BeanDefinition beanDefinition = builder.getBeanDefinition();
			BeanDefinitionRegistry beanFactory = (BeanDefinitionRegistry)cac.getBeanFactory();
			String serviceUrl = "/" + getExporterNameByInterface(beanInterface);
			beanFactory.registerBeanDefinition(serviceUrl, beanDefinition);
			if (logger.isDebugEnabled()) {
				logger.debug(serviceUrl + " registed!");
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Custom exporters init end");
		}

	}

	@SuppressWarnings("unused")
	private String getExporterNameByClass(Class<?> beanClass) {
		Service service = beanClass.getAnnotation(Service.class);
		String result = service.value();
		if ("".equals(result)) {
			String className = beanClass.getSimpleName();
			result = className.replaceAll(DEFAULT_IMPLEMENT_SURFIX, DEFAULT_SERVICE_URL_SURFIX);
			result = result.substring(0, 1).toLowerCase() + result.substring(1);
		}
		return result;
	}

	private String getExporterNameByInterface(Class<?> beanInterface) {
		return beanInterface.getName();
	}
}
