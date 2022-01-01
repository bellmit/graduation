package com.software.service.impl;

import com.software.mapper.AjMapper;
import com.software.mapper.SyjfMapper;
import com.software.model.*;
import com.software.service.AjService;
import com.software.service.SyjfService;
import com.software.utils.AgeUtils;
import com.software.utils.DateUtil;
import com.software.utils.DateUtils;
import com.software.utils.WordUtil;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.krb5.internal.crypto.HmacSha1Aes128CksumType;

import javax.servlet.http.HttpServletResponse;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SyjfServicelmpl implements SyjfService {
    @Autowired
    SyjfMapper syjfMapper;
    @Autowired
    AjService ajService;


    @Override
    public List<K3vModel> getQyajsl(String fydm){
        List<K3vModel> list=new ArrayList<>();
        int curYear= DateUtils.getCurYear();
        String begin=(curYear-5)+"-01-01";
        String end=curYear+"-01-01";
        List<KvModel> laList=syjfMapper.getQyajslLa(fydm,begin,end);
        List<KvModel> jaList=syjfMapper.getQyajslJa(fydm,begin,end);
        List<TimeModel> slList=syjfMapper.getQyajslavg(fydm,begin,end);
        K3vModel k3vModel1=new K3vModel((curYear-5)+"",0,0,0);
        K3vModel k3vModel2=new K3vModel((curYear-4)+"",0,0,0);
        K3vModel k3vModel3=new K3vModel((curYear-3)+"",0,0,0);
        K3vModel k3vModel4=new K3vModel((curYear-2)+"",0,0,0);
        K3vModel k3vModel5=new K3vModel((curYear-1)+"",0,0,0);
        list.add(k3vModel1);list.add(k3vModel2);list.add(k3vModel3);list.add(k3vModel4);list.add(k3vModel5);
        for(int i=0;i<laList.size();i++){
            for(int j=0;j<5;j++) {
                if (laList.get(i).getName().equals(list.get(j).getName())){
                    list.get(j).setValue1(laList.get(i).getValue());
                    break;
                }
            }
        }
        for(int i=0;i<jaList.size();i++){
            for(int j=0;j<5;j++) {
                if (jaList.get(i).getName().equals(list.get(j).getName())){
                    list.get(j).setValue2(jaList.get(i).getValue());
                    break;
                }
            }
        }
        for(int i=0;i<slList.size();i++){
            for(int j=0;j<5;j++){
                if(slList.get(i).getYear().equals(list.get(j).getName())){
                    list.get(j).setValue3(list.get(j).getValue3()+(int) Math.abs(DateUtil.getDiffDays(slList.get(i).getLarq(),slList.get(i).getJarq())));
                }
            }
        }
        for(int i=0;i<list.size();i++){
            if(list.get(i).getValue2()!=0) {
                list.get(i).setValue3(list.get(i).getValue3() / list.get(i).getValue2());
            }
        }
        return list;
    }

    @Override
    public List<K3vModel> getBmajsl(String fydm,String beginTime,String endTime){
        List<K3vModel> list=new ArrayList<>();

        List<KvModel> laList=syjfMapper.getBmajslLa(fydm,beginTime,endTime);
        List<KvModel> jaList=syjfMapper.getBmajslJa(fydm,beginTime,endTime);
        List<TimeModel> slList=syjfMapper.getBmajslavg(fydm,beginTime,endTime);
        for(int i=0;i<laList.size();i++){
            K3vModel model=new K3vModel(laList.get(i).getName(),laList.get(i).getValue(),0,0);
            list.add(model);
        }
        boolean t=true;
        for(int i=0;i<jaList.size();i++){
            t=true;
            for(int j=0;j<list.size();j++){
                if(jaList.get(i).getName().equals(list.get(j).getName())){
                    list.get(j).setValue2(jaList.get(i).getValue());
                    t=false;
                    break;
                }
            }
            if(t){
                K3vModel model1=new K3vModel(jaList.get(i).getName(),0,jaList.get(i).getValue(),0);
                list.add(model1);
            }
        }
        for(int i=0;i<slList.size();i++){
            for(int j=0;j<list.size();j++){
                if(slList.get(i).getYear().equals(list.get(j).getName())){
                    list.get(j).setValue3(list.get(j).getValue3()+(int) Math.abs(DateUtil.getDiffDays(slList.get(i).getLarq(),slList.get(i).getJarq())));
                }
            }
        }
        for(int i=0;i<list.size();i++){
            if(list.get(i).getValue2()!=0) {
                list.get(i).setValue3(list.get(i).getValue3() / list.get(i).getValue2());
            }
        }
        return list;
    }

    @Override
    public List<KvModel> getBgxb(String fydm,String beginTime,String endTime){
        List<K2vModel> BgList=getBgSsdw(fydm,beginTime,endTime);
        if(BgList.size()==0){
            return new ArrayList<>();
        }
        List<KvModel> kvModels=new ArrayList<>();
        if(fydm.equals("120242 22A")){
            kvModels=syjfMapper.getXbBH(BgList);
        }
        else {
            kvModels=syjfMapper.getXb(BgList);
        }
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
    public List<KvModel> getYgxb(String fydm,String beginTime,String endTime){
        List<K2vModel> YgList=getYgSsdw(fydm,beginTime,endTime);
        if(YgList.size()==0){
            return new ArrayList<>();
        }
        List<KvModel> kvModels=new ArrayList<>();
        if(fydm.equals("120242 22A")){
            kvModels=syjfMapper.getXbBH(YgList);
        }
        else {
            kvModels=syjfMapper.getXb(YgList);
        }
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
    public List<KvModel> getBgsf(String fydm,String beginTime,String endTime){
        List<K2vModel> BgList=getBgSsdw(fydm,beginTime,endTime);
        if(BgList.size()==0){
            return new ArrayList<>();
        }
        List<KvModel> kvModels=new ArrayList<>();
        if(fydm.equals("120242 22A")){
            kvModels=syjfMapper.getSfBH(BgList);
        }
        else {
            kvModels=syjfMapper.getSf(BgList);
        }
        return kvModels;
    }
    @Override
    public List<KvModel> getYgsf(String fydm,String beginTime,String endTime){
        List<K2vModel> YgList=getYgSsdw(fydm,beginTime,endTime);
        if(YgList.size()==0){
            return new ArrayList<>();
        }
        List<KvModel> kvModels=new ArrayList<>();
        if(fydm.equals("120242 22A")){
            kvModels=syjfMapper.getSfBH(YgList);
        }
        else {
            kvModels=syjfMapper.getSf(YgList);
        }
        return kvModels;
    }
    @Override
    public List<KvModel> getBgAge(String fydm,String beginTime,String endTime){
        List<K2vModel> BgList=getBgSsdw(fydm,beginTime,endTime);
        if(BgList.size()==0){
            return new ArrayList<>();
        }
        List<BgrxxModel> bgrxxModels=new ArrayList<>();
        if(fydm.equals("120242 22A")){
            bgrxxModels=syjfMapper.getAgeBH(BgList);
        }
        else {
            bgrxxModels=syjfMapper.getAge(BgList);
        }
        List<KvModel> kvModels=new ArrayList<>();
        KvModel kvModel0=new KvModel("未满14周岁",0);
        KvModel kvModel1=new KvModel("已满14岁不满18岁",0);
        KvModel kvModel2=new KvModel("已满18岁不满25岁",0);
        KvModel kvModel3=new KvModel("已满25岁不满39岁",0);
        KvModel kvModel4=new KvModel("已满39岁不满49岁",0);
        KvModel kvModel5=new KvModel("已满49岁不满59岁",0);
        KvModel kvModel6=new KvModel("59岁以上",0);
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
    public List<KvModel> getYgAge(String fydm,String beginTime,String endTime){
        List<K2vModel> YgList=getYgSsdw(fydm,beginTime,endTime);
        if(YgList.size()==0){
            return new ArrayList<>();
        }
        List<BgrxxModel> bgrxxModels=new ArrayList<>();
        if(fydm.equals("120242 22A")){
            bgrxxModels=syjfMapper.getAgeBH(YgList);
        }
        else {
            bgrxxModels=syjfMapper.getAge(YgList);
        }
        List<KvModel> kvModels=new ArrayList<>();
        KvModel kvModel0=new KvModel("未满14周岁",0);
        KvModel kvModel1=new KvModel("已满14岁不满18岁",0);
        KvModel kvModel2=new KvModel("已满18岁不满25岁",0);
        KvModel kvModel3=new KvModel("已满25岁不满39岁",0);
        KvModel kvModel4=new KvModel("已满39岁不满49岁",0);
        KvModel kvModel5=new KvModel("已满49岁不满59岁",0);
        KvModel kvModel6=new KvModel("59岁以上",0);
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
     public List<KvModel> getResult(String fydm,String beginTime,String endTime){
         String sjxx="";
         String sjxx2016="";
         if(fydm.equals("120242 22A")){
             sjxx=syjfMapper.getSJXXBH("JAFS","PUB_AJ_JB",fydm);
             sjxx2016=syjfMapper.getSJXX2016BH("JAFS","PUB_AJ_JB",fydm);
             HashMap<String, List<KvModel>> ajMap = new HashMap<>();
             if (sjxx2016 != null) {
                 ajMap = getSjxx2016BH(sjxx,sjxx2016, fydm, beginTime, endTime);
             } else {
                 List<K2vModelBH> list = syjfMapper.getSyajBH(beginTime, endTime);
                 ajMap = getSjxxBH(sjxx, fydm, beginTime, endTime,list);
             }
             Map<String, Integer> map = new HashMap<>();
             for (String str : ajMap.keySet()) {
                 List<KvModel> modelList = syjfMapper.getResultBH(str, ajMap.get(str));
                 for (KvModel model : modelList) {
                     if (map.containsKey(model.getName())) {
                         map.put(model.getName(), map.get(model.getName()) + model.getValue());
                     } else {
                         map.put(model.getName(), model.getValue());
                     }
                 }
             }
             List<KvModel> result = new ArrayList<>();
             for (String str : map.keySet()) {
                 KvModel kvModel = new KvModel(str, map.get(str));
                 result.add(kvModel);
             }
             return result;
         }
         else {
             sjxx = syjfMapper.getSJXX("JAFS", "PUB_AJ_JB");
             sjxx2016 = syjfMapper.getSJXX2016("JAFS", "PUB_AJ_JB");
             HashMap<String, List<Integer>> ajMap = new HashMap<>();
             if (sjxx2016 != null) {
                 ajMap = getSjxx2016(sjxx,sjxx2016, fydm, beginTime, endTime);
             } else {
                 List<KvModel> list = syjfMapper.getSyaj(beginTime, endTime);
                 ajMap = getSjxx(sjxx, fydm, beginTime, endTime,list);
             }
             Map<String, Integer> map = new HashMap<>();
             for (String str : ajMap.keySet()) {
                 List<KvModel> modelList = syjfMapper.getResult(str, ajMap.get(str));
                 for (KvModel model : modelList) {
                     if (map.containsKey(model.getName())) {
                         map.put(model.getName(), map.get(model.getName()) + model.getValue());
                     } else {
                         map.put(model.getName(), model.getValue());
                     }
                 }
             }
             List<KvModel> result = new ArrayList<>();
             for (String str : map.keySet()) {
                 KvModel kvModel = new KvModel(str, map.get(str));
                 result.add(kvModel);
             }
             return result;
         }
     }

     @Override
     public List<K3vModel> getPjjg(String beginTime,String endTime,String fydm){
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
         String sjxx="";
         String sjxx2016="";
         if(fydm.equals("120242 22A")){
             sjxx=syjfMapper.getSJXXBH("JAFS","PUB_AJ_JB",fydm);
             sjxx2016=syjfMapper.getSJXX2016BH("JAFS","PUB_AJ_JB",fydm);
             HashMap<String, List<KvModel>> ajMap = new HashMap<>();
             HashMap<String, List<KvModel>> ajMap1 = new HashMap<>();
             HashMap<String, List<KvModel>> ajMap2 = new HashMap<>();
             if (sjxx2016 != null) {
                 ajMap = getSjxx2016BH(sjxx,sjxx2016, fydm, beginTime, endTime);
                 ajMap1 = getSjxx2016BH(sjxx,sjxx2016, fydm, lastBegin, lastEnd);
                 ajMap2 = getSjxx2016BH(sjxx,sjxx2016, fydm, lastMonth, beginTime);
             } else {
                 List<K2vModelBH> list = syjfMapper.getSyajBH(beginTime, endTime);
                 List<K2vModelBH> list1 = syjfMapper.getSyajBH(lastBegin, lastEnd);
                 List<K2vModelBH> list2 = syjfMapper.getSyajBH(lastMonth, beginTime);
                 ajMap = getSjxxBH(sjxx, fydm, beginTime, endTime,list);
                 ajMap1 = getSjxxBH(sjxx, fydm, lastBegin, lastEnd,list1);
                 ajMap2 = getSjxxBH(sjxx, fydm, lastMonth, beginTime,list2);
             }
             Map<String, Integer> map = new HashMap<>();
             for (String str : ajMap.keySet()) {
                 List<KvModel> modelList = syjfMapper.getResultBH(str, ajMap.get(str));
                 for (KvModel model : modelList) {
                     if (map.containsKey(model.getName())) {
                         map.put(model.getName(), map.get(model.getName()) + model.getValue());
                     } else {
                         map.put(model.getName(), model.getValue());
                     }
                 }
             }
             Map<String, Integer> map1 = new HashMap<>();
             for (String str : ajMap1.keySet()) {
                 List<KvModel> modelList = syjfMapper.getResultBH(str, ajMap1.get(str));
                 for (KvModel model : modelList) {
                     if (map1.containsKey(model.getName())) {
                         map1.put(model.getName(), map1.get(model.getName()) + model.getValue());
                     } else {
                         map1.put(model.getName(), model.getValue());
                     }
                 }
             }
             Map<String, Integer> map2 = new HashMap<>();
             for (String str : ajMap2.keySet()) {
                 List<KvModel> modelList = syjfMapper.getResultBH(str, ajMap2.get(str));
                 for (KvModel model : modelList) {
                     if (map2.containsKey(model.getName())) {
                         map2.put(model.getName(), map2.get(model.getName()) + model.getValue());
                     } else {
                         map2.put(model.getName(), model.getValue());
                     }
                 }
             }
             for(String str:map.keySet()){
                 K3vModel model=new K3vModel(str,map.get(str),0,0);
                 result.add(model);
             }
             boolean t=true;
             for(String str:map1.keySet()){
                 t=true;
                 for(K3vModel k3vModel:result){
                     if(k3vModel.getName().equals(str)){
                         k3vModel.setValue2(map1.get(str));
                         t=false;
                         break;
                     }
                 }
                 if(t){
                     K3vModel model=new K3vModel(str,0,map1.get(str),0);
                     result.add(model);
                 }
             }
             for(String str:map2.keySet()){
                 t=true;
                 for(K3vModel k3vModel:result){
                     if(k3vModel.getName().equals(str)){
                         k3vModel.setValue3(map2.get(str));
                         t=false;
                         break;
                     }
                 }
                 if(t){
                     K3vModel model=new K3vModel(str,0,0,map2.get(str));
                     result.add(model);
                 }
             }
             return result;
         }
         else {
             sjxx = syjfMapper.getSJXX("JAFS", "PUB_AJ_JB");
             sjxx2016 = syjfMapper.getSJXX2016("JAFS", "PUB_AJ_JB");
             HashMap<String, List<Integer>> ajMap = new HashMap<>();
             HashMap<String, List<Integer>> ajMap1 = new HashMap<>();
             HashMap<String, List<Integer>> ajMap2 = new HashMap<>();
             if (sjxx2016 != null) {
                 ajMap = getSjxx2016(sjxx,sjxx2016, fydm, beginTime, endTime);
                 ajMap1 = getSjxx2016(sjxx,sjxx2016, fydm, lastBegin, lastEnd);
                 ajMap2 = getSjxx2016(sjxx,sjxx2016, fydm, lastMonth, beginTime);
             } else {
                 List<KvModel> list = syjfMapper.getSyaj(beginTime, endTime);
                 List<KvModel> list1 = syjfMapper.getSyaj(lastBegin, lastEnd);
                 List<KvModel> list2 = syjfMapper.getSyaj(lastMonth, beginTime);
                 ajMap = getSjxx(sjxx, fydm, beginTime, endTime,list);
                 ajMap1 = getSjxx(sjxx, fydm, lastBegin, lastEnd,list1);
                 ajMap2 = getSjxx(sjxx, fydm, lastMonth, beginTime,list2);
             }
             Map<String, Integer> map = new HashMap<>();
             for (String str : ajMap.keySet()) {
                 List<KvModel> modelList = syjfMapper.getResult(str, ajMap.get(str));
                 for (KvModel model : modelList) {
                     if (map.containsKey(model.getName())) {
                         map.put(model.getName(), map.get(model.getName()) + model.getValue());
                     } else {
                         map.put(model.getName(), model.getValue());
                     }
                 }
             }
             Map<String, Integer> map1 = new HashMap<>();
             for (String str : ajMap1.keySet()) {
                 List<KvModel> modelList = syjfMapper.getResult(str, ajMap1.get(str));
                 for (KvModel model : modelList) {
                     if (map1.containsKey(model.getName())) {
                         map1.put(model.getName(), map1.get(model.getName()) + model.getValue());
                     } else {
                         map1.put(model.getName(), model.getValue());
                     }
                 }
             }
             Map<String, Integer> map2 = new HashMap<>();
             for (String str : ajMap2.keySet()) {
                 List<KvModel> modelList = syjfMapper.getResult(str, ajMap2.get(str));
                 for (KvModel model : modelList) {
                     if (map2.containsKey(model.getName())) {
                         map2.put(model.getName(), map2.get(model.getName()) + model.getValue());
                     } else {
                         map2.put(model.getName(), model.getValue());
                     }
                 }
             }
             for(String str:map.keySet()){
                 K3vModel model=new K3vModel(str,map.get(str),0,0);
                 result.add(model);
             }
             boolean t=true;
             for(String str:map1.keySet()){
                 t=true;
                 for(K3vModel k3vModel:result){
                     if(k3vModel.getName().equals(str)){
                         k3vModel.setValue2(map1.get(str));
                         t=false;
                         break;
                     }
                 }
                 if(t){
                     K3vModel model=new K3vModel(str,0,map1.get(str),0);
                     result.add(model);
                 }
             }
             for(String str:map2.keySet()){
                 t=true;
                 for(K3vModel k3vModel:result){
                     if(k3vModel.getName().equals(str)){
                         k3vModel.setValue3(map2.get(str));
                         t=false;
                         break;
                     }
                 }
                 if(t){
                     K3vModel model=new K3vModel(str,0,0,map2.get(str));
                     result.add(model);
                 }
             }
             return result;
         }
     }

    @Override
    public void exportWord(HttpServletResponse response, String beginTime, String endTime, String fydm) {
        List<String> list=new ArrayList<>();
        list.add(ajService.getTwoBarsDes(fydm,beginTime,endTime,"1、案件收结统计",getAjSj(fydm)));
        list.add(ajService.getThreeBarsDes(fydm,beginTime,endTime,"2、案件审理统计",getAjsl(beginTime,endTime,fydm)));
        list.add(ajService.getThreeBarsDes(fydm,beginTime,endTime,"3、部门审理统计（立案数）",getBmslLa(beginTime,endTime,fydm)));
        list.add(ajService.getThreeBarsDes(fydm,beginTime,endTime,"4、部门审理统计（结案数）",getBmslJa(beginTime,endTime,fydm)));
        list.add(ajService.getThreeBarsDes1(fydm,beginTime,endTime,"5、部门审理统计（平均审理天数）",getBmslAvg(beginTime,endTime,fydm)));
        list.add(ajService.getBarPerDes1(fydm,beginTime,endTime,getBgxb(fydm,beginTime,endTime),"6、被告性别特征统计"));
        list.add(ajService.getBarPerDes1(fydm,beginTime,endTime,getYgxb(fydm,beginTime,endTime),"7、原告性别特征统计"));
        list.add(ajService.getBarPerDes1(fydm,beginTime,endTime,getBgsf(fydm,beginTime,endTime),"8、被告身份特征统计"));
        list.add(ajService.getBarPerDes1(fydm,beginTime,endTime,getYgsf(fydm,beginTime,endTime),"9、原告身份特征统计"));
        list.add(ajService.getBarPerDes1(fydm,beginTime,endTime,getBgAge(fydm,beginTime,endTime),"10、被告年龄特征统计"));
        list.add(ajService.getBarPerDes1(fydm,beginTime,endTime,getYgAge(fydm,beginTime,endTime),"11、原告年龄特征统计"));
        list.add(ajService.getThreeBarsPerDes(fydm,beginTime,endTime,"12、被告人特征统计（身份）",getBgSf(beginTime,endTime,fydm)));
        list.add(ajService.getBarDes1(fydm,beginTime,endTime,getResult(fydm,beginTime,endTime),"13、案件判决结果统计"));
        list.add(ajService.getThreeBarsDes(fydm,beginTime,endTime,"14、案件特征统计（判决结果）",getPjjg(beginTime,endTime,fydm)));
        WordUtil.exportWord(response,"aj.docx","赡养纠纷案件专题分析",list);
    }


    @Override
     public List<AjVO> getQyajJaList(String index,String fydm){
        String beginTime=index+"-01-01";
        String endTime=(Integer.parseInt(index)+1)+"-01-01";
        List<AjModel> list=syjfMapper.getQyajJaList(fydm,beginTime,endTime);
         SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
         List<AjVO> res=new ArrayList<>();
        for(AjModel model:list){
            AjVO ajVO=new AjVO(model.getAh(),model.getAjmc(),format.format(model.getLarq()),format.format(model.getJarq()),model.getSx(),model.getFjsx());
            res.add(ajVO);
        }
        return res;
     }

    @Override
    public List<AjVO> getQyajLaList(String index,String fydm){
        String beginTime=index+"-01-01";
        String endTime=(Integer.parseInt(index)+1)+"-01-01";
        List<AjModel> list=syjfMapper.getQyajLaList(fydm,beginTime,endTime);
        SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        List<AjVO> res=new ArrayList<>();
        for(AjModel model:list){
            if(model.getJarq()==null){
                AjVO ajVO = new AjVO(model.getAh(), model.getAjmc(), format.format(model.getLarq()), "", model.getSx(), model.getFjsx());
                res.add(ajVO);
            }
            else {
                AjVO ajVO = new AjVO(model.getAh(), model.getAjmc(), format.format(model.getLarq()), format.format(model.getJarq()), model.getSx(), model.getFjsx());
                res.add(ajVO);
            }
        }
        return res;
    }

    @Override
    public List<AjVO> getBmajJaList(String index,String fydm,String beginTime,String endTime){
        List<AjModel> list=syjfMapper.getBmajJaList(index,fydm,beginTime,endTime);
        SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        List<AjVO> res=new ArrayList<>();
        for(AjModel model:list){
            AjVO ajVO=new AjVO(model.getAh(),model.getAjmc(),format.format(model.getLarq()),format.format(model.getJarq()),model.getSx(),model.getFjsx());
            res.add(ajVO);
        }
        return res;
    }

    @Override
    public List<AjVO> getBmajLaList(String index,String fydm,String beginTime,String endTime){
        List<AjModel> list=syjfMapper.getBmajLaList(index,fydm,beginTime,endTime);
        SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        List<AjVO> res=new ArrayList<>();
        for(AjModel model:list){
            if(model.getJarq()==null){
                AjVO ajVO = new AjVO(model.getAh(), model.getAjmc(), format.format(model.getLarq()), "", model.getSx(), model.getFjsx());
                res.add(ajVO);
            }
            else {
                AjVO ajVO = new AjVO(model.getAh(), model.getAjmc(), format.format(model.getLarq()), format.format(model.getJarq()), model.getSx(), model.getFjsx());
                res.add(ajVO);
            }

        }
        return res;
    }

    @Override
    public List<K3vModel> getAjsl(String beginTime, String endTime, String fydm){
        return ajService.getAjsl(beginTime,endTime,syjfMapper,fydm);
    }
    @Override
    public List<K3vModel> getBmslLa(String beginTime, String endTime, String fydm){
        return ajService.getBmslLa(beginTime,endTime,syjfMapper,fydm);
    }
    @Override
    public List<K3vModel> getBmslJa(String beginTime,String endTime,String fydm){
        return ajService.getBmslJa(beginTime,endTime,syjfMapper,fydm);
    }
    @Override
    public List<K3vModel> getBmslAvg(String beginTime,String endTime,String fydm){
        return ajService.getBmslAvg(beginTime,endTime,syjfMapper,fydm);
    }

    @Override
    public List<K2vModel> getAjSj(String fydm){
        return ajService.getAjSj(fydm,syjfMapper);
    }
    @Override
    public List<K3vModel> getBgSf(String beginTime, String endTime, String fydm) {
        return ajService.getBgSf(beginTime,endTime,syjfMapper,fydm);
    }


    //得到被告申诉地位
    public List<K2vModel> getBgSsdw(String fydm,String beginTime,String endTime){
        String sjxx="";
        String sjxx2016="";
        if(fydm.equals("120242 22A")){
            sjxx=syjfMapper.getSJXXBH("DSRSSDW","DSR_JB",fydm);
            sjxx2016=syjfMapper.getSJXX2016BH("DSRSSDW","DSR_JB",fydm);
            if(sjxx2016!=null){
                return getBgdsrBH(getSjxx2016BH(sjxx,sjxx2016,fydm,beginTime,endTime));
            }
            else {
                List<K2vModelBH> list = syjfMapper.getSyajBH(beginTime, endTime);
                return getBgdsrBH(getSjxxBH(sjxx,fydm,beginTime,endTime,list));
            }
        }
        else {
            sjxx = syjfMapper.getSJXX("DSRSSDW", "DSR_JB");
            sjxx2016 = syjfMapper.getSJXX2016("DSRSSDW", "DSR_JB");
            if(sjxx2016!=null){
                return getBgdsr(getSjxx2016(sjxx,sjxx2016,fydm,beginTime,endTime));
            }
            else {
                List<KvModel> list = syjfMapper.getSyaj(beginTime, endTime);
                return getBgdsr(getSjxx(sjxx,fydm,beginTime,endTime,list));
            }
        }

    }

    //得到原告申诉地位
    public List<K2vModel> getYgSsdw(String fydm,String beginTime,String endTime){
        String sjxx="";
        String sjxx2016="";
        if(fydm.equals("120242 22A")){
            sjxx=syjfMapper.getSJXXBH("DSRSSDW","DSR_JB",fydm);
            sjxx2016=syjfMapper.getSJXX2016BH("DSRSSDW","DSR_JB",fydm);
            if(sjxx2016!=null){
                return getYgdsrBH(getSjxx2016BH(sjxx,sjxx2016,fydm,beginTime,endTime));
            }
            else {
                List<K2vModelBH> list = syjfMapper.getSyajBH(beginTime, endTime);
                return getYgdsrBH(getSjxxBH(sjxx,fydm,beginTime,endTime,list));
            }
        }
        else {
            sjxx = syjfMapper.getSJXX("DSRSSDW", "DSR_JB");
            sjxx2016 = syjfMapper.getSJXX2016("DSRSSDW", "DSR_JB");
            if(sjxx2016!=null){
                return getYgdsr(getSjxx2016(sjxx,sjxx2016,fydm,beginTime,endTime));
            }
            else {
                List<KvModel> list = syjfMapper.getSyaj(beginTime, endTime);
                return getYgdsr(getSjxx(sjxx,fydm,beginTime,endTime,list));
            }
        }
    }




    //ajMap:(sjxx,ajxh的列表)
    public HashMap<String,List<Integer>> getSjxx(String sjxx,String fydm,String beginTime,String endTime,List<KvModel> list){
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

    public HashMap<String,List<Integer>> getSjxx2016(String sjxx1,String sjxx2016,String fydm,String beginTime,String endTime){
        String sjxx=syjfMapper.getSJXX("SPCX","PUB_AJ_JB");
        List<KvModel> list = syjfMapper.getSyajAfter(beginTime, endTime);
        HashMap<String,List<Integer>> map=getSjxx(sjxx,fydm,beginTime,endTime,list);
        List<KvModel> list1 = syjfMapper.getSyajBefore(beginTime, endTime);
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

    public HashMap<String,List<KvModel>> getSjxx2016BH(String sjxx1,String sjxx2016,String fydm,String beginTime,String endTime){
        String sjxx=syjfMapper.getSJXXBH("SPCX","PUB_AJ_JB",fydm);
        List<K2vModelBH> k2vModelBHList = syjfMapper.getSyajBHAfter(beginTime, endTime);
        HashMap<String,List<KvModel>> map=getSjxxBH(sjxx,fydm,beginTime,endTime,k2vModelBHList);
        List<K2vModelBH> k2vModelBHList1 = syjfMapper.getSyajBHBefore(beginTime, endTime);
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
}
