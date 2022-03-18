package com.example.demo.office.word;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @className: com.example.demo.office.word-> DocUtil
 * @description:
 * @author: xiongzq
 * @createDate: 2022-03-18 15:16
 * @version: 1.0
 * @todo:
 */
public class PoiTest {
    public static void main(String[] args) throws FileNotFoundException {
        Map map = new HashMap();
        map.put("aaa", "月报");
        map.put("bbb", "2018-5-28");
        map.put("ccc", "岁的法国大使馆的风格");
        getBuild("D:\\tmp\\1.doc", map, "D:\\tmp\\2.doc");
    }

    public static void getBuild(String tmpFile, Map<String, String> contentMap, String exportFile) throws FileNotFoundException {

        //InputStream inputStream = DocUtil.class.getClassLoader().getResourceAsStream(tmpFile);
        File f=new File(tmpFile);
        FileInputStream fi=new FileInputStream(f);
        System.out.println(f.exists());
//        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(tmpFile);
        HWPFDocument document = null;
        try {
            document = new HWPFDocument(fi);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 读取文本内容
        Range bodyRange = document.getRange();
        // 替换内容
        for (Map.Entry<String, String> entry : contentMap.entrySet()) {
            bodyRange.replaceText("${" + entry.getKey() + "}", entry.getValue());
        }
        //导出到文件
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            document.write(byteArrayOutputStream);
            OutputStream outputStream = new FileOutputStream(exportFile);
            outputStream.write(byteArrayOutputStream.toByteArray());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
