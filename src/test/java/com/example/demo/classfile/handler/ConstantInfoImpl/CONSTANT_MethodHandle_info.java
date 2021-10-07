package com.example.demo.classfile.handler.ConstantInfoImpl;

import com.example.demo.classfile.type.CpInfo;
import com.example.demo.classfile.type.U1;
import com.example.demo.classfile.type.U2;

import java.nio.ByteBuffer;

/**
 * @Author XZQ
 * @Date 2021/9/27 22:42
 **/
public class CONSTANT_MethodHandle_info extends CpInfo {
    private U1 reference_kind;
    private U2 reference_index;
    public CONSTANT_MethodHandle_info(U1 tag) {
        super(tag);
    }

    @Override
    public void read(ByteBuffer codeBuf) throws Exception {
        reference_kind=new U1(codeBuf.get());
        reference_index=new U2(codeBuf.get(), codeBuf.get());
    }
}
