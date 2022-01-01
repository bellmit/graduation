package com.software.service.impl;

import com.software.mapper.AjMapper;
import com.software.mapper.DxwlzpMapper;
import com.software.mapper.SyjfMapper;
import com.software.model.*;
import com.software.service.AjService;
import com.software.service.DxwlzpService;
import com.software.utils.AgeUtils;
import com.software.utils.DateUtils;
import com.software.utils.WordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DxwlzpServicelmpl implements DxwlzpService {
    @Autowired
    DxwlzpMapper dxwlzpMapper;
    @Autowired
    AjService ajService;
    @Autowired
    SyjfMapper syjfMapper;

    @Override
    public List<KvModel> getAjCount(String fydm) {
        return null;
    }

    @Override
    public List<KvModel> getAreaDistribution(String beginTime, String endTime, String fydm) {
        return null;
    }

    @Override
    public List<KvModel> getBgrxb(String beginTime, String endTime, String fydm) {
        return null;
    }

    @Override
    public List<KvModel> getBgrsf(String beginTime, String endTime, String fydm) {
        return null;
    }

    @Override
    public List<KvModel> getBgrWhcd(String beginTime, String endTime, String fydm) {
        return null;
    }

    @Override
    public List<KvModel> getBrgage(String beginTime, String endTime, String fydm) {
        return null;
    }

    @Override
    public List<K3vModel> getBgXb(String beginTime, String endTime, String fydm) {
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
        List<K2vModel> BgList=getBgSsdw(fydm,beginTime,endTime,dxwlzpMapper);
        List<K2vModel> BgList1=getBgSsdw(fydm,lastBegin,lastEnd,dxwlzpMapper);
        List<K2vModel> BgList2=getBgSsdw(fydm,lastMonth,beginTime,dxwlzpMapper);

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
    public List<K3vModel> getBgSf(String beginTime, String endTime, String fydm) {
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
        List<K2vModel> BgList=getBgSsdw(fydm,beginTime,endTime,dxwlzpMapper);
        List<K2vModel> BgList1=getBgSsdw(fydm,lastBegin,lastEnd,dxwlzpMapper);
        List<K2vModel> BgList2=getBgSsdw(fydm,lastMonth,beginTime,dxwlzpMapper);
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
    public List<K3vModel> getBgWhcd(String beginTime, String endTime, String fydm) {
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
        List<K2vModel> BgList=getBgSsdw(fydm,beginTime,endTime,dxwlzpMapper);
        List<K2vModel> BgList1=getBgSsdw(fydm,lastBegin,lastEnd,dxwlzpMapper);
        List<K2vModel> BgList2=getBgSsdw(fydm,lastMonth,beginTime,dxwlzpMapper);
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
    public List<K3vModel> getBgAge(String beginTime, String endTime, String fydm) {
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
        List<K2vModel> BgList=getBgSsdw(fydm,beginTime,endTime,dxwlzpMapper);
        List<K2vModel> BgList1=getBgSsdw(fydm,lastBegin,lastEnd,dxwlzpMapper);
        List<K2vModel> BgList2=getBgSsdw(fydm,lastMonth,beginTime,dxwlzpMapper);
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
                        int larqYear= DateUtils.getYear(bgrxxModel.getLarq());
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
    public List<KvModel> getYqtxlx(String beginTime, String endTime, String fydm) {
        return null;
    }

    @Override
    public List<K3vModel> getAjsl(String beginTime, String endTime, String fydm){
        return ajService.getAjsl(beginTime,endTime,dxwlzpMapper,fydm);
    }
    @Override
    public List<K3vModel> getBmslLa(String beginTime, String endTime, String fydm){
        return ajService.getBmslLa(beginTime,endTime,dxwlzpMapper,fydm);
    }
    @Override
    public List<K3vModel> getBmslJa(String beginTime,String endTime,String fydm){
        return ajService.getBmslJa(beginTime,endTime,dxwlzpMapper,fydm);
    }
    @Override
    public List<K3vModel> getBmslAvg(String beginTime,String endTime,String fydm){
        return ajService.getBmslAvg(beginTime,endTime,dxwlzpMapper,fydm);
    }

    @Override
    public List<K2vModel> getAjSj(String fydm){
        return ajService.getAjSj(fydm,dxwlzpMapper);
    }

    @Override
    public void exportWord(HttpServletResponse response, String beginTime, String endTime, String fydm) {
        List<String> list=new ArrayList<>();
        list.add(ajService.getTwoBarsDes(fydm,beginTime,endTime,"1、案件收结统计",getAjSj(fydm)));
        list.add(ajService.getThreeBarsDes(fydm,beginTime,endTime,"2、案件审理统计",getAjsl(beginTime,endTime,fydm)));
        list.add(ajService.getThreeBarsDes(fydm,beginTime,endTime,"3、部门审理统计（立案数）",getBmslLa(beginTime,endTime,fydm)));
        list.add(ajService.getThreeBarsDes(fydm,beginTime,endTime,"4、部门审理统计（结案数）",getBmslJa(beginTime,endTime,fydm)));
        list.add(ajService.getThreeBarsDes1(fydm,beginTime,endTime,"5、部门审理统计（平均审理天数）",getBmslAvg(beginTime,endTime,fydm)));
        list.add(ajService.getThreeBarsPerDes(fydm,beginTime,endTime,"6、被告人特征统计（性别）",getBgXb(beginTime,endTime,fydm)));
        list.add(ajService.getThreeBarsPerDes(fydm,beginTime,endTime,"7、被告人特征统计（年龄）",getBgAge(beginTime,endTime,fydm)));
        list.add(ajService.getThreeBarsPerDes(fydm,beginTime,endTime,"8、被告人特征统计（文化程度）",getBgWhcd(beginTime,endTime,fydm)));
        list.add(ajService.getThreeBarsPerDes(fydm,beginTime,endTime,"9、被告人特征统计（身份）",getBgSf(beginTime,endTime,fydm)));
        list.add(ajService.getThreeBarsDes(fydm,beginTime,endTime,"10、被告规模",getBgNum(beginTime,endTime,fydm)));
        WordUtil.exportWord(response,"aj.docx","诈骗案件专题分析",list);
    }

    @Override
    public List<K3vModel> getBgNum(String beginTime,String endTime,String fydm){
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
        List<K2vModel> BgList=getBgSsdw(fydm,beginTime,endTime,dxwlzpMapper);
        List<K2vModel> BgList1=getBgSsdw(fydm,lastBegin,lastEnd,dxwlzpMapper);
        List<K2vModel> BgList2=getBgSsdw(fydm,lastMonth,beginTime,dxwlzpMapper);
        Map<String, Map<Integer,Integer>> map=new HashMap<>();
        Map<String,Map<Integer,Integer>> map1=new HashMap<>();
        Map<String,Map<Integer,Integer>> map2=new HashMap<>();
        for(K2vModel model:BgList){
            if(map.containsKey(model.getName())){
                map.get(model.getName()).put(model.getValue1(),map.get(model.getName()).getOrDefault(model.getValue1(),0)+1);
            }
            else{
                Map<Integer,Integer> ajMap=new HashMap<>();
                ajMap.put(model.getValue1(),1);
                map.put(model.getName(),ajMap);
            }
        }
        K3vModel k3vModel=new K3vModel("一个被告",0,0,0);
        K3vModel k3vModel1=new K3vModel("2个被告",0,0,0);
        K3vModel k3vModel2=new K3vModel("2个以上被告",0,0,0);
        list.add(k3vModel);list.add(k3vModel1);list.add(k3vModel2);
        for(String str:map.keySet()){
            for(Integer num:map.get(str).keySet()){
                if(map.get(str).get(num)==1){
                    k3vModel.setValue1(k3vModel.getValue1()+1);
                }
                else if(map.get(str).get(num)==2){
                    k3vModel1.setValue1(k3vModel1.getValue1()+1);
                }
                else{
                    k3vModel2.setValue1(k3vModel2.getValue1()+1);
                }
            }
        }
        for(K2vModel model:BgList1){
            if(map1.containsKey(model.getName())){
                map1.get(model.getName()).put(model.getValue1(),map1.get(model.getName()).getOrDefault(model.getValue1(),0)+1);
            }
            else{
                Map<Integer,Integer> ajMap=new HashMap<>();
                ajMap.put(model.getValue1(),1);
                map1.put(model.getName(),ajMap);
            }
        }
        for(String str:map1.keySet()){
            for(Integer num:map1.get(str).keySet()){
                if(map1.get(str).get(num)==1){
                    k3vModel.setValue2(k3vModel.getValue2()+1);
                }
                else if(map1.get(str).get(num)==2){
                    k3vModel1.setValue2(k3vModel1.getValue2()+1);
                }
                else{
                    k3vModel2.setValue2(k3vModel2.getValue2()+1);
                }
            }
        }
        for(K2vModel model:BgList2){
            if(map2.containsKey(model.getName())){
                map2.get(model.getName()).put(model.getValue1(),map2.get(model.getName()).getOrDefault(model.getValue1(),0)+1);
            }
            else{
                Map<Integer,Integer> ajMap=new HashMap<>();
                ajMap.put(model.getValue1(),1);
                map2.put(model.getName(),ajMap);
            }
        }
        for(String str:map2.keySet()){
            for(Integer num:map2.get(str).keySet()){
                if(map2.get(str).get(num)==1){
                    k3vModel.setValue3(k3vModel.getValue3()+1);
                }
                else if(map2.get(str).get(num)==2){
                    k3vModel1.setValue3(k3vModel1.getValue3()+1);
                }
                else{
                    k3vModel2.setValue3(k3vModel2.getValue3()+1);
                }
            }
        }
        return list;
    }



    public List<K2vModel> getBgSsdw(String fydm, String beginTime, String endTime, AjMapper ajMapper){
        String sjxx="";
        String sjxx2016="";
        if(fydm.equals("120242 22A")){
            sjxx=syjfMapper.getSJXXBH("DSRSSDW","DSR_JB",fydm);
            sjxx2016=syjfMapper.getSJXX2016BH("DSRSSDW","DSR_JB",fydm);
            if(sjxx2016!=null){
                List<K2vModelBH> k2vModelBHList = ajMapper.getAjBHAfter(beginTime, endTime);
                List<K2vModelBH> k2vModelBHList1 = ajMapper.getAjBHBefore(beginTime, endTime);
                return getBgdsrBH(ajService.getSjxx2016BH(sjxx,sjxx2016,fydm,beginTime,endTime,k2vModelBHList,k2vModelBHList1));
            }
            else {
                List<K2vModelBH> list = ajMapper.getAjBH(beginTime, endTime);
                return getBgdsrBH(ajService.getSjxxBH(sjxx,fydm,beginTime,endTime,list));
            }
        }
        else {
            sjxx = syjfMapper.getSJXX("DSRSSDW", "DSR_JB");
            sjxx2016 = syjfMapper.getSJXX2016("DSRSSDW", "DSR_JB");
            if(sjxx2016!=null){
                List<KvModel> list = ajMapper.getAjAfter(beginTime, endTime);
                List<KvModel> list1 = ajMapper.getAjBefore(beginTime, endTime);
                return getBgdsr(ajService.getSjxx2016(sjxx,sjxx2016,fydm,beginTime,endTime,list,list1));
            }
            else {
                List<KvModel> list = ajMapper.getAj(beginTime, endTime);
                return getBgdsr(ajService.getSjxx(sjxx,fydm,beginTime,endTime,list));
            }
        }

    }

    public List<K2vModel> getBgdsr(HashMap<String,List<Integer>> ajMap){
        List<K2vModel> modelList = new ArrayList<>();
        for (String str : ajMap.keySet()) {
            List<K2vModel> list1 = dxwlzpMapper.getBgDSR(str, ajMap.get(str));
            for (K2vModel model : list1) {
                modelList.add(model);
            }
        }
        return modelList;
    }

    public List<K2vModel> getBgdsrBH(HashMap<String,List<KvModel>> ajMap){
        List<K2vModel> modelList = new ArrayList<>();
        for (String str : ajMap.keySet()) {
            List<K2vModel> list1 = dxwlzpMapper.getBgDSRBH(str, ajMap.get(str));
            for (K2vModel model : list1) {
                modelList.add(model);
            }
        }
        return modelList;
    }


}
