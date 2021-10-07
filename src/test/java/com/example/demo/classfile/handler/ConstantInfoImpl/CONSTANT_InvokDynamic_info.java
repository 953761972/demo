package com.example.demo.classfile.handler.ConstantInfoImpl;

import com.example.demo.classfile.type.CpInfo;
import com.example.demo.classfile.type.U1;
import com.example.demo.classfile.type.U2;

import java.nio.ByteBuffer;

/**
 * @Author XZQ
 * @Date 2021/9/27 22:48
 **/
public class CONSTANT_InvokDynamic_info extends CpInfo {
    private U2 boostrap_method_attr_index;
    private U2 name_and_type_index;
    public CONSTANT_InvokDynamic_info(U1 tag) {
        super(tag);
    }

    @Override
    public void read(ByteBuffer codeBuf) throws Exception {
        boostrap_method_attr_index=new U2(codeBuf.get(), codeBuf.get());
        name_and_type_index=new U2(codeBuf.get(), codeBuf.get());
    }
}
