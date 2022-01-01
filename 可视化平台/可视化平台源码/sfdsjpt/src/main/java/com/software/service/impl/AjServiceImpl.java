package com.software.service.impl;

import com.software.mapper.SyjfMapper;
import com.software.model.*;
import com.software.service.AjService;
import com.software.datasource.DataSourceEnum;
import com.software.datasource.DataSourceRouter;
import com.software.mapper.AjMapper;
import com.software.utils.AgeUtils;
import com.software.utils.DateUtil;
import com.software.utils.DateUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.util.LittleEndianOutputStream;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
@Service
public class AjServiceImpl implements AjService {
    @Autowired
    SyjfMapper syjfMapper;


    @Override
    public List<KvModel> getAjCount(AjMapper ajMapper,String fydm) {
        int year= DateUtils.getCurYear();
        //不统计当年的
        year=year-1;
        List<KvModel> list=new ArrayList<>();
        for(int i=year-4;i<=year;i++){
            KvModel kvModel=new KvModel();
            kvModel.setName(i +"年");
            Date last=DateUtils.getYearLast(i);
            Date first=DateUtils.getYearFirst(i);
            String lastDay=DateUtils.formatDate(last);
            String firstDay=DateUtils.formatDate(first);


            kvModel.setValue(ajMapper.getAjCount(firstDay,lastDay,fydm));
            list.add(kvModel);
        }
        return list;
    }
    @Override
    public List<KvModel> getAreaDistribution(String beginTime,String endTime,String fydm,AjMapper ajMapper) {
        List<KvModel> kvModels = new ArrayList<>();
        ExecutorService executors = Executors.newFixedThreadPool(10);
        List<String> fydms = DataSourceEnum.getSubFydm(fydm);
        for (String dm : fydms) {
            executors.execute(() -> {
                KvModel k = new KvModel();
                DataSourceRouter.routerTo(dm);
                int ajs = ajMapper.getAjCount(beginTime, endTime,dm);
                k.setName(DataSourceEnum.getFydqByFydm(dm));
                k.setValue(ajs);
                kvModels.add(k);
            });
        }
        try {
            executors.shutdown();
            executors.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException ignored) {

        }
        return kvModels;
    }
    @Override
    public List<KvModel> getBgrxb(String beginTime, String endTime, AjMapper ajMapper,String fydm) {
        List<KvModel> kvModels= ajMapper.getBgrxb(beginTime,endTime,fydm);
        kvModels=kvModels.stream().filter(k-> "1".equals(k.getName())|| "2".equals(k.getName()))
                .collect(Collectors.toList());
        for (KvModel t:kvModels){
            if ("1".equals(t.getName())){
                t.setName("男性");
            }else if("2".equals(t.getName())){
                t.setName("女性");
            }
        }
        return kvModels;
    }
    @Override
    public List<KvModel> getBgrsf(String beginTime, String endTime, AjMapper ajMapper,String fydm) {
        return ajMapper.getBgrsf(beginTime,endTime,fydm);
    }
    @Override
    public List<KvModel> getBgrWhcd(String beginTime, String endTime, AjMapper ajMapper,String fydm) {
        return ajMapper.getBgrWhcd(beginTime,endTime,fydm);
    }
    @Override
    public List<KvModel> getBrgage(String beginTime, String endTime,AjMapper ajMapper,String fydm){
        List<BgrxxModel> bgrxxModels=ajMapper.getBgrAge(beginTime,endTime,fydm);
        List<KvModel> kvModels=new ArrayList<>();
        KvModel kvModel1=new KvModel("已满14岁不满18岁",0);
        KvModel kvModel2=new KvModel("已满18岁不满25岁",0);
        KvModel kvModel3=new KvModel("已满25岁不满39岁",0);
        KvModel kvModel4=new KvModel("已满39岁不满49岁",0);
        KvModel kvModel5=new KvModel("已满49岁不满59岁",0);
        KvModel kvModel6=new KvModel("59岁以上",0);
        kvModels.add(kvModel1);
        kvModels.add(kvModel2);
        kvModels.add(kvModel3);
        kvModels.add(kvModel4);
        kvModels.add(kvModel5);
        kvModels.add(kvModel6);
        int[][] ages=new int[][]{{14,18}, {18,25}, {25,39},{39,49}, {49,59}, {59,200}};
        for (BgrxxModel bgrxxModel:bgrxxModels){
            int age=0;
            if (bgrxxModel.getLarq()==null){
                continue;
            }
            if (bgrxxModel.getCsnyr()!=null) {
                age = DateUtils.getDiffYear(bgrxxModel.getCsnyr(),bgrxxModel.getLarq());
            }else{
                //如果是合法的身份证号
                if (!"".equals(AgeUtils.ze(bgrxxModel.getSfzhm()))){
                    //获取身份证号的年份
                    String[] res= AgeUtils.ze(bgrxxModel.getSfzhm()).split(":");
                    if (res.length>=2){
                        int year=Integer.parseInt(res[0]);
                        int larqYear=DateUtils.getYear(bgrxxModel.getLarq());
                        age=larqYear-year;
                    }
                }
            }
            if (age<14) {
                continue;
            }
            for(int i=0;i<ages.length;i++){
                if (age>=ages[i][0]&&age<ages[i][1]){
                    kvModels.get(i).setValue(kvModels.get(i).getValue()+1);
                    break;
                }

            }
        }

        boolean isNull=true;
        for (KvModel k:kvModels){
            if (k.getValue()!=0){
                isNull=false;
                break;
            }
        }
        return isNull?null:kvModels;
    }

    @Override
    public List<K3vModel> getBgXb(String beginTime, String endTime, AjMapper ajMapper, String fydm) {
        List<K3vModel> list=new ArrayList<>();
        String lastBegin=Integer.parseInt(beginTime.substring(0,4))-1+beginTime.substring(4);
        String lastEnd=Integer.parseInt(endTime.substring(0,4))-1+endTime.substring(4);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar=Calendar.getInstance();
        try {
            calendar.setTime(format.parse(endTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.MONTH,-2);
        String lastMonth=format.format(calendar.getTime());
        List<K2vModel> BgList=getBgSsdw(fydm,beginTime,endTime,ajMapper);
        List<K2vModel> BgList1=getBgSsdw(fydm,lastBegin,lastEnd,ajMapper);
        List<K2vModel> BgList2=getBgSsdw(fydm,lastMonth,beginTime,ajMapper);

        List<KvModel> kvModels=new ArrayList<>();
        List<KvModel> kvModels1=new ArrayList<>();
        List<KvModel> kvModels2=new ArrayList<>();
        if(fydm.equals("120242 22A")){
            if(BgList.size()>0) {
                kvModels = syjfMapper.getXbBH(BgList);
            }
            if(BgList1.size()>0){
                kvModels1 = syjfMapper.getXbBH(BgList1);
            }
            if(BgList2.size()>0){
                kvModels2=syjfMapper.getXbBH(BgList2);
            }
        }
        else {
            if(BgList.size()>0) {
                kvModels = syjfMapper.getXb(BgList);
            }
            if(BgList1.size()>0){
                kvModels1 = syjfMapper.getXb(BgList1);
            }
            if(BgList2.size()>0){
                kvModels2=syjfMapper.getXb(BgList2);
            }
        }
        kvModels=kvModels.stream().filter(k-> "1".equals(k.getName())|| "2".equals(k.getName()))
                .collect(Collectors.toList());
        kvModels1=kvModels1.stream().filter(k-> "1".equals(k.getName())|| "2".equals(k.getName()))
                .collect(Collectors.toList());
        kvModels2=kvModels2.stream().filter(k-> "1".equals(k.getName())|| "2".equals(k.getName()))
                .collect(Collectors.toList());
        for(KvModel model:kvModels){
            K3vModel k3vModel=new K3vModel(model.getName(),model.getValue(),0,0);
            list.add(k3vModel);
        }
        boolean t=true;
        for(KvModel model:kvModels1){
            t=true;
            for(K3vModel k3vModel:list){
                if(k3vModel.getName().equals(model.getName())){
                    k3vModel.setValue2(model.getValue());
                    t=false;
                    break;
                }
            }
            if(t){
                K3vModel k3vModel=new K3vModel(model.getName(),0,model.getValue(),0);
                list.add(k3vModel);
            }
        }
        for(KvModel model:kvModels2){
            t=true;
            for(K3vModel k3vModel:list){
                if(k3vModel.getName().equals(model.getName())){
                    k3vModel.setValue3(model.getValue());
                    t=false;
                    break;
                }
            }
            if(t){
                K3vModel k3vModel=new K3vModel(model.getName(),0,0,model.getValue());
                list.add(k3vModel);
            }
        }

        for (K3vModel k3vModel:list){
            if ("1".equals(k3vModel.getName())){
                k3vModel.setName("男性");
            }else if("2".equals(k3vModel.getName())){
                k3vModel.setName("女性");
            }
        }
        return list;
    }

    @Override
    public List<K3vModel> getBgSf(String beginTime, String endTime, AjMapper ajMapper, String fydm) {
        List<K3vModel> list=new ArrayList<>();
        String lastBegin=Integer.parseInt(beginTime.substring(0,4))-1+beginTime.substring(4);
        String lastEnd=Integer.parseInt(endTime.substring(0,4))-1+endTime.substring(4);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar=Calendar.getInstance();
        try {
            calendar.setTime(format.parse(endTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.MONTH,-2);
        String lastMonth=format.format(calendar.getTime());
        List<K2vModel> BgList=getBgSsdw(fydm,beginTime,endTime,ajMapper);
        List<K2vModel> BgList1=getBgSsdw(fydm,lastBegin,lastEnd,ajMapper);
        List<K2vModel> BgList2=getBgSsdw(fydm,lastMonth,beginTime,ajMapper);
        List<KvModel> kvModels=new ArrayList<>();
        List<KvModel> kvModels1=new ArrayList<>();
        List<KvModel> kvModels2=new ArrayList<>();
        if(fydm.equals("120242 22A")){
            if(BgList.size()>0) {
                kvModels = syjfMapper.getSfBH(BgList);
            }
            if(BgList1.size()>0){
                kvModels1 = syjfMapper.getSfBH(BgList1);
            }
            if(BgList2.size()>0){
                kvModels2=syjfMapper.getSfBH(BgList2);
            }
        }
        else {
            if(BgList.size()>0) {
                kvModels = syjfMapper.getSf(BgList);
            }
            if(BgList1.size()>0){
                kvModels1 = syjfMapper.getSf(BgList1);
            }
            if(BgList2.size()>0){
                kvModels2=syjfMapper.getSf(BgList2);
            }
        }
        for(KvModel model:kvModels){
            K3vModel k3vModel=new K3vModel(model.getName(),model.getValue(),0,0);
            list.add(k3vModel);
        }
        boolean t=true;
        for(KvModel model:kvModels1){
            t=true;
            for(K3vModel k3vModel:list){
                if(k3vModel.getName().equals(model.getName())){
                    k3vModel.setValue2(model.getValue());
                    t=false;
                    break;
                }
            }
            if(t){
                K3vModel k3vModel=new K3vModel(model.getName(),0,model.getValue(),0);
                list.add(k3vModel);
            }
        }
        for(KvModel model:kvModels2){
            t=true;
            for(K3vModel k3vModel:list){
                if(k3vModel.getName().equals(model.getName())){
                    k3vModel.setValue3(model.getValue());
                    t=false;
                    break;
                }
            }
            if(t){
                K3vModel k3vModel=new K3vModel(model.getName(),0,0,model.getValue());
                list.add(k3vModel);
            }
        }
        return list;
    }

    @Override
    public List<K3vModel> getBgWhcd(String beginTime, String endTime, AjMapper ajMapper, String fydm) {
        List<K3vModel> list=new ArrayList<>();
        String lastBegin=Integer.parseInt(beginTime.substring(0,4))-1+beginTime.substring(4);
        String lastEnd=Integer.parseInt(endTime.substring(0,4))-1+endTime.substring(4);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar=Calendar.getInstance();
        try {
            calendar.setTime(format.parse(endTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.MONTH,-2);
        String lastMonth=format.format(calendar.getTime());
        List<K2vModel> BgList=getBgSsdw(fydm,beginTime,endTime,ajMapper);
        List<K2vModel> BgList1=getBgSsdw(fydm,lastBegin,lastEnd,ajMapper);
        List<K2vModel> BgList2=getBgSsdw(fydm,lastMonth,beginTime,ajMapper);
        List<KvModel> kvModels=new ArrayList<>();
        List<KvModel> kvModels1=new ArrayList<>();
        List<KvModel> kvModels2=new ArrayList<>();
        if(fydm.equals("120242 22A")){
            if(BgList.size()>0) {
                kvModels = syjfMapper.getWhcdBH(BgList);
            }
            if(BgList1.size()>0){
                kvModels1 = syjfMapper.getWhcdBH(BgList1);
            }
            if(BgList2.size()>0){
                kvModels2=syjfMapper.getWhcdBH(BgList2);
            }
        }
        else {
            if(BgList.size()>0) {
                kvModels = syjfMapper.getWhcd(BgList);
            }
            if(BgList1.size()>0){
                kvModels1 = syjfMapper.getWhcd(BgList1);
            }
            if(BgList2.size()>0){
                kvModels2=syjfMapper.getWhcd(BgList2);
            }
        }
        kvModels=kvModels.stream().filter(k->k.getValue()>0).collect(Collectors.toList());
        kvModels1=kvModels1.stream().filter(k->k.getValue()>0).collect(Collectors.toList());
        kvModels2=kvModels2.stream().filter(k->k.getValue()>0).collect(Collectors.toList());
        for(KvModel model:kvModels){
            K3vModel k3vModel=new K3vModel(model.getName(),model.getValue(),0,0);
            list.add(k3vModel);
        }
        boolean t=true;
        for(KvModel model:kvModels1){
            t=true;
            for(K3vModel k3vModel:list){
                if(k3vModel.getName().equals(model.getName())){
                    k3vModel.setValue2(model.getValue());
                    t=false;
                    break;
                }
            }
            if(t){
                K3vModel k3vModel=new K3vModel(model.getName(),0,model.getValue(),0);
                list.add(k3vModel);
            }
        }
        for(KvModel model:kvModels2){
            t=true;
            for(K3vModel k3vModel:list){
                if(k3vModel.getName().equals(model.getName())){
                    k3vModel.setValue3(model.getValue());
                    t=false;
                    break;
                }
            }
            if(t){
                K3vModel k3vModel=new K3vModel(model.getName(),0,0,model.getValue());
                list.add(k3vModel);
            }
        }
        return list;
    }

    @Override
    public List<K3vModel> getBgAge(String beginTime, String endTime, AjMapper ajMapper, String fydm) {
        String lastBegin=Integer.parseInt(beginTime.substring(0,4))-1+beginTime.substring(4);
        String lastEnd=Integer.parseInt(endTime.substring(0,4))-1+endTime.substring(4);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar=Calendar.getInstance();
        try {
            calendar.setTime(format.parse(endTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.MONTH,-2);
        String lastMonth=format.format(calendar.getTime());
        List<K2vModel> BgList=getBgSsdw(fydm,beginTime,endTime,ajMapper);
        List<K2vModel> BgList1=getBgSsdw(fydm,lastBegin,lastEnd,ajMapper);
        List<K2vModel> BgList2=getBgSsdw(fydm,lastMonth,beginTime,ajMapper);
        List<BgrxxModel> bgrxxModels=new ArrayList<>();
        List<BgrxxModel> bgrxxModels1=new ArrayList<>();
        List<BgrxxModel> bgrxxModels2=new ArrayList<>();
        if(fydm.equals("120242 22A")){
            if(BgList.size()>0) {
                bgrxxModels = syjfMapper.getAgeBH(BgList);
            }
            if(BgList1.size()>0){
                bgrxxModels1=syjfMapper.getAgeBH(BgList1);
            }
            if(BgList2.size()>0){
                bgrxxModels2=syjfMapper.getAgeBH(BgList2);
            }
        }
        else {
            if(BgList.size()>0) {
                bgrxxModels = syjfMapper.getAge(BgList);
            }
            if(BgList1.size()>0){
                bgrxxModels1=syjfMapper.getAge(BgList1);
            }
            if(BgList2.size()>0){
                bgrxxModels2=syjfMapper.getAge(BgList2);
            }
        }
        List<K3vModel> kvModels=new ArrayList<>();
        K3vModel kvModel0=new K3vModel("未满14周岁",0,0,0);
        K3vModel kvModel1=new K3vModel("已满14岁不满18岁",0,0,0);
        K3vModel kvModel2=new K3vModel("已满18岁不满25岁",0,0,0);
        K3vModel kvModel3=new K3vModel("已满25岁不满39岁",0,0,0);
        K3vModel kvModel4=new K3vModel("已满39岁不满49岁",0,0,0);
        K3vModel kvModel5=new K3vModel("已满49岁不满59岁",0,0,0);
        K3vModel kvModel6=new K3vModel("59岁以上",0,0,0);
        kvModels.add(kvModel0);
        kvModels.add(kvModel1);
        kvModels.add(kvModel2);
        kvModels.add(kvModel3);
        kvModels.add(kvModel4);
        kvModels.add(kvModel5);
        kvModels.add(kvModel6);
        int[][] ages=new int[][]{{0,14}, {14,18}, {18,25}, {25,39},{39,49}, {49,59}, {59,200}};

        for (BgrxxModel bgrxxModel:bgrxxModels){
            int age=0;
            if (bgrxxModel.getLarq()==null){
                continue;
            }
            if (bgrxxModel.getCsnyr()!=null) {
                age = DateUtils.getDiffYear(bgrxxModel.getCsnyr(),bgrxxModel.getLarq());
            }else{
                //如果是合法的身份证号
                if (!"".equals(AgeUtils.ze(bgrxxModel.getSfzhm()))){
                    //获取身份证号的年份
                    String[] res= AgeUtils.ze(bgrxxModel.getSfzhm()).split(":");
                    if (res.length>=2){
                        int year=Integer.parseInt(res[0]);
                        int larqYear=DateUtils.getYear(bgrxxModel.getLarq());
                        age=larqYear-year;
                    }
                }
            }
            for(int i=0;i<ages.length;i++){
                if (age!=0&&age>=ages[i][0]&&age<ages[i][1]){
                    kvModels.get(i).setValue1(kvModels.get(i).getValue1()+1);
                    break;
                }

            }
        }
        for (BgrxxModel bgrxxModel:bgrxxModels1){
            int age=0;
            if (bgrxxModel.getLarq()==null){
                continue;
            }
            if (bgrxxModel.getCsnyr()!=null) {
                age = DateUtils.getDiffYear(bgrxxModel.getCsnyr(),bgrxxModel.getLarq());
            }else{
                //如果是合法的身份证号
                if (!"".equals(AgeUtils.ze(bgrxxModel.getSfzhm()))){
                    //获取身份证号的年份
                    String[] res= AgeUtils.ze(bgrxxModel.getSfzhm()).split(":");
                    if (res.length>=2){
                        int year=Integer.parseInt(res[0]);
                        int larqYear=DateUtils.getYear(bgrxxModel.getLarq());
                        age=larqYear-year;
                    }
                }
            }
            for(int i=0;i<ages.length;i++){
                if (age!=0&&age>=ages[i][0]&&age<ages[i][1]){
                    kvModels.get(i).setValue2(kvModels.get(i).getValue2()+1);
                    break;
                }

            }
        }

        for (BgrxxModel bgrxxModel:bgrxxModels2){
            int age=0;
            if (bgrxxModel.getLarq()==null){
                continue;
            }
            if (bgrxxModel.getCsnyr()!=null) {
                age = DateUtils.getDiffYear(bgrxxModel.getCsnyr(),bgrxxModel.getLarq());
            }else{
                //如果是合法的身份证号
                if (!"".equals(AgeUtils.ze(bgrxxModel.getSfzhm()))){
                    //获取身份证号的年份
                    String[] res= AgeUtils.ze(bgrxxModel.getSfzhm()).split(":");
                    if (res.length>=2){
                        int year=Integer.parseInt(res[0]);
                        int larqYear=DateUtils.getYear(bgrxxModel.getLarq());
                        age=larqYear-year;
                    }
                }
            }
            for(int i=0;i<ages.length;i++){
                if (age!=0&&age>=ages[i][0]&&age<ages[i][1]){
                    kvModels.get(i).setValue3(kvModels.get(i).getValue3()+1);
                    break;
                }
            }
        }
        for(int i=0;i<kvModels.size();i++){
            if(kvModels.get(i).getValue1()==0&&kvModels.get(i).getValue2()==0&&kvModels.get(i).getValue3()==0){
                kvModels.remove(i);
                i--;
            }
        }
        return kvModels;
    }

    @Override
    public List<KvModel> getYqtxlx(String beginTime, String endTime,AjMapper ajMapper,String fydm) {
        List<KvModel> list=ajMapper.getYqtxlx(beginTime,endTime,fydm);
        list.stream().forEach(k->k.setName(k.getName()+"年"));
        return list;
    }

    @Override
    public List<K3vModel> getAjsl(String beginTime,String endTime,AjMapper ajMapper,String fydm){
        List<K3vModel> result=new ArrayList<>();
        String lastBegin=Integer.parseInt(beginTime.substring(0,4))-1+beginTime.substring(4);
        String lastEnd=Integer.parseInt(endTime.substring(0,4))-1+endTime.substring(4);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar=Calendar.getInstance();
        try {
            calendar.setTime(format.parse(endTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.MONTH,-2);
        String lastMonth=format.format(calendar.getTime());
        result.add(new K3vModel("立案数",ajMapper.getAjslLa(beginTime,endTime,fydm),ajMapper.getAjslLa(lastBegin,lastEnd,fydm),ajMapper.getAjslLa(lastMonth,beginTime,fydm)));
        result.add(new K3vModel("结案数",ajMapper.getAjslJa(beginTime,endTime,fydm),ajMapper.getAjslJa(lastBegin,lastEnd,fydm),ajMapper.getAjslJa(lastMonth,beginTime,fydm)));
        List<TimeModel> list=ajMapper.getAjslAvg(beginTime,endTime,fydm);

        int count=0;
        for(TimeModel model:list){
            count=count+(int) Math.abs(DateUtil.getDiffDays(model.getLarq(),model.getJarq()));
        }
        int average=0;
        if(list.size()!=0){
            average=count/list.size();
        }


        List<TimeModel> list1=ajMapper.getAjslAvg(lastBegin,lastEnd,fydm);

        count=0;
        for(TimeModel model:list1){
            count=count+(int) Math.abs(DateUtil.getDiffDays(model.getLarq(),model.getJarq()));
        }
        int average1=0;
        if(list1.size()!=0){
            average1=count/list1.size();
        }
        List<TimeModel> list2=ajMapper.getAjslAvg(lastMonth,beginTime,fydm);
        count=0;
        for(TimeModel model:list2){
            count=count+(int) Math.abs(DateUtil.getDiffDays(model.getLarq(),model.getJarq()));
        }
        int average2=0;
        if(list2.size()!=0){
            average2=count/list2.size();
        }
        result.add(new K3vModel("平均审理天数",average,average1,average2));
        return result;
    }



    @Override
    public List<K3vModel> getBmslLa(String beginTime,String endTime,AjMapper ajMapper,String fydm){
        List<K3vModel> list=new ArrayList<>();
        String lastBegin=Integer.parseInt(beginTime.substring(0,4))-1+beginTime.substring(4);
        String lastEnd=Integer.parseInt(endTime.substring(0,4))-1+endTime.substring(4);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar=Calendar.getInstance();
        try {
            calendar.setTime(format.parse(endTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.MONTH,-2);
        String lastMonth=format.format(calendar.getTime());
        List<KvModel> laList=ajMapper.getBmajslLa(fydm,beginTime,endTime);
        List<KvModel> laList1=ajMapper.getBmajslLa(fydm,lastBegin,lastEnd);
        List<KvModel> laList2=ajMapper.getBmajslLa(fydm,lastMonth,beginTime);
        for(int i=0;i<laList.size();i++){
            K3vModel model=new K3vModel(laList.get(i).getName(),laList.get(i).getValue(),0,0);
            list.add(model);
        }
        boolean t=true;
        for(int i=0;i<laList1.size();i++){
            t=true;
            for(int j=0;j<list.size();j++){
                if(laList1.get(i).getName().equals(list.get(j).getName())){
                    list.get(j).setValue2(laList1.get(i).getValue());
                    t=false;
                    break;
                }
            }
            if(t){
                K3vModel model1=new K3vModel(laList1.get(i).getName(),0,laList1.get(i).getValue(),0);
                list.add(model1);
            }
        }
        for(int i=0;i<laList2.size();i++){
            t=true;
            for(int j=0;j<list.size();j++){
                if(laList2.get(i).getName().equals(list.get(j).getName())){
                    list.get(j).setValue3(laList2.get(i).getValue());
                    t=false;
                    break;
                }
            }
            if(t){
                K3vModel model1=new K3vModel(laList2.get(i).getName(),0,0,laList2.get(i).getValue());
                list.add(model1);
            }
        }
        return list;
    }

    @Override
    public List<K3vModel> getBmslJa(String beginTime,String endTime,AjMapper ajMapper,String fydm){
        List<K3vModel> list=new ArrayList<>();
        String lastBegin=Integer.parseInt(beginTime.substring(0,4))-1+beginTime.substring(4);
        String lastEnd=Integer.parseInt(endTime.substring(0,4))-1+endTime.substring(4);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar=Calendar.getInstance();
        try {
            calendar.setTime(format.parse(endTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.MONTH,-2);
        String lastMonth=format.format(calendar.getTime());
        List<KvModel> laList=ajMapper.getBmajslJa(fydm,beginTime,endTime);
        List<KvModel> laList1=ajMapper.getBmajslJa(fydm,lastBegin,lastEnd);
        List<KvModel> laList2=ajMapper.getBmajslJa(fydm,lastMonth,beginTime);
        for(int i=0;i<laList.size();i++){
            K3vModel model=new K3vModel(laList.get(i).getName(),laList.get(i).getValue(),0,0);
            list.add(model);
        }
        boolean t=true;
        for(int i=0;i<laList1.size();i++){
            t=true;
            for(int j=0;j<list.size();j++){
                if(laList1.get(i).getName().equals(list.get(j).getName())){
                    list.get(j).setValue2(laList1.get(i).getValue());
                    t=false;
                    break;
                }
            }
            if(t){
                K3vModel model1=new K3vModel(laList1.get(i).getName(),0,laList1.get(i).getValue(),0);
                list.add(model1);
            }
        }
        for(int i=0;i<laList2.size();i++){
            t=true;
            for(int j=0;j<list.size();j++){
                if(laList2.get(i).getName().equals(list.get(j).getName())){
                    list.get(j).setValue3(laList2.get(i).getValue());
                    t=false;
                    break;
                }
            }
            if(t){
                K3vModel model1=new K3vModel(laList2.get(i).getName(),0,0,laList2.get(i).getValue());
                list.add(model1);
            }
        }
        return list;
    }

    @Override
    public List<K3vModel> getBmslAvg(String beginTime,String endTime,AjMapper ajMapper,String fydm){
        List<K3vModel> list=new ArrayList<>();
        String lastBegin=Integer.parseInt(beginTime.substring(0,4))-1+beginTime.substring(4);
        String lastEnd=Integer.parseInt(endTime.substring(0,4))-1+endTime.substring(4);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar=Calendar.getInstance();
        try {
            calendar.setTime(format.parse(endTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.MONTH,-2);
        String lastMonth=format.format(calendar.getTime());
        List<TimeModel> laList=ajMapper.getBmajslavg(fydm,beginTime,endTime);
        List<TimeModel> laList1=ajMapper.getBmajslavg(fydm,lastBegin,lastEnd);
        List<TimeModel> laList2=ajMapper.getBmajslavg(fydm,lastMonth,beginTime);
        Map<String,Integer> map=new HashMap<>();
        boolean t=true;
        for(int i=0;i<laList.size();i++){
            t=true;
            for(K3vModel k3vModel:list) {
                if(laList.get(i).getYear().equals(k3vModel.getName())) {
                    k3vModel.setValue1(k3vModel.getValue1()+(int) Math.abs(DateUtil.getDiffDays(laList.get(i).getLarq(),laList.get(i).getJarq())));
                    t=false;
                    break;
                }
            }
            if(t){
                K3vModel model=new K3vModel(laList.get(i).getYear(),(int) Math.abs(DateUtil.getDiffDays(laList.get(i).getLarq(),laList.get(i).getJarq())),0,0);
                list.add(model);
            }
            if(map.containsKey(laList.get(i).getYear())){
                map.put(laList.get(i).getYear(),map.get(laList.get(i).getYear())+1);
            }
            else{
                map.put(laList.get(i).getYear(),1);
            }
        }
        for(K3vModel model:list){
            model.setValue1(model.getValue1()/map.get(model.getName()));
        }
        map.clear();
        for(int i=0;i<laList1.size();i++){
            t=true;
            for(K3vModel k3vModel:list) {
                if(laList1.get(i).getYear().equals(k3vModel.getName())) {
                    k3vModel.setValue2(k3vModel.getValue2()+(int) Math.abs(DateUtil.getDiffDays(laList1.get(i).getLarq(),laList1.get(i).getJarq())));
                    t=false;
                    break;
                }
            }
            if(t){
                K3vModel model=new K3vModel(laList1.get(i).getYear(),0,(int) Math.abs(DateUtil.getDiffDays(laList1.get(i).getLarq(),laList1.get(i).getJarq())),0);
                list.add(model);
            }
            if(map.containsKey(laList1.get(i).getYear())){
                map.put(laList1.get(i).getYear(),map.get(laList1.get(i).getYear())+1);
            }
            else{
                map.put(laList1.get(i).getYear(),1);
            }
        }
        for(K3vModel model:list){
            if(model.getValue2()>0) {
                model.setValue2(model.getValue2() / map.get(model.getName()));
            }
        }
        map.clear();
        for(int i=0;i<laList2.size();i++){
            t=true;
            for(K3vModel k3vModel:list) {
                if(laList2.get(i).getYear().equals(k3vModel.getName())) {
                    k3vModel.setValue3(k3vModel.getValue3()+(int) Math.abs(DateUtil.getDiffDays(laList2.get(i).getLarq(),laList2.get(i).getJarq())));
                    t=false;
                    break;
                }
            }
            if(t){
                K3vModel model=new K3vModel(laList2.get(i).getYear(),0,0,(int) Math.abs(DateUtil.getDiffDays(laList2.get(i).getLarq(),laList2.get(i).getJarq())));
                list.add(model);
            }
            if(map.containsKey(laList2.get(i).getYear())){
                map.put(laList2.get(i).getYear(),map.get(laList2.get(i).getYear())+1);
            }
            else{
                map.put(laList2.get(i).getYear(),1);
            }
        }
        for(K3vModel model:list){
            if(model.getValue3()>0) {
                model.setValue3(model.getValue3() / map.get(model.getName()));
            }
        }
        return list;
    }

    @Override
    public List<K3vModel> getDsrXb(String beginTime,String endTime,AjMapper ajMapper,String fydm){
        List<K3vModel> list=new ArrayList<>();
        String lastBegin=Integer.parseInt(beginTime.substring(0,4))-1+beginTime.substring(4);
        String lastEnd=Integer.parseInt(endTime.substring(0,4))-1+endTime.substring(4);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar=Calendar.getInstance();
        try {
            calendar.setTime(format.parse(endTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.MONTH,-2);
        String lastMonth=format.format(calendar.getTime());
        List<KvModel> kvModels= ajMapper.getDsrxb(beginTime,endTime,fydm);
        // 去年这个月
        List<KvModel> kvModels1=ajMapper.getDsrxb(lastBegin,lastEnd,fydm);
        // 上个月
        List<KvModel> kvModels2=ajMapper.getDsrxb(lastMonth,beginTime,fydm);
        kvModels=kvModels.stream().filter(k-> "1".equals(k.getName())|| "2".equals(k.getName()))
                .collect(Collectors.toList());
        kvModels1=kvModels1.stream().filter(k-> "1".equals(k.getName())|| "2".equals(k.getName()))
                .collect(Collectors.toList());
        kvModels2=kvModels2.stream().filter(k-> "1".equals(k.getName())|| "2".equals(k.getName()))
                .collect(Collectors.toList());
        // 加入当期数据
        for(KvModel model:kvModels){
            K3vModel k3vModel=new K3vModel(model.getName(),model.getValue(),0,0);
            list.add(k3vModel);
        }
        boolean t;
        // 同比
        for(KvModel model:kvModels1){
            t=true;
            for(K3vModel k3vModel:list){
                if(k3vModel.getName().equals(model.getName())){
                    k3vModel.setValue2(model.getValue());
                    t=false;
                    break;
                }
            }
            if(t){
                K3vModel k3vModel=new K3vModel(model.getName(),0,model.getValue(),0);
                list.add(k3vModel);
            }
        }
        // 环比
        for(KvModel model:kvModels2){
            t=true;
            for(K3vModel k3vModel:list){
                if(k3vModel.getName().equals(model.getName())){
                    k3vModel.setValue3(model.getValue());
                    t=false;
                    break;
                }
            }
            if(t){
                K3vModel k3vModel=new K3vModel(model.getName(),0,0,model.getValue());
                list.add(k3vModel);
            }
        }

        for (K3vModel k3vModel:list){
            if ("1".equals(k3vModel.getName())){
                k3vModel.setName("男性");
            }else if("2".equals(k3vModel.getName())){
                k3vModel.setName("女性");
            }
        }
        return list;
    }
    @Override
    public List<K3vModel> getDsrSf(String beginTime,String endTime,AjMapper ajMapper,String fydm){
        List<K3vModel> list=new ArrayList<>();
        String lastBegin=Integer.parseInt(beginTime.substring(0,4))-1+beginTime.substring(4);
        String lastEnd=Integer.parseInt(endTime.substring(0,4))-1+endTime.substring(4);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar=Calendar.getInstance();
        try {
            calendar.setTime(format.parse(endTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.MONTH,-2);
        String lastMonth=format.format(calendar.getTime());
        List<KvModel> kvModels= ajMapper.getDsrSf(beginTime,endTime,fydm);
        List<KvModel> kvModels1=ajMapper.getDsrSf(lastBegin,lastEnd,fydm);
        List<KvModel> kvModels2=ajMapper.getDsrSf(lastMonth,beginTime,fydm);
        for(KvModel model:kvModels){
            K3vModel k3vModel=new K3vModel(model.getName(),model.getValue(),0,0);
            list.add(k3vModel);
        }
        boolean t=true;
        for(KvModel model:kvModels1){
            t=true;
            for(K3vModel k3vModel:list){
                if(k3vModel.getName().equals(model.getName())){
                    k3vModel.setValue2(model.getValue());
                    t=false;
                    break;
                }
            }
            if(t){
                K3vModel k3vModel=new K3vModel(model.getName(),0,model.getValue(),0);
                list.add(k3vModel);
            }
        }
        for(KvModel model:kvModels2){
            t=true;
            for(K3vModel k3vModel:list){
                if(k3vModel.getName().equals(model.getName())){
                    k3vModel.setValue3(model.getValue());
                    t=false;
                    break;
                }
            }
            if(t){
                K3vModel k3vModel=new K3vModel(model.getName(),0,0,model.getValue());
                list.add(k3vModel);
            }
        }
        return list;
    }
    @Override
    public List<K3vModel> getDsrAge(String beginTime,String endTime,AjMapper ajMapper,String fydm){
        List<K3vModel> list=new ArrayList<>();
        String lastBegin=Integer.parseInt(beginTime.substring(0,4))-1+beginTime.substring(4);
        String lastEnd=Integer.parseInt(endTime.substring(0,4))-1+endTime.substring(4);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar=Calendar.getInstance();
        try {
            calendar.setTime(format.parse(endTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.MONTH,-2);
        String lastMonth=format.format(calendar.getTime());
        List<KvModel> kvModels= ajMapper.getDsrAge(beginTime,endTime,fydm);
        List<KvModel> kvModels1=ajMapper.getDsrAge(lastBegin,lastEnd,fydm);
        List<KvModel> kvModels2=ajMapper.getDsrAge(lastMonth,beginTime,fydm);
        kvModels=kvModels.stream().sorted(Comparator.comparingInt(a -> Integer.parseInt(a.getName()))).collect(Collectors.toList());
        //list=list.stream().sorted((a,b)->Integer.parseInt(a.getName())-Integer.parseInt(b.getName())).collect(Collectors.toList());
        kvModels1=kvModels1.stream().sorted(Comparator.comparingInt(a -> Integer.parseInt(a.getName()))).collect(Collectors.toList());
        kvModels2=kvModels2.stream().sorted(Comparator.comparingInt(a -> Integer.parseInt(a.getName()))).collect(Collectors.toList());

        K3vModel kvModel0=new K3vModel("未满14周岁",0,0,0);
        K3vModel kvModel1=new K3vModel("已满14岁不满18岁",0,0,0);
        K3vModel kvModel2=new K3vModel("已满18岁不满25岁",0,0,0);
        K3vModel kvModel3=new K3vModel("已满25岁不满39岁",0,0,0);
        K3vModel kvModel4=new K3vModel("已满39岁不满49岁",0,0,0);
        K3vModel kvModel5=new K3vModel("已满49岁不满59岁",0,0,0);
        K3vModel kvModel6=new K3vModel("59岁以上",0,0,0);
        list.add(kvModel0);
        list.add(kvModel1);
        list.add(kvModel2);
        list.add(kvModel3);
        list.add(kvModel4);
        list.add(kvModel5);
        list.add(kvModel6);
        int[][] ages=new int[][]{{0,14}, {14,18}, {18,25}, {25,39},{39,49}, {49,59}, {59,200}};
        for(KvModel model:kvModels){
            for(int i=0;i<ages.length;i++){
                if(Integer.parseInt(model.getName())>=ages[i][0] && Integer.parseInt(model.getName())<ages[i][1]){
                    list.get(i).setValue1(list.get(i).getValue1()+model.getValue());
                    break;
                }
            }
        }
        for(KvModel model:kvModels1){
            for(int i=0;i<ages.length;i++){
                if(Integer.parseInt(model.getName())>=ages[i][0] && Integer.parseInt(model.getName())<ages[i][1]){
                    list.get(i).setValue2(list.get(i).getValue2()+model.getValue());
                    break;
                }
            }
        }
        for(KvModel model:kvModels2){
            for(int i=0;i<ages.length;i++){
                if(Integer.parseInt(model.getName())>=ages[i][0] && Integer.parseInt(model.getName())<ages[i][1]){
                    list.get(i).setValue3(list.get(i).getValue3()+model.getValue());
                    break;
                }
            }
        }
        for(int i=0;i<list.size();i++){
            if(list.get(i).getValue1()==0&&list.get(i).getValue2()==0&&list.get(i).getValue3()==0){
                list.remove(i);
                i--;
            }
        }
        return list;
    }
    @Override
    public List<K3vModel> getDsrWhcd(String beginTime,String endTime,AjMapper ajMapper,String fydm){
        List<K3vModel> list=new ArrayList<>();
        String lastBegin=Integer.parseInt(beginTime.substring(0,4))-1+beginTime.substring(4);
        String lastEnd=Integer.parseInt(endTime.substring(0,4))-1+endTime.substring(4);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar=Calendar.getInstance();
        try {
            calendar.setTime(format.parse(endTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.MONTH,-2);
        String lastMonth=format.format(calendar.getTime());
        List<KvModel> kvModels= ajMapper.getDsrWhcd(beginTime,endTime,fydm);
        List<KvModel> kvModels1=ajMapper.getDsrWhcd(lastBegin,lastEnd,fydm);
        List<KvModel> kvModels2=ajMapper.getDsrWhcd(lastMonth,beginTime,fydm);
        for(KvModel model:kvModels){
            K3vModel k3vModel=new K3vModel(model.getName(),model.getValue(),0,0);
            list.add(k3vModel);
        }
        boolean t=true;
        for(KvModel model:kvModels1){
            t=true;
            for(K3vModel k3vModel:list){
                if(k3vModel.getName().equals(model.getName())){
                    k3vModel.setValue2(model.getValue());
                    t=false;
                    break;
                }
            }
            if(t){
                K3vModel k3vModel=new K3vModel(model.getName(),0,model.getValue(),0);
                list.add(k3vModel);
            }
        }
        for(KvModel model:kvModels2){
            t=true;
            for(K3vModel k3vModel:list){
                if(k3vModel.getName().equals(model.getName())){
                    k3vModel.setValue3(model.getValue());
                    t=false;
                    break;
                }
            }
            if(t){
                K3vModel k3vModel=new K3vModel(model.getName(),0,0,model.getValue());
                list.add(k3vModel);
            }
        }
        return list;
    }
    @Override
    public List<K2vModel> getBgSsdw(String fydm,String beginTime,String endTime,AjMapper ajMapper){
        String sjxx="";
        String sjxx2016="";
        if(fydm.equals("120242 22A")){
            sjxx=syjfMapper.getSJXXBH("DSRSSDW","DSR_JB",fydm);
            sjxx2016=syjfMapper.getSJXX2016BH("DSRSSDW","DSR_JB",fydm);
            if(sjxx2016!=null){
                List<K2vModelBH> k2vModelBHList = ajMapper.getAjBHAfter(beginTime, endTime);
                List<K2vModelBH> k2vModelBHList1 = ajMapper.getAjBHBefore(beginTime, endTime);
                return getBgdsrBH(getSjxx2016BH(sjxx,sjxx2016,fydm,beginTime,endTime,k2vModelBHList,k2vModelBHList1));
            }
            else {
                List<K2vModelBH> list = ajMapper.getAjBH(beginTime, endTime);
                return getBgdsrBH(getSjxxBH(sjxx,fydm,beginTime,endTime,list));
            }
        }
        else {
            sjxx = syjfMapper.getSJXX("DSRSSDW", "DSR_JB");
            sjxx2016 = syjfMapper.getSJXX2016("DSRSSDW", "DSR_JB");
            if(sjxx2016!=null){
                List<KvModel> list = ajMapper.getAjAfter(beginTime, endTime);
                List<KvModel> list1 = ajMapper.getAjBefore(beginTime, endTime);
                return getBgdsr(getSjxx2016(sjxx,sjxx2016,fydm,beginTime,endTime,list,list1));
            }
            else {
                List<KvModel> list = ajMapper.getAj(beginTime, endTime);
                return getBgdsr(getSjxx(sjxx,fydm,beginTime,endTime,list));
            }
        }

    }

    //得到原告申诉地位
    public List<K2vModel> getYgSsdw(String fydm, String beginTime, String endTime, AjMapper ajMapper){
        String sjxx="";
        String sjxx2016="";
        if(fydm.equals("120242 22A")){
            sjxx=syjfMapper.getSJXXBH("DSRSSDW","DSR_JB",fydm);
            sjxx2016=syjfMapper.getSJXX2016BH("DSRSSDW","DSR_JB",fydm);
            if(sjxx2016!=null){
                List<K2vModelBH> k2vModelBHList = ajMapper.getAjBHAfter(beginTime, endTime);
                List<K2vModelBH> k2vModelBHList1 = ajMapper.getAjBHBefore(beginTime, endTime);
                return getYgdsrBH(getSjxx2016BH(sjxx,sjxx2016,fydm,beginTime,endTime,k2vModelBHList,k2vModelBHList1));
            }
            else {
                List<K2vModelBH> list = ajMapper.getAjBH(beginTime, endTime);
                return getYgdsrBH(getSjxxBH(sjxx,fydm,beginTime,endTime,list));
            }
        }
        else {
            sjxx = syjfMapper.getSJXX("DSRSSDW", "DSR_JB");
            sjxx2016 = syjfMapper.getSJXX2016("DSRSSDW", "DSR_JB");
            if(sjxx2016!=null){
                List<KvModel> list = ajMapper.getAjAfter(beginTime, endTime);
                List<KvModel> list1 = ajMapper.getAjBefore(beginTime, endTime);
                return getYgdsr(getSjxx2016(sjxx,sjxx2016,fydm,beginTime,endTime,list,list1));
            }
            else {
                List<KvModel> list = ajMapper.getAj(beginTime, endTime);
                return getYgdsr(getSjxx(sjxx,fydm,beginTime,endTime,list));
            }
        }
    }

    public List<K2vModel> getBgdsr(HashMap<String,List<Integer>> ajMap){
        List<K2vModel> modelList = new ArrayList<>();
        for (String str : ajMap.keySet()) {
            List<K2vModel> list1 = syjfMapper.getBgDSR(str, ajMap.get(str));
            for (K2vModel model : list1) {
                modelList.add(model);
            }
        }
        return modelList;
    }

    public List<K2vModel> getBgdsrBH(HashMap<String,List<KvModel>> ajMap){
        List<K2vModel> modelList = new ArrayList<>();
        for (String str : ajMap.keySet()) {
            List<K2vModel> list1 = syjfMapper.getBgDSRBH(str, ajMap.get(str));
            for (K2vModel model : list1) {
                modelList.add(model);
            }
        }
        return modelList;
    }

    public List<K2vModel> getYgdsr(HashMap<String,List<Integer>> ajMap){
        List<K2vModel> modelList = new ArrayList<>();
        for (String str : ajMap.keySet()) {
            List<K2vModel> list1 = syjfMapper.getYgDSR(str, ajMap.get(str));
            for (K2vModel model : list1) {
                modelList.add(model);
            }
        }
        return modelList;
    }
    public List<K2vModel> getYgdsrBH(HashMap<String,List<KvModel>> ajMap){
        List<K2vModel> modelList = new ArrayList<>();
        for (String str : ajMap.keySet()) {
            List<K2vModel> list1 = syjfMapper.getYgDSRBH(str, ajMap.get(str));
            for (K2vModel model : list1) {
                modelList.add(model);
            }
        }
        return modelList;
    }



    @Override
    public HashMap<String,List<Integer>> getSjxx(String sjxx, String fydm, String beginTime, String endTime, List<KvModel> list){
        String[] sjxxs = sjxx.split(",");
//        List<KvModel> list = syjfMapper.getSyaj(beginTime, endTime);
        HashMap<String, List<Integer>> ajMap = new HashMap<>();
        for (KvModel model : list) {
            if (ajMap.containsKey(model.getName())) {
                ajMap.get(model.getName()).add(model.getValue());
            } else {
                List<Integer> ajList = new ArrayList<>();
                ajList.add(model.getValue());
                ajMap.put(model.getName(), ajList);
            }
        }
        List<String> strList = new ArrayList<>();
        List<String> strList1 = new ArrayList<>();
        for (String str : ajMap.keySet()) {
            for (int i = 0; i < sjxxs.length; i++) {
                if (sjxxs[i].substring(0, 2).equals(str)) {
                    strList.add(str);
                    strList1.add(sjxxs[i].substring(2));
                    break;
                }
            }
        }
        for (int i = 0; i < strList.size(); i++) {
            ajMap.put(strList1.get(i), ajMap.remove(strList.get(i)));
        }
        strList.clear();
        strList1.clear();
        for (String str : ajMap.keySet()) {
            if (str.length() == 2) {
                for (int i = 0; i < sjxxs.length; i++) {
                    if (sjxxs[i].substring(0, 2).equals(str.substring(0, 1) + "*")) {
                        strList.add(str);
                        strList1.add(sjxxs[i].substring(2));
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < strList.size(); i++) {
            if(ajMap.containsKey(strList1.get(i))){
                ajMap.get(strList1.get(i)).addAll(ajMap.remove(strList.get(i)));
            }
            else {
                ajMap.put(strList1.get(i), ajMap.remove(strList.get(i)));
            }
        }
        return ajMap;
    }
    @Override
    public HashMap<String,List<KvModel>> getSjxxBH(String sjxx,String fydm,String beginTime,String endTime,List<K2vModelBH> list){
        String[] sjxxs = sjxx.split(",");
//        List<K2vModelBH> list = syjfMapper.getSyajBH(beginTime, endTime);
        HashMap<String, List<KvModel>> ajMap = new HashMap<>();
        if(list.size()==0){
            return ajMap;
        }
        for (K2vModelBH model : list) {
            if (ajMap.containsKey(model.getName1())) {
                KvModel kvModel=new KvModel(model.getName2(),model.getValue());
                ajMap.get(model.getName1()).add(kvModel);
            } else {
                List<KvModel> ajList = new ArrayList<>();
                ajList.add(new KvModel(model.getName2(),model.getValue()));
                ajMap.put(model.getName1(), ajList);
            }
        }
        List<String> strList = new ArrayList<>();
        List<String> strList1 = new ArrayList<>();
        for (String str : ajMap.keySet()) {
            for (int i = 0; i < sjxxs.length; i++) {
                if (sjxxs[i].substring(0, 2).equals(str)) {
                    strList.add(str);
                    strList1.add(sjxxs[i].substring(2));
                    break;
                }
            }
        }
        for (int i = 0; i < strList.size(); i++) {
            ajMap.put(strList1.get(i), ajMap.remove(strList.get(i)));
        }
        strList.clear();
        strList1.clear();
        for (String str : ajMap.keySet()) {
            if (str.length() == 2) {
                for (int i = 0; i < sjxxs.length; i++) {
                    if (sjxxs[i].substring(0, 2).equals(str.substring(0, 1) + "*")) {
                        strList.add(str);
                        strList1.add(sjxxs[i].substring(2));
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < strList.size(); i++) {
            if(ajMap.containsKey(strList1.get(i))){
                ajMap.get(strList1.get(i)).addAll(ajMap.remove(strList.get(i)));
            }
            else {
                ajMap.put(strList1.get(i), ajMap.remove(strList.get(i)));
            }
        }
        return ajMap;
    }

    @Override
    public HashMap<String,List<Integer>> getSjxx2016(String sjxx1,String sjxx2016,String fydm,String beginTime,String endTime,List<KvModel> list,List<KvModel> list1){
        String sjxx=syjfMapper.getSJXX("SPCX","PUB_AJ_JB");
//        List<KvModel> list = syjfMapper.getSyajAfter(beginTime, endTime);
        HashMap<String,List<Integer>> map=getSjxx(sjxx,fydm,beginTime,endTime,list);
//        List<KvModel> list1 = syjfMapper.getSyajBefore(beginTime, endTime);
        HashMap<String,List<Integer>> map1=getSjxx(sjxx1,fydm,beginTime,endTime,list1);
        HashMap<String,List<Integer>> ajMap=new HashMap<>();
        for(String str:map.keySet()){
            for(Integer ajxh:map.get(str)){
                String xgdm2=syjfMapper.getXgdm(str,ajxh);
                if(xgdm2==null){
                    String name=syjfMapper.getAjxzSpcx(ajxh,fydm)+"0";
                    if(ajMap.containsKey(name)){
                        ajMap.get(name).add(ajxh);
                    }
                    else {
                        List<Integer> list11=new ArrayList<>();
                        list11.add(ajxh);
                        ajMap.put(name, list11);
                    }
                }
                else{
                    String name=syjfMapper.getAjxzSpcxSpcxdz(ajxh,fydm);
                    if(ajMap.containsKey(name)){
                        ajMap.get(name).add(ajxh);
                    }
                    else {
                        List<Integer> list11=new ArrayList<>();
                        list11.add(ajxh);
                        ajMap.put(name, list11);
                    }
                }

            }
        }
        String[] sjxxs = sjxx2016.split(",");
        List<String> strList = new ArrayList<>();
        List<String> strList1 = new ArrayList<>();
        for (String str : ajMap.keySet()) {
            for (int i = 0; i < sjxxs.length; i++) {
                if (sjxxs[i].substring(0, 3).equals(str)) {
                    strList.add(str);
                    strList1.add(sjxxs[i].substring(3));
                    break;
                }
            }
        }
        for (int i = 0; i < strList.size(); i++) {
            ajMap.put(strList1.get(i), ajMap.remove(strList.get(i)));
        }
        for(String str:map1.keySet()){
            if(ajMap.containsKey(str)){
                ajMap.get(str).addAll(map1.get(str));
            }
            else{
                ajMap.put(str,map1.get(str));
            }
        }
        return ajMap;
    }
    @Override
    public HashMap<String,List<KvModel>> getSjxx2016BH(String sjxx1,String sjxx2016,String fydm,String beginTime,String endTime,List<K2vModelBH> k2vModelBHList,List<K2vModelBH> k2vModelBHList1){
        String sjxx=syjfMapper.getSJXXBH("SPCX","PUB_AJ_JB",fydm);
//        List<K2vModelBH> k2vModelBHList = syjfMapper.getSyajBHAfter(beginTime, endTime);
        HashMap<String,List<KvModel>> map=getSjxxBH(sjxx,fydm,beginTime,endTime,k2vModelBHList);
//        List<K2vModelBH> k2vModelBHList1 = syjfMapper.getSyajBHBefore(beginTime, endTime);
        HashMap<String,List<KvModel>> map1=getSjxxBH(sjxx1,fydm,beginTime,endTime,k2vModelBHList1);
        HashMap<String,List<KvModel>> ajMap=new HashMap<>();
        for(String str:map.keySet()){
            for(KvModel model:map.get(str)){
                String xgdm2=syjfMapper.getXgdmBH(str,model.getValue(),model.getName());
                if(xgdm2==null){
                    String name=syjfMapper.getAjxzSpcx(model.getValue(),model.getName())+"0";
                    if(ajMap.containsKey(name)){
                        ajMap.get(name).add(model);
                    }
                    else {
                        List<KvModel> list=new ArrayList<>();
                        list.add(model);
                        ajMap.put(name, list);
                    }
                }
                else{
                    String name=syjfMapper.getAjxzSpcxSpcxdz(map.get(str).get(0).getValue(),fydm);
                    if(ajMap.containsKey(name)){
                        ajMap.get(name).add(model);
                    }
                    else {
                        List<KvModel> list=new ArrayList<>();
                        list.add(model);
                        ajMap.put(name, list);
                    }
                }

            }
        }
        String[] sjxxs = sjxx2016.split(",");
        List<String> strList = new ArrayList<>();
        List<String> strList1 = new ArrayList<>();
        for (String str : ajMap.keySet()) {
            for (int i = 0; i < sjxxs.length; i++) {
                if (sjxxs[i].substring(0, 3).equals(str)) {
                    strList.add(str);
                    strList1.add(sjxxs[i].substring(3));
                    break;
                }
            }
        }
        for (int i = 0; i < strList.size(); i++) {
            ajMap.put(strList1.get(i), ajMap.remove(strList.get(i)));
        }
        for(String str:map1.keySet()){
            if(ajMap.containsKey(str)){
                ajMap.get(str).addAll(map1.get(str));
            }
            else{
                ajMap.put(str,map1.get(str));
            }
        }
        return ajMap;
    }


    @Override
    public List<K2vModel> getAjSj(String fydm,AjMapper ajMapper){
        List<K2vModel> res=new ArrayList<>();
        int curYear= DateUtils.getCurYear();
        String begin=(curYear-5)+"-01-01";
        String end=curYear+"-01-01";
        List<KvModel> list1=ajMapper.getSjCount(begin,end,fydm);
        List<KvModel> list2=ajMapper.getYjCount(begin,end,fydm);
        K2vModel model1=new K2vModel((curYear-5)+"",0,0);
        K2vModel model2=new K2vModel((curYear-4)+"",0,0);
        K2vModel model3=new K2vModel((curYear-3)+"",0,0);
        K2vModel model4=new K2vModel((curYear-2)+"",0,0);
        K2vModel model5=new K2vModel((curYear-1)+"",0,0);
        res.add(model1);res.add(model2);res.add(model3);res.add(model4);res.add(model5);
        for(K2vModel model:res){
            for(KvModel kvModel:list1){
                if(model.getName().equals(kvModel.getName())){
                    model.setValue1(kvModel.getValue());
                    break;
                }
            }
            for(KvModel kvModel:list2){
                if(model.getName().equals(kvModel.getName())){
                    model.setValue2(kvModel.getValue());
                    break;
                }
            }
        }
        res.stream().forEach(k->k.setName(k.getName()+"年"));
        return res;
    }

    @Override
    public String getBarDes(String fydm,List<KvModel> list1,String name){

        String str=name+"\n    ";
        if(list1.size()!=0) {
            for (KvModel model : list1) {
                if(model.getValue()>0) {
                    if (model.getName() != null) {
                        str += model.getName().replaceAll((char) 12288 + "", "") + "为" + model.getValue() + "件，";
                    }
                }
            }
            if(!str.equals(name+"\n    ")) {
                str = str.substring(0, str.length() - 1) + "。\n";
            }
        }
        return str;
    }

    @Override
    public String getBarDes1(String fydm,String beginTime,String endTime,List<KvModel> list1,String name){

        String str="";
        if(list1.size()!=0) {

            for (KvModel model : list1) {
                if(model.getValue()>0) {
                    if (model.getName() != null) {
                        str += model.getName().replaceAll((char) 12288 + "", "") + "为" + model.getValue() + "件，";
                    }
                }
            }
            if(!str.equals("")) {
                str = name+"\n    "+"统计时间范围：" + beginTime.substring(0, 4) + "年" + Integer.parseInt(beginTime.substring(5, 7)) + "月" + Integer.parseInt(beginTime.substring(8, 10)) + "日至"
                        + endTime.substring(0, 4) + "年" + Integer.parseInt(endTime.substring(5, 7)) + "月" + Integer.parseInt(endTime.substring(8, 10)) + "日。"+str;

                str = str.substring(0, str.length() - 1) + "。\n";
            }
            else{
                str=name+"\n    ";
            }
        }
        else{
            str=name+"\n    ";
        }
        return str;
    }

    @Override
    public String getBarPerDes(String fydm,List<KvModel> list1,String name){

        String str=name+"\n    ";
        if(list1.size()!=0) {
            for (KvModel model : list1) {
                if (model.getName()!=null){
                    str += model.getName().replaceAll((char) 12288 + "", "") + "为" + model.getValue() + "人，";
                }
            }
            str = str.substring(0, str.length() - 1) + "。\n";
        }
        return str;
    }

    @Override
    public String getBarPerDes1(String fydm,String beginTime,String endTime,List<KvModel> list1,String name){

        String str="";
        if(list1.size()!=0) {
            for (KvModel model : list1) {
                if (model.getName() != null) {
                    str += model.getName().replaceAll((char) 12288 + "", "") + "为" + model.getValue() + "人，";
                }
            }
            if(!str.equals("")) {
                str = name+"\n    "+"统计时间范围：" + beginTime.substring(0, 4) + "年" + Integer.parseInt(beginTime.substring(5, 7)) + "月" + Integer.parseInt(beginTime.substring(8, 10)) + "日至"
                        + endTime.substring(0, 4) + "年" + Integer.parseInt(endTime.substring(5, 7)) + "月" + Integer.parseInt(endTime.substring(8, 10)) + "日。"+str;

                str = str.substring(0, str.length() - 1) + "。\n";
            }
            else{
                str=name+"\n    ";
            }
        }
        else{
            str=name+"\n    ";
        }
        return str;
    }

    @Override
    public String getTwoBarsDes(String fydm,String beginTime,String endTime,String name,List<K2vModel> models){
        String str=name+"\n";
        for(K2vModel model:models) {
            if (model.getName() != null) {
                str = str + "    " + model.getName().replaceAll((char) 12288 + "", "") + "：新收案件数为" + model.getValue1() + "件，已结案件数为" + model.getValue2() + "件。\n";
            }
        }
        return str;
    }

    @Override
    public String getThreeBarsDes(String fydm,String beginTime,String endTime,String name,List<K3vModel> models){
        String str=name+"\n    ";
        if(models.size()!=0) {
            str += "统计时间范围：" + beginTime.substring(0, 4) + "年" + Integer.parseInt(beginTime.substring(5, 7)) + "月" + Integer.parseInt(beginTime.substring(8, 10)) + "日至"
                    + endTime.substring(0, 4) + "年" + Integer.parseInt(endTime.substring(5, 7)) + "月" + Integer.parseInt(endTime.substring(8, 10)) + "日。\n";
            for (K3vModel model : models) {
                if (model.getName() != null) {
                    if (model.getName().equals("平均审理天数")) {
                        str = str + "    " + model.getName().replaceAll((char) 12288 + "", "") + "：当期为" + model.getValue1() + "天，同比为" + model.getValue2() + "天，环比为" + model.getValue3() + "天。\n";
                    } else {
                        str = str + "    " + model.getName().replaceAll((char) 12288 + "", "") + "：当期为" + model.getValue1() + "件，同比为" + model.getValue2() + "件，环比为" + model.getValue3() + "件。\n";
                    }
                }
            }
        }
        return str;
    }

    @Override
    public String getThreeBarsDes1(String fydm,String beginTime,String endTime,String name,List<K3vModel> models){
        String str=name+"\n    ";
        if(models.size()!=0) {
            str += "统计时间范围：" + beginTime.substring(0, 4) + "年" + Integer.parseInt(beginTime.substring(5, 7)) + "月" + Integer.parseInt(beginTime.substring(8, 10)) + "日至"
                    + endTime.substring(0, 4) + "年" + Integer.parseInt(endTime.substring(5, 7)) + "月" + Integer.parseInt(endTime.substring(8, 10)) + "日。\n";
            for (K3vModel model : models) {
                if (model.getName() != null) {
                    str = str + "    " + model.getName().replaceAll((char) 12288 + "", "") + "：当期为" + model.getValue1() + "天，同比为" + model.getValue2() + "天，环比为" + model.getValue3() + "天。\n";
                }
            }
        }
        return str;
    }

    @Override
    public String getThreeBarsPerDes(String fydm,String beginTime,String endTime,String name,List<K3vModel> models){
        String str=name+"\n    ";
        if(models.size()!=0) {
            str += "统计时间范围：" + beginTime.substring(0, 4) + "年" + Integer.parseInt(beginTime.substring(5, 7)) + "月" + Integer.parseInt(beginTime.substring(8, 10)) + "日至"
                    + endTime.substring(0, 4) + "年" + Integer.parseInt(endTime.substring(5, 7)) + "月" + Integer.parseInt(endTime.substring(8, 10)) + "日。\n";
            for (K3vModel model : models) {
                if (model.getName() != null) {
                    str = str + "    " + model.getName().replaceAll((char) 12288 + "", "") + "：当期为" + model.getValue1() + "人，同比为" + model.getValue2() + "人，环比为" + model.getValue3() + "人。\n";
                }
            }
        }
        return str;
    }
}
