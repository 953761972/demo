package com.example.demo.classfile.handler;

import com.example.demo.classfile.type.*;

import java.nio.ByteBuffer;

/**
 * @Author XZQ
 * @Date 2021/10/1 20:48
 **/
public class FieldHandler implements BaseByteCodeHandler {
    @Override
    public int order() {
        return 6;
    }

    @Override
    public void read(ByteBuffer codeBuf, ClassFile classFile) throws Exception {
        //读取字段总数
        classFile.setFields_count(new U2(codeBuf.get(), codeBuf.get()));
        int len=classFile.getFields_count().toint();
        if(len==0){
            return;
        }
        //创建字段表
        FieldInfo[] fieldInfos=new FieldInfo[len];
        classFile.setFields(fieldInfos);
        //循环解析出每个字段
        for(int i=0;i< fieldInfos.length;i++){
            //解析字段
            fieldInfos[i]=new FieldInfo();
            //读取字段访问标志
            fieldInfos[i].setAccess_flags(new U2(codeBuf.get(), codeBuf.get()));
            //读取字段名称
            fieldInfos[i].setName_index(new U2(codeBuf.get(), codeBuf.get()));
            //读取字段描述符索引
            fieldInfos[i].setDescriptor_index(new U2(codeBuf.get(), codeBuf.get()));
            //读取属性总数
            fieldInfos[i].setAttributes_count(new U2(codeBuf.get(), codeBuf.get()));
            //获取字段的属性总数
            int attr_len=fieldInfos[i].getAttributes_count().toint();
            if(attr_len == 0){
                continue;
            }
            //创建字段属性表
            fieldInfos[i].setAttributes(new Attributeinfo[attr_len]);
            //循环解析每个属性，先使用通用属性结构解析每个属性
            for(int j=0;j<attr_len;j++){
                //解析字段属性
                fieldInfos[i].getAttributes()[j].setAttribute_name_index(new U2(codeBuf.get(), codeBuf.get()));
                //获取属性info的长度
                U4 attr_info_len=new U4(codeBuf.get(), codeBuf.get(), codeBuf.get(), codeBuf.get());
                fieldInfos[i].getAttributes()[j].setAttribute_length(attr_info_len);
                //解析info
                byte[] info=new byte[attr_info_len.toInt()];
                codeBuf.get(info,0,attr_info_len.toInt());
                fieldInfos[i].getAttributes()[j].setInfo(info);
            }
        }

    }
}
