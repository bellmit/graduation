package com.software.service.impl;

import com.software.model.K2vModel;
import com.software.model.K2vModelBH;
import com.software.model.K3vModel;
import com.software.model.KvModel;
import com.software.service.ShfzDescService;
import com.software.utils.DescriptionUtils;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShfzDescServiceImpl implements ShfzDescService {

    @Override
    public List<String> getBgrGlzm(List<KvModel> kvModels){
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
    @Override
    public List<String> getBrgage(List<KvModel> kvModels){
        if (kvModels==null) return null;
        List<String> list=DescriptionUtils.getMaxBlDesc(kvModels,"年龄段");
        return list;
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
}
