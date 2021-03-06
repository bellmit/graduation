package com.software.service.impl;

import com.software.mapper.ZscqqqMapper;
import com.software.model.KvModel;
import com.software.service.AjDescriptionService;
import com.software.service.ZscqqqDescService;
import com.software.service.ZscqqqService;
import com.software.utils.DescriptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ZscqqqDescServiceImpl implements ZscqqqDescService {
    @Autowired
    AjDescriptionService ads;


    @Override
    public List<String> getAyDistribution(List<KvModel> kvModels) {
        if (kvModels==null) return null;
        // 案由分布
        List<String>  list=DescriptionUtils.getMaxBlDesc(kvModels,"案由分布");
        return list;
    }

    @Override
    public List<String> getDsrType(int[][] kvModels) {
        if (kvModels==null) return null;
        // 当事人分为被告和原告
        // 当事人类别为法人、、、
        int[] bg=new int[]{kvModels[0][1],kvModels[1][1],kvModels[2][1]};
        int[] yg=new int[]{kvModels[0][0],kvModels[1][0],kvModels[2][0]};
        Map<Integer,String> map=new HashMap();
        map.put(0,"法人");
        map.put(1,"个人");
        map.put(2,"行政机关");
        List<String> list=new ArrayList<>();
        int bgsum=kvModels[0][1]+kvModels[1][1]+kvModels[2][1];
        int ygsum=kvModels[0][0]+kvModels[1][0]+kvModels[2][0];
        int bgMax=0;
        int ygMax=0;
        String bgsf="",ygsf="";
        for (int i=0;i<3;i++){
            if (bgMax<bg[i]){
                bgMax=bg[i];
                bgsf=map.get(i);
            }
            if (ygMax<yg[i]){
                ygMax=yg[i];
                ygsf=map.get(i);
            }

        }

        List<String> res=new ArrayList<>();
        DecimalFormat df=new DecimalFormat("0.00%");
        if (bgsum!=0){
            double d=(double)bgMax/(double)bgsum;
            res.add("被告中占比最多的是"+bgsf+";占比为"+df.format(d));
        }else{
            res.add("暂未收录被告信息!");
        }
        if (ygsum!=0){
            double d=(double)ygMax/(double)ygsum;
            res.add("原告中占比最多的是"+ygsf+";占比为"+df.format(d));
        }else{
            res.add("暂未收录原告信息！");
        }

        return res;
    }

    @Override
    public List<String> getDwNature(List<KvModel> kvModels) {
        if (kvModels==null) return null;
        // 单位分为机关单位dsr_xp_jg和普通单位dsr_dw，需要分别查询
        List<String> description=DescriptionUtils.getMaxBlDesc(kvModels,"被告单位性质");
        return  description;
    }

    @Override
    public List<String> getBgCount(List<KvModel> kvModels) {
        if (kvModels==null) return null;

        List<String> description=DescriptionUtils.getMaxBlDesc(kvModels,"被告个数");
        return  description;
    }

    @Override
    public List<String> getProcessingCycle(List<KvModel> kvModels) {
        if (kvModels==null) return null;
        List<String> res=new ArrayList<>();
        if (kvModels.size()==0) {
            res.add("暂未收录当前案件信息！");
        }
        String ay="";
        int max=0;
        for (KvModel kvModel:kvModels){
            if (kvModel.getValue()>max){
                max=kvModel.getValue();
                ay=kvModel.getName();
            }
        }
        res.add("平均审理周期时间最长是"+ay+";"+"时间为"+max+"天");
        return  res;
    }

}
