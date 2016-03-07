/*
 * @(#) RemoteBeanInitializer.java 2015年4月2日
 *
 * Copyright (c) 2014, SIMPO Technology. All Rights Reserved.
 * SIMPO Technology. CONFIDENTIAL
 */
package cn.telling.rcb.listener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.support.ServletContextResourcePatternResolver;

import cn.telling.rcb.factory.RemoteProxyFactoryBean;
import cn.telling.rcb.router.RemoteServiceRouter;


@Component
public class RemoteBeanInitializer implements BeanDefinitionRegistryPostProcessor,
		ServletContextAware {

	protected final Log logger = LogFactory.getLog(getClass());

	private static String DEFAULT_SCAN_PACKAGE = "cn.telling";
	private static String DEFAULT_SERVICE_PROJECT_SUFFIX = "Service";
	private static String DEFAULT_INTERFACE_PROJECT_SUFFIX = "Interface";
	private static String DEFAULT_JAR_FILE_SURFIX = ".jar";
	private static String DEFAULT_LOCAL_SERVICE_ROUTER = "cn.telling.rcb.router.LocalServiceRouter";
	private static String DEFAULT_REMOTE_SERVICE_ROUTER = "cn.telling.rcb.router.RemoteServiceRouter";
	private static String DEFAULT_ROUTER_DEFINE_PATH = "/WEB-INF/router/*";

	private ServletContext servletContext;

	/*****
	 * spring bean create before register customer bean 
	 */
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry)
			throws BeansException {
		if (logger.isDebugEnabled()) {
			logger.debug("RCB scan jar package Custom Remote service init begin");
		}
		/*resolve route file and regist new bean**/
		String remoteServiceDefine = buildRemoteServiceRouter(registry);

		try {
		    /***find all interface**/
			List<Class<?>> allInterfaces = findAllInterfaces(DEFAULT_SCAN_PACKAGE);
			for (Class<?> serviceInterface : allInterfaces) {
				String serviceContext = getServiceContextName(serviceInterface
						.getProtectionDomain().getCodeSource().getLocation().getPath());

				if (serviceContext == null) continue;

				BeanDefinitionBuilder builder = BeanDefinitionBuilder
						.genericBeanDefinition(RemoteProxyFactoryBean.class);
				builder.addPropertyValue("serviceInterface", serviceInterface);
				builder.addPropertyValue("serviceContext", serviceContext);
				// overloadEnabled
				builder.addPropertyValue("overloadEnabled", true);
				if (remoteServiceDefine == null || "".equals(remoteServiceDefine)) {
					builder.addPropertyReference("serviceRouter", DEFAULT_LOCAL_SERVICE_ROUTER);
				}
				else {
					builder.addPropertyReference("serviceRouter",
							remoteServiceDefine);
				}
				registry.registerBeanDefinition(serviceInterface.getName(),
						builder.getBeanDefinition());
				if (logger.isDebugEnabled()) {
					logger.debug("RCB scan jar package Remote service " + serviceInterface.getName() + " registed!");
				}
			}
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Custom remote service init end");
		}
	}

	
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
			throws BeansException {
	}

	@Override
	public void setServletContext(ServletContext paramServletContext) {
		servletContext = paramServletContext;
	}

	/**
	 * find web project compile classes all interface 
	 */
	private List<Class<?>> findAllInterfaces(String basePackage) throws IOException,
			ClassNotFoundException {
		ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
		MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourcePatternResolver);

		List<Class<?>> candidates = new ArrayList<Class<?>>();
		String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX+ resolveBasePackage(basePackage) + "/" + "**/*.class";
		Resource[] resources = resourcePatternResolver.getResources(packageSearchPath);
		for (Resource resource : resources) {
			if (resource.isReadable()) {
				MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
				if (isCandidate(metadataReader)) {
					candidates.add(Class.forName(metadataReader.getClassMetadata().getClassName()));
				}
			}
		}
		return candidates;
	}

	private String resolveBasePackage(String basePackage) {
		return ClassUtils.convertClassNameToResourcePath(SystemPropertyUtils.resolvePlaceholders(basePackage));
	}

	private boolean isCandidate(MetadataReader metadataReader) throws ClassNotFoundException {
		Class<?> c = Class.forName(metadataReader.getClassMetadata().getClassName());
		if (c.isInterface()) {
			return true;
		}
		return false;
	}

	private String getServiceContextName(String path) {
		if (path.length() < 4) {
			return null;
		}
		if (!path.substring(path.length() - 4).equalsIgnoreCase(DEFAULT_JAR_FILE_SURFIX)) {
			return null;
		}
		String jarName = path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf("."));
		int index = jarName.indexOf(DEFAULT_INTERFACE_PROJECT_SUFFIX);
		if (index < 0) {
			return null;
		}
		return jarName.substring(0, index) + DEFAULT_SERVICE_PROJECT_SUFFIX;
	}

	private String buildRemoteServiceRouter(BeanDefinitionRegistry registry) {
		if (logger.isDebugEnabled()) {
			logger.debug("Remote service router init begin");
		}

		// Map<String, String> routerMap = new HashMap<String, String>();
		Resource[] resources = null;
		try {
			PathMatchingResourcePatternResolver resolver = new ServletContextResourcePatternResolver(
					servletContext);
			resources = resolver.getResources(DEFAULT_ROUTER_DEFINE_PATH);
			Arrays.sort(resources, new Comparator<Resource>() {
				@Override
				public int compare(Resource o1, Resource o2) {
					return o1.getFilename().compareTo(o2.getFilename());
				}
			});
		}
		catch (IOException e) {
			if (logger.isDebugEnabled()) {
				logger.debug("No remote service router has been defined.Turn to local service.");
				logger.debug("Remote service router init end");
			}
			return null;
		}

		String remoteServiceRouter = null;

		for (Resource resource : resources) {
			if (resource.exists() && resource.isReadable()) {
			    /***解析router文件*/
				String[] routerServer = resource.getFilename().split("_");

				remoteServiceRouter = DEFAULT_REMOTE_SERVICE_ROUTER + "." + resource.getFilename();
				BeanDefinitionBuilder builder = BeanDefinitionBuilder
						.genericBeanDefinition(RemoteServiceRouter.class);
				builder.addPropertyValue("ip", routerServer[0]);
				int port = 80;
				if (routerServer.length >= 2) {
					port = Integer.parseInt(routerServer[1]);
				}
				builder.addPropertyValue("port", port);
				registry.registerBeanDefinition(remoteServiceRouter, builder.getBeanDefinition());

				if (logger.isDebugEnabled()) {
					logger.debug("route file "+remoteServiceRouter + " registed!");
				}
				break;

                // InputStream is;
                // try {
                // is = resource.getInputStream();
                // BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
                // String line = null;
                // while ((line = bufferedReader.readLine()) != null) {
                // if (!routerMap.containsKey(line)) {
                // routerMap.put(line, remoteServiceRouter);
                // }
                // }
                // bufferedReader.close();
                // }
                // catch (IOException e) {
                // e.printStackTrace();
                // }
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Remote service router init end");
		}

		return remoteServiceRouter;
	}
	
	
}
