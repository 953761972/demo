package com.example.demo.classfile.handler.ConstantInfoImpl;

import com.example.demo.classfile.type.CpInfo;
import com.example.demo.classfile.type.U1;
import com.example.demo.classfile.type.U2;
import com.example.demo.classfile.type.U4;

import java.nio.ByteBuffer;

/**
 * @Author XZQ
 * @Date 2021/9/27 22:35
 **/
public class CONSTANT_Integer_info extends CpInfo {
    private U4 bytes;
    public CONSTANT_Integer_info(U1 tag) {
        super(tag);
    }

    @Override
    public void read(ByteBuffer codeBuf) throws Exception {
        bytes =new U4(codeBuf.get(), codeBuf.get(), codeBuf.get(), codeBuf.get());
    }

    public U4 getBytes() {
        return bytes;
    }

    public void setBytes(U4 bytes) {
        this.bytes = bytes;
    }
}
