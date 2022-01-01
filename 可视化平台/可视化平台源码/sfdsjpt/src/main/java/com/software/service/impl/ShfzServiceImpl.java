package com.software.service.impl;


import com.software.model.*;
import com.software.service.AjService;
import com.software.service.ShfzService;
import com.software.mapper.ShfzMapper;
import com.software.utils.DateUtil;
import com.software.utils.WordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;

//涉黑犯罪
@Service
public class ShfzServiceImpl implements ShfzService {
    @Autowired
    ShfzMapper shfzMapper;
    @Autowired
    AjService ajService;

    @Override
    public List<KvModel> getAjCount(String fydm) {
        return ajService.getAjCount(shfzMapper,fydm);
    }

    @Override
    public List<KvModel> getAreaDistribution(String beginTime, String endTime, String fydm) {
        return ajService.getAreaDistribution(beginTime,endTime,fydm,shfzMapper);
    }

    @Override
    public List<K3vModel> getDsrXb(String beginTime, String endTime, String fydm) {
        return ajService.getDsrXb(beginTime,endTime,shfzMapper,fydm);
    }

    @Override
    public List<K3vModel> getDsrSf(String beginTime, String endTime, String fydm) {
        return ajService.getDsrSf(beginTime,endTime,shfzMapper,fydm);
    }

    @Override
    public List<K3vModel> getDsrWhcd(String beginTime, String endTime, String fydm) {
        return ajService.getDsrWhcd(beginTime,endTime,shfzMapper,fydm);
    }

    @Override
    public List<KvModel> getYqtxlx(String beginTime, String endTime, String fydm) {
        return ajService.getYqtxlx(beginTime,endTime,shfzMapper,fydm);
    }
    @Override
    public List<KvModel>  getBgrGlzm(String beginTime, String endTime, String fydm){
        List<KvModel> list= shfzMapper.getBgrGlzm(beginTime,endTime,fydm);
        if (list.size()<=6){
            return list;
        }else{
            //降序排序
            list=list.stream().sorted((a,b)-> b.getValue()-a.getValue()).collect(Collectors.toList());

            //排序后取前6 剩下的归于其他
            int sum=0;
            for (int i=5;i<list.size();i++){
                sum+=list.get(i).getValue();
            }
            KvModel k=new KvModel();
            k.setName("其他");
            k.setValue(sum);
            list=list.stream().limit(6).collect(Collectors.toList());
            list.add(k);
            return list;
        }
    }
    @Override
    public List<K3vModel> getDsrAge(String beginTime, String endTime, String fydm){
        return ajService.getDsrAge(beginTime,endTime,shfzMapper,fydm);
    }

    @Override
    public List<K3vModel> getAjsl(String beginTime,String endTime,String fydm){
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
        result.add(new K3vModel("立案数",shfzMapper.getAjslLa(beginTime,endTime,fydm),shfzMapper.getAjslLa(lastBegin,lastEnd,fydm),shfzMapper.getAjslLa(lastMonth,beginTime,fydm)));
        result.add(new K3vModel("结案数",shfzMapper.getAjslJa(beginTime,endTime,fydm),shfzMapper.getAjslJa(lastBegin,lastEnd,fydm),shfzMapper.getAjslJa(lastMonth,beginTime,fydm)));
        List<TimeModel> list=shfzMapper.getAjslAvg(beginTime,endTime,fydm);

        int count=0;
        for(TimeModel model:list){
            count=count+(int) Math.abs(DateUtil.getDiffDays(model.getLarq(),model.getJarq()));
        }
        int average=0;
        if(list.size()!=0){
            average=count/list.size();
        }


        List<TimeModel> list1=shfzMapper.getAjslAvg(lastBegin,lastEnd,fydm);

        count=0;
        for(TimeModel model:list1){
            count=count+(int) Math.abs(DateUtil.getDiffDays(model.getLarq(),model.getJarq()));
        }
        int average1=0;
        if(list1.size()!=0){
            average1=count/list1.size();
        }
        List<TimeModel> list2=shfzMapper.getAjslAvg(lastMonth,beginTime,fydm);
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

    public List<K3vModel> getBmsl(String beginTime,String endTime,String fydm){
        List<K3vModel> list=new ArrayList<>();

        List<KvModel> laList=shfzMapper.getBmajslLa(fydm,beginTime,endTime);
        List<KvModel> jaList=shfzMapper.getBmajslJa(fydm,beginTime,endTime);
        List<TimeModel> slList=shfzMapper.getBmajslavg(fydm,beginTime,endTime);
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
    public List<K3vModel> getBmslLa(String beginTime,String endTime,String fydm){
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
        List<KvModel> laList=shfzMapper.getBmajslLa(fydm,beginTime,endTime);
        List<KvModel> laList1=shfzMapper.getBmajslLa(fydm,lastBegin,lastEnd);
        List<KvModel> laList2=shfzMapper.getBmajslLa(fydm,lastMonth,beginTime);
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
    public List<K3vModel> getBmslJa(String beginTime,String endTime,String fydm){
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
        List<KvModel> laList=shfzMapper.getBmajslJa(fydm,beginTime,endTime);
        List<KvModel> laList1=shfzMapper.getBmajslJa(fydm,lastBegin,lastEnd);
        List<KvModel> laList2=shfzMapper.getBmajslJa(fydm,lastMonth,beginTime);
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
    public List<K3vModel> getBmslAvg(String beginTime,String endTime,String fydm){
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
        List<TimeModel> laList=shfzMapper.getBmajslavg(fydm,beginTime,endTime);
        List<TimeModel> laList1=shfzMapper.getBmajslavg(fydm,lastBegin,lastEnd);
        List<TimeModel> laList2=shfzMapper.getBmajslavg(fydm,lastMonth,beginTime);
        Map<String,Integer> map=new HashMap<>();
        boolean t;
        // 当期
        for(int i=0;i<laList.size();i++){
            t=true;
            // todo 利用set会不会好一点，重写k3vmodelk的equals方法
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
            map.put(laList.get(i).getYear(),map.getOrDefault(laList.get(i).getYear(),0)+1);
        }
        // 计算平均审理天数
        for(K3vModel model:list){
            model.setValue1(model.getValue1()/map.get(model.getName()));
        }
        map.clear();
        // 同比
        for(int i=0;i<laList1.size();i++){
            t=true;
            for(K3vModel k3vModel:list) {
                if(laList1.get(i).getYear().equals(k3vModel.getName())) {
                    k3vModel.setValue2(k3vModel.getValue2()+(int) Math.abs(DateUtil.getDiffDays(laList1.get(i).getLarq(),laList1.get(i).getJarq())));
                    t=false;
                    break;
                }
            }
            // 记录该部门处理的案件花的总时间
            if(t){
                K3vModel model=new K3vModel(laList1.get(i).getYear(),0,(int) Math.abs(DateUtil.getDiffDays(laList1.get(i).getLarq(),laList1.get(i).getJarq())),0);
                list.add(model);
            }
            // 记录该部门处理的案件数
            map.put(laList.get(i).getYear(),map.getOrDefault(laList.get(i).getYear(),0)+1);
        }
        for(K3vModel model:list){
            if(model.getValue2()>0) {
                model.setValue2(model.getValue2() / map.get(model.getName()));
            }
        }
        map.clear();
        // 环比
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
            map.put(laList.get(i).getYear(),map.getOrDefault(laList.get(i).getYear(),0)+1);
        }
        for(K3vModel model:list){
            if(model.getValue3()>0) {
                model.setValue3(model.getValue3() / map.get(model.getName()));
            }
        }
        return list;
    }
    @Override
    public List<KvModel> getBgrxb(String beginTime, String endTime,String fydm) {
        return null;
    }

    @Override
    public List<KvModel> getBgrsf(String beginTime, String endTime,String fydm) {
        return null;
    }

    @Override
    public List<KvModel> getBgrWhcd(String beginTime, String endTime,String fydm) {
        return null;
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
    public List<K2vModel> getAjSj(String fydm){
        return ajService.getAjSj(fydm,shfzMapper);
    }

    @Override
    public void exportWord(HttpServletResponse response, String beginTime, String endTime, String fydm) {
        List<String> list=new ArrayList<>();
        list.add(ajService.getTwoBarsDes(fydm,beginTime,endTime,"1、案件收结统计",getAjSj(fydm)));
        list.add(ajService.getThreeBarsDes(fydm,beginTime,endTime,"2、案件审理统计",getAjsl(beginTime,endTime,fydm)));
        list.add(ajService.getThreeBarsDes(fydm,beginTime,endTime,"3、部门审理统计（立案数）",getBmslLa(beginTime,endTime,fydm)));
        list.add(ajService.getThreeBarsDes(fydm,beginTime,endTime,"4、部门审理统计（结案数）",getBmslJa(beginTime,endTime,fydm)));
        list.add(ajService.getThreeBarsDes1(fydm,beginTime,endTime,"5、部门审理统计（平均审理天数）",getBmslAvg(beginTime,endTime,fydm)));
        list.add(ajService.getThreeBarsPerDes(fydm,beginTime,endTime,"6、当事人特征统计（性别）",getDsrXb(beginTime,endTime,fydm)));
        list.add(ajService.getThreeBarsPerDes(fydm,beginTime,endTime,"7、当事人特征统计（年龄）",getDsrAge(beginTime,endTime,fydm)));
        list.add(ajService.getThreeBarsPerDes(fydm,beginTime,endTime,"8、当事人特征统计（身份）",getDsrSf(beginTime,endTime,fydm)));
        list.add(ajService.getThreeBarsPerDes(fydm,beginTime,endTime,"9、当事人特征统计（文化程度）",getDsrWhcd(beginTime,endTime,fydm)));
        String str="10、案件特征（罪名）"+"\n    ";
        List<KvModel> list1=getBgrGlzm(beginTime,endTime,fydm);
        if(list1.size()!=0) {
            for (KvModel model : list1) {

                str += model.getName().replaceAll((char)12288+"","")+ "为" + model.getValue() + "件，";
            }
            str = str.substring(0, str.length() - 1) + "。\n";
        }
        list.add(str);
        list.add(ajService.getBarPerDes(fydm,getYqtxlx(beginTime,endTime,fydm),"11、案件特征（量刑）"));

        WordUtil.exportWord(response,"aj.docx","涉黑案件专题分析",list);
    }
}
