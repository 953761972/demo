package com.example.demo.netty.tomcat.tomcat;

/**
 * @Author XZQ
 * @Date 2021/9/14 21:53
 **/
public abstract class GPServlet {
    public void service(GPRequest request,GPResponse response) throws Exception {
        if("get".equalsIgnoreCase(request.getMethod())){
            doGet(request,response);
        }else{
            doPost(request,response);
        }
    }

    protected abstract void doGet(GPRequest request, GPResponse response) throws Exception;

    protected abstract void doPost(GPRequest request, GPResponse response) throws  Exception;
}
