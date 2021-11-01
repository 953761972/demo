package com.example.demo.netty.tomcat.tomcatusenetty;


/**
 * @Author XZQ
 * @Date 2021/9/14 22:10
 **/
public class SecondServlet extends GPServlet {
    @Override
    protected void doGet(GPRequest request, GPResponse response) throws Exception {
        doPost(request,response);
    }

    @Override
    protected void doPost(GPRequest request, GPResponse response) throws Exception {
        response.write("This is netty2 Servlet");
    }
}
