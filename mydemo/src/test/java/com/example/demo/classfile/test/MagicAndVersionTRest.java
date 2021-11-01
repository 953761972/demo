package com.example.demo.classfile.test;

import com.example.demo.classfile.ClassFileAnalysisMain;
import com.example.demo.classfile.type.ClassFile;
import com.example.demo.classfile.util.ClassFileAnalysiser;
import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * @Author XZQ
 * @Date 2021/9/26 23:48
 **/
public class MagicAndVersionTRest {
    @Test
    public void testMagicAndVersionHandler() throws Exception{
        String path="/Volumes/FastSSD/GitHubDesktop/demo/target/test-classes/com/example/demo/classfile/test/targetClass.class";
        ByteBuffer codeBuf= ClassFileAnalysisMain.readFile(path);
        ClassFile classFile= ClassFileAnalysiser.analysis(codeBuf);
        System.out.println(classFile.getMagic().toHexString());
        System.out.println(classFile.getMagor_version().toint());
        System.out.println(classFile.getMinor_version().toint());
    }
}
