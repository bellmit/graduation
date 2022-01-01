package com.software.utils;

import java.util.HashMap;
import java.util.Map;

public class ToolsUtils {


   public static Map<String,String> getSf(){
      String str="河北:13," +
              "河南:41," +
              "云南:53," +
              "辽宁:21," +
              "黑龙江:23," +
              "湖南:43," +
              "安徽:34," +
              "山东:37," +
              "新疆:65," +
              "江苏:32," +
              "浙江:33," +
              "江西:36," +
              "湖北:42," +
              "广西:45," +
              "甘肃:62," +
              "山西:14," +
              "内蒙古:15," +
              "陕西:61," +
              "吉林:22," +
              "福建:35," +
              "贵州:52," +
              "广东:44," +
              "青海:63," +
              "西藏:54," +
              "四川:51," +
              "天津:12," +
              "北京:11," +
              "上海:31," +
              "重庆:50," +
              "台湾:71,";
      String[] sf=str.split(",");
      Map<String,String> map=new HashMap<>();
      for(int i=0;i<sf.length;i++){
         String[] temp=sf[i].split(":");
         map.put(temp[1],temp[0]);

      }
      return map;

   }

   //获取子法院的数据。



}
