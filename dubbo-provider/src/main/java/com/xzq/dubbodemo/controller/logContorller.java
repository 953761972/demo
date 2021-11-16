package com.xzq.dubbodemo.controller;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.xzq.dubbocommon.bean.Log;
import com.xzq.dubbocommon.service.LogService;
import com.xzq.dubbodemo.config.log;
import com.xzq.dubbodemo.exception.MyException;
import com.xzq.dubbodemo.pojo.ErrorInfo;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author XZQ
 * @Date 2021/11/1 00:23
 **/
@Controller
@RequestMapping("/")
public class logContorller {
    @Resource
    LogService logService;

    @ResponseBody
    @RequestMapping("/getlog/{logid}")
    @log
    public String getlog(@PathVariable int logid){
        return logService.gerLogbyId(logid).toString();
    }

    @ResponseBody
    @RequestMapping("/getlog1/{logid}")
    @log
    public ResponseEntity<?> getlog1(@PathVariable int logid) throws  MyException {
        throw new MyException("error happends");
    }

    @ResponseBody
    @RequestMapping("/insert")
    @log
    @Transactional
    public ResponseEntity<?> insertone()  {
        try{
            Log log1=new Log();
            log1.setAge(100);
            log1.setName("dddd");
            logService.Insert(log1);
            Log log2=new Log();
            log2.setLogid(100);
            log2.setAge(100);
            log2.setName("dddd");
            logService.Insert(log2);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("succedd");
        return new ResponseEntity<>(new ErrorInfo<>(), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping("/download/{count}")
    @log
    @Transactional
    public void download(@PathVariable("")int count, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setHeader("Content-Type","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        resp.setHeader("Content-Disposition","attachment; filename=111.xlsx");
        List<Log> list=logService.selectByCount();
        ExcelWriter writer = ExcelUtil.getWriter();
        writer.write(list);
        writer.flush(resp.getOutputStream());
    }

    @ResponseBody
    @RequestMapping("/downloadexcelbypoi/{count}")
    @log
    @Transactional
    public void downloadexcelbypoi(@PathVariable("")int count, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setHeader("Content-Type","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        resp.setHeader("Content-Disposition","attachment; filename=111.xlsx");
        List<Log> list=logService.selectByCount();
        HSSFWorkbook hook=new HSSFWorkbook();
        HSSFSheet sheet = hook.createSheet("Sheet1");
        String[] tableHeaders = {"id", "年龄", "姓名"};

        HSSFCellStyle cellStyle = hook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        Font font = hook.createFont();
        short j=10;
        font.setColor(j);
        font.setBold(true);
        cellStyle.setFont(font);

        HSSFRow row=null;
        // 将第一行的三个单元格给合并
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));
        row = sheet.createRow(0);
        HSSFCell beginCell = row.createCell(0);
        beginCell.setCellValue("通讯录");
        beginCell.setCellStyle(cellStyle);

        row = sheet.createRow(1);
        // 创建表头
        for (int i = 0; i < tableHeaders.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(tableHeaders[i]);
            cell.setCellStyle(cellStyle);
        }

        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow(i + 2);

            Log log = list.get(i);
            row.createCell(0).setCellValue(log.getLogid());
            row.createCell(1).setCellValue(log.getName());
            row.createCell(2).setCellValue(log.getAge());
        }

        hook.write(resp.getOutputStream());
    }
}
