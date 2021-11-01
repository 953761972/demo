package com.example.demo.classfile.test;

import com.example.demo.classfile.ClassFileAnalysisMain;
import com.example.demo.classfile.handler.ConstantInfoImpl.CONSTANT_Class_info;
import com.example.demo.classfile.handler.ConstantInfoImpl.CONSTANT_UTF8_info;
import com.example.demo.classfile.type.ClassFile;
import com.example.demo.classfile.type.U2;
import com.example.demo.classfile.util.ClassFileAnalysiser;
import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * @Author XZQ
 * @Date 2021/10/1 20:28
 **/
public class InterfacesHandlerTest {

    @Test
    public void InterfacesHandlerTest() throws Exception {
        String path="/Volumes/FastSSD/GitHubDesktop/demo/target/test-classes/com/example/demo/classfile/test/targetClass.class";
        ByteBuffer codeBuf= ClassFileAnalysisMain.readFile(path);
        ClassFile classFile= ClassFileAnalysiser.analysis(codeBuf);
        System.out.println("接口总数："+classFile.getInterfaces_count().toint());
        if(classFile.getInterfaces_count().toint() == 0){
            return;
        }
        U2[] interfaces= classFile.getInterfaces();

        //遍历接口表
        for(U2 interfacesIndex : interfaces){
            //根据索引从常量池中取得一个CONSTANRT_class_info常量
            CONSTANT_Class_info interfaces_class_info= (CONSTANT_Class_info) classFile.getConstant_pool()[interfacesIndex.toint()-1];
            //根据CONSTANT_class_info的name_index从常量池取得一个
            //CONSTANT_class_info常量
            CONSTANT_UTF8_info interfaces_class_name_info= (CONSTANT_UTF8_info) classFile.getConstant_pool()[interfaces_class_info.getName_index().toint()-1];
            System.out.println(interfaces_class_name_info);
        }
    }
}
