package com.example.demo.office.pdf;

import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * @className: com.example.demo.office.pdf-> EditPDF
 * @description:
 * @author: xiongzq
 * @createDate: 2022-03-18 11:41
 * @version: 1.0
 * @todo:
 */
public class EditPDF {
    public static String path="D:\\tmp\\1.pdf";
    static File f=new File("D:\\tmp\\2.pdf");
    static OutputStream ouputStream = null;

    public static void main(String[] args) throws FileNotFoundException {
        ouputStream=new FileOutputStream(f);

        PdfReader reader = null;
        ByteArrayOutputStream bos;
        PdfStamper stamper = null;
        try{
            reader = new PdfReader(path);// 读取pdf模板
            bos = new ByteArrayOutputStream();
            stamper = new PdfStamper(reader, bos);
            AcroFields form = stamper.getAcroFields();

            int i = 0;
            java.util.Iterator<String> it = form.getFields().keySet().iterator();
            while (it.hasNext()) {
                String name = it.next().toString();
                form.setField(name, "ddsds");
            }
            stamper.setFormFlattening(true);//如果为false那么生成的PDF文件还能编辑，一定要设为true
            stamper.close();
            //在建多一份document导出。
            Document doc = new Document();
            PdfCopy copy = new PdfCopy(doc, ouputStream);
            doc.open();
            PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);
            copy.addPage(importPage);
            doc.close();
            ouputStream.flush();
        }catch (Exception e){

        }

    }
}
