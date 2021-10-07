package com.example.demo.classfile.handler;

import com.example.demo.classfile.type.ClassFile;
import com.example.demo.classfile.type.U2;

import java.nio.ByteBuffer;

/**
 * @Author XZQ
 * @Date 2021/10/1 18:45
 **/
public class ThisAndSuperClassHandler implements BaseByteCodeHandler{
    @Override
    public int order() {
        return 4;
    }

    @Override
    public void read(ByteBuffer codeBuf, ClassFile classFile) throws Exception {
        classFile.setThis_class(new U2(codeBuf.get(), codeBuf.get()));
        classFile.setSuper_class(new U2(codeBuf.get(),codeBuf.get()));
    }
}
