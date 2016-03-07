/*
 * @(#) ServiceRouter.java 2015年4月3日
 *
 * Copyright (c) 2014, SIMPO Technology. All Rights Reserved.
 * SIMPO Technology. CONFIDENTIAL
 */
package cn.telling.rcb.router;

import java.net.URL;


public interface ServiceRouter {

	public URL getServiceURL(String servicename);

}
