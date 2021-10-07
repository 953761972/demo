package com.example.demo.classfile.handler.ConstantInfoImpl;

import com.example.demo.classfile.type.CpInfo;
import com.example.demo.classfile.type.U1;
import com.example.demo.classfile.type.U2;

import java.nio.ByteBuffer;

/**
 * @Author XZQ
 * @Date 2021/9/27 22:19
 **/
public class CONSTANT_Class_info extends CpInfo {
    private U2 name_index;
    public CONSTANT_Class_info(U1 tag) {
        super(tag);
    }

    @Override
    public void read(ByteBuffer codeBuf) throws Exception {
        this.name_index=new U2(codeBuf.get(), codeBuf.get());
    }

    public U2 getName_index() {
        return name_index;
    }

    public void setName_index(U2 name_index) {
        this.name_index = name_index;
    }

    @Override
    public String toString() {
        return "CONSTANT_Class_info{" +
                "name_index=" + name_index.toint() +
                '}';
    }
}
