package com.software.service.impl;

import com.software.model.K3vModel;
import com.software.service.AjService;
import com.software.service.MinorFzService;
import com.software.datasource.DataSourceEnum;
import com.software.datasource.DataSourceRouter;
import com.software.mapper.AjMapper;
import com.software.mapper.WcnfzMapper;
import com.software.model.K2vModel;
import com.software.model.KvModel;
import com.software.utils.DateUtils;
import com.software.utils.ToolsUtils;
import com.software.utils.WordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Service
public class MinorFzServiceImpl implements MinorFzService {
    @Autowired
    WcnfzMapper wcnfzMapper;
    @Autowired
    AjService ajService;

    //未成年人罪名分布
    @Override
    public List<KvModel> getMinorZmfb(String beginTime, String endTime,String fydm){
        List<KvModel> list=wcnfzMapper.getMinorZmfb(beginTime,endTime,fydm);
        list=list.stream().limit(6).collect(Collectors.toList());
        return list;
    }
    //返回的是最近三年的 包括年新收案件量 年审结案件量
    @Override
    public List<K2vModel> getMinorYearAjCount(String fydm){
        int year=DateUtils.getCurYear();
        List<K2vModel> list=new ArrayList<>();
        for(int i=year-2;i<=year;i++){
            K2vModel k2vModel=new K2vModel();
            k2vModel.setName(String.valueOf(i));
            Date last=DateUtils.getYearLast(i);
            Date first=DateUtils.getYearFirst(i);
            String lastday=DateUtils.formatDate(last);
            String fristday=DateUtils.formatDate(first);
            k2vModel.setValue1(wcnfzMapper.getMinorXsajCount(fristday,lastday,fydm));
            System.out.println("新收数"+wcnfzMapper.getMinorXsajCount(fristday,lastday,fydm));
            k2vModel.setValue2(wcnfzMapper.getMinorYjajCount(fristday,lastday,fydm));
            System.out.println("已结数"+wcnfzMapper.getMinorYjajCount(fristday,lastday,fydm));
            list.add(k2vModel);
        }
        return list;
    }
    //案件地域分布
    @Override
    public List<KvModel> getMinorDyfb (String fydm, String beginTime, String endTime) {
        //中院显示辖区 高院显示全部
        //List<KvModel> list=wcnfzMapper.getMinorWlaj(beginTime,endTime,fydm);
        List<KvModel> kvModels=new ArrayList<>();
        ExecutorService executors = Executors.newFixedThreadPool(10);
        List<String> fydms=DataSourceEnum.getSubFydm(fydm);
        for(String dm:fydms){
            Runnable task= () -> {
                KvModel k=new KvModel();
                System.out.println("法院："+dm);
                DataSourceRouter.routerTo(dm);
                int ajs=wcnfzMapper.getMinorYjajCount(beginTime,endTime,dm);
                k.setName(DataSourceEnum.getFydqByFydm(dm));
                k.setValue(ajs);
                kvModels.add(k);
            };
            executors.execute(task);
        }

        try {
            executors.shutdown();
            executors.awaitTermination(1,TimeUnit.MINUTES);
        }catch (InterruptedException ignored){

        }
        return kvModels;
    }

    //被告人性别
    @Override
    public List<K3vModel> getMinorBgrxb(String beginTime, String endTime,String fydm){
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
        List<KvModel> kvModels= wcnfzMapper.getMinorBgrxb(beginTime,endTime,fydm);
        List<KvModel> kvModels1=wcnfzMapper.getMinorBgrxb(lastBegin,lastEnd,fydm);
        List<KvModel> kvModels2=wcnfzMapper.getMinorBgrxb(lastMonth,beginTime,fydm);
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
    //各省未成年犯罪情况
    @Override
    public List<KvModel> getMinorWlaj(String beginTime, String endTime,String fydm){
        List<KvModel> list=wcnfzMapper.getMinorWlaj(beginTime,endTime,fydm);
        Map<String,String> map=ToolsUtils.getSf();
        list.forEach(a->{
            String t=a.getName();
            if (map.containsKey(t)){
                a.setName(map.get(t));
            }
        });
        return list;
    }
    //未成年人年龄分布 一个年龄一个段
    @Override
    public List<K3vModel> getMinorBgrAge(String beginTime, String endTime,String fydm){
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
        List<KvModel> kvModels= wcnfzMapper.getMinorBgrAge(beginTime,endTime,fydm);
        List<KvModel> kvModels1=wcnfzMapper.getMinorBgrAge(lastBegin,lastEnd,fydm);
        List<KvModel> kvModels2=wcnfzMapper.getMinorBgrAge(lastMonth,beginTime,fydm);
        kvModels=kvModels.stream().sorted(Comparator.comparingInt(a -> Integer.parseInt(a.getName()))).collect(Collectors.toList());
        //list=list.stream().sorted((a,b)->Integer.parseInt(a.getName())-Integer.parseInt(b.getName())).collect(Collectors.toList());
        kvModels1=kvModels1.stream().sorted(Comparator.comparingInt(a -> Integer.parseInt(a.getName()))).collect(Collectors.toList());
        kvModels2=kvModels2.stream().sorted(Comparator.comparingInt(a -> Integer.parseInt(a.getName()))).collect(Collectors.toList());
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
        list=list.stream().sorted(Comparator.comparingInt(k->Integer.parseInt(k.getName()))).collect(Collectors.toList());
        list.stream().forEach(k->k.setName(k.getName()+"岁"));
        return list;
    }
    //未成年人文化程度
    @Override
    public List<K3vModel> getMinorWhcd(String beginTime, String endTime,String fydm){
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
        List<KvModel> kvModels= wcnfzMapper.getMinorWhcd(beginTime,endTime,fydm);
        List<KvModel> kvModels1=wcnfzMapper.getMinorWhcd(lastBegin,lastEnd,fydm);
        List<KvModel> kvModels2=wcnfzMapper.getMinorWhcd(lastMonth,beginTime,fydm);
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

    //未成年人外来人口
    @Override
    public List<K3vModel> getMinorWlrk(String beginTime, String endTime,String fydm){
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
        K2vModel kvModels= wcnfzMapper.getMinorWlrk(beginTime,endTime,fydm);
        K2vModel kvModels1=wcnfzMapper.getMinorWlrk(lastBegin,lastEnd,fydm);
        K2vModel kvModels2=wcnfzMapper.getMinorWlrk(lastMonth,beginTime,fydm);
        K3vModel k3vModel1=new K3vModel("本地",0,0,0);
        K3vModel k3vModel2=new K3vModel("外来",0,0,0);
        list.add(k3vModel1);list.add(k3vModel2);
        if(kvModels !=null) {
            k3vModel1.setValue1(kvModels.getValue1());
            k3vModel2.setValue1(kvModels.getValue2());
        }
        if(kvModels1 !=null) {
            k3vModel1.setValue2(kvModels1.getValue1());
            k3vModel2.setValue2(kvModels1.getValue2());
        }
        if(kvModels2 !=null) {
            k3vModel1.setValue3(kvModels2.getValue1());
            k3vModel2.setValue3(kvModels2.getValue2());
        }
        return list;
    }
    //涉诉罪名情况 最近两年
    @Override
    public List<K2vModel> getMinorSszm(String fydm){
        int year=DateUtils.getCurYear();
        List<K2vModel> list=new ArrayList<>();
        int lastyear=year-1;
        String fristday1=DateUtils.formatDate(DateUtils.getCurYearFirst());
        String lastDay1=DateUtils.formatDate(DateUtils.getCurYearLast());
        Date first1=DateUtils.getYearFirst(lastyear);
        Date last1=DateUtils.getYearLast(lastyear);
        String fristday=DateUtils.formatDate(first1);
        String lastday=DateUtils.formatDate(last1);

        List<KvModel> kvModels=wcnfzMapper.getMinorZmfb(fristday,lastDay1,fydm);
        //得到前五名的案由
        List<String> ayList;
        if (kvModels.size()>=5){
            ayList=kvModels.stream().map(KvModel::getName).limit(5).collect(Collectors.toList());
        }else{
            ayList=kvModels.stream().map(KvModel::getName).collect(Collectors.toList());
        }
        //对于每一个案由 查出最近两年的数量
        for (String str:ayList){
            K2vModel k2vModel=new K2vModel();
            k2vModel.setName(str);
            //去年的
            int lastCount=wcnfzMapper.getMinorajCountByAy(fristday,lastday,str,fydm);
            //今年的
            int curCount=wcnfzMapper.getMinorajCountByAy(fristday1,lastDay1,str,fydm);
            k2vModel.setValue1(lastCount);
            k2vModel.setValue2(curCount);
            list.add(k2vModel);
        }
        return list;
    }
    //14到15周岁涉诉罪名
    @Override
    public List<KvModel> getMinorSszmRange1(String beginTime, String endTime,String fydm){
        List<KvModel> list=wcnfzMapper.getMinorSszmRange1(beginTime,endTime,fydm);
//        list=list.stream().limit(6).collect(Collectors.toList());
        return list;
    }
    //16-17周岁涉诉罪名
    @Override
    public List<KvModel> getMinorSszmRange2(String beginTime, String endTime,String fydm){
        return wcnfzMapper.getMinorSszmRange2(beginTime,endTime,fydm);
    }

    @Override
    public List<K3vModel> getAjsl(String beginTime, String endTime, String fydm){
        return ajService.getAjsl(beginTime,endTime,wcnfzMapper,fydm);
    }
    @Override
    public List<K3vModel> getBmslLa(String beginTime, String endTime, String fydm){
        return ajService.getBmslLa(beginTime,endTime,wcnfzMapper,fydm);
    }
    @Override
    public List<K3vModel> getBmslJa(String beginTime,String endTime,String fydm){
        return ajService.getBmslJa(beginTime,endTime,wcnfzMapper,fydm);
    }
    @Override
    public List<K3vModel> getBmslAvg(String beginTime,String endTime,String fydm){
        return ajService.getBmslAvg(beginTime,endTime,wcnfzMapper,fydm);
    }

    @Override
    public List<KvModel> yqtx(String beginTime,String endTime,String fydm){
        List<K3vModel> list=wcnfzMapper.yqtx(beginTime,endTime,fydm);
        List<KvModel> result=list.stream().map(k->new KvModel(k.getValue1()*12+k.getValue2()+"个月",k.getValue3())).collect(Collectors.toList());
        return result;

    }

    @Override
    public List<K2vModel> getAjSj(String fydm){
        return ajService.getAjSj(fydm,wcnfzMapper);
    }

    @Override
    public void exportWord(HttpServletResponse response, String beginTime, String endTime, String fydm) {
        List<String> list=new ArrayList<>();
        list.add(ajService.getTwoBarsDes(fydm,beginTime,endTime,"1、案件收结统计",getAjSj(fydm)));
        list.add(ajService.getBarPerDes1(fydm,beginTime,endTime,getMinorZmfb(beginTime,endTime,fydm),"2、罪名分布"));
        list.add(ajService.getBarDes(fydm,getMinorDyfb(fydm,beginTime,endTime),"3、案件地域分布"));
        list.add(ajService.getThreeBarsDes(fydm,beginTime,endTime,"4、案件审理统计",getAjsl(beginTime,endTime,fydm)));
        list.add(ajService.getThreeBarsDes(fydm,beginTime,endTime,"5、部门审理统计（立案数）",getBmslLa(beginTime,endTime,fydm)));
        list.add(ajService.getThreeBarsDes(fydm,beginTime,endTime,"6、部门审理统计（结案数）",getBmslJa(beginTime,endTime,fydm)));
        list.add(ajService.getThreeBarsDes1(fydm,beginTime,endTime,"7、部门审理统计（平均审理天数）",getBmslAvg(beginTime,endTime,fydm)));
        list.add(ajService.getThreeBarsPerDes(fydm,beginTime,endTime,"8、当事人特征统计（性别）",getMinorBgrxb(beginTime,endTime,fydm)));
        list.add(ajService.getThreeBarsPerDes(fydm,beginTime,endTime,"9、当事人特征统计（年龄）",getMinorBgrAge(beginTime,endTime,fydm)));
        list.add(ajService.getThreeBarsPerDes(fydm,beginTime,endTime,"10、当事人特征统计（文化程度）",getMinorWhcd(beginTime,endTime,fydm)));
        list.add(ajService.getThreeBarsPerDes(fydm,beginTime,endTime,"11、当事人特征统计（外来人口）",getMinorWlrk(beginTime,endTime,fydm)));
        list.add(ajService.getBarPerDes1(fydm,beginTime,endTime,getMinorSszmRange1(beginTime,endTime,fydm),"12、罪名分布（14-15岁）"));
        list.add(ajService.getBarPerDes1(fydm,beginTime,endTime,getMinorSszmRange2(beginTime,endTime,fydm),"13、罪名分布（16-17岁）"));
        list.add(ajService.getBarPerDes1(fydm,beginTime,endTime,yqtx(beginTime,endTime,fydm),"14、案件特征（有期徒刑）"));
        WordUtil.exportWord(response,"aj.docx","未成年人案件专题分析",list);
    }

}
