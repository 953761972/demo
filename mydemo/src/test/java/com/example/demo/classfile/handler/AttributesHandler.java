package com.example.demo.classfile.handler;

import com.example.demo.classfile.type.Attributeinfo;
import com.example.demo.classfile.type.ClassFile;
import com.example.demo.classfile.type.U2;
import com.example.demo.classfile.type.U4;

import java.nio.ByteBuffer;

/**
 * @Author XZQ
 * @Date 2021/10/1 22:47
 **/
public class AttributesHandler implements BaseByteCodeHandler{
    @Override
    public int order() {
        return 8;
    }

    @Override
    public void read(ByteBuffer codeBuf, ClassFile classFile) throws Exception {
        classFile.setAttributes_count(new U2(codeBuf.get(), codeBuf.get()));
        //获取属性总数
        int len=classFile.getAttributes_count().toint();
        if(len == 0){
            return;
        }
        //创建属性表
        Attributeinfo[] attributeinfos=new Attributeinfo[len];
        classFile.setAttributes(attributeinfos);
        for(int i=0;i<len;i++){
            //创建属性
            Attributeinfo attributeinfo = new Attributeinfo();
            attributeinfos[i]=attributeinfo;
            //解析属性
            attributeinfo.setAttribute_name_index(new U2(codeBuf.get(), codeBuf.get()));
            attributeinfo.setAttribute_length(new U4(codeBuf.get(), codeBuf.get(), codeBuf.get(), codeBuf.get()));
            int attr_len=attributeinfo.getAttribute_length().toInt();
            if(attr_len == 0){
                continue;
            }
            //解析属性的info项
            byte[] bytes=new byte[attr_len];
            codeBuf.get(bytes,0,bytes.length);
            attributeinfo.setInfo(bytes);
        }
    }
}
