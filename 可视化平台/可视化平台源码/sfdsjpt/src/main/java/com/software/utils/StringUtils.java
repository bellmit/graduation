package com.software.utils;

public class StringUtils {
    public static String likePattern(String[] strs,String str){
        for (String s:strs){
            if (s.charAt(0)==str.charAt(0)&&s.charAt(1)=='*') {
                return s.substring(2);
            }
        }
        return "";
    }
    // 字符串精确匹配

    public static String equalPattern(String[] strs,String str){

        for(String s:strs){
            if (s.startsWith(str)) {
                //这里的审判程序代字如果加上就是三位如果不加上就是2位
                if (str.length()==3){
                    return s.substring(3);
                }
                if (str.length()==2){
                    return s.substring(2);
                }

            }
        }
        return "";
    }
    //获取类别编号
    public static String jxLbbh(String ajxz, String spcx,String spcxdz, String sjxx){
        String[] sjxxs = sjxx.split(",");
        //匹配类型
        String pplx = ajxz+spcx+spcxdz;
        // 先进行精确匹配
        String lbbh = StringUtils.equalPattern(sjxxs,pplx);
        //若精确匹配未得到结果 则进行模糊匹配
        if ("".equals(lbbh)){
            lbbh = StringUtils.likePattern(sjxxs,pplx);
        }
        return lbbh;
    }
}
