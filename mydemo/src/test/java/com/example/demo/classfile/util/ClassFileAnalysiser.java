package com.example.demo.classfile.util;

import com.example.demo.classfile.handler.*;
import com.example.demo.classfile.type.ClassFile;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @Author XZQ
 * @Date 2021/9/26 22:44
 **/
public class ClassFileAnalysiser {
    private final static List<BaseByteCodeHandler> handlers=new ArrayList<>();

    static{
        handlers.add(new MagicHandler());
        handlers.add(new VersionHandler());
        handlers.add(new ConstantPoolHandler());
        handlers.add(new AccessFlagHandler());
        handlers.add(new ThisAndSuperClassHandler());
        handlers.add(new InterfacesHandler());
        handlers.add(new FieldHandler());
        handlers.add(new MethodHandler());
        handlers.add(new AttributesHandler());
        handlers.sort(Comparator.comparingInt(BaseByteCodeHandler::order));
    }
    public static ClassFile analysis(ByteBuffer codeBuf) throws Exception{
        codeBuf.position(0);
        ClassFile classfile=new ClassFile();

        for(BaseByteCodeHandler handler:handlers){
            handler.read(codeBuf,classfile);
        }
        System.out.println("class文件解析完成，解析是否正常（剩余位解析的字节数）："+codeBuf.remaining());
        return classfile;
    }
}
