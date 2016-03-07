/*
 * @(#) RegistClient.java 2015年6月3日
 *
 * Copyright (c) 2014, SIMPO Technology. All Rights Reserved.
 * SIMPO Technology. CONFIDENTIAL
 */
package cn.telling.rsb.reg;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import org.springframework.stereotype.Component;


@Component("cn.telling.rsb.reg.RegistClient")
public final class RegistClient {

	static final String HOST = System.getProperty("service_reg_server_ip", "service_reg_server");
	static final int PORT = Integer.parseInt(System.getProperty("service_reg_server_port", "18012"));
	public static RegistInfo REGINFO = new RegistInfo();

	private EventLoopGroup loop = new NioEventLoopGroup();

	public static void main(String[] args) throws Exception {

		new RegistClient().run();
	}

	public Bootstrap createBootstrap(Bootstrap bootstrap, EventLoopGroup eventLoop) {
		if (bootstrap != null) {
			final RegClientHandler handler = new RegClientHandler(this);
			bootstrap.group(eventLoop);
			bootstrap.channel(NioSocketChannel.class);
			bootstrap.option(ChannelOption.TCP_NODELAY, true);
			bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
			bootstrap.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ChannelPipeline pipeline = ch.pipeline();

					pipeline.addLast("framer",new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
					pipeline.addLast("decoder", new StringDecoder());
					pipeline.addLast("encoder", new StringEncoder());

					// 客户端的逻辑
					pipeline.addLast("handler", handler);
				}
			});
			bootstrap.remoteAddress(HOST, PORT);
			bootstrap.connect().addListener(new RegConnectionListener(this));
		}
		return bootstrap;
	}

	public void run() {
		System.out.println("client run---------------");
		createBootstrap(new Bootstrap(), loop);
	}

	public void shutdonw() {
		System.out.println("client shutdonw---------------");
		loop.shutdownGracefully();
	}
}
