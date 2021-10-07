package com.example.demo.classfile.handler;

import java.nio.ByteBuffer;

/**
 * @Author XZQ
 * @Date 2021/9/27 00:31
 **/
public interface ConstantInfoHandler {
    void read(ByteBuffer codeBuf) throws Exception;
}
