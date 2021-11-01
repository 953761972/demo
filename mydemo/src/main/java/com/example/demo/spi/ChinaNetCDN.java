package com.example.demo.spi;

/**
 * @Author XZQ
 * @Date 2021/7/10 12:55
 **/
public class ChinaNetCDN implements UploadCDN{
    @Override
    public void upload(String url) {
        System.out.println("upload to chinaNet cdn");
    }
}
