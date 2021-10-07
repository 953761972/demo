package com.example.demo.netty.tomcat.tomcat;

/**
 * @Author XZQ
 * @Date 2021/9/14 22:09
 **/
public class FirstServlet extends GPServlet{
    @Override
    protected void doGet(GPRequest request, GPResponse response) throws Exception {
        doPost(request,response);
    }

    @Override
    protected void doPost(GPRequest request, GPResponse response) throws Exception {
    response.write("This is First Servlet");
    }
}
