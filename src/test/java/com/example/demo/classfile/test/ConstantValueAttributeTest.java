package com.example.demo.classfile.test;

import com.example.demo.classfile.ClassFileAnalysisMain;
import com.example.demo.classfile.handler.AttributeProcessingFactory;
import com.example.demo.classfile.handler.ConstantInfoImpl.*;
import com.example.demo.classfile.type.*;
import com.example.demo.classfile.util.ClassFileAnalysiser;
import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * @Author XZQ
 * @Date 2021/10/1 23:17
 **/
public class ConstantValueAttributeTest {
    @Test
    public void testConstantValue() throws Exception {
        String path="/Volumes/FastSSD/GitHubDesktop/demo/target/test-classes/com/example/demo/classfile/test/targetClass.class";
        ByteBuffer codeBuf= ClassFileAnalysisMain.readFile(path);
        ClassFile classFile = ClassFileAnalysiser.analysis(codeBuf);
        //获取所有字段
        FieldInfo[] fieldInfos=classFile.getFields();
        for(FieldInfo fieldInfo: fieldInfos){
            //获取字段的所有属性
            Attributeinfo[] attributeinfos= fieldInfo.getAttributes();
            if(attributeinfos == null || attributeinfos.length == 0){
                continue;
            }
            System.out.println("字段："+classFile.getConstant_pool()[fieldInfo.getName_index().toint()-1]);
            //遍历所有属性
            for(Attributeinfo attributeinfo:attributeinfos){
                //获取所有属性
                U2 name_index=attributeinfo.getAttribute_name_index();
                CONSTANT_UTF8_info name_info= (CONSTANT_UTF8_info) classFile.getConstant_pool()[name_index.toint()-1];
                String name=new String(name_info.getBytes());
                //如果属性名是ConstantValue 则对属性进行二次解析
                if(name.equalsIgnoreCase("Constantvalue")){
                    //属性二次解析
                    ConstantValue_attribute constantValue= AttributeProcessingFactory.processingConstantValue(attributeinfo);
                    //取得ConstantValue_index 从常量池取值
                    U2 cv_index=constantValue.getConstantvalue_index();
                    Object cv=classFile.getConstant_pool()[cv_index.toint()-1];
                    //需要判断常量的类型
                    if(cv instanceof  CONSTANT_UTF8_info){
                        System.out.println("ConstantValue:"+cv.toString());
                    }
                    if(cv instanceof  CONSTANT_Integer_info){
                        System.out.println("ConstantValue:"+(((CONSTANT_Integer_info) cv).getBytes().toInt()) );
                    }
                    if(cv instanceof CONSTANT_Float_info){
                        //todo
                        System.out.println("ConstantValue:"+cv.toString());
                    }
                    if(cv instanceof CONSTANT_Long_info){
                        //todo
                        System.out.println("ConstantValue:"+cv.toString());
                    }
                    if(cv instanceof  CONSTANT_Double_info){
                        //todo
                        System.out.println("ConstantValue:"+cv.toString());
                    }
                }
            }
        }
    }
}
