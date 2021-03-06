package com.software.service;

import com.software.mapper.AjMapper;
import com.software.model.K2vModel;
import com.software.model.K3vModel;
import com.software.model.KvModel;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

//未成年人犯罪service
public interface MinorFzService {
    //未成年人罪名分布
    List<KvModel> getMinorZmfb(String beginTime,String endTime,String fydm);
    //返回的是最近三年的 包括年新收案件量 年审结案件量
    List<K2vModel> getMinorYearAjCount(String fydm);
    //案件地域分布
    List<KvModel> getMinorDyfb(String fydm,String beginTime, String endTime);

    //被告人性别
    List<K3vModel> getMinorBgrxb(String beginTime,String endTime,String fydm);
    //各省未成年犯罪情况
    List<KvModel> getMinorWlaj(String beginTime,String endTime,String fydm);
    //未成年人年龄分布 一个年龄一个段
    List<K3vModel> getMinorBgrAge(String beginTime,String endTime,String fydm);
    //未成年人文化程度
    List<K3vModel> getMinorWhcd(String beginTime,String endTime,String fydm);
    //未成年人外来人口
    List<K3vModel> getMinorWlrk(String beginTime,String endTime,String fydm);
    //涉诉罪名情况 最近两年
    List<K2vModel> getMinorSszm(String fydm);
    //14到15周岁涉诉罪名
    List<KvModel> getMinorSszmRange1(String beginTime,String endTime,String fydm);
    //16-17周岁涉诉罪名
    List<KvModel> getMinorSszmRange2(String beginTime,String endTime,String fydm);
    List<KvModel> yqtx(String beginTime,String endTime,String fydm);
    List<K3vModel> getAjsl(String beginTime,String endTime,String fydm);
    List<K3vModel> getBmslLa(String beginTime, String endTime, String fydm);
    List<K3vModel> getBmslJa(String beginTime,String endTime,String fydm);
    List<K3vModel> getBmslAvg(String beginTime,String endTime,String fydm);
    List<K2vModel> getAjSj(String fydm);
    void exportWord(HttpServletResponse response, String beginTime, String endTime, String fydm);
}
