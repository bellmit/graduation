package com.software.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultModel implements Serializable {

    private int code;
    private String message;
    private Object chartData;
    private Object title;
    private Object description;

    public ResultModel(int code,String message,Object chartData,Object title,Object description){
        this.code = code;
        this.message = message;
        this.chartData = chartData;
        this.title=title;
        this.description=description;
    }
    public ResultModel(Object data){
        this.chartData=data;
    }

}
