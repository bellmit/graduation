package com.software.service.impl;

import com.software.mapper.SyjfMapper;
import com.software.model.*;
import com.software.service.AjService;
import com.software.service.LhjfService;
import com.software.mapper.LhjfMapper;
import com.software.utils.DateUtils;
import com.software.utils.StringUtils;
import com.software.utils.WordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class LhjfServiceImpl implements LhjfService {
    @Autowired
    private LhjfMapper lhjfMapper;
    @Autowired
    SyjfMapper syjfMapper;
    @Autowired
    AjService ajService;
    @Override
    public List<K3vModel> getLhjfPjjg(String beginTime, String endTime,String fydm){
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
        // 当前这个月
        List<KvModel> laList=getResult(beginTime,endTime,fydm);
        // 去年这个月
        List<KvModel> laList1=getResult(lastBegin,lastEnd,fydm);
        // 上个月
        List<KvModel> laList2=getResult(lastMonth,beginTime,fydm);
        for(int i=0;i<laList.size();i++){
            K3vModel model=new K3vModel(laList.get(i).getName(),laList.get(i).getValue(),0,0);
            list.add(model);
        }
        boolean t;
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

    public List<KvModel> getResult(String beginTime, String endTime,String fydm){
        String sjxx="";
        String sjxx2016="";
        if(fydm.equals("120242 22A")){
            sjxx=syjfMapper.getSJXXBH("JAFS","PUB_AJ_JB",fydm);
            sjxx2016=syjfMapper.getSJXX2016BH("JAFS","PUB_AJ_JB",fydm);
            HashMap<String, List<KvModel>> ajMap;
            if (sjxx2016 != null) {
                ajMap = ajService.getSjxx2016BH(sjxx,sjxx2016, fydm, beginTime, endTime,lhjfMapper.getLhajBHAfter(beginTime,endTime),lhjfMapper.getLhajBHBefore(beginTime,endTime));
            } else {
                List<K2vModelBH> list = lhjfMapper.getLhajBH(beginTime, endTime);
                ajMap = ajService.getSjxxBH(sjxx, fydm, beginTime, endTime,list);
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
            HashMap<String, List<Integer>> ajMap;
            if (sjxx2016 != null) {
                ajMap = ajService.getSjxx2016(sjxx,sjxx2016, fydm, beginTime, endTime,lhjfMapper.getLhajAfter(beginTime,endTime),lhjfMapper.getLhajBefore(beginTime,endTime));
            } else {
                List<KvModel> list = lhjfMapper.getLhaj(beginTime, endTime);
                ajMap = ajService.getSjxx(sjxx, fydm, beginTime, endTime,list);
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
    public List<KvModel> getAjCount(String fydm) {
        return ajService.getAjCount(lhjfMapper,fydm);
    }

    @Override
    public List<KvModel> getAreaDistribution(String beginTime, String endTime, String fydm) {
        return ajService.getAreaDistribution(beginTime,endTime,fydm,lhjfMapper);
    }

    @Override
    public List<KvModel> getBgrxb(String beginTime, String endTime,String fydm) {
        return ajService.getBgrxb(beginTime,endTime,lhjfMapper,fydm);
    }

    @Override
    public List<KvModel> getBgrsf(String beginTime, String endTime,String fydm) {
        return ajService.getBgrsf(beginTime,endTime,lhjfMapper,fydm);
    }

    @Override
    public List<KvModel> getBgrWhcd(String beginTime, String endTime,String fydm) {
        return ajService.getBgrWhcd(beginTime,endTime,lhjfMapper,fydm);
    }

    @Override
    public List<KvModel> getBrgage(String beginTime, String endTime,String fydm) {
        return null;
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
    public List<KvModel> getYqtxlx(String beginTime, String endTime,String fydm) {
        return ajService.getYqtxlx(beginTime,endTime,lhjfMapper,fydm);
    }

    @Override
    public List<K3vModel> getDsrWhcd(String beginTime, String endTime, String fydm) {
        return ajService.getDsrWhcd(beginTime,endTime,lhjfMapper,fydm);
    }
    @Override
    public List<K3vModel> getDsrAge(String beginTime, String endTime, String fydm){
        return ajService.getDsrAge(beginTime,endTime,lhjfMapper,fydm);
    }
    @Override
    public List<K3vModel> getAjsl(String beginTime, String endTime, String fydm){
        return ajService.getAjsl(beginTime,endTime,lhjfMapper,fydm);
    }
    @Override
    public List<K3vModel> getBmslLa(String beginTime, String endTime, String fydm){
        return ajService.getBmslLa(beginTime,endTime,lhjfMapper,fydm);
    }
    @Override
    public List<K3vModel> getBmslJa(String beginTime,String endTime,String fydm){
        return ajService.getBmslJa(beginTime,endTime,lhjfMapper,fydm);
    }
    @Override
    public List<K3vModel> getBmslAvg(String beginTime,String endTime,String fydm){
        return ajService.getBmslAvg(beginTime,endTime,lhjfMapper,fydm);
    }

    @Override
    public List<K2vModel> getAjSj(String fydm){
        return ajService.getAjSj(fydm,lhjfMapper);
    }

    @Override
    public void exportWord(HttpServletResponse response, String beginTime, String endTime, String fydm) {
        List<String> list=new ArrayList<>();
        list.add(ajService.getTwoBarsDes(fydm,beginTime,endTime,"1、案件收结统计",getAjSj(fydm)));
        list.add(ajService.getThreeBarsDes(fydm,beginTime,endTime,"2、案件审理统计",getAjsl(beginTime,endTime,fydm)));
        list.add(ajService.getThreeBarsDes(fydm,beginTime,endTime,"3、部门审理统计（立案数）",getBmslLa(beginTime,endTime,fydm)));
        list.add(ajService.getThreeBarsDes(fydm,beginTime,endTime,"4、部门审理统计（结案数）",getBmslJa(beginTime,endTime,fydm)));
        list.add(ajService.getThreeBarsDes1(fydm,beginTime,endTime,"5、部门审理统计（平均审理天数）",getBmslAvg(beginTime,endTime,fydm)));
        list.add(ajService.getBarDes1(fydm,beginTime,endTime,getAreaDistribution(beginTime,endTime,fydm),"6、案件地域分布"));
        list.add(ajService.getThreeBarsPerDes(fydm,beginTime,endTime,"6、当事人特征统计（年龄）",getDsrAge(beginTime,endTime,fydm)));
        list.add(ajService.getThreeBarsPerDes(fydm,beginTime,endTime,"7、当事人特征统计（文化程度）",getDsrWhcd(beginTime,endTime,fydm)));
        list.add(ajService.getThreeBarsPerDes(fydm,beginTime,endTime,"8、当事人特征统计（判决结果）",getLhjfPjjg(beginTime,endTime,fydm)));
        WordUtil.exportWord(response,"aj.docx","离婚案件专题分析",list);
    }
}
