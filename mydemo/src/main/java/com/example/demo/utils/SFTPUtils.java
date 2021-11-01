package com.example.demo.utils;

import com.jcraft.jsch.*;

import java.util.Vector;

/**
 * @Author XZQ
 * @Date 2021/10/29 23:37
 **/
public class SFTPUtils {
    static Session session = null;
    static Channel channel = null;
    static ChannelSftp sftp = null;
    static String rootPath = "/Volumes/FastSSD/files/";

    public static ChannelSftp getSFTP(int port, String user, String psw, String ip) throws Exception {
        JSch jsch = new JSch();
        if (port <= 0) {
            //连接服务器，采用默认端口
            session = jsch.getSession(user, ip);
        } else {
            //采用指定的端口连接服务器
            session = jsch.getSession(user, ip, port);
        }

        //如果服务器连接不上，则抛出异常
        if (session == null) {
            throw new Exception("session is null");
        }

        //设置登陆主机的密码
        session.setPassword(psw);//设置密码
        //设置第一次登陆的时候提示，可选值：(ask | yes | no)
        session.setConfig("StrictHostKeyChecking", "no");
        //设置登陆超时时间
        session.connect(30000);

        try {
            //创建sftp通信通道
            channel = (Channel) session.openChannel("sftp");
            channel.connect(1000);
            sftp = (ChannelSftp) channel;


            //进入服务器指定的文件夹
            sftp.cd(rootPath);
            //打印当前路径
            System.out.println(sftp.pwd());
            //System.out.println(sftp.ls("."));

        } finally {

        }
        return sftp;
    }
}