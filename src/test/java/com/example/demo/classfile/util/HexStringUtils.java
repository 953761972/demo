package com.example.demo.classfile.util;

/**
 * @Author XZQ
 * @Date 2021/10/2 00:34
 **/
public class HexStringUtils {
    public static String toHexString(byte[] codes) {
        StringBuilder codeStrBuild = new StringBuilder();
        int i = 0;
        for (byte code : codes) {
            // toHexString将字节转十六进制，实现略...
            codeStrBuild.append(toHexString(code)).append(" ");
            if (++i == 9) {
                i = 0;
                codeStrBuild.append("\n");
            }
        }
        return codeStrBuild.toString();
    }

    private static char[] toHexString(byte code) {
        String hex = Integer.toHexString(code & 0xFF);
        if(hex.length() < 2){
            hex = "0" + hex;
        }
        return hex.toCharArray();
    }
}
