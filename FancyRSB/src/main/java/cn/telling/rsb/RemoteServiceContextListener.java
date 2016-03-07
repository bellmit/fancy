/*
 * @(#) RemoteServiceContextListener.java 2015年6月3日
 *
 * Copyright (c) 2014, SIMPO Technology. All Rights Reserved.
 * SIMPO Technology. CONFIDENTIAL
 */
package cn.telling.rsb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import cn.telling.rsb.reg.RegistClient;


@Component
public class RemoteServiceContextListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private RegistClient registClient;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		if (event.getApplicationContext().getParent() == null) {
			try {
//				Thread t1 = new Thread(new RunClient());
//				t1.start();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@SuppressWarnings("unused")
    private class RunClient implements Runnable {

		private boolean isServerRunning = false;

		public void run() {
			try {
				while (!isServerRunning) {
					if (RegistClient.REGINFO.getPort() != null&& !"".equals(RegistClient.REGINFO.getPort())
							&& RegistClient.REGINFO.getServiceName() != null&& !"".equals(RegistClient.REGINFO.getServiceName())) {
						isServerRunning = true;
						registClient.run();
					}
					Thread.sleep(1000);
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
