package com.software.service.impl;

import com.software.model.K2vModel;
import com.software.model.K3vModel;
import com.software.model.KvModel;
import com.software.service.LhjfDescService;
import com.software.utils.DescriptionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LhjfDescServiceImpl implements LhjfDescService {

    @Override
    public List<String> getLhjfPjjg(List<KvModel> kvModels) {
        if (kvModels==null) return null;
        List<String> res=DescriptionUtils.getMaxBlDesc(kvModels,"判决结果");
        return res;
    }

    @Override
    public List<String> getAjSj(List<K2vModel> k2vModels){
        if(k2vModels==null||k2vModels.size()==0){
            return null;
        }
        List<String> list=new ArrayList<>();
        k2vModels=k2vModels.stream().sorted(Comparator.comparingInt(k->k.getValue1())).collect(Collectors.toList());
        if(k2vModels.get(k2vModels.size()-1).getValue1()!=0){
            String str="新收案件数最少为";
            for(int i=0;i<k2vModels.size();i++){
                if(k2vModels.get(i).getValue1()==k2vModels.get(0).getValue1()){
                    str=str+k2vModels.get(i).getName()+"，";
                }
                else{
                    break;
                }
            }
            str=str.substring(0,str.length()-1);
            String str1="新收案件数最多为";
            for(int i=k2vModels.size()-1;i>=0;i--){
                if(k2vModels.get(i).getValue1()==k2vModels.get(k2vModels.size()-1).getValue1()){
                    str1=str1+k2vModels.get(i).getName()+"，";
                }
            }
            str1=str1.substring(0,str1.length()-1);
            list.add(str1);list.add(str);
        }
        k2vModels=k2vModels.stream().sorted(Comparator.comparingInt(k->k.getValue2())).collect(Collectors.toList());
        if(k2vModels.get(k2vModels.size()-1).getValue2()!=0){
            String str="已结案件数最少为";
            for(int i=0;i<k2vModels.size();i++){
                if(k2vModels.get(i).getValue2()==k2vModels.get(0).getValue2()){
                    str=str+k2vModels.get(i).getName()+"，";
                }
                else{
                    break;
                }
            }
            str=str.substring(0,str.length()-1);
            String str1="已结案件数最多为";
            for(int i=k2vModels.size()-1;i>=0;i--){
                if(k2vModels.get(i).getValue2()==k2vModels.get(k2vModels.size()-1).getValue2()){
                    str1=str1+k2vModels.get(i).getName()+"，";
                }
            }
            str1=str1.substring(0,str1.length()-1);
            list.add(str1);list.add(str);
        }
        return list;
    }

    @Override
    public List<String> getAjDesc(List<K3vModel> k3vModels) {
        if(k3vModels.size()==0){
            return new ArrayList<>();
        }
        k3vModels=k3vModels.stream().sorted(Comparator.comparingInt(k->k.getValue1())).collect(Collectors.toList());
        List<String> list=new ArrayList<>();
        if(k3vModels.get(k3vModels.size()-1).getValue1()==0){
            return list;
        }
        String str="当期案件数最少为";
        for(int i=0;i<k3vModels.size();i++){
            if(k3vModels.get(i).getValue1()==k3vModels.get(0).getValue1()){
                str=str+k3vModels.get(i).getName()+"，";
            }
            else{
                break;
            }
        }
        str=str.substring(0,str.length()-1);
        String str1="当期案件数最多为";
        for(int i=k3vModels.size()-1;i>=0;i--){
            if(k3vModels.get(i).getValue1()==k3vModels.get(k3vModels.size()-1).getValue1()){
                str1=str1+k3vModels.get(i).getName()+"，";
            }
        }
        str1=str1.substring(0,str1.length()-1);
        list.add(str1);list.add(str);
        return list;
    }
}
