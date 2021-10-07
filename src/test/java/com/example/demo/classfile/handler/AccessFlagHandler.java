package com.example.demo.classfile.handler;

import com.example.demo.classfile.type.ClassFile;
import com.example.demo.classfile.type.U2;

import java.nio.ByteBuffer;

/**
 * @Author XZQ
 * @Date 2021/10/1 18:35
 **/
public class AccessFlagHandler implements BaseByteCodeHandler{
    @Override
    public int order() {
        return 3;
    }

    @Override
    public void read(ByteBuffer codeBuf, ClassFile classFile) throws Exception {
        classFile.setAccess_flag(new U2(codeBuf.get(),codeBuf.get()));
    }
}
