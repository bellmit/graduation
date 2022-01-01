package com.software.utils;

import com.software.model.AjjbModel;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ExcelUtil {
    public static void uploadExcelAboutUser(HttpServletResponse response, String fileName, List<String>columnList,List<AjjbModel> dataList){
        OutputStream os=null;
        setResponseHeader(response,fileName);
        try{
            os=response.getOutputStream();
            SXSSFWorkbook wb=new SXSSFWorkbook(1000);
            Sheet sheet1=wb.createSheet("sheet1");
            sheet1.setDefaultColumnWidth(20);
            int excelRow=0;
            Row titleRow=sheet1.createRow(excelRow++);
            for(int i=0;i<columnList.size();i++){
                Cell cell=titleRow.createCell(i);
                cell.setCellValue(columnList.get(i));
            }
            if(dataList!=null && dataList.size()>0){
                int count=1;
                for(int i=0;i<dataList.size();i++){
                    Row dataRow=sheet1.createRow(excelRow++);
                    Cell cell=dataRow.createCell(0);
                    cell.setCellValue(dataList.get(i).getAh());
                    Cell cell1=dataRow.createCell(1);
                    cell1.setCellValue(dataList.get(i).getAjmc());
                    Cell cell2=dataRow.createCell(2);
                    cell2.setCellValue(dataList.get(i).getLarq());
                    Cell cell3=dataRow.createCell(3);
                    cell3.setCellValue(dataList.get(i).getJarq());
                    Cell cell4=dataRow.createCell(4);
                    cell4.setCellValue(dataList.get(i).getAjxz());
                    Cell cell5=dataRow.createCell(5);
                    cell5.setCellValue(dataList.get(i).getSpcx());


                }

            }
            wb.write(os);
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(os!=null){
                    os.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    private static void setResponseHeader(HttpServletResponse response,String fileName){
        try{
            try{
                fileName=new String(fileName.getBytes(),"ISO8859-1");
            }catch (UnsupportedEncodingException e){
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(fileName,"utf-8"));
//            response.addHeader("Pargam","no-cache");
//            response.addHeader("Cache-Control","no-cache");
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
