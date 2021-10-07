package com.example.demo.classfile.handler;

import com.example.demo.classfile.type.ClassFile;
import com.example.demo.classfile.type.U4;

import java.nio.ByteBuffer;

/**
 * @Author XZQ
 * @Date 2021/9/26 22:40
 **/
public class MagicHandler implements BaseByteCodeHandler {

    @Override
    public int order() {
        return 0;
    }

    @Override
    public void read(ByteBuffer codeBuf, ClassFile classFile) throws Exception {
        classFile.setMagic(new U4(codeBuf.get(),codeBuf.get(),codeBuf.get(),codeBuf.get()));
        if(!"0xCAFEBABE".equalsIgnoreCase(classFile.getMagic().toHexString())){
            throw new Exception("这不是一个class文件！");
        }
    }
}
