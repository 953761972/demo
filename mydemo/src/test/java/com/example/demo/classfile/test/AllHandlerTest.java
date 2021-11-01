package com.example.demo.classfile.test;

import com.example.demo.classfile.ClassFileAnalysisMain;
import com.example.demo.classfile.type.ClassFile;
import com.example.demo.classfile.util.ClassFileAnalysiser;
import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * @Author XZQ
 * @Date 2021/10/1 22:57
 **/
public class AllHandlerTest {

    @Test
    public void test() throws Exception {
        String path="/Volumes/FastSSD/GitHubDesktop/demo/target/test-classes/com/example/demo/classfile/test/targetClass.class";
        ByteBuffer codeBuf= ClassFileAnalysisMain.readFile(path);
        ClassFile classFile= ClassFileAnalysiser.analysis(codeBuf);
        System.out.println(classFile.toString());
        System.out.println();
    }
}
