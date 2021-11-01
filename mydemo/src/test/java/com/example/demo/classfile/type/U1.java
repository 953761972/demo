package com.example.demo.classfile.type;

import java.util.Arrays;

/**
 * @Author XZQ
 * @Date 2021/9/27 22:43
 **/
public class U1 {
    private byte[] value;
    public U1(byte byte1){
        value=new byte[]{byte1};
    }

    public Integer toint(){
        return (value[0] & 0xff);
    }

    public String toHexString(){

        char[] hexChar=new char[]{'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F',};
        StringBuilder hexStr = new StringBuilder();
        for(int i=1;i>=0;i--){
            int v=value[i]& 0xff;
            while(v>0){
                int c=v % 16;
                v=v>>>4;
                hexStr.insert(0,hexChar[c]);
            }
            if((hexStr.length()& 0x01)==1){
                hexStr.insert(0,'0');
            }
        }
        return "0x"+(hexStr.length()==0?"00":hexStr.toString());
    }

    @Override
    public String toString() {
        return "U1{" +
                "value=" + Arrays.toString(value) +
                '}';
    }
}
