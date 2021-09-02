package com.example.demo.spi;

/**
 * @Author XZQ
 * @Date 2021/7/10 12:54
 **/
public class QiyiCDN implements  UploadCDN{
    @Override
    public void upload(String url) {
        System.out.println("upload to qiyi cdn");
    }
}
