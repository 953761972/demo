package com.example.demo.classfile.util;

import com.example.demo.classfile.type.U2;
import org.apache.commons.collections.map.HashedMap;

import java.util.Map;

/**
 * @Author XZQ
 * @Date 2021/9/26 22:44
 **/
public class ClassAccessFlagUtils {

    private static final Map<Integer,String> classAccessFlagFlagMap=new HashedMap();

    static{
        //公有
        classAccessFlagFlagMap.put(0x0001,"public");

        //不允许有子类
        classAccessFlagFlagMap.put(0x0010,"final");
        classAccessFlagFlagMap.put(0x0020,"super");

        //接口
        classAccessFlagFlagMap.put(0x0200,"interface");

        //抽象
        classAccessFlagFlagMap.put(0x0400,"abstract");

        //该class非java代码编译后生成
        classAccessFlagFlagMap.put(0x1000,"synthetic");

        //注解类型
        classAccessFlagFlagMap.put(0x2000,"annotation");

        //枚举类型
        classAccessFlagFlagMap.put(0x4000,"enum");
    }
    //获取16进制对应的访问标志字符串表示（仅用于类的访问标志）
    public static String toClassAccessFlagString(U2 flag){
        final int flagValue =flag.toint();
        StringBuilder flagBuild=new StringBuilder();
        classAccessFlagFlagMap.keySet().forEach(key->{
            if((flagValue & key) ==key) {
                flagBuild.append(classAccessFlagFlagMap.get(key)).append(",");
            }
        });
        return flagBuild.length()>0 && flagBuild.charAt(flagBuild.length()-1)==','?flagBuild.substring(0,flagBuild.length()-1):
                flagBuild.toString();
    }
}
