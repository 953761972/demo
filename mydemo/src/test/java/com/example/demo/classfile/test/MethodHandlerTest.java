package com.example.demo.classfile.test;

import com.example.demo.classfile.ClassFileAnalysisMain;
import com.example.demo.classfile.handler.ConstantInfoImpl.CONSTANT_UTF8_info;
import com.example.demo.classfile.type.ClassFile;
import com.example.demo.classfile.type.MethodInfo;
import com.example.demo.classfile.type.U2;
import com.example.demo.classfile.util.ClassFileAnalysiser;
import com.example.demo.classfile.util.FieldAccessFlagUtils;
import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * @Author XZQ
 * @Date 2021/10/1 22:06
 **/
public class MethodHandlerTest {
    private static String getName(U2 name_index, ClassFile classFile){
        CONSTANT_UTF8_info name_info= (CONSTANT_UTF8_info) classFile.getConstant_pool()[name_index.toint()-1];
        return name_info.toString();
    }

    @Test
    public void testMethodHandler() throws Exception {
        String path="/Volumes/FastSSD/GitHubDesktop/demo/target/test-classes/com/example/demo/classfile/test/targetClass.class";
        ByteBuffer codeBuf= ClassFileAnalysisMain.readFile(path);
        ClassFile classFile= ClassFileAnalysiser.analysis(codeBuf);
        System.out.println("方法总数："+classFile.getMethods_count().toint());
        System.out.println();
        MethodInfo[] methodInfos= classFile.getMethods();
        //遍历方法表
        for(MethodInfo methodInfo:methodInfos){
            System.out.println("访问标志和属性："+ FieldAccessFlagUtils.toFieldAccessFlagsString(methodInfo.getAccess_flag()));
            System.out.println("方法名："+getName(methodInfo.getName_index(),classFile));
            System.out.println("方法描述符："+getName(methodInfo.getDescriptor_index(),classFile));
            System.out.println("属性总数："+methodInfo.getAttributes_count().toint());
            System.out.println();
        }
    }
}
