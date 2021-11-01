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
 * @Date 2021/10/1 18:49
 **/
public class ThisAndSpuerHandlerTest {

    @Test
    public void testThisAndSuperHandler() throws Exception {
        String path="/Volumes/FastSSD/GitHubDesktop/demo/target/test-classes/com/example/demo/classfile/test/targetClass.class";
        ByteBuffer codeBuf= ClassFileAnalysisMain.readFile(path);
        ClassFile classFile = ClassFileAnalysiser.analysis(codeBuf);
        //this_class
        U2 this_class=classFile.getThis_class();
        //根据this_class到常量池获取CONSTANT_CLASS_Info常量
        //由于常量池的索引是从1开始的，所以需要将索引减1取得数组下标
        CONSTANT_Class_info this_class_cpInfo= (CONSTANT_Class_info) classFile.getConstant_pool()[this_class.toint()-1];

        CONSTANT_UTF8_info this_class_name= (CONSTANT_UTF8_info) classFile.getConstant_pool()[this_class_cpInfo.getName_index().toint()-1];

        System.out.println("this_class:"+this_class_name);

        //super class
        U2 super_class = classFile.getSuper_class();
        CONSTANT_Class_info super_class_cpinfo= (CONSTANT_Class_info) classFile.getConstant_pool()[super_class.toint()-1];
        CONSTANT_UTF8_info super_class_name= (CONSTANT_UTF8_info) classFile.getConstant_pool()[super_class_cpinfo.getName_index().toint()-1];
        System.out.println("spuer_class:"+super_class_name);
    }
}
