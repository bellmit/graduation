package com.software.service.impl;

import com.software.model.KvModel;
import com.software.service.LadsjDescService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LadsjDescServicelmpl implements LadsjDescService {
    @Override
    public List<String> getSqtjsjDesc(List<KvModel> kvModels){
        List<String> res=new ArrayList<>();
        for(KvModel kvModel:kvModels){
            String str=kvModel.getName()+"为"+kvModel.getValue()+"件";
            res.add(str);
        }
        return res;
    }

    @Override
    public List<String> getDjlasj(List<KvModel> kvModels){
        List<String> res=new ArrayList<>();
        for(KvModel model:kvModels){
            String str=model.getName()+"的案件数量为"+model.getValue()+"件";
            res.add(str);
        }
        return res;
    }

    @Override
    public List<String> getDjlasjLx(List<KvModel> kvModels){
        List<String> res=new ArrayList<>();
        int max=0;
        String name="";
        for(KvModel model:kvModels){
            if(model.getValue()>max){
                max=model.getValue();
                name=model.getName();
            }
        }
        res.add(name+"类型的立案案件数量最多，为"+max+"件");
        for(KvModel model:kvModels){
            if(name.equals(model.getName())){
                continue;
            }
            String str=model.getName()+"类型的立案案件数量为"+model.getValue()+"件";
            res.add(str);
        }
        return res;
    }
}
