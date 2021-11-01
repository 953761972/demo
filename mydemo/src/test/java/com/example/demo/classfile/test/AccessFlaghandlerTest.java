package com.example.demo.classfile.test;

import com.example.demo.classfile.ClassFileAnalysisMain;
import com.example.demo.classfile.type.ClassFile;
import com.example.demo.classfile.type.U2;
import com.example.demo.classfile.util.ClassAccessFlagUtils;
import com.example.demo.classfile.util.ClassFileAnalysiser;
import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * @Author XZQ
 * @Date 2021/10/1 18:36
 **/
public class AccessFlaghandlerTest {
    @Test
    public void testAccessFlagHandlerHandler() throws Exception {
        String path="/Volumes/FastSSD/GitHubDesktop/demo/target/test-classes/com/example/demo/classfile/test/targetClass.class";
        ByteBuffer codeBuf= ClassFileAnalysisMain.readFile(path);
        ClassFile classFile= ClassFileAnalysiser.analysis(codeBuf);

        //获取访问标志
        U2 accessFlags= classFile.getAccess_flag();

        //输出为字符串
        System.out.println(ClassAccessFlagUtils.toClassAccessFlagString(accessFlags));
    }
}
