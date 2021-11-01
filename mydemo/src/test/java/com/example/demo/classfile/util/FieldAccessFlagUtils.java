package com.example.demo.classfile.util;

import com.example.demo.classfile.type.U2;
import org.apache.commons.collections.map.HashedMap;

import java.util.Map;

/**
 * @Author XZQ
 * @Date 2021/10/1 21:17
 **/
public class FieldAccessFlagUtils {
    private static final Map<Integer,String> fieldAccessFlagMap=new HashedMap();

    static {
        fieldAccessFlagMap.put(0x0001,"public");
        fieldAccessFlagMap.put(0x0002,"private");
        fieldAccessFlagMap.put(0x0004,"protected");
        fieldAccessFlagMap.put(0x0008,"static");
        fieldAccessFlagMap.put(0x0010,"final");
        fieldAccessFlagMap.put(0x0040,"volatile");
        fieldAccessFlagMap.put(0x0080,"transient");
        fieldAccessFlagMap.put(0x1000,"synthtic");
        fieldAccessFlagMap.put(0x4001,"enum");
    }
    //取16进制对应的访问标志和属性字符串表示
    public static String toFieldAccessFlagsString(U2 flag){
        final int flagValue= flag.toint();
        StringBuilder flagBuild=new StringBuilder();
        fieldAccessFlagMap.keySet().forEach(key->{
            if((flagValue & key)==key ){
                flagBuild.append(fieldAccessFlagMap.get(key)).append(",");
            }
        });
        return flagBuild.length()>0 && flagBuild.charAt(flagBuild.length()-1)==','?flagBuild.substring(0,flagBuild.length()-1):
                flagBuild.toString();
    }
}
