package com.software.service.impl;

import com.software.model.KvModel;
import com.software.service.SplcjdDescService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SplcjdDescServicelmpl implements SplcjdDescService {

    @Override
    public List<String> getDrajDesc(List<KvModel> kvModels){
        List<String> res=new ArrayList<>();
        int count=0;
        for(KvModel kvModel:kvModels){
            String str="";
            str=str+kvModel.getName()+"为"+kvModel.getValue()+"件";
            res.add(str);
            count=count+kvModel.getValue();
        }
        String strr="案件总数为"+count+"件";
        res.add(0,strr);
        return res;
    }

    @Override
    public List<String> getDycqjaDesc(List<KvModel> kvModels){
        List<String> res=new ArrayList<>();
        for(KvModel kvModel:kvModels){
            String str="";
            str=str+kvModel.getName()+"为"+kvModel.getValue()+"件";
            res.add(str);
        }
        return res;
    }
}
