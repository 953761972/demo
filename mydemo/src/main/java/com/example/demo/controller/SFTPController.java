package com.example.demo.controller;

import com.example.demo.utils.SFTPUtils;
import com.jcraft.jsch.ChannelSftp;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

/**
 * @Author XZQ
 * @Date 2021/10/29 23:25
 **/
@Controller
public class SFTPController {

    @RequestMapping("/getFile")
    public void getFile(@RequestParam("filename") String filename, HttpServletResponse response) throws Exception {

        ChannelSftp sftp = SFTPUtils.getSFTP(-1, "xzq", "xzq520", "localhost");
        //写之前设置响应流以附件的形式打开返回值,这样可以保证前边打开文件出错时异常可以返回给前台
        response.setHeader("Content-Disposition","attachment;filename="+"1.txt");
        sftp.get(filename,response.getOutputStream());

    }

    @RequestMapping("/UploadFile")
    @ResponseBody
    public String UploadFile(@RequestParam("file") MultipartFile file) {
        try{
            ChannelSftp sftp = SFTPUtils.getSFTP(-1, "xzq", "xzq520", "localhost");
        //以下代码实现从本地上传一个文件到服务器，如果要实现下载，对换以下流就可以了
        InputStream instream = file.getInputStream();
        sftp.put(instream,"1.txt");
        instream.close();
    } catch(Exception e) {
        e.printStackTrace();
    }
        return"success";
    }
}
