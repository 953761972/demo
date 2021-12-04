package com.example.demo.office.word;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.junit.Assert;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author XZQ
 * @Date 2021/12/4 22:43
 **/
public class wordTest {
    //https://poi.apache.org/components/spreadsheet/how-to.html#sxssf
    public static void main(String[] args) throws IOException {
        SXSSFWorkbook wb = new SXSSFWorkbook(100); // keep 100 rows in memory, exceeding rows will be flushed to disk
        Sheet sh = wb.createSheet();
        for(int rownum = 0; rownum < 1000; rownum++){
            Row row = sh.createRow(rownum);
            for(int cellnum = 0; cellnum < 10; cellnum++){
                Cell cell = row.createCell(cellnum);
                String address = rownum+"ddd"+cellnum;
                cell.setCellValue(address);
            }
        }
        // Rows with rownum < 900 are flushed and not accessible
        for(int rownum = 0; rownum < 900; rownum++){
            Assert.assertNull(sh.getRow(rownum));
        }
        // ther last 100 rows are still in memory
        for(int rownum = 900; rownum < 1000; rownum++){
            Assert.assertNotNull(sh.getRow(rownum));
        }
        FileOutputStream out = new FileOutputStream("sxssf.xlsx");
        wb.write(out);
        out.close();
        // dispose of temporary files backing this workbook on disk
        wb.dispose();
    }
}
