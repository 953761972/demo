package com.example.demo.classfile.test;

import com.example.demo.classfile.ClassFileAnalysisMain;
import com.example.demo.classfile.handler.AttributeProcessingFactory;
import com.example.demo.classfile.handler.ConstantInfoImpl.CONSTANT_UTF8_info;
import com.example.demo.classfile.type.*;
import com.example.demo.classfile.util.ClassFileAnalysiser;
import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * @Author XZQ
 * @Date 2021/10/2 00:16
 **/
public class CodeAttributeTest {
    @Test
    public void testCodeAttribute() throws Exception {
        String path="/Volumes/FastSSD/GitHubDesktop/demo/target/test-classes/com/example/demo/classfile/test/targetClass.class";
        String path1="/Volumes/FastSSD/GitHubDesktop/demo/target/test-classes/com/example/demo/classfile/RecursionAlgorithmMain.class";
        ByteBuffer codeBuf = ClassFileAnalysisMain.readFile(path);
        ClassFile classFile = ClassFileAnalysiser.analysis(codeBuf);
        // 获取方法表
        MethodInfo[] methodInfos = classFile.getMethods();
        // 遍历方法表         

        for (MethodInfo methodInfo : methodInfos) {
            // 获取方法的属性表            
            Attributeinfo[] attributeInfos = methodInfo.getAttributes();
            if (attributeInfos == null || attributeInfos.length == 0) {
                continue;
            }
            System.out.println("方法：" + classFile.getConstant_pool()[methodInfo.getName_index().toint() - 1]);
            // 遍历属性表
            for (Attributeinfo attributeInfo : attributeInfos) {
                // 获取属性的名称            
                U2 name_index = attributeInfo.getAttribute_name_index();
                CONSTANT_UTF8_info name_info = (CONSTANT_UTF8_info) classFile.getConstant_pool()[name_index.toint() - 1];
                String name = new String(name_info.getBytes());
                // 对Code属性二次解析     
                if (name.equalsIgnoreCase("Code")) {
                    // 属性二次解析        
                    Code_attribute code = AttributeProcessingFactory.processingCode(attributeInfo);
                    System.out.println("操作数栈大小：" + code.getMax_stack().toint());
                    System.out.println("局部变量表大小：" + code.getMax_locals().toint());
                    System.out.println("字节码数组长度：" + code.getCode_length().toInt());
                    System.out.println("字节码：");
                    for (byte b : code.getCode()) {
                        System.out.print((b & 0xff) + " ");
                    }
                    System.out.println("\n");
                }
            }
        }
    }

}