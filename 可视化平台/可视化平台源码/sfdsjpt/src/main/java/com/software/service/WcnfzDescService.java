package com.software.service;

import com.software.model.K2vModel;
import com.software.model.KvModel;

import java.util.List;

public interface WcnfzDescService {

    //返回的是最近三年的 包括年新收案件量 年审结案件量
    List<String> getMinorYearAjCount(List<K2vModel> kvModels);
    List<String> getMinorSszm(List<KvModel> kvModels);
    //14到15周岁涉诉罪名
    List<String> getMinorSszmRange1(List<KvModel> kvModels);
    //16-17周岁涉诉罪名
    List<String> getMinorSszmRange2(List<KvModel> kvModels);

    List<String> yqtxDesc(List<KvModel> kvModels);



}
