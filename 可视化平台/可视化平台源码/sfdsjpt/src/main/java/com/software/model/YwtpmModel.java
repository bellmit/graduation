package com.software.model;


import lombok.Data;
//业务庭排名model
@Data
public class YwtpmModel {
    //审判庭名称
    private String baspt;
    //当月结案数
    private int dyjas;
    //当月未结数
    private int dywjs;
}
