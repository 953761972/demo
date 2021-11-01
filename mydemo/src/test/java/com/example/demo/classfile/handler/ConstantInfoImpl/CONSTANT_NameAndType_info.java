package com.example.demo.classfile.handler.ConstantInfoImpl;

import com.example.demo.classfile.type.CpInfo;
import com.example.demo.classfile.type.U1;
import com.example.demo.classfile.type.U2;

import java.nio.ByteBuffer;

/**
 * @Author XZQ
 * @Date 2021/9/27 22:40
 **/
public class CONSTANT_NameAndType_info extends CpInfo {
    private U2 name_index;
    private U2 descriptor_index;
    public CONSTANT_NameAndType_info(U1 tag) {
        super(tag);
    }

    @Override
    public void read(ByteBuffer codeBuf) throws Exception {
        name_index=new U2(codeBuf.get(), codeBuf.get());
        descriptor_index=new U2(codeBuf.get(), codeBuf.get());
    }

    @Override
    public String toString() {
        return "CONSTANT_NameAndType_info{" +
                "name_index=" + name_index.toint() +
                ", descriptor_index=" + descriptor_index.toint() +
                '}';
    }
}
