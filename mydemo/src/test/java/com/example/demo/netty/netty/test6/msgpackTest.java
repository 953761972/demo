package com.example.demo.netty.netty.test6;

import org.msgpack.MessagePack;
import org.msgpack.template.Templates;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author XZQ
 * @Date 2021/6/19 21:21
 * MessagePack测试
 **/
public class msgpackTest {
    public static void main(String[] args) {
        List<String> src=new ArrayList<String>();
        src.add("msgpack");
        src.add("kumofs");
        src.add("viver");
        MessagePack messagePack=  new MessagePack();
        try {
            byte[] raw=messagePack.write(src);
            List<String> dst1=messagePack.read(raw, Templates.tList(Templates.TString));
            System.out.println(dst1.get(0));
            System.out.println(dst1.get(1));
            System.out.println(dst1.get(2));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
