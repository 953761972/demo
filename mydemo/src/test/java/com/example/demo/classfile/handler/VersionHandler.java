package com.example.demo.classfile.handler;

import com.example.demo.classfile.type.ClassFile;
import com.example.demo.classfile.type.U2;

import java.nio.ByteBuffer;

/**
 * @Author XZQ
 * @Date 2021/9/26 22:40
 **/
public class VersionHandler implements BaseByteCodeHandler{

    @Override
    public int order() {
        return 1;
    }

    @Override
    public void read(ByteBuffer codeBuf, ClassFile classFile) throws Exception {
        U2 minVersion=new U2(codeBuf.get(),codeBuf.get());
        classFile.setMinor_version(minVersion);
        U2 majorVersion =new U2(codeBuf.get(),codeBuf.get());
        classFile.setMagor_version(majorVersion);
    }
}
