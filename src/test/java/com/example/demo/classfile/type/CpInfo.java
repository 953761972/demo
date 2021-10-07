package com.example.demo.classfile.type;

import com.example.demo.classfile.handler.ConstantInfoHandler;
import com.example.demo.classfile.handler.ConstantInfoImpl.*;

/**
 * @Author XZQ
 * @Date 2021/9/27 00:33
 **/
public abstract  class CpInfo implements ConstantInfoHandler {
    private U1 tag;
    public CpInfo(U1 tag){
        this.tag=tag;
    }

    @Override
    public String toString() {
        return "tag="+tag.toString();
    }

    public static CpInfo newCpInfo(U1 tag) throws Exception{
        int tagValue=tag.toint();
        CpInfo info;
        switch(tagValue){
            case 1:
                info=new CONSTANT_UTF8_info(tag);
                break;
            case 3:
                info=new CONSTANT_Integer_info(tag);
                break;
            case 4:
                info=new CONSTANT_Float_info(tag);
                break;
            case 5:
                info=new CONSTANT_Long_info(tag);
                break;
            case 6:
                info=new CONSTANT_Double_info(tag);
                break;
            case 7:
                info=new CONSTANT_Class_info(tag);
                break;
            case 8:
                info=new CONSTANT_String_info(tag);
                break;
            case 9:
                info=new CONSTANT_Fieldref_info(tag);
                break;
            case 10:
                info=new CONSTANT_Methodref_info(tag);
                break;
            case 11:
                info=new CONSTANT_InterfaceMethodref_info(tag);
                break;
            case 12:
                info=new CONSTANT_NameAndType_info(tag);
                break;
            case 15:
                info=new CONSTANT_MethodHandle_info(tag);
                break;
            case 16:
                info=new CONSTANT_MethodType_info(tag);
                break;
            case 18:
                info=new CONSTANT_InvokDynamic_info(tag);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + tagValue);
        }
        return info;
    }
}
