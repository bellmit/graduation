package com.software.service.impl;

import com.software.model.K2vModel;
import com.software.model.K3vModel;
import com.software.model.KvModel;
import com.software.service.AjDescriptionService;
import com.software.utils.DescriptionUtils;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AjDescServiceImpl implements AjDescriptionService {
    @Override
    public List<String> getAjCountDesc(List<KvModel> kvModels){
        if (kvModels==null) {
            return null;
        }
        List<String> res=new ArrayList<>();
        int len=kvModels.size();
        String curYear="",preYear="";
        int curCount=0,preCount=0;
        for(int i=1;i<len;i++) {
            StringBuffer desc=new StringBuffer("");
            KvModel curDate = kvModels.get(i);
            KvModel preDate = kvModels.get(i - 1);
            curYear = curDate.getName();
            preYear = preDate.getName();
            curCount = curDate.getValue();
            preCount = preDate.getValue();

            String flag="";
            if (curCount>preCount){
                flag="上升";
            }else if (curCount<preCount){
                flag="下降";
            }
            if (flag.length()!=0){
                //防止除0
                if (preCount==0){
                    desc.append(curYear).append("比").append(preYear).append("增加").append(curCount).append("个案件");
                }else{
                    double d=Math.abs(curCount-preCount)/(double)preCount;
                    DecimalFormat df=new DecimalFormat("0.00%");
                    desc.append(curYear).append("比").append(preYear).append(flag).append(df.format(d));
                }

            }else{
                //
                desc.append(curYear).append("和").append(preYear).append("持平");
            }

            res.add(desc.toString());
        }
        return res;
    }

    @Override
    public List<String> getAreaDistributionDesc(List<KvModel> kvModels){
        if (kvModels==null) return null;
        List<String> res=new ArrayList<>();
        int len=kvModels.size();
        String curArea="";
        int curCount=0;
        for(int i=0;i<len;i++) {
            StringBuffer desc=new StringBuffer("");
            KvModel curData = kvModels.get(i);
            curArea = curData.getName();
            curCount = curData.getValue();
            desc.append(curArea).append(":").append(curCount);
            res.add(desc.toString());
        }
        return res;
    }

    @Override
    public List<String> getBgrxbDesc(List<KvModel> kvModels){

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
            res.add("男性被告人多于女性被告人");
        }else if (boyCount<girlCount){
            res.add("女性被告人多于男性被告人");
        }else{
            res.add("男女被告人人数相等");
        }
        res.add("男性被告人数为"+boyCount+"人");
        res.add("女性被告人数为"+girlCount+"人");
        return res;
    }

    @Override
    public List<String> getBgrsfDesc(List<KvModel> kvModels){
        if (kvModels==null) {
            return null;
        }
        List<String> res=DescriptionUtils.getMaxBlDesc(kvModels,"身份");
        for(KvModel model:kvModels){
            res.add(model.getName()+"的人数为"+model.getValue()+"人");
        }
        return res;
    }

    @Override
    public List<String> getBgrWhcdDesc(List<KvModel> kvModels){
        if (kvModels==null) {
            return null;
        }
        List<String> res=DescriptionUtils.getMaxBlDesc(kvModels,"文化程度");
        for(KvModel model:kvModels){
            res.add(model.getName()+"的人数为"+model.getValue()+"人");
        }
        return res;
    }

    @Override
    public List<String> getBgrAgeDesc(List<KvModel> kvModels){
        if (kvModels==null) {
            return null;
        }
        List<String> res=DescriptionUtils.getMaxBlDesc(kvModels,"年龄段");
        for(KvModel model:kvModels){
            res.add(model.getName()+"的人数为"+model.getValue()+"人");
        }
        return res;
    }

    @Override
    public List<String> getDsrxbDesc(List<KvModel> kvModels){

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
            res.add("男性当事人多于女性当事人");
        }else if (boyCount<girlCount){
            res.add("女性当事人多于男性当事人");
        }else{
            res.add("男女当事人人数相等");
        }
        res.add("男性当事人数为"+boyCount+"人");
        res.add("女性当事人数为"+girlCount+"人");
        return res;
    }



    /**
     * 有期徒刑量刑
     */
    @Override
    public List<String> getYqtxlxDesc(List<KvModel> kvModels){
        if (kvModels==null) return null;
        List<String> res=DescriptionUtils.getMaxBlDesc(kvModels,"有期徒刑量刑");
        return res;
    }


    @Override
    public List<String> getAjslDesc(List<K3vModel> k3vModels){
        List<String> list=new ArrayList<>();
        DecimalFormat df=new DecimalFormat("0.00%");
        for(K3vModel model:k3vModels){
            String str="";
            if(model.getValue1()>model.getValue2()){
                if(model.getValue2()!=0) {
                    str = model.getName() + "上同比上升" + df.format((model.getValue1() - model.getValue2()) / (double) model.getValue2());
                }
                else{
                    str = model.getName() + "上同比上升";
                }
            }
            else if(model.getValue1()<model.getValue2()){
                if(model.getValue1()!=0) {
                    str = model.getName() + "上同比下降" + df.format((model.getValue2() - model.getValue1()) / (double) model.getValue2());
                }
                else{
                    str = model.getName() + "上同比下降";
                }
            }
            else{
                str=model.getName()+"上同比持平";

            }
            if(model.getValue1()>model.getValue3()){
                if(model.getValue3()!=0) {
                    str += "，环比上升" + df.format((model.getValue1() - model.getValue3()) / (double) model.getValue3());
                }
                else{
                    str += "，环比上升";
                }
            }
            else if(model.getValue1()<model.getValue3()){
                if(model.getValue1()!=0) {
                    str += "，环比下降" + df.format((model.getValue3() - model.getValue1()) / (double) model.getValue3());
                }
                else{
                    str += "，环比下降";
                }
            }
            else{
                str+="，环比持平";

            }
            list.add(str);
        }
        return list;
    }
    @Override
    public List<String> getBmslLa(List<K3vModel> k3vModels){
        List<String> list=new ArrayList<>();
        DecimalFormat df=new DecimalFormat("0.00%");
        for(K3vModel model:k3vModels){
            String str="";
            if(model.getValue1()>model.getValue2()){
                if(model.getValue2()!=0) {
                    str = model.getName() + "立案数同比上升" + df.format((model.getValue1() - model.getValue2()) / (double) model.getValue2());
                }
                else{
                    str = model.getName() + "立案数同比上升";
                }
            }
            else if(model.getValue1()<model.getValue2()){
                if(model.getValue1()!=0) {
                    str = model.getName() + "立案数同比下降" + df.format((model.getValue2() - model.getValue1()) / (double) model.getValue2());
                }
                else{
                    str = model.getName() + "立案数同比下降";
                }
            }
            else{
                str=model.getName()+"立案数同比持平";

            }
            if(model.getValue1()>model.getValue3()){
                if(model.getValue3()!=0) {
                    str += "，环比上升" + df.format((model.getValue1() - model.getValue3()) / (double) model.getValue3());
                }
                else{
                    str += "，环比上升";
                }
            }
            else if(model.getValue1()<model.getValue3()){
                if(model.getValue1()!=0) {
                    str += "，环比下降" + df.format((model.getValue3() - model.getValue1()) / (double) model.getValue3());
                }
                else{
                    str += "，环比下降";
                }
            }
            else{
                str+="，环比持平";

            }
            list.add(str);
        }
        return list;
    }
    @Override
    public List<String> getBmslJa(List<K3vModel> k3vModels){
        List<String> list=new ArrayList<>();
        DecimalFormat df=new DecimalFormat("0.00%");
        for(K3vModel model:k3vModels){
            String str="";
            if(model.getValue1()>model.getValue2()){
                if(model.getValue2()!=0) {
                    str = model.getName() + "结案数同比上升" + df.format((model.getValue1() - model.getValue2()) / (double) model.getValue2());
                }
                else{
                    str = model.getName() + "结案数同比上升";
                }
            }
            else if(model.getValue1()<model.getValue2()){
                if(model.getValue1()!=0) {
                    str = model.getName() + "结案数同比下降" + df.format((model.getValue2() - model.getValue1()) / (double) model.getValue2());
                }
                else{
                    str = model.getName() + "结案数同比下降";
                }
            }
            else{
                str=model.getName()+"结案数同比持平";

            }
            if(model.getValue1()>model.getValue3()){
                if(model.getValue3()!=0) {
                    str += "，环比上升" + df.format((model.getValue1() - model.getValue3()) / (double) model.getValue3());
                }
                else{
                    str += "，环比上升";
                }
            }
            else if(model.getValue1()<model.getValue3()){
                if(model.getValue1()!=0) {
                    str += "，环比下降" + df.format((model.getValue3() - model.getValue1()) / (double) model.getValue3());
                }
                else{
                    str += "，环比下降";
                }
            }
            else{
                str+="，环比持平";

            }
            list.add(str);
        }
        return list;
    }
    @Override
    public List<String> getBmslAvg(List<K3vModel> k3vModels){
        List<String> list=new ArrayList<>();
        DecimalFormat df=new DecimalFormat("0.00%");
        for(K3vModel model:k3vModels){
            String str="";
            if(model.getValue1()>model.getValue2()){
                if(model.getValue2()!=0) {
                    str = model.getName() + "平均审理天数同比上升" + df.format((model.getValue1() - model.getValue2()) / (double) model.getValue2());
                }
                else{
                    str = model.getName() + "平均审理天数同比上升";
                }
            }
            else if(model.getValue1()<model.getValue2()){
                if(model.getValue1()!=0) {
                    str = model.getName() + "平均审理天数同比下降" + df.format((model.getValue2() - model.getValue1()) / (double) model.getValue2());
                }
                else{
                    str = model.getName() + "平均审理天数同比下降";
                }
            }
            else{
                str=model.getName()+"平均审理天数同比持平";

            }
            if(model.getValue1()>model.getValue3()){
                if(model.getValue3()!=0) {
                    str += "，环比上升" + df.format((model.getValue1() - model.getValue3()) / (double) model.getValue3());
                }
                else{
                    str += "，环比上升";
                }
            }
            else if(model.getValue1()<model.getValue3()){
                if(model.getValue1()!=0) {
                    str += "，环比下降" + df.format((model.getValue3() - model.getValue1()) / (double) model.getValue3());
                }
                else{
                    str += "，环比下降";
                }
            }
            else{
                str+="，环比持平";

            }
            list.add(str);
        }
        return list;
    }

    @Override
    public List<String> getDsrDesc(List<K3vModel> k3vModels){
        List<String> list=new ArrayList<>();
        DecimalFormat df=new DecimalFormat("0.00%");
        for(K3vModel model:k3vModels){
            String str="";
            if(model.getValue1()>model.getValue2()){
                if(model.getValue2()!=0) {
                    str = model.getName() + "人数同比上升" + df.format((model.getValue1() - model.getValue2()) / (double) model.getValue2());
                }
                else{
                    str = model.getName() + "人数同比上升";
                }
            }
            else if(model.getValue1()<model.getValue2()){
                if(model.getValue1()!=0) {
                    str = model.getName() + "人数同比下降" + df.format((model.getValue2() - model.getValue1()) / (double) model.getValue2());
                }
                else{
                    str = model.getName() + "人数同比下降";
                }
            }
            else{
                str=model.getName()+"人数同比持平";
            }
            if(model.getValue1()>model.getValue3()){
                if(model.getValue3()!=0) {
                    str += "，环比上升" + df.format((model.getValue1() - model.getValue3()) / (double) model.getValue3());
                }
                else{
                    str += "，环比上升";
                }
            }
            else if(model.getValue1()<model.getValue3()){
                if(model.getValue1()!=0) {
                    str += "，环比下降" + df.format((model.getValue3() - model.getValue1()) / (double) model.getValue3());
                }
                else{
                    str += "，环比下降";
                }
            }
            else{
                str+="，环比持平";
            }
            list.add(str);
        }
        return list;
    }

    @Override
    public List<String> getDsrABDesc(List<K3vModel> k3vModels){
        if(k3vModels.size()==0){
            return new ArrayList<>();
        }
        k3vModels=k3vModels.stream().sorted(Comparator.comparingInt(k->k.getValue1())).collect(Collectors.toList());
        List<String> list=new ArrayList<>();
        if(k3vModels.get(k3vModels.size()-1).getValue1()==0){
            return list;
        }
        String str="当期当事人数最少为";
        for(int i=0;i<k3vModels.size();i++){
            if(k3vModels.get(i).getValue1()==k3vModels.get(0).getValue1()){
                str=str+k3vModels.get(i).getName()+"，";
            }
            else{
                break;
            }
        }
        str=str.substring(0,str.length()-1);
        String str1="当期当事人数最多为";
        for(int i=k3vModels.size()-1;i>=0;i--){
            if(k3vModels.get(i).getValue1()==k3vModels.get(k3vModels.size()-1).getValue1()){
                str1=str1+k3vModels.get(i).getName()+"，";
            }
        }
        str1=str1.substring(0,str1.length()-1);
        list.add(str1);list.add(str);
        return list;
    }



    @Override
    public List<String> getBgABDesc(List<K3vModel> k3vModels){
        if(k3vModels.size()==0){
            return new ArrayList<>();
        }
        k3vModels=k3vModels.stream().sorted(Comparator.comparingInt(k->k.getValue1())).collect(Collectors.toList());
        List<String> list=new ArrayList<>();
        if(k3vModels.get(k3vModels.size()-1).getValue1()==0){
            return list;
        }
        String str="当期被告人数最少为";
        for(int i=0;i<k3vModels.size();i++){
            if(k3vModels.get(i).getValue1()==k3vModels.get(0).getValue1()){
                str=str+k3vModels.get(i).getName()+"，";
            }
            else{
                break;
            }
        }
        str=str.substring(0,str.length()-1);
        String str1="当期被告人数最多为";
        for(int i=k3vModels.size()-1;i>=0;i--){
            if(k3vModels.get(i).getValue1()==k3vModels.get(k3vModels.size()-1).getValue1()){
                str1=str1+k3vModels.get(i).getName()+"，";
            }
        }
        str1=str1.substring(0,str1.length()-1);
        list.add(str1);list.add(str);
        return list;
    }

    @Override
    public List<String> getPjjgDesc(List<K3vModel> k3vModels){
        if(k3vModels.size()==0){
            return new ArrayList<>();
        }
        k3vModels=k3vModels.stream().sorted(Comparator.comparingInt(k->k.getValue1())).collect(Collectors.toList());
        List<String> list=new ArrayList<>();
        if(k3vModels.get(k3vModels.size()-1).getValue1()==0){
            return list;
        }
        String str="当期案件的判决结果最少为";
        for(int i=0;i<k3vModels.size();i++){
            if(k3vModels.get(i).getValue1()==k3vModels.get(0).getValue1()){
                str=str+k3vModels.get(i).getName()+"，";
            }
            else{
                break;
            }
        }
        str=str.substring(0,str.length()-1);
        String str1="当期案件的判决结果最多为";
        for(int i=k3vModels.size()-1;i>=0;i--){
            if(k3vModels.get(i).getValue1()==k3vModels.get(k3vModels.size()-1).getValue1()){
                str1=str1+k3vModels.get(i).getName()+"，";
            }
        }
        str1=str1.substring(0,str1.length()-1);
        list.add(str1);list.add(str);
        return list;
    }

    @Override
    public List<String> getBgNumDesc(List<K3vModel> k3vModels){
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
}
