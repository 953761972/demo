package com.example.demo.classfile.handler;

import com.example.demo.classfile.type.ClassFile;
import org.bson.ByteBuf;

import java.nio.ByteBuffer;

/**
 * @Author XZQ
 * @Date 2021/9/26 22:39
 **/
public interface BaseByteCodeHandler {
    int order();

    void read(ByteBuffer codeBuf, ClassFile classFile) throws Exception;
}
