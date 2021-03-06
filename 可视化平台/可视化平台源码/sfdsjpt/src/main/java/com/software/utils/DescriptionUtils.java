package com.software.utils;

import com.software.model.KvModel;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class DescriptionUtils {

    //获得某一项的最大比例
    public static List<String> getMaxBlDesc(List<KvModel> kvModels,String title){
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
            res.add("当事人主要"+title+"为"+curInfo+";占比为"+df.format(bl));
        }else{
            res.add("暂未收录当前案件信息！");
        }
        return res;
    }

    //柱状图的通用描述
    public static List<String> getMainDesc(List<KvModel> kvModels,String title){
        List<String> res=new ArrayList<>();
        int len=kvModels.size();
        String curZm="";
        int curCount=0;
        StringBuffer desc=new StringBuffer(title+"分别为：");
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
        res.add("最多的"+title+"是"+curZm);
        return res;

    }
}
