/*
 * @(#) RemoteProxyFactoryBean.java 2015年4月2日
 *
 * Copyright (c) 2014, SIMPO Technology. All Rights Reserved.
 * SIMPO Technology. CONFIDENTIAL
 */
package cn.telling.rcb.factory;

import java.net.URL;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

import cn.telling.rcb.router.ServiceRouter;


public class RemoteProxyFactoryBean extends HessianProxyFactoryBean {

	private String serviceContext;
	private ServiceRouter serviceRouter;

	@Override
	public String getServiceUrl() {
		String host = null;
		URL url = serviceRouter.getServiceURL(getServiceContext());
		if (url == null) {
			host = "http://127.0.0.1/";
		}
		else {
			host = url.toExternalForm();
		}
		String s = host + "/" + getServiceContext() + "/" + getServiceInterface().getName();
		return s;
	}

	@Override
    public void setServiceUrl(String serviceUrl) {
	    System.out.println("============================"+serviceUrl);
        super.setServiceUrl(serviceUrl);
    }

    @Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		super.prepare();
		return super.invoke(invocation);
	}

	public String getServiceContext() {
		return serviceContext;
	}

	public void setServiceContext(String serviceContext) {
		this.serviceContext = serviceContext;
	}

	public ServiceRouter getServiceRouter() {
		return serviceRouter;
	}

	public void setServiceRouter(ServiceRouter serviceRouter) {
		this.serviceRouter = serviceRouter;
	}
}
