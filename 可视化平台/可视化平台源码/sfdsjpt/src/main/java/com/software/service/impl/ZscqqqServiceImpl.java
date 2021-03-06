package com.software.service.impl;

import com.software.mapper.LhjfMapper;
import com.software.model.*;
import com.software.service.AjService;
import com.software.service.ZscqqqService;
import com.software.mapper.ZscqqqMapper;
import com.software.utils.WordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.x509.EDIPartyName;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ZscqqqServiceImpl implements ZscqqqService {
    @Autowired
    ZscqqqMapper zscqqqMapper;
    @Autowired
    AjService ajService;
    @Autowired
    LhjfMapper lhjfMapper;

    @Override
    public List<KvModel> getAjCount(String fydm) {
        return ajService.getAjCount(zscqqqMapper,fydm);
    }

    @Override
    public List<KvModel> getAreaDistribution(String beginTime, String endTime, String fydm) {
        return ajService.getAreaDistribution(beginTime,endTime,fydm,zscqqqMapper);
    }

    @Override
    public List<KvModel> getBgrxb(String beginTime, String endTime, String fydm) {
        return ajService.getBgrxb(beginTime,endTime,zscqqqMapper,fydm);
    }

    @Override
    public List<KvModel> getBgrsf(String beginTime, String endTime, String fydm) {
        return ajService.getBgrsf(beginTime,endTime,zscqqqMapper,fydm);
    }

    @Override
    public List<KvModel> getBgrWhcd(String beginTime, String endTime, String fydm) {
        return ajService.getBgrWhcd(beginTime,endTime,zscqqqMapper,fydm);
    }

    @Override
    public List<KvModel> getBrgage(String beginTime, String endTime, String fydm) {
        return ajService.getBrgage(beginTime,endTime,zscqqqMapper,fydm);
    }

    @Override
    public List<K3vModel> getBgXb(String beginTime, String endTime, String fydm) {
        return null;
    }

    @Override
    public List<K3vModel> getBgSf(String beginTime, String endTime, String fydm) {
        return null;
    }

    @Override
    public List<K3vModel> getBgWhcd(String beginTime, String endTime, String fydm) {
        return null;
    }

    @Override
    public List<K3vModel> getBgAge(String beginTime, String endTime, String fydm) {
        return null;
    }

    @Override
    public List<KvModel> getYqtxlx(String beginTime, String endTime, String fydm) {
        return ajService.getYqtxlx(beginTime,endTime,zscqqqMapper,fydm);
    }

    /**
     * ?????????????????????????????????????????????
     */
    @Override
    public List<KvModel> getAyDistribution(String beginTime, String endTime, String fydm) {
        List<KvModel> kvModels=new ArrayList<>();
        // ??????????????????????????????????????????????????????
        List<AyModel> list=zscqqqMapper.getAyType(fydm);
        // ????????????????????????????????????
        for(AyModel ayModel:list){
            if("1113070000".equals(ayModel.getDmbh())){
                continue;
            }
            Integer ajs=zscqqqMapper.getAjCountByAy(beginTime, endTime,ayModel.getDmbh(),fydm);
            if(ajs==null||ajs==0){
                continue;
            }
            KvModel kvModel=new KvModel(ayModel.getDmms(),ajs);
            kvModels.add(kvModel);
        }
        // ????????????
        return kvModels;
    }

    @Override
    public int[][] getDsrType(String beginTime, String endTime, String fydm) {
        // ??????????????????????????????
        // ????????????????????????????????????????????????
        List<DsrModel> dsrModels=zscqqqMapper.getDsrType(beginTime,endTime,fydm);
        // ?????????????????????
        Map<String,List<DsrModel>> bgMap=dsrModels.stream().collect(Collectors.groupingBy(DsrModel::getDsrSsdw));
        //          ?????? ??????
        // ??????
        // ??????
        // ????????????
        int[][] res=new int[3][2];
        // ??????
        List<DsrModel> ygList=bgMap.get("11");
        if(ygList!=null){
            ygList.forEach((bg)->{
                if("??????".equals(bg.getDsrLb())){
                    res[0][0]=bg.getValue();
                }else if("????????????".equals(bg.getDsrLb())){
                    res[2][0]=bg.getValue();
                }else if("??????".equals(bg.getDsrLb())) {
                    res[1][0]=bg.getValue();
                }
            });
        }

        // ??????
        List<DsrModel> bgList=bgMap.get("12");
        if(bgList!=null){
            bgList.forEach((yg)->{
                if("??????".equals(yg.getDsrLb())){
                    res[0][1]=yg.getValue();
                }else if("????????????".equals(yg.getDsrLb())){
                    res[2][1]=yg.getValue();
                }else if("??????".equals(yg.getDsrLb())) {
                    res[1][1]=yg.getValue();
                }
            });
        }
        return res;
    }

    @Override
    public List<KvModel> getDwNature(String beginTime, String endTime, String fydm) {
        List<KvModel> res=new ArrayList<>();
        // ????????????????????????dsr_xp_jg???????????????dsr_dw?????????????????????
        List<KvModel> dsrDw=zscqqqMapper.getDsrDw(beginTime,endTime,fydm);
        List<KvModel> dsrJg=zscqqqMapper.getDsrJg(beginTime,endTime,fydm);
        // ?????????????????????????????????
        // FRXZ
        String frxzSjxx=lhjfMapper.getSjxx2016List("DSR_DW","FRXZ").get(0).getName();
        if(dsrDw!=null){
            dsrDw.forEach((dw)->{
                if(dw.getName()!=null&&!"".equals(dw.getName().trim())){
                    String dmms=lhjfMapper.getDmms(frxzSjxx,dw.getName(),fydm);
                    KvModel kv=new KvModel(dmms,dw.getValue());
                    res.add(kv);
                }
            });
        }

        // XZJGXZ
        String xzjgxzSjxx=lhjfMapper.getSjxxList("DSR_XP_JG","XZJGXZ").get(0).getName();
        if(dsrJg!=null){
            dsrJg.forEach((jg)->{
                if(jg.getName()!=null&&!"".equals(jg.getName().trim())){
                    String dmms=lhjfMapper.getDmms(xzjgxzSjxx,jg.getName(),fydm);
                    KvModel kv=new KvModel(dmms,jg.getValue());
                    res.add(kv);
                }
            });
        }
        return  res;
    }

    @Override
    public List<KvModel> getBgCount(String beginTime, String endTime, String fydm) {
        // ??????????????????????????????????????????
        List<Integer> bgCountList=zscqqqMapper.getBgCount(beginTime,endTime,fydm);
        List<KvModel> kvModels=new ArrayList<>();
        kvModels.add(new KvModel("1???",0));
        kvModels.add(new KvModel("2???",0));
        kvModels.add(new KvModel("3?????????????????????????????????",0));
        kvModels.add(new KvModel("5??????????????????",0));
        if(bgCountList!=null){
            bgCountList.forEach((count)->{
                if(count==1){
                    kvModels.get(0).addValue(1);
                }else if(count==2){
                    kvModels.get(1).addValue(1);
                }else if(count>=3&&count<5){
                    kvModels.get(2).addValue(1);
                }else if(count>=5){
                    kvModels.get(3).addValue(1);
                }
            });
        }
        return kvModels;
    }

    /**
     * ?????????????????????????????????????????????
     */
    @Override
    public List<KvModel> getProcessingCycle(String beginTime, String endTime, String fydm) {
        List<KvModel> kvModels=new ArrayList<>();
        // ??????????????????????????????????????????????????????
        List<AyModel> list=zscqqqMapper.getAyType(fydm);
        // ????????????????????????????????????
        for(AyModel ayModel:list){
            if("1113070000".equals(ayModel.getDmbh())){
                continue;
            }
            Integer  avg=zscqqqMapper.getAjAvgDayByAy(beginTime,endTime,ayModel.getDmbh(),fydm);
            KvModel kvModel=new KvModel(ayModel.getDmms(),avg==null?0: avg);
            kvModels.add(kvModel);
        }
        // ????????????
        return kvModels;
    }

    @Override
    public List<K3vModel> getDsrSf(String beginTime, String endTime, String fydm) {
        return ajService.getDsrSf(beginTime,endTime,zscqqqMapper,fydm);
    }

    @Override
    public List<K3vModel> getAjsl(String beginTime, String endTime, String fydm){
        return ajService.getAjsl(beginTime,endTime,zscqqqMapper,fydm);
    }
    @Override
    public List<K3vModel> getBmslLa(String beginTime, String endTime, String fydm){
        return ajService.getBmslLa(beginTime,endTime,zscqqqMapper,fydm);
    }
    @Override
    public List<K3vModel> getBmslJa(String beginTime,String endTime,String fydm){
        return ajService.getBmslJa(beginTime,endTime,zscqqqMapper,fydm);
    }
    @Override
    public List<K3vModel> getBmslAvg(String beginTime,String endTime,String fydm){
        return ajService.getBmslAvg(beginTime,endTime,zscqqqMapper,fydm);
    }

    @Override
    public List<K2vModel> getAjSj(String fydm){
        return ajService.getAjSj(fydm,zscqqqMapper);
    }

    @Override
    public void exportWord(HttpServletResponse response, String beginTime, String endTime, String fydm) {
        List<String> list=new ArrayList<>();
        list.add(ajService.getTwoBarsDes(fydm,beginTime,endTime,"1?????????????????????",getAjSj(fydm)));
        list.add(ajService.getThreeBarsDes(fydm,beginTime,endTime,"2?????????????????????",getAjsl(beginTime,endTime,fydm)));
        list.add(ajService.getThreeBarsDes(fydm,beginTime,endTime,"3????????????????????????????????????",getBmslLa(beginTime,endTime,fydm)));
        list.add(ajService.getThreeBarsDes(fydm,beginTime,endTime,"4????????????????????????????????????",getBmslJa(beginTime,endTime,fydm)));
        list.add(ajService.getThreeBarsDes1(fydm,beginTime,endTime,"5?????????????????????????????????????????????",getBmslAvg(beginTime,endTime,fydm)));
        list.add(ajService.getBarDes1(fydm,beginTime,endTime,getAyDistribution(beginTime,endTime,fydm),"6?????????????????????"));
        list.add(ajService.getBarDes1(fydm,beginTime,endTime,getAreaDistribution(beginTime,endTime,fydm),"7?????????????????????"));
        int[][] dsrType=getDsrType(beginTime,endTime,fydm);
        String str="8????????????????????????????????????"+"\n    ";
        str=str+"???????????????"+dsrType[0][0]+"????????????"+dsrType[1][0]+"??????????????????"+dsrType[2][0]+"??????\n";
        str=str+"    ???????????????"+dsrType[0][1]+"????????????"+dsrType[1][1]+"??????????????????"+dsrType[2][1]+"??????\n";
        list.add(str);
        list.add(ajService.getBarPerDes1(fydm,beginTime,endTime,getDwNature(beginTime,endTime,fydm),"9????????????????????????????????????"));
        list.add(ajService.getBarPerDes1(fydm,beginTime,endTime,getBgrsf(beginTime,endTime,fydm),"10????????????????????????????????????"));
        list.add(ajService.getThreeBarsPerDes(fydm,beginTime,endTime,"11????????????????????????????????????",getDsrSf(beginTime,endTime,fydm)));
        list.add(ajService.getBarDes1(fydm,beginTime,endTime,getBgCount(beginTime,endTime,fydm),"12????????????????????????????????????"));
        list.add(ajService.getBarDes1(fydm,beginTime,endTime,getProcessingCycle(beginTime,endTime,fydm),"13???????????????????????????????????????"));
        list.add(ajService.getThreeBarsPerDes(fydm,beginTime,endTime,"14???????????????????????????",getBgDw(beginTime, endTime,fydm)));
        list.add(ajService.getThreeBarsPerDes(fydm,beginTime,endTime,"15?????????????????????",getBgNum(beginTime,endTime,fydm)));
        list.add(ajService.getThreeBarsDes(fydm,beginTime,endTime,"16?????????????????????????????????",getAy(beginTime,endTime,fydm)));
        WordUtil.exportWord(response,"aj.docx","????????????????????????????????????",list);
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
        List<K2vModel> BgList=ajService.getBgSsdw(fydm,beginTime,endTime,zscqqqMapper);
        List<K2vModel> BgList1=ajService.getBgSsdw(fydm,lastBegin,lastEnd,zscqqqMapper);
        List<K2vModel> BgList2=ajService.getBgSsdw(fydm,lastMonth,beginTime,zscqqqMapper);
        Map<String,Map<Integer,Integer>> map=new HashMap<>();
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
        K3vModel k3vModel=new K3vModel("????????????",0,0,0);
        K3vModel k3vModel1=new K3vModel("2?????????",0,0,0);
        K3vModel k3vModel2=new K3vModel("2???????????????",0,0,0);
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

    @Override
    public List<K3vModel> getAy(String beginTime, String endTime, String fydm) {
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
        List<KvModel> kvModels=zscqqqMapper.getAy(beginTime,endTime,fydm);
        List<KvModel> kvModels1=zscqqqMapper.getAy(lastBegin,lastEnd,fydm);
        List<KvModel> kvModels2=zscqqqMapper.getAy(lastMonth,beginTime,fydm);
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
    public List<K3vModel> getBgDw(String beginTime,String endTime,String fydm){
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
        List<K2vModel> BgList=ajService.getBgSsdw(fydm,beginTime,endTime,zscqqqMapper);
        List<K2vModel> BgList1=ajService.getBgSsdw(fydm,lastBegin,lastEnd,zscqqqMapper);
        List<K2vModel> BgList2=ajService.getBgSsdw(fydm,lastMonth,beginTime,zscqqqMapper);
        List<KvModel> kvModels=new ArrayList<>();
        List<KvModel> kvModels1=new ArrayList<>();
        List<KvModel> kvModels2=new ArrayList<>();
        if(fydm.equals("120242 22A")){
            if(BgList.size()>0) {
                kvModels = zscqqqMapper.getBgDwBH(BgList);
            }
            if(BgList1.size()>0){
                kvModels1 = zscqqqMapper.getBgDwBH(BgList1);
            }
            if(BgList2.size()>0){
                kvModels2=zscqqqMapper.getBgDwBH(BgList2);
            }
        }
        else {
            if(BgList.size()>0) {
                kvModels = zscqqqMapper.getBgDw(BgList);
            }
            if(BgList1.size()>0){
                kvModels1 = zscqqqMapper.getBgDw(BgList1);
            }
            if(BgList2.size()>0){
                kvModels2=zscqqqMapper.getBgDw(BgList2);
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
}
