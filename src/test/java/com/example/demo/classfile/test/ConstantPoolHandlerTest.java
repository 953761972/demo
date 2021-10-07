package com.example.demo.classfile.test;

import com.example.demo.classfile.ClassFileAnalysisMain;
import com.example.demo.classfile.type.ClassFile;
import com.example.demo.classfile.type.CpInfo;
import com.example.demo.classfile.util.ClassFileAnalysiser;
import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * @Author XZQ
 * @Date 2021/10/1 18:09
 **/
public class ConstantPoolHandlerTest {

    @Test
    public void testConstantPoolHandler() throws Exception{
        String path="/Volumes/FastSSD/GitHubDesktop/demo/target/test-classes/com/example/demo/classfile/test/targetClass.class";
        ByteBuffer codeBuf= ClassFileAnalysisMain.readFile(path);
        ClassFile classFile= ClassFileAnalysiser.analysis(codeBuf);
        int cp_info_count=classFile.getConstant_pool_count().toint();
        System.out.println("常量池中的常量总数："+cp_info_count);

        //遍历
        CpInfo[] cpInfo=classFile.getConstant_pool();
        for(CpInfo cp:cpInfo){
            System.out.println(cp.toString());
        }
    }
}
