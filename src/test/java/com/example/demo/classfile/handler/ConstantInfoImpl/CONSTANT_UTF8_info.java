package com.example.demo.classfile.handler.ConstantInfoImpl;

import com.example.demo.classfile.type.CpInfo;
import com.example.demo.classfile.type.U1;
import com.example.demo.classfile.type.U2;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * @Author XZQ
 * @Date 2021/9/27 22:15
 **/
public class CONSTANT_UTF8_info extends CpInfo {
    private U2 length;
    private byte[] bytes;
    public CONSTANT_UTF8_info(U1 tag) {
        super(tag);
    }

    @Override
    public void read(ByteBuffer codeBuf) throws Exception {
        //需要读取的长度
        length=new U2(codeBuf.get(),codeBuf.get());
        bytes=new byte[length.toint()];
        //读取指定长度
        codeBuf.get(bytes,0, length.toint());
    }

    @Override
    public String toString() {
        return super.toString()+",length="+length.toint()+".str="+new String(bytes, StandardCharsets.UTF_8);
    }

    public U2 getLength() {
        return length;
    }

    public void setLength(U2 length) {
        this.length = length;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
