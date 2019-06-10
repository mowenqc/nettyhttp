package com.mowen.message;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpServerCodec;

/***
 * desc  : com.mowen.message
 * author: mowen
 * create_time: 2019/6/10 10:23
 * project_name : mowen_parent
 */
public class ChannelInitHandler extends ChannelInitializer {

    @Override
    protected void initChannel(Channel channel) throws Exception {
//        ch.pipeline().addLast(new HttpRequestEncoder()).addLast(new HttpRequestDecoder()).addLast(new MessageHandler());
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast(new HttpServerCodec());// http 编解码
        pipeline.addLast(new HttpObjectAggregator(1024*1024)); // http 消息聚合器                                                                     512*1024为接收的最大contentlength
        pipeline.addLast(new MessageHandler());// 请求处理器
    }
}
