package com.software.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AgeUtils {
    public static String ze(String idCard){
        if(idCard==null||idCard.length()==0) return "";
        String dateReg = "\\d{6}(\\d{8})\\d{3}\\w";
        String detailReg = "(\\d{4})(\\d{2})(\\d{2})";
        Pattern pattern = Pattern.compile(dateReg);
        Matcher matcher = pattern.matcher(idCard);
        if(matcher.find()){
            String date = matcher.group(1);
            pattern = Pattern.compile(detailReg);
            matcher = pattern.matcher(date);
            if(matcher.find()){
                return matcher.group(1)+":"+matcher.group(2)+":"+matcher.group(3);
            }
        }
        return "";
    }
}
