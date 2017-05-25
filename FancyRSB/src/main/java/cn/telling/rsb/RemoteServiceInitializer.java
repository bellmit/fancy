/*
 * @(#) RemoteServiceInitializer.java 2015年3月31日
 *
 * Copyright (c) 2014, SIMPO Technology. All Rights Reserved.
 * SIMPO Technology. CONFIDENTIAL
 */
package cn.telling.rsb;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import cn.telling.rsb.servlet.RemoteServiceServlet;

/**添加一个自定义servlet(RemoteServiceServlet) 扫描Service模块并注册Service接口,手动启动Server服务***/
//实现WebApplicationinitializer的类都可以在web应用程序启动时被加载。
public class RemoteServiceInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext mvcContext = new AnnotationConfigWebApplicationContext();
        System.out.println("RemoteService start loading:"+mvcContext.getClassLoader().getResource("/"));
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("RemoteService",new RemoteServiceServlet(mvcContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/*");
	}

}
