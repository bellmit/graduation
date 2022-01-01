package com.software.service.impl;

import com.software.datasource.DataSourceEnum;
import com.software.datasource.DataSourceRouter;
import com.software.mapper.AjMapper;
import com.software.mapper.WcnfzMapper;
import com.software.model.K2vModel;
import com.software.model.KvModel;
import com.software.service.MinorFzService;
import com.software.service.WcnfzDescService;
import com.software.utils.DateUtils;
import com.software.utils.DescriptionUtils;
import com.software.utils.ToolsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Service
public class MinorFzDescServiceImpl implements WcnfzDescService {
    //返回的是最近三年的 包括年新收案件量 年审结案件量
    @Override
    public List<String> getMinorYearAjCount(List<K2vModel> kvModels){
        if (kvModels==null) return null;
        List<String> res=new ArrayList<>();
        int len=kvModels.size();
        String curYear="",preYear="";
        int curCount=0,preCount=0;
        for(int i=1;i<len;i++) {
            StringBuffer desc=new StringBuffer("");
            K2vModel curDate = kvModels.get(i);
            K2vModel preDate = kvModels.get(i - 1);
            curYear = curDate.getName();
            preYear = preDate.getName();
            curCount = curDate.getValue1();
            preCount = preDate.getValue1();
            DecimalFormat df=new DecimalFormat("0.00%");
            double d=Math.abs(curCount-preCount)/(double)preCount;
            String flag="";
            if (curCount>=preCount){
                flag="上升";
            }else{
                flag="下降";
            }
            desc.append("新收案件量").append(curYear).append("年比").append(preYear).append("年").append(flag).append(df.format(d));
            res.add(desc.toString());
            //已结案件量
            curCount = curDate.getValue2();
            preCount = preDate.getValue2();
            d=Math.abs(curCount-preCount)/(double)preCount;
            if (curCount>=preCount){
                flag="上升";
            }else{
                flag="下降";
            }
            desc=new StringBuffer("");
            desc.append("已结案件量").append(curYear).append("年比").append(preYear).append("年").append(flag).append(df.format(d));
            res.add(desc.toString());
        }
        return res;
    }

    @Override
    public List<String> getMinorSszm(List<KvModel> kvModels){
        if (kvModels==null) return null;
        List<String> res=new ArrayList<>();
        int len=kvModels.size();
        String curZm="";
        int curCount=0;
        StringBuffer desc=new StringBuffer("被告人罪名主要罪名分别为：");
        int max=0,sum=0;
        for(int i=0;i<len;i++) {

            KvModel curData = kvModels.get(i);
            curCount = curData.getValue();
            sum+=curCount;

            if (curData.getName().equals("其他")){
                continue;
            }
            if(curCount>max){
                max=curCount;
                curZm=curData.getName();
            }
            max=Math.max(curCount,max);
            desc.append(curData.getName()).append(",");
        }
        res.add(desc.toString());
        //  double d=(double)max/(double)sum;
        res.add("最多的罪名是"+curZm);

        return res;

    }
    //14到15周岁涉诉罪名
    @Override
    public List<String> getMinorSszmRange1(List<KvModel> kvModels){
        if (kvModels==null) return null;
        return DescriptionUtils.getMaxBlDesc(kvModels,"14到15周岁涉诉罪名");
    }
    //16-17周岁涉诉罪名
    @Override
    public List<String> getMinorSszmRange2(List<KvModel> kvModels){
        if (kvModels==null) return null;
        return DescriptionUtils.getMaxBlDesc(kvModels,"16到17周岁涉诉罪名");
    }

    @Override
    public List<String> yqtxDesc(List<KvModel> kvModels){
        List<String> result=new ArrayList<>();
        for(KvModel model:kvModels){
            String str=model.getName()+"人数为"+model.getValue()+"人";
            result.add(str);
        }
        return result;
    }
}
