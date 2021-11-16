package com.xzq.dubbodemo.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @Author XZQ
 * @Date 2021/11/9 20:15
 **/
public class MD5Util {
    private static final String SALT = "mrbird";

    private static final String ALGORITH_NAME = "md5";

    private static final int HASH_ITERATIONS = 2;

    public static String encrypt(String pswd) {
        String newPassword = new SimpleHash(ALGORITH_NAME, pswd, ByteSource.Util.bytes(SALT), HASH_ITERATIONS).toHex();
        return newPassword;
    }

    public static String encrypt(String username, String pswd) {
        String newPassword = new SimpleHash(ALGORITH_NAME, pswd, ByteSource.Util.bytes(username + SALT),
                HASH_ITERATIONS).toHex();
        return newPassword;
    }
    public static void main(String[] args) {

        System.out.println(MD5Util.encrypt("test", "123456"));
        System.out.println(MD5Util.encrypt("test"));
    }

}
