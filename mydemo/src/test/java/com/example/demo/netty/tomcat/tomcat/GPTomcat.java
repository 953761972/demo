package com.example.demo.netty.tomcat.tomcat;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Author XZQ
 * @Date 2021/9/14 22:15
 **/
public class GPTomcat {
    private int port =8081;
    private ServerSocket server;
    private Map<String,GPServlet> servletMapping=new HashMap<String,GPServlet>();
    private Properties webxml=new Properties();

    private void init(){
        //加载web。xml文件
        String WEB_INF=this.getClass().getResource("/").getPath();
        try{
            FileInputStream fis=new FileInputStream(WEB_INF+ "/src/test/resources/web.properties");
            webxml.load(fis);
            for(Object k:webxml.keySet()){
                String key=k.toString();
                if(key.endsWith(".url")){
                    String servletName=key.replaceAll("\\.url$","");
                    String url= webxml.getProperty(key);
                    String className=webxml.getProperty(servletName+".className");
                    GPServlet obj= (GPServlet) Class.forName(className).newInstance();
                    servletMapping.put(url,obj);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void  start(){
        init();
        try {
            server=new ServerSocket(this.port);
            System.out.println("GPTomcat 已经启动，端口："+this.port);
            while(true){
                Socket client=server.accept();
                process(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void process(Socket client) throws Exception {
        InputStream is=client.getInputStream();
        OutputStream out=client.getOutputStream();

        GPRequest request=new GPRequest(is);
        GPResponse response=new GPResponse(out);

        String url=request.getUrl();

        if(servletMapping.containsKey(url)){
            servletMapping.get(url).service(request,response);
        }else{
            response.write("404 - Not Found");
        }
        out.flush();
        out.close();

        is.close();
        client.close();
    }
}
