/*
 * @(#) RegConnectionListener.java 2015年6月3日
 *
 * Copyright (c) 2014, SIMPO Technology. All Rights Reserved.
 * SIMPO Technology. CONFIDENTIAL
 */
package cn.telling.rsb.reg;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoop;

import java.util.concurrent.TimeUnit;


public class RegConnectionListener implements ChannelFutureListener {
	private RegistClient client;

	public RegConnectionListener(RegistClient client) {
		this.client = client;
	}

	@Override
	public void operationComplete(ChannelFuture channelFuture) throws Exception {
		if (!channelFuture.isSuccess()) {
			final EventLoop loop = channelFuture.channel().eventLoop();
			loop.schedule(new Runnable() {
				@Override
				public void run() {
					client.createBootstrap(new Bootstrap(), loop);
				}
			}, 1L, TimeUnit.SECONDS);
		}
	}
}