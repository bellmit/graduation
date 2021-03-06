package com.software.service.impl;

import com.software.service.IndexService;
import com.software.mapper.IndexMapper;
import com.software.model.AjjbModel;
import com.software.model.K2vModel;

import com.software.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IndexServiceImpl implements IndexService {

   @Autowired
   private IndexMapper indexMapper;

    @Override
    public List<K2vModel> getYwtpm(String beginTime, String endTime,String fydm){
        //格式 办案审判庭名称 当月结案数 当月未结数
        List<K2vModel> list=indexMapper.getYwtsj(beginTime,endTime,fydm);
        //取前五名
        list=list.stream().limit(5).collect(Collectors.toList());
        return list;
    }
    // 得到当天的实时数据并展示。每五分钟更新一次
    @Override
    public List<AjjbModel> getAjxxIntime(String fydm){
        Date time1=DateUtils.getZeroIntime();
        String beginTime=DateUtils.formatDateBySecond(time1);
        Date time2=DateUtils.getIntime();
        String endTime=DateUtils.formatDateBySecond(time2);
        List<AjjbModel> ajjbModels=indexMapper.getAjxxIntime(beginTime,endTime,fydm);
        return ajjbModels;
    }
}
