package com.example.demo.classfile.handler.ConstantInfoImpl;

import com.example.demo.classfile.type.CpInfo;
import com.example.demo.classfile.type.U1;
import com.example.demo.classfile.type.U4;

import java.nio.ByteBuffer;

/**
 * @Author XZQ
 * @Date 2021/9/27 22:37
 **/
public class CONSTANT_Long_info extends CpInfo {
    private U4 hight_bytes;
    private U4 low_bytes;
    public CONSTANT_Long_info(U1 tag) {
        super(tag);
    }

    @Override
    public void read(ByteBuffer codeBuf) throws Exception {
        hight_bytes =new U4(codeBuf.get(), codeBuf.get(), codeBuf.get(), codeBuf.get());
        low_bytes=new U4(codeBuf.get(), codeBuf.get(), codeBuf.get(), codeBuf.get());
    }

    @Override
    public String toString() {
        return super.toString()+",CONSTANT_Long_info{" +
                "hight_bytes=" + hight_bytes.toInt()+
                ", low_bytes=" + low_bytes.toInt() +
                '}';
    }
}
