package com.software.service.impl;

import com.software.model.K2vModel;
import com.software.model.K3vModel;
import com.software.model.KvModel;
import com.software.service.SyjfDescService;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class SyjfDescServicelmpl implements SyjfDescService {
    @Override
    public List<String> getSyjfAjCount(List<K3vModel> kvModels){
        if (kvModels==null) return null;
        List<String> res=new ArrayList<>();
        int len=kvModels.size();
        String curYear="",preYear="";
        int curCount=0,preCount=0;
        for(int i=1;i<len;i++) {
            StringBuffer desc=new StringBuffer("");
            K3vModel curDate = kvModels.get(i);
            K3vModel preDate = kvModels.get(i - 1);
            curYear = curDate.getName();
            preYear = preDate.getName();
            curCount = curDate.getValue1();
            preCount = preDate.getValue1();
            DecimalFormat df=new DecimalFormat("0.00%");
            if(preCount==0){
                continue;
            }
            double d=Math.abs(curCount-preCount)/(double)preCount;
            String flag="";
            if (curCount>=preCount){
                flag="上升";
            }else{
                flag="下降";
            }
            desc.append("立案数").append(curYear).append("年比").append(preYear).append("年").append(flag).append(df.format(d));
            res.add(desc.toString());
            //已结案件量
            curCount = curDate.getValue2();
            preCount = preDate.getValue2();
            if(preCount==0){
                continue;
            }
            d=Math.abs(curCount-preCount)/(double)preCount;
            if (curCount>=preCount){
                flag="上升";
            }else{
                flag="下降";
            }
            desc=new StringBuffer("");
            desc.append("结案数").append(curYear).append("年比").append(preYear).append("年").append(flag).append(df.format(d));
            res.add(desc.toString());
        }
        return res;
    }

    @Override
    public List<String> getSyjfBmAjCount(List<K3vModel> k3vModels){
        List<String> list=new ArrayList<>();
        for(K3vModel model:k3vModels){
            String str=model.getName()+"的立案数为"+model.getValue1()+"件，结案数为"+model.getValue2()+"件";
            list.add(str);
        }
        return list;
    }
    @Override
    public List<String> getYgrxbDesc(List<KvModel> kvModels){
        List<String> res=new ArrayList<>();
        int boyCount=0,girlCount=0;

        for (KvModel kvModel:kvModels){
            if (kvModel.getName().equals("男性")){
                boyCount=kvModel.getValue();
            }else if (kvModel.getName().equals("女性")){
                girlCount=kvModel.getValue();
            }
        }
        if (boyCount>girlCount){
            res.add("男性原告人多于女性原告人");
        }else if (boyCount<girlCount){
            res.add("女性原告人多于男性原告人");
        }else{
            res.add("男女原告人人数相等");
        }
        res.add("男性原告人数为"+boyCount+"人");
        res.add("女性原告人数为"+girlCount+"人");
        return res;
    }
    @Override
    public List<String> getYgrsfDesc(List<KvModel> kvModels){
        if (kvModels==null) return null;
        List<String> res=new ArrayList<>();
        int len=kvModels.size();
        String curInfo="";
        int curCount=0;
        int sum=0,max=0;

        for(int i=0;i<len;i++) {
            //   StringBuffer desc=new StringBuffer("");
            KvModel curData = kvModels.get(i);
            if(curData==null){
                continue;
            }
            curCount = curData.getValue();
            max=Math.max(max,curCount);
            if (curCount==max){
                curInfo=curData.getName();
            }
            sum+=curCount;
        }
        if (sum!=0){
            DecimalFormat df=new DecimalFormat("0.00%");
            double bl=(double) max/(double)sum;
            res.add("原告人主要身份为"+curInfo+";占比为"+df.format(bl));
            for(KvModel model:kvModels){
                res.add(model.getName()+"的人数为"+model.getValue()+"人");
            }
        }else{
            res.add("暂未收录当前案件信息！");
        }
        return res;
    }

    @Override
    public List<String> getYgrAgeDesc(List<KvModel> kvModels){
        if (kvModels==null) return null;
        List<String> res=new ArrayList<>();
        int len=kvModels.size();
        String curInfo="";
        int curCount=0;
        int sum=0,max=0;

        for(int i=0;i<len;i++) {
            //   StringBuffer desc=new StringBuffer("");
            KvModel curData = kvModels.get(i);
            if(curData==null){
                continue;
            }
            curCount = curData.getValue();
            max=Math.max(max,curCount);
            if (curCount==max){
                curInfo=curData.getName();
            }
            sum+=curCount;
        }
        if (sum!=0){
            DecimalFormat df=new DecimalFormat("0.00%");
            double bl=(double) max/(double)sum;
            res.add("原告人主要"+"年龄段"+"为"+curInfo+";占比为"+df.format(bl));
            for(KvModel model:kvModels){
                res.add(model.getName()+"的人数为"+model.getValue()+"人");
            }
        }else{
            res.add("暂未收录当前案件信息！");
        }
        return res;
    }

    @Override
    public List<String> getResultDesc(List<KvModel> kvModels){
        int max=0;
        String res="";
        for(KvModel model:kvModels){
            if(model.getValue()>max){
                max=model.getValue();
                res=model.getName();
            }
        }
        String result="判决结果为"+res+"的案子数量最多，为"+max+"件";
        List<String> results=new ArrayList<>();
        results.add(result);
        return results;
    }
}
