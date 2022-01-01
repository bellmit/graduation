package com.software.service.impl;

import com.software.mapper.LadsjMapper;
import com.software.model.AjjbModel;
import com.software.model.K2vModel;
import com.software.model.K2vModelBH;
import com.software.model.KvModel;
import com.software.service.LadsjService;
import com.software.utils.WordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;

@Service
public class LadsjServicelmpl implements LadsjService {
    @Autowired
    LadsjMapper ladsjMapper;

    @Override
    public List<KvModel> getDjlasj(String fydm){
        List<KvModel> list=new ArrayList<>();
        LocalDateTime dateTime=LocalDateTime.now();
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String date=dateTime.format(formatter);
        String dateDay=date.substring(0,10);
        String dateMonth=date.substring(0,7)+"-01";
        int month=Integer.parseInt(date.substring(5,7));
        if(month%3==0){
            month=(month-1)/3*3+1;
        }
        else{
            month=month/3*3+1;
        }
        String mon="";
        if(month>=10){
            mon=mon+"10";
        }
        else{
            mon=mon+"0"+month;
        }
        String dateQuarter=date.substring(0,5)+mon+"-01";
        String dateYear=date.substring(0,4)+"-01-01";
        KvModel kvModel1=new KvModel("当日登记立案",ladsjMapper.getDjlasj(dateDay,fydm));
        KvModel kvModel2=new KvModel("当月登记立案",ladsjMapper.getDjlasj(dateMonth,fydm));
        KvModel kvModel3=new KvModel("当季度登记立案",ladsjMapper.getDjlasj(dateQuarter,fydm));
        KvModel kvModel4=new KvModel("当年登记立案",ladsjMapper.getDjlasj(dateYear,fydm));
        list.add(kvModel1);list.add(kvModel2);list.add(kvModel3);list.add(kvModel4);
        return list;
    }

    @Override
    public List<KvModel> getDjlasjLx(String fydm,String beginTime,String endTime){
        List<KvModel> list=new ArrayList<>();
        List<KvModel> res=new ArrayList<>();
        res=ladsjMapper.getDjlasjLx(beginTime,endTime,fydm);
        int count=0;
        for(KvModel kvModel:res){
            switch (kvModel.getName()){
                case "1":
                    KvModel kvModel1=new KvModel("刑事",kvModel.getValue());
                    list.add(kvModel1);
                    break;
                case "2":
                    KvModel kvModel2=new KvModel("民事",kvModel.getValue());
                    list.add(kvModel2);
                    break;
                case "6":
                    KvModel kvModel3=new KvModel("行政",kvModel.getValue());
                    list.add(kvModel3);
                    break;
                case "7":
                    KvModel kvModel4=new KvModel("赔偿",kvModel.getValue());
                    list.add(kvModel4);
                    break;
                case "8":
                    KvModel kvModel5=new KvModel("执行",kvModel.getValue());
                    list.add(kvModel5);
                    break;
                default:
                    count=count+kvModel.getValue();
                    break;
            }

        }
        if(count!=0) {
            KvModel kvModel6 = new KvModel("其他", count);
            list.add(kvModel6);
        }
        return list;
    }

//    @Override
//    public List<KvModel> getDjlasjLx(String fydm,String index){
//        List<KvModel> list=new ArrayList<>();
//        LocalDateTime dateTime=LocalDateTime.now();
//        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String date=dateTime.format(formatter);
//        String dateDay=date.substring(0,10);
//        String dateMonth=date.substring(0,7)+"-01";
//        int month=Integer.parseInt(date.substring(5,7));
//        if(month%3==0){
//            month=(month-1)/3*3+1;
//        }
//        else{
//            month=month/3*3+1;
//        }
//        String mon="";
//        if(month>=10){
//            mon=mon+"10";
//        }
//        else{
//            mon=mon+"0"+month;
//        }
//        String dateQuarter=date.substring(0,5)+mon+"-01";
//        String dateYear=date.substring(0,4)+"-01-01";
//        List<KvModel> res=new ArrayList<>();
//        if(index.equals("当日登记立案")){
//            res=ladsjMapper.getDjlasjLx(dateDay,fydm);
//        }
//        else if(index.equals("当月登记立案")){
//            res=ladsjMapper.getDjlasjLx(dateMonth,fydm);
//        }
//        else if(index.equals("当季度登记立案")){
//            res=ladsjMapper.getDjlasjLx(dateQuarter,fydm);
//        }
//        else if(index.equals("当年登记立案")){
//            res=ladsjMapper.getDjlasjLx(dateYear,fydm);
//        }
//        int count=0;
//        for(KvModel kvModel:res){
//            switch (kvModel.getName()){
//                case "1":
//                    KvModel kvModel1=new KvModel("刑事",kvModel.getValue());
//                    list.add(kvModel1);
//                    break;
//                case "2":
//                    KvModel kvModel2=new KvModel("民事",kvModel.getValue());
//                    list.add(kvModel2);
//                    break;
//                case "6":
//                    KvModel kvModel3=new KvModel("行政",kvModel.getValue());
//                    list.add(kvModel3);
//                    break;
//                case "7":
//                    KvModel kvModel4=new KvModel("赔偿",kvModel.getValue());
//                    list.add(kvModel4);
//                    break;
//                case "8":
//                    KvModel kvModel5=new KvModel("执行",kvModel.getValue());
//                    list.add(kvModel5);
//                    break;
//                default:
//                    count=count+kvModel.getValue();
//                    break;
//            }
//
//        }
//        if(count!=0) {
//            KvModel kvModel6 = new KvModel("其他", count);
//            list.add(kvModel6);
//        }
//        return list;
//    }

    @Override
    public List<KvModel> getDjladsrsj(String fydm){
        List<KvModel> list=new ArrayList<>();
        LocalDateTime dateTime=LocalDateTime.now();
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String date=dateTime.format(formatter);
        String dateDay=date.substring(0,10);
        String dateMonth=date.substring(0,7)+"-01";
        int month=Integer.parseInt(date.substring(5,7));
        if(month%3==0){
            month=(month-1)/3*3+1;
        }
        else{
            month=month/3*3+1;
        }
        String mon="";
        if(month>=10){
            mon=mon+"10";
        }
        else{
            mon=mon+"0"+month;
        }
        String dateQuarter=date.substring(0,5)+mon+"-01";
        String dateYear=date.substring(0,4)+"-01-01";
        KvModel kvModel1=new KvModel("当日登记立案人数",ladsjMapper.getDjladsrsj(dateDay,fydm));
        KvModel kvModel2=new KvModel("当月登记立案人数",ladsjMapper.getDjladsrsj(dateMonth,fydm));
        KvModel kvModel3=new KvModel("当季度登记立案人数",ladsjMapper.getDjladsrsj(dateQuarter,fydm));
        KvModel kvModel4=new KvModel("当年登记立案人数",ladsjMapper.getDjladsrsj(dateYear,fydm));
        list.add(kvModel1);list.add(kvModel2);list.add(kvModel3);list.add(kvModel4);
        return list;
    }

    @Override
    public List<KvModel> getDjladsrsjLx(String fydm,String index){
        List<KvModel> list=new ArrayList<>();
        LocalDateTime dateTime=LocalDateTime.now();
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String date=dateTime.format(formatter);
        String dateDay=date.substring(0,10);
        String dateMonth=date.substring(0,7)+"-01";
        int month=Integer.parseInt(date.substring(5,7));
        if(month%3==0){
            month=(month-1)/3*3+1;
        }
        else{
            month=month/3*3+1;
        }
        String mon="";
        if(month>=10){
            mon=mon+"10";
        }
        else{
            mon=mon+"0"+month;
        }
        String dateQuarter=date.substring(0,5)+mon+"-01";
        String dateYear=date.substring(0,4)+"-01-01";
        List<KvModel> res=new ArrayList<>();
        if(index.equals("当日登记立案人数")){
            res=ladsjMapper.getDjladsrsjLx(dateDay,fydm);
        }
        else if(index.equals("当月登记立案人数")){
            res=ladsjMapper.getDjladsrsjLx(dateMonth,fydm);
        }
        else if(index.equals("当季度登记立案人数")){
            res=ladsjMapper.getDjladsrsjLx(dateQuarter,fydm);
        }
        else if(index.equals("当年登记立案人数")){
            res=ladsjMapper.getDjladsrsjLx(dateYear,fydm);
        }
        int count=0;
        for(KvModel kvModel:res){
            switch (kvModel.getName()){
                case "1":
                    KvModel kvModel1=new KvModel("刑事",kvModel.getValue());
                    list.add(kvModel1);
                    break;
                case "2":
                    KvModel kvModel2=new KvModel("民事",kvModel.getValue());
                    list.add(kvModel2);
                    break;
                case "6":
                    KvModel kvModel3=new KvModel("行政",kvModel.getValue());
                    list.add(kvModel3);
                    break;
                case "7":
                    KvModel kvModel4=new KvModel("赔偿",kvModel.getValue());
                    list.add(kvModel4);
                    break;
                case "8":
                    KvModel kvModel5=new KvModel("执行",kvModel.getValue());
                    list.add(kvModel5);
                    break;
                default:
                    count=count+kvModel.getValue();
                    break;
            }

        }
        if(count!=0) {
            KvModel kvModel6 = new KvModel("其他", count);
            list.add(kvModel6);
        }
        return list;
    }

    @Override
    public List<KvModel> getDrlasj(String fydm){
        List<KvModel> list=new ArrayList<>();
        LocalDateTime dateTime=LocalDateTime.now();
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String date=dateTime.format(formatter);
        String dateDay=date.substring(0,10);
        KvModel kvModel1=new KvModel("民事案件数",ladsjMapper.getDrlasjMS(dateDay,fydm));
        KvModel kvModel2=new KvModel("行政案件数",ladsjMapper.getDrlasjXZ(dateDay,fydm));
        KvModel kvModel3=new KvModel("执行案件数",ladsjMapper.getDrlasjZX(dateDay,fydm));
        KvModel kvModel4=new KvModel("刑事案件数",ladsjMapper.getDrlasjXS(dateDay,fydm));
        KvModel kvModel5=new KvModel("赔偿案件数",ladsjMapper.getDrlasjPC(dateDay,fydm));
        KvModel kvModel6=new KvModel("其他案件数",ladsjMapper.getDrlasjQT(dateDay,fydm));
        list.add(kvModel4);list.add(kvModel1);list.add(kvModel2);list.add(kvModel3);list.add(kvModel5);list.add(kvModel6);
        return list;
    }
    @Override
    public List<K2vModel> getDrlasjSp(String fydm){
        List<K2vModel> list=new ArrayList<>();
        LocalDateTime dateTime=LocalDateTime.now();
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String date=dateTime.format(formatter);
        String dateDay=date.substring(0,10);
        KvModel kvModel=ladsjMapper.getDrlasjMSSP(dateDay,fydm);
        K2vModel kvModel1=new K2vModel("民事",Integer.parseInt(kvModel.getName()),kvModel.getValue());
        kvModel=ladsjMapper.getDrlasjXZSP(dateDay,fydm);
        K2vModel kvModel2=new K2vModel("行政",Integer.parseInt(kvModel.getName()),kvModel.getValue());
        kvModel=ladsjMapper.getDrlasjZXSP(dateDay,fydm);
        K2vModel kvModel3=new K2vModel("执行",Integer.parseInt(kvModel.getName()),kvModel.getValue());
        kvModel=ladsjMapper.getDrlasjXSSP(dateDay,fydm);
        K2vModel kvModel4=new K2vModel("刑事",Integer.parseInt(kvModel.getName()),kvModel.getValue());
        kvModel=ladsjMapper.getDrlasjPCSP(dateDay,fydm);
        K2vModel kvModel5=new K2vModel("赔偿",Integer.parseInt(kvModel.getName()),kvModel.getValue());
        kvModel=ladsjMapper.getDrlasjQTSP(dateDay,fydm);
        K2vModel kvModel6=new K2vModel("其他",Integer.parseInt(kvModel.getName()),kvModel.getValue());
        list.add(kvModel4);list.add(kvModel1);list.add(kvModel2);list.add(kvModel3);list.add(kvModel5);list.add(kvModel6);
        return list;
    }

    @Override
    public List<KvModel> getDylasj(String fydm){
        List<KvModel> list=new ArrayList<>();
        LocalDateTime dateTime=LocalDateTime.now();
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String date=dateTime.format(formatter);
        String dateMonth=date.substring(0,7)+"-01";
        KvModel kvModel1=new KvModel("民事案件数",ladsjMapper.getDrlasjMS(dateMonth,fydm));
        KvModel kvModel2=new KvModel("行政案件数",ladsjMapper.getDrlasjXZ(dateMonth,fydm));
        KvModel kvModel3=new KvModel("执行案件数",ladsjMapper.getDrlasjZX(dateMonth,fydm));
        KvModel kvModel4=new KvModel("刑事案件数",ladsjMapper.getDrlasjXS(dateMonth,fydm));
        KvModel kvModel5=new KvModel("赔偿案件数",ladsjMapper.getDrlasjPC(dateMonth,fydm));
        KvModel kvModel6=new KvModel("其他案件数",ladsjMapper.getDrlasjQT(dateMonth,fydm));
        list.add(kvModel4);list.add(kvModel1);list.add(kvModel2);list.add(kvModel3);list.add(kvModel5);list.add(kvModel6);
        return list;
    }
    @Override
    public List<K2vModel> getDylasjSp(String fydm){
        List<K2vModel> list=new ArrayList<>();
        LocalDateTime dateTime=LocalDateTime.now();
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String date=dateTime.format(formatter);
        String dateMonth=date.substring(0,7)+"-01";
        KvModel kvModel=ladsjMapper.getDrlasjMSSP(dateMonth,fydm);
        K2vModel kvModel1=new K2vModel("民事",Integer.parseInt(kvModel.getName()),kvModel.getValue());
        kvModel=ladsjMapper.getDrlasjXZSP(dateMonth,fydm);
        K2vModel kvModel2=new K2vModel("行政",Integer.parseInt(kvModel.getName()),kvModel.getValue());
        kvModel=ladsjMapper.getDrlasjZXSP(dateMonth,fydm);
        K2vModel kvModel3=new K2vModel("执行",Integer.parseInt(kvModel.getName()),kvModel.getValue());
        kvModel=ladsjMapper.getDrlasjXSSP(dateMonth,fydm);
        K2vModel kvModel4=new K2vModel("刑事",Integer.parseInt(kvModel.getName()),kvModel.getValue());
        kvModel=ladsjMapper.getDrlasjPCSP(dateMonth,fydm);
        K2vModel kvModel5=new K2vModel("赔偿",Integer.parseInt(kvModel.getName()),kvModel.getValue());
        kvModel=ladsjMapper.getDrlasjQTSP(dateMonth,fydm);
        K2vModel kvModel6=new K2vModel("其他",Integer.parseInt(kvModel.getName()),kvModel.getValue());
        list.add(kvModel4);list.add(kvModel1);list.add(kvModel2);list.add(kvModel3);list.add(kvModel5);list.add(kvModel6);
        return list;
    }
    @Override
    public List<KvModel> getDjdlasj(String fydm){
        List<KvModel> list=new ArrayList<>();
        LocalDateTime dateTime=LocalDateTime.now();
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String date=dateTime.format(formatter);
        int month=Integer.parseInt(date.substring(5,7));
        if(month%3==0){
            month=(month-1)/3*3+1;
        }
        else{
            month=month/3*3+1;
        }
        String mon="";
        if(month>=10){
            mon=mon+"10";
        }
        else{
            mon=mon+"0"+month;
        }
        String dateQuarter=date.substring(0,5)+mon+"-01";
        KvModel kvModel1=new KvModel("民事案件数",ladsjMapper.getDrlasjMS(dateQuarter,fydm));
        KvModel kvModel2=new KvModel("行政案件数",ladsjMapper.getDrlasjXZ(dateQuarter,fydm));
        KvModel kvModel3=new KvModel("执行案件数",ladsjMapper.getDrlasjZX(dateQuarter,fydm));
        KvModel kvModel4=new KvModel("刑事案件数",ladsjMapper.getDrlasjXS(dateQuarter,fydm));
        KvModel kvModel5=new KvModel("赔偿案件数",ladsjMapper.getDrlasjPC(dateQuarter,fydm));
        KvModel kvModel6=new KvModel("其他案件数",ladsjMapper.getDrlasjQT(dateQuarter,fydm));
        list.add(kvModel4);list.add(kvModel1);list.add(kvModel2);list.add(kvModel3);list.add(kvModel5);list.add(kvModel6);
        return list;
    }
    @Override
    public List<K2vModel> getDjdlasjSp(String fydm){
        List<K2vModel> list=new ArrayList<>();
        LocalDateTime dateTime=LocalDateTime.now();
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String date=dateTime.format(formatter);
        int month=Integer.parseInt(date.substring(5,7));
        if(month%3==0){
            month=(month-1)/3*3+1;
        }
        else{
            month=month/3*3+1;
        }
        String mon="";
        if(month>=10){
            mon=mon+"10";
        }
        else{
            mon=mon+"0"+month;
        }
        String dateQuarter=date.substring(0,5)+mon+"-01";
        KvModel kvModel=ladsjMapper.getDrlasjMSSP(dateQuarter,fydm);
        K2vModel kvModel1=new K2vModel("民事",Integer.parseInt(kvModel.getName()),kvModel.getValue());
        kvModel=ladsjMapper.getDrlasjXZSP(dateQuarter,fydm);
        K2vModel kvModel2=new K2vModel("行政",Integer.parseInt(kvModel.getName()),kvModel.getValue());
        kvModel=ladsjMapper.getDrlasjZXSP(dateQuarter,fydm);
        K2vModel kvModel3=new K2vModel("执行",Integer.parseInt(kvModel.getName()),kvModel.getValue());
        kvModel=ladsjMapper.getDrlasjXSSP(dateQuarter,fydm);
        K2vModel kvModel4=new K2vModel("刑事",Integer.parseInt(kvModel.getName()),kvModel.getValue());
        kvModel=ladsjMapper.getDrlasjPCSP(dateQuarter,fydm);
        K2vModel kvModel5=new K2vModel("赔偿",Integer.parseInt(kvModel.getName()),kvModel.getValue());
        kvModel=ladsjMapper.getDrlasjQTSP(dateQuarter,fydm);
        K2vModel kvModel6=new K2vModel("其他",Integer.parseInt(kvModel.getName()),kvModel.getValue());
        list.add(kvModel4);list.add(kvModel1);list.add(kvModel2);list.add(kvModel3);list.add(kvModel5);list.add(kvModel6);
        return list;
    }
    @Override
    public List<KvModel> getDnlasj(String fydm){
        List<KvModel> list=new ArrayList<>();
        LocalDateTime dateTime=LocalDateTime.now();
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String date=dateTime.format(formatter);
        String dateYear=date.substring(0,4)+"-01-01";
        KvModel kvModel1=new KvModel("民事案件数",ladsjMapper.getDrlasjMS(dateYear,fydm));
        KvModel kvModel2=new KvModel("行政案件数",ladsjMapper.getDrlasjXZ(dateYear,fydm));
        KvModel kvModel3=new KvModel("执行案件数",ladsjMapper.getDrlasjZX(dateYear,fydm));
        KvModel kvModel4=new KvModel("刑事案件数",ladsjMapper.getDrlasjXS(dateYear,fydm));
        KvModel kvModel5=new KvModel("赔偿案件数",ladsjMapper.getDrlasjPC(dateYear,fydm));
        KvModel kvModel6=new KvModel("其他案件数",ladsjMapper.getDrlasjQT(dateYear,fydm));
        list.add(kvModel4);list.add(kvModel1);list.add(kvModel2);list.add(kvModel3);list.add(kvModel5);list.add(kvModel6);
        return list;
    }
    @Override
    public List<K2vModel> getDnlasjSp(String fydm){
        List<K2vModel> list=new ArrayList<>();
        LocalDateTime dateTime=LocalDateTime.now();
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String date=dateTime.format(formatter);
        String dateYear=date.substring(0,4)+"-01-01";
        KvModel kvModel=ladsjMapper.getDrlasjMSSP(dateYear,fydm);
        K2vModel kvModel1=new K2vModel("民事",Integer.parseInt(kvModel.getName()),kvModel.getValue());
        kvModel=ladsjMapper.getDrlasjXZSP(dateYear,fydm);
        K2vModel kvModel2=new K2vModel("行政",Integer.parseInt(kvModel.getName()),kvModel.getValue());
        kvModel=ladsjMapper.getDrlasjZXSP(dateYear,fydm);
        K2vModel kvModel3=new K2vModel("执行",Integer.parseInt(kvModel.getName()),kvModel.getValue());
        kvModel=ladsjMapper.getDrlasjXSSP(dateYear,fydm);
        K2vModel kvModel4=new K2vModel("刑事",Integer.parseInt(kvModel.getName()),kvModel.getValue());
        kvModel=ladsjMapper.getDrlasjPCSP(dateYear,fydm);
        K2vModel kvModel5=new K2vModel("赔偿",Integer.parseInt(kvModel.getName()),kvModel.getValue());
        kvModel=ladsjMapper.getDrlasjQTSP(dateYear,fydm);
        K2vModel kvModel6=new K2vModel("其他",Integer.parseInt(kvModel.getName()),kvModel.getValue());
        list.add(kvModel4);list.add(kvModel1);list.add(kvModel2);list.add(kvModel3);list.add(kvModel5);list.add(kvModel6);
        return list;
    }
    @Override
    public List<KvModel> getSqtjsj(String fydm){
        List<KvModel> list=new ArrayList<>();
        LocalDateTime dateTime=LocalDateTime.now();
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String date=dateTime.format(formatter);
        String dateDay=date.substring(0,10);
        String dateMonth=date.substring(0,7)+"-01";
        int month=Integer.parseInt(date.substring(5,7));
        if(month%3==0){
            month=(month-1)/3*3+1;
        }
        else{
            month=month/3*3+1;
        }
        String mon="";
        if(month>=10){
            mon=mon+"10";
        }
        else{
            mon=mon+"0"+month;
        }
        String dateQuarter=date.substring(0,5)+mon+"-01";
        String dateYear=date.substring(0,4)+"-01-01";
        KvModel kvModel1=new KvModel("当日诉前调解转立案",ladsjMapper.getSqtjsj(dateDay,fydm));
        KvModel kvModel2=new KvModel("当月诉前调解转立案",ladsjMapper.getSqtjsj(dateMonth,fydm));
        KvModel kvModel3=new KvModel("当季度诉前调解转立案",ladsjMapper.getSqtjsj(dateQuarter,fydm));
        KvModel kvModel4=new KvModel("当年诉前调解转立案",ladsjMapper.getSqtjsj(dateYear,fydm));
        list.add(kvModel1);list.add(kvModel2);list.add(kvModel3);list.add(kvModel4);
        return list;
    }

    @Override
    public List<AjjbModel> getDjlasssj(String fydm,String beginTime,String endTime){
        List<AjjbModel> list= ladsjMapper.getDjlasssj(beginTime,endTime,fydm);
        for(AjjbModel model:list){
            model.setLarq(model.getLarq().substring(0,19));
        }
        return list;
    }

    @Override
    public List<AjjbModel> getLasssj(String fydm,String beginTime,String endTime){
        Date today=new Date();
        Date nextDay=new Date(today.getTime()+86400000);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        List<AjjbModel> list= ladsjMapper.getLasssj(format.format(today),format.format(nextDay),fydm);
        for(AjjbModel model:list){
            model.setLarq(model.getLarq().substring(0,19));
        }
        return list;
    }
    @Override
    public List<AjjbModel> getSqtjsssj(String fydm,String beginTime,String endTime){
//        Date today=new Date();
//        Date lastDay=new Date(today.getTime()-86400000);
//        Date nextDay=new Date(today.getTime()+86400000);
//        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        List<AjjbModel> list= ladsjMapper.getSqtjsssj(beginTime,endTime,fydm);
        for(AjjbModel model:list){
            model.setLarq(model.getLarq().substring(0,19));
        }
        return list;
    }

    @Override
    public List<KvModel> getDjlalzsj(String fydm,String beginTime,String endTime){
        List<KvModel> list= new ArrayList<>();
        KvModel model1=new KvModel("登记立案数",ladsjMapper.getDjlasj1(beginTime,endTime,fydm));
        KvModel model2=new KvModel("登记立案转立案数",ladsjMapper.getDjlalzsj(beginTime,endTime,fydm));
        list.add(model1);list.add(model2);
        return list;
    }

    @Override
    public List<KvModel> getLayssj(String fydm,String beginTime,String endTime){
        List<KvModel> list= new ArrayList<>();
        KvModel model1=new KvModel("立案数",ladsjMapper.getLasj(beginTime,endTime,fydm));
        KvModel model2=new KvModel("立案已移送数",ladsjMapper.getLayssj(beginTime,endTime,fydm));
        list.add(model1);list.add(model2);
        return list;
    }

    @Override
    public void exportWord(HttpServletResponse response,String beginTime,String endTime,String fydm){
        List<String> list=new ArrayList<>();

        List<KvModel> list1=getDjlalzsj(fydm,beginTime,endTime);
        String str1="1、登记立案流转数据\n    ";
        str1+="统计时间范围："+beginTime.substring(0,4)+"年"+Integer.parseInt(beginTime.substring(5,7))+"月"+Integer.parseInt(beginTime.substring(8,10))+"日至"
                +endTime.substring(0,4)+"年"+Integer.parseInt(endTime.substring(5,7))+"月"+Integer.parseInt(endTime.substring(8,10))+"日。";
        for(KvModel model:list1){
            str1+=model.getName()+model.getValue()+"件，";
        }
        str1=str1.substring(0,str1.length()-1)+"。\n";
        list.add(str1);


        List<KvModel> list2=getDjlasj(fydm);
        String str2="2、登记立案数据统计\n    ";
        for(KvModel model:list2){
            str2+=model.getName()+model.getValue()+"件，";
        }
        str2=str2.substring(0,str2.length()-1)+"。\n";
        list.add(str2);

        List<KvModel> list3=getDjlasjLx(fydm,beginTime,endTime);
        String str3="3、登记立案案件类型统计\n    ";
        str3+="统计时间范围："+beginTime.substring(0,4)+"年"+Integer.parseInt(beginTime.substring(5,7))+"月"+Integer.parseInt(beginTime.substring(8,10))+"日至"
                +endTime.substring(0,4)+"年"+Integer.parseInt(endTime.substring(5,7))+"月"+Integer.parseInt(endTime.substring(8,10))+"日。";
        for(KvModel model:list3){
            str3+=model.getName()+model.getValue()+"件，";
        }
        str3=str3.substring(0,str3.length()-1)+"。\n";
        list.add(str3);

        List<KvModel> list4=getLayssj(fydm,beginTime,endTime);
        String str4="4、立案移送统计\n    ";
        str4+="统计时间范围："+beginTime.substring(0,4)+"年"+Integer.parseInt(beginTime.substring(5,7))+"月"+Integer.parseInt(beginTime.substring(8,10))+"日至"
                +endTime.substring(0,4)+"年"+Integer.parseInt(endTime.substring(5,7))+"月"+Integer.parseInt(endTime.substring(8,10))+"日。";
        for(KvModel model:list4){
            str4+=model.getName()+model.getValue()+"件，";
        }
        str4=str4.substring(0,str4.length()-1)+"。\n";
        list.add(str4);

        List<K2vModel> list5=getDrlasjSp(fydm);
        String str5="5、当日立案数据统计\n";
        for(K2vModel model:list5){
            str5+="    当日"+model.getName()+"案件立案数量"+model.getValue1()+"件，移送审判庭数量"+model.getValue2()+"件。\n";
        }
        list.add(str5);

        List<K2vModel> list6=getDylasjSp(fydm);
        String str6="6、当月立案数据统计\n";
        for(K2vModel model:list6){
            str6+="    当月"+model.getName()+"案件立案数量"+model.getValue1()+"件，移送审判庭数量"+model.getValue2()+"件。\n";
        }
        list.add(str6);

        List<K2vModel> list7=getDrlasjSp(fydm);
        String str7="7、当季度立案数据统计\n";
        for(K2vModel model:list7){
            str7+="    当季度"+model.getName()+"案件立案数量"+model.getValue1()+"件，移送审判庭数量"+model.getValue2()+"件。\n";
        }
        list.add(str7);

        List<K2vModel> list8=getDnlasjSp(fydm);
        String str8="8、当年立案数据统计\n";
        for(K2vModel model:list8){
            str8+="    当年"+model.getName()+"案件立案数量"+model.getValue1()+"件，移送审判庭数量"+model.getValue2()+"件。\n";
        }
        list.add(str8);

        List<KvModel> list9=getSqtjsj(fydm);
        String str9="9、诉前调解数据统计\n    ";
        for(KvModel model:list9){
            str9+=model.getName()+model.getValue()+"件，";
        }
        str9=str9.substring(0,str9.length()-1)+"。\n";
        list.add(str9);

        WordUtil.exportWord(response,"aj.docx","立案大数据分析",list);

    }
}
