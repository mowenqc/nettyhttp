package com.mowen.server;


import com.mowen.message.ChannelInitHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.log4j.Logger;

/**
 * mowen_parent com.mowen.server
 *
 * @author: mowen
 * @createTime: 2019/6/9 9:04
 * @group:
 * @description:
 */
public class NettyServer {
	private Logger logger = Logger.getLogger(NettyServer.class);

	private int port;
	public NettyServer(int port){
		this.port = port;
	}

	public void start(){
		EventLoopGroup bossEvent = new NioEventLoopGroup();
		EventLoopGroup workEvent = new NioEventLoopGroup();

		ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            bootstrap.group(bossEvent,workEvent ).channel(NioServerSocketChannel.class).option(
ChannelOption.SO_BACKLOG, 1024).childHandler(new ChannelInitHandler());
            ChannelFuture channelFuture = bootstrap.bind(port).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        finally {
            bossEvent.shutdownGracefully();
            workEvent.shutdownGracefully();
        }
    }
}
