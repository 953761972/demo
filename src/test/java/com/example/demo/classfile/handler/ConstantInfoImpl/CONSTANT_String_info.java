package com.example.demo.classfile.handler.ConstantInfoImpl;

import com.example.demo.classfile.type.CpInfo;
import com.example.demo.classfile.type.U1;
import com.example.demo.classfile.type.U2;

import java.nio.ByteBuffer;

/**
 * @Author XZQ
 * @Date 2021/9/27 22:30
 **/
public class CONSTANT_String_info extends CpInfo {
    private U2 String_index;
    public CONSTANT_String_info(U1 tag) {
        super(tag);
    }

    @Override
    public void read(ByteBuffer codeBuf) throws Exception {
        String_index=new U2(codeBuf.get(), codeBuf.get());
    }
}
