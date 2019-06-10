package com.mowen.message;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import java.nio.charset.Charset;

/***
 * desc  : com.mowen.message
 * author: mowen
 * create_time: 2019/6/10 10:12
 * project_name : mowen_parent
 */
public class MessageHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接到来：" + ctx.name());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof HttpRequest){
            FullHttpRequest request = (FullHttpRequest) msg;
            String uri = request.uri();
            System.out.println(uri);
            //1.可以在这儿进行路由分发

            //2. 获取应该服务的数据

            //3. 渲染数据
            //构建响应结果
            FullHttpResponse response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer("OK OK OK OK"
                .getBytes()));
            response.headers().set("Content-Type", "text/plain");
            response.headers().set("Content-Lenght",
                response.content().readableBytes());
            response.headers().set("Connection", HttpHeaderValues.KEEP_ALIVE);
            ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
        }

    }
}
