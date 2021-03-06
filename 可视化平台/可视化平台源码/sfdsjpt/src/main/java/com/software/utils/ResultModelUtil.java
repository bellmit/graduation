package com.software.utils;


import com.software.model.ResultModel;

/**
 * code 0表示成功, 1表示失败
 * */
public class ResultModelUtil {


    public static ResultModel success(String message){
        ResultModel rm=new ResultModel(0,message,"","","");
        return rm;
    }
    public static ResultModel success(Object chartData,Object title,Object description){
        ResultModel rm=new ResultModel(0,"",chartData,title,description);
        return rm;
    }
    public static ResultModel success(String message,Object chartData,Object title,Object description){
        ResultModel rm=new ResultModel(0,message,chartData,title,description);
        return rm;
    }
    public static ResultModel failed(String message){
        ResultModel rm=new ResultModel(1,message,"","","");
        return rm;
    }
}
