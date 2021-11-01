package com.example.demo.netty.netty.test7;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;

/**
 * @Author XZQ
 * @Date 2021/6/19 22:13
 **/
public class MsgpackEncoder extends MessageToByteEncoder<Object> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        MessagePack msgpack=new MessagePack();
        byte[] raw=msgpack.write(o);
        byteBuf.writeBytes(raw);
    }
}
