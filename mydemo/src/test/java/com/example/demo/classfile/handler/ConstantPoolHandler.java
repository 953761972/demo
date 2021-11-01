package com.example.demo.classfile.handler;

import com.example.demo.classfile.type.ClassFile;
import com.example.demo.classfile.type.CpInfo;
import com.example.demo.classfile.type.U1;
import com.example.demo.classfile.type.U2;

import java.nio.ByteBuffer;

/**
 * @Author XZQ
 * @Date 2021/9/27 23:08
 **/
public class ConstantPoolHandler implements BaseByteCodeHandler{
    @Override
    public int order() {
        return 2;
    }

    @Override
    public void read(ByteBuffer codeBuf, ClassFile classFile) throws Exception {
        //获取常量池计数器的值
        U2 cplen=new U2(codeBuf.get(), codeBuf.get());
        classFile.setConstant_pool_count(cplen);
        //常亮池中常量的总数
        int cpInfoLeng=cplen.toint()-1;
        classFile.setConstant_pool(new CpInfo[cpInfoLeng]);
        //解析所有常量
        for(int i=0;i<cpInfoLeng;i++){
            U1 tag=new U1(codeBuf.get());
            CpInfo cpInfo=CpInfo.newCpInfo(tag);
            cpInfo.read(codeBuf);
            System.out.println("#"+(i+1)+":"+cpInfo);
            classFile.getConstant_pool()[i]=cpInfo;
            if(tag.toint()==5){
                //long型占用两个字符串常量池位置，原因尚未找到
                i++;
            }
        }
    }
}
