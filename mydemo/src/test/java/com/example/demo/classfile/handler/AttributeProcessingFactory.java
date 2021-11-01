package com.example.demo.classfile.handler;

import com.example.demo.classfile.type.*;

import java.nio.ByteBuffer;

/**
 * @Author XZQ
 * @Date 2021/10/1 23:12
 **/
public class AttributeProcessingFactory {
    public static ConstantValue_attribute processingConstantValue(Attributeinfo attributeinfo){
        ConstantValue_attribute attribute = new ConstantValue_attribute();
        attribute.setAttribute_name_index(attributeinfo.getAttribute_name_index());
        attribute.setAttribute_length(attributeinfo.getAttribute_length());
        attribute.setConstantvalue_index(new U2(attributeinfo.getInfo()[0],attributeinfo.getInfo()[1] ));
        return  attribute;
    }

    public static Code_attribute processingCode(Attributeinfo attributeinfo){
        Code_attribute code=new Code_attribute();
        ByteBuffer body=ByteBuffer.wrap(attributeinfo.getInfo());
        //操作数栈大小
        code.setMax_stack(new U2(body.get(), body.get()));
        //局部变量表大小
        code.setMax_locals(new U2(body.get(), body.get()));
        //字节码数组长度
        code.setCode_length(new U4(body.get(), body.get(), body.get(), body.get()));
        //解析获取字节码
        byte[] byteCode=new byte[code.getCode_length().toInt()];
        body.get(byteCode,0,byteCode.length);
        code.setCode(byteCode);
        return code;
    }
}
