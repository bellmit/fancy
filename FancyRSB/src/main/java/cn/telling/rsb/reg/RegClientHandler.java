/*
 * @(#) RegClientHandler.java 2015年6月3日
 *
 * Copyright (c) 2014, SIMPO Technology. All Rights Reserved.
 * SIMPO Technology. CONFIDENTIAL
 */
package cn.telling.rsb.reg;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoop;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.TimeUnit;


public class RegClientHandler extends SimpleChannelInboundHandler<String> {

	private RegistClient client;

	public RegClientHandler(RegistClient client) {
		this.client = client;
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		System.out.println("Server say : " + msg);

		if ("A".equals(msg)) {
			ctx.channel().writeAndFlush("O\n");
		}
		else {
			// 业务逻辑
		}
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("REGINFO -------------------");
		System.out.println(RegistClient.REGINFO.getPort());
		System.out.println(RegistClient.REGINFO.getServiceName());
		ctx.channel().writeAndFlush(
				"HI_" + RegistClient.REGINFO.getPort() + "_"
						+ RegistClient.REGINFO.getServiceName() + "\n");
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		final EventLoop eventLoop = ctx.channel().eventLoop();
		eventLoop.schedule(new Runnable() {
			@Override
			public void run() {
				client.createBootstrap(new Bootstrap(), eventLoop);
			}
		}, 1L, TimeUnit.SECONDS);
		super.channelInactive(ctx);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		// Close the connection when an exception is raised.
		cause.printStackTrace();
		ctx.close();
	}
}
