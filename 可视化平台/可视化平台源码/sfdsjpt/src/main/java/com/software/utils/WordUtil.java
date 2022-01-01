package com.software.utils;

import com.software.model.AjjbModel;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class WordUtil {
    public static void exportWord(HttpServletResponse response, String fileName,String title, List<String> dataList){
        OutputStream os=null;
        setResponseHeader(response,fileName);
        try{
            os=response.getOutputStream();
            XWPFDocument document=new XWPFDocument();
            XWPFParagraph titlePar=document.createParagraph();
            titlePar.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun titleRun=titlePar.createRun();
            titleRun.setText(title);
            titleRun.setFontSize(12);
            titleRun.setFontFamily("华文楷体");
            titleRun.addBreak();
            for(String str:dataList){
                String[] strs=str.split("\n");
                for(int i=0;i<strs.length;i++) {
                    XWPFParagraph paragraph = document.createParagraph();
//                    paragraph.setIndentationFirstLine(2);
                    XWPFRun run = paragraph.createRun();
                    run.setText(strs[i]);
                    run.setFontSize(10);
                    run.setFontFamily("华文楷体");
                    if(i==strs.length-1){
                        run.addBreak();
                    }
                }

            }
            document.write(os);
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
                fileName=new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
            }catch (UnsupportedEncodingException e){
                e.printStackTrace();
            }
            response.setContentType("application/msword");
            response.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(fileName,"utf-8"));
//            response.addHeader("Pargam","no-cache");
//            response.addHeader("Cache-Control","no-cache");
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
