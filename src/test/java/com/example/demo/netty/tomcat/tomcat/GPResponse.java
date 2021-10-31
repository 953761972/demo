package com.example.demo.netty.tomcat.tomcat;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * @Author XZQ
 * @Date 2021/9/14 22:02
 **/
public class GPResponse {
    private OutputStream out;

    public GPResponse(OutputStream out) {
        this.out = out;
    }

    public void write(String s) throws IOException {
        StringBuilder sb=new StringBuilder();
        sb.append("HTTP/1.1 200 ok\n")
                .append("Content-Type:text/html;\n")
                .append("aaa:bbb;\n")
                .append("\r\n")
                .append(s);
        out.write(sb.toString().getBytes(StandardCharsets.UTF_8));
    }
}
