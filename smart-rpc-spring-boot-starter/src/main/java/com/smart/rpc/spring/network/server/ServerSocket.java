package com.smart.rpc.spring.network.server;

import com.smart.rpc.spring.network.codec.RpcDecoder;
import com.smart.rpc.spring.network.codec.RpcEncoder;
import com.smart.rpc.spring.network.msg.Request;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.context.ApplicationContext;

import javax.xml.ws.Response;

/**
 * @author yangqian
 * @date 2021/4/29
 */
public class ServerSocket implements Runnable {

    private ChannelFuture channelFuture;
    private transient ApplicationContext applicationContext;

    public ServerSocket(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public boolean isActiveSocketServer() {
        try {
            if (channelFuture != null) {
                return channelFuture.channel().isActive();
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void run() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(
                                new RpcDecoder(Request.class), new RpcEncoder(Response.class)
                            );
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }

    }

}
