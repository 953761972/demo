package com.example.demo.classfile;

import com.example.demo.classfile.type.ClassFile;
import com.example.demo.classfile.util.ClassFileAnalysiser;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 * @Author XZQ
 * @Date 2021/9/26 22:45
 **/
public class ClassFileAnalysisMain {
    public static ByteBuffer readFile(String classFilePath)throws Exception{
        File file=new File(classFilePath);
        if(!file.exists()){
            throw new Exception("file not existss");
        }
        byte[] byteCodeBuf=new byte[4096];
        int length;
        try {
            InputStream in = new FileInputStream(file);
            length = in.read(byteCodeBuf);
            if (length < 1) {
                throw new Exception("not read byte code.");
            }
        } finally {

        }
        return ByteBuffer.wrap(byteCodeBuf,0,length).asReadOnlyBuffer();
    }

    public static void main(String[] args) throws Exception {
        ByteBuffer codeBuf=readFile("/Volumes/FastSSD/GitHubDesktop/demo/target/test-classes/com/example/demo/classfile/test/targetClass.class");
        ClassFile calssFile= ClassFileAnalysiser.analysis(codeBuf);
        System.out.println(calssFile.getMagic().toHexString());
    }
}
