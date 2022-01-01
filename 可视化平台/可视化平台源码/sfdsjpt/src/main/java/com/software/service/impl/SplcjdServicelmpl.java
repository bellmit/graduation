package com.software.service.impl;

import com.software.mapper.LadsjMapper;
import com.software.mapper.SplcjdMapper;
import com.software.mapper.SyjfMapper;
import com.software.model.*;
import com.software.service.AjService;
import com.software.service.SplcjdService;
import com.software.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class SplcjdServicelmpl implements SplcjdService {
    @Autowired
    LadsjMapper ladsjMapper;
    @Autowired
    SyjfMapper syjfMapper;
    @Autowired
    SplcjdMapper splcjdMapper;
    @Autowired
    AjService ajService;

    @Override
    public List<KvModel> getDrla(String fydm){
        List<KvModel> list=new ArrayList<>();
        String dateDay=now();
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
    public List<KvModel> getDyla(String fydm){
        List<KvModel> list=new ArrayList<>();
        String dateDay=thisMonth();
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
    public List<KvModel> getDrfa(String fydm){
        List<KvModel> list=new ArrayList<>();
        String dateDay=now();
        String tomorrow=tomorrow();
        List<KvModel> res=splcjdMapper.getDrfa(dateDay,tomorrow,fydm);
        int countPC=splcjdMapper.getDrfaPC(dateDay,tomorrow,fydm);
        return classification(res,countPC);
    }

    @Override
    public List<KvModel> getDyfa(String fydm){
        List<KvModel> list=new ArrayList<>();
        String dateDay=thisMonth();
        String tomorrow=nextMonth();
        List<KvModel> res=splcjdMapper.getDrfa(dateDay,tomorrow,fydm);
        int countPC=splcjdMapper.getDrfaPC(dateDay,tomorrow,fydm);
        return classification(res,countPC);
    }

    @Override
    public List<KvModel> getDrkt(String fydm){
        List<KvModel> list=new ArrayList<>();
        String dateDay=now();
        String tomorrow=tomorrow();
        List<KvModel> res=splcjdMapper.getDrkt(dateDay,tomorrow,fydm);
        int countPC=splcjdMapper.getDrktPC(dateDay,tomorrow,fydm);
        return classification(res,countPC);
    }

    @Override
    public List<KvModel> getDykt(String fydm){
        List<KvModel> list=new ArrayList<>();
        String dateDay=thisMonth();
        String tomorrow=nextMonth();
        List<KvModel> res=splcjdMapper.getDrkt(dateDay,tomorrow,fydm);
        int countPC=splcjdMapper.getDrktPC(dateDay,tomorrow,fydm);
        return classification(res,countPC);
    }

    @Override
    public List<KvModel> getDrts(String fydm){
        List<KvModel> list=new ArrayList<>();
        String dateDay=now();
        String tomorrow=tomorrow();
        List<KvModel> res=splcjdMapper.getDrts(dateDay,tomorrow,fydm);
        int countPC=splcjdMapper.getDrtsPC(dateDay,tomorrow,fydm);
        return classification(res,countPC);
    }

    @Override
    public List<KvModel> getDyts(String fydm){
        List<KvModel> list=new ArrayList<>();
        String dateDay=thisMonth();
        String tomorrow=nextMonth();
        List<KvModel> res=splcjdMapper.getDrts(dateDay,tomorrow,fydm);
        int countPC=splcjdMapper.getDrtsPC(dateDay,tomorrow,fydm);
        return classification(res,countPC);
    }

    @Override
    public List<KvModel> getDrsx(String fydm){
        List<KvModel> list=new ArrayList<>();
        String dateDay=now();
        String tomorrow=tomorrow();
        List<KvModel> res=splcjdMapper.getDrsx(dateDay,tomorrow,fydm);
        int countPC=splcjdMapper.getDrsxPC(dateDay,tomorrow,fydm);
        return classification(res,countPC);
    }

    @Override
    public List<KvModel> getDysx(String fydm){
        List<KvModel> list=new ArrayList<>();
        String dateDay=thisMonth();
        String tomorrow=nextMonth();
        List<KvModel> res=splcjdMapper.getDrsx(dateDay,tomorrow,fydm);
        int countPC=splcjdMapper.getDrsxPC(dateDay,tomorrow,fydm);
        return classification(res,countPC);
    }

    @Override
    public List<KvModel> getDrbj(String fydm){
        List<KvModel> list=new ArrayList<>();
        String dateDay=now();
        String tomorrow=tomorrow();
        List<KvModel> res=splcjdMapper.getDrbj(dateDay,tomorrow,fydm);
        int countPC=splcjdMapper.getDrbjPC(dateDay,tomorrow,fydm);
        return classification(res,countPC);
    }

    @Override
    public List<KvModel> getDybj(String fydm){
        List<KvModel> list=new ArrayList<>();
        String dateDay=thisMonth();
        String tomorrow=nextMonth();
        List<KvModel> res=splcjdMapper.getDrbj(dateDay,tomorrow,fydm);
        int countPC=splcjdMapper.getDrbjPC(dateDay,tomorrow,fydm);
        return classification(res,countPC);
    }

    @Override
    public List<KvModel> getDrja(String fydm){
        List<KvModel> list=new ArrayList<>();
        String dateDay=now();
        String tomorrow=tomorrow();
        List<KvModel> res=splcjdMapper.getDrja(dateDay,tomorrow,fydm);
        int countPC=splcjdMapper.getDrjaPC(dateDay,tomorrow,fydm);
        return classification(res,countPC);
    }

    @Override
    public List<KvModel> getDyja(String fydm){
        List<KvModel> list=new ArrayList<>();
        String dateDay=thisMonth();
        String tomorrow=nextMonth();
        List<KvModel> res=splcjdMapper.getDrja(dateDay,tomorrow,fydm);
        int countPC=splcjdMapper.getDrjaPC(dateDay,tomorrow,fydm);
        return classification(res,countPC);
    }

    @Override
    public List<KvModel> getDrgd(String fydm){
        List<KvModel> list=new ArrayList<>();
        String dateDay=now();
        String tomorrow=tomorrow();
        List<KvModel> res=splcjdMapper.getDrgd(dateDay,tomorrow,fydm);
        int countPC=splcjdMapper.getDrgdPC(dateDay,tomorrow,fydm);
        return classification(res,countPC);
    }

    @Override
    public List<KvModel> getDygd(String fydm){
        List<KvModel> list=new ArrayList<>();
        String dateDay=thisMonth();
        String tomorrow=nextMonth();
        List<KvModel> res=splcjdMapper.getDrgd(dateDay,tomorrow,fydm);
        int countPC=splcjdMapper.getDrgdPC(dateDay,tomorrow,fydm);
        return classification(res,countPC);
    }

    @Override
    public List<KvModel> getDycqja(String fydm){
        List<KvModel> list=new ArrayList<>();
        String dateMonth=thisMonth();
        String endTime=nextMonth();
        List<DycqjaModel> res=splcjdMapper.getDycqja(dateMonth,endTime,fydm);
        int countJa=0;int countWja=0;int countCqja=0;int countCqwja=0;
        int count=0;
        for(DycqjaModel model:res){
            if(model==null){
                System.out.println(count++);
                continue;
            }
            int sx=0;
            int fjsx=0;
            if (model.getSx() == null) {
                sx=0;
            }
            else{
                sx=model.getSx();
            }
            if (model.getFjsx() == null) {
                fjsx=0;
            }
            else{
                fjsx=model.getFjsx();
            }
            if(model.getJarq()==null){
                countWja++;
                if(model.getLarq()!=null) {
                    int sjslts = (int) Math.abs(DateUtil.getDiffDays(model.getLarq(), new Date()));
                    if (sjslts - sx - fjsx > 0) {
                        countCqwja++;
                    }
                }
            }
            else{
                countJa++;
                if(model.getLarq()!=null) {
                    int sjslts = (int) Math.abs(DateUtil.getDiffDays(model.getLarq(), model.getJarq()));
                    if (sjslts - sx - fjsx > 0) {
                        countCqja++;
                    }
                }
            }

        }
        KvModel kvModel1=new KvModel("当月结案数",countJa);
        KvModel kvModel2=new KvModel("当月超期结案数",countCqja);
        KvModel kvModel3=new KvModel("当月未结案数",countWja);
        KvModel kvModel4=new KvModel("当月超期未结案数",countCqwja);
        list.add(kvModel1);list.add(kvModel2);list.add(kvModel3);list.add(kvModel4);
        return list;
    }

    @Override
    public List<AjjbModel> getDycqjaList(String fydm,String index){
        String beginTime=thisMonth();
        String endTime=nextMonth();
        List<AjjbModel> result=new ArrayList<>();
        List<AjjbModel> list=splcjdMapper.getDycqjaList(beginTime,endTime,fydm);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(index.equals("当月结案数")){
            for(AjjbModel model:list){
                if(model.getJarq()!=null){
                    result.add(model);
                }
            }
        }
        else if(index.equals("当月超期结案数")){
            for(AjjbModel model:list){
                int sx=0;
                int fjsx=0;
                if (model.getSx() == null) {
                    sx=0;
                }
                else{
                    sx=model.getSx();
                }
                if (model.getFjsx() == null) {
                    fjsx=0;
                }
                else{
                    fjsx=model.getFjsx();
                }
                if(model.getJarq()!=null){
                    if(model.getLarq()!=null) {
                        try{
                            int sjslts = (int) Math.abs(DateUtil.getDiffDays(format.parse(model.getLarq()), format.parse(model.getJarq())));
                            if (sjslts - sx - fjsx > 0) {
                                result.add(model);
                            }
                        }catch(ParseException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        else if(index.equals("当月未结案数")){
            for(AjjbModel model:list){
                if(model.getJarq()==null){
                    result.add(model);
                }
            }
        }
        else{
            for(AjjbModel model:list) {
                int sx = 0;
                int fjsx = 0;
                if (model.getSx() == null) {
                    sx = 0;
                } else {
                    sx = model.getSx();
                }
                if (model.getFjsx() == null) {
                    fjsx = 0;
                } else {
                    fjsx = model.getFjsx();
                }
                if (model.getJarq() == null) {
                    if (model.getLarq() != null) {

                        try {
                            int sjslts = 0;
                            sjslts = (int) Math.abs(DateUtil.getDiffDays(format.parse(model.getLarq()), new Date()));
                            if (sjslts - sx - fjsx > 0) {
                                result.add(model);
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        }
        return result;

    }

    @Override
    public List<CqwgdVOModel> getDycqgd(){
        List<CqwgdVOModel> list=new ArrayList<>();
        List<CqwgdModel> res=splcjdMapper.getDycqgd();
        SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        for(CqwgdModel model:res){
            if((int) Math.abs(DateUtil.getDiffDays(model.getJarq(), new Date()))>30){
                CqwgdVOModel voModel=new CqwgdVOModel(model.getAh(),model.getAjmc(),format.format(model.getJarq()));
                list.add(voModel);
            }
        }
        return list;
    }

    @Override
    public List<AjjbModel> getDrbjList(String fydm,String index){
        String beginTime=now();
        String endTime=tomorrow();
        List<AjjbModel> list=getBjAjByAJXZ(fydm,index,beginTime,endTime);
        return list;
    }

    @Override
    public List<AjjbModel> getDybjList(String fydm,String index){
        String beginTime=thisMonth();
        String endTime=nextMonth();
        List<AjjbModel> list=getBjAjByAJXZ(fydm,index,beginTime,endTime);
        return list;
    }

    @Override
    public List<AjjbModel> getDrfaList(String fydm,String index){
        String beginTime=now();
        String endTime=tomorrow();
        List<AjjbModel> list=getFaAjByAJXZ(fydm,index,beginTime,endTime);
        return list;
    }

    @Override
    public List<AjjbModel> getDyfaList(String fydm,String index){
        String beginTime=thisMonth();
        String endTime=nextMonth();
        List<AjjbModel> list=getFaAjByAJXZ(fydm,index,beginTime,endTime);
        return list;
    }

    @Override
    public List<AjjbModel> getDrktList(String fydm,String index){
        String beginTime=now();
        String endTime=tomorrow();
        List<AjjbModel> list=getKtAjByAJXZ(fydm,index,beginTime,endTime);
        return list;
    }

    @Override
    public List<AjjbModel> getDyktList(String fydm,String index){
        String beginTime=thisMonth();
        String endTime=nextMonth();
        List<AjjbModel> list=getKtAjByAJXZ(fydm,index,beginTime,endTime);
        return list;
    }

    @Override
    public List<AjjbModel> getDrtsList(String fydm,String index){
        String beginTime=now();
        String endTime=tomorrow();
        List<AjjbModel> list=getTsAjByAJXZ(fydm,index,beginTime,endTime);
        return list;
    }

    @Override
    public List<AjjbModel> getDytsList(String fydm,String index){
        String beginTime=thisMonth();
        String endTime=nextMonth();
        List<AjjbModel> list=getTsAjByAJXZ(fydm,index,beginTime,endTime);
        return list;
    }

    @Override
    public List<AjjbModel> getDrsxList(String fydm,String index){
        String beginTime=now();
        String endTime=tomorrow();
        List<AjjbModel> list=getSxAjByAJXZ(fydm,index,beginTime,endTime);
        return list;
    }

    @Override
    public List<AjjbModel> getDysxList(String fydm,String index){
        String beginTime=thisMonth();
        String endTime=nextMonth();
        List<AjjbModel> list=getSxAjByAJXZ(fydm,index,beginTime,endTime);
        return list;
    }

    @Override
    public List<AjjbModel> getDrjaList(String fydm,String index){
        String beginTime=now();
        String endTime=tomorrow();
        List<AjjbModel> list=getJaAjByAJXZ(fydm,index,beginTime,endTime);
        return list;
    }

    @Override
    public List<AjjbModel> getDyjaList(String fydm,String index){
        String beginTime=thisMonth();
        String endTime=nextMonth();
        List<AjjbModel> list=getJaAjByAJXZ(fydm,index,beginTime,endTime);
        return list;
    }

    @Override
    public List<AjjbModel> getDrgdList(String fydm,String index){
        String beginTime=now();
        String endTime=tomorrow();
        List<AjjbModel> list=getGdAjByAJXZ(fydm,index,beginTime,endTime);
        return list;
    }

    @Override
    public List<AjjbModel> getDygdList(String fydm,String index){
        String beginTime=thisMonth();
        String endTime=nextMonth();
        List<AjjbModel> list=getGdAjByAJXZ(fydm,index,beginTime,endTime);
        return list;
    }

    @Override
    public List<AjjbModel> getDrlaList(String fydm,String index){
        String beginTime=now();
        String endTime=tomorrow();
        List<AjjbModel> list=getLaAjByAJXZ(fydm,index,beginTime,endTime);
        return list;
    }

    @Override
    public List<AjjbModel> getDylaList(String fydm,String index){
        String beginTime=thisMonth();
        String endTime=nextMonth();
        List<AjjbModel> list=getLaAjByAJXZ(fydm,index,beginTime,endTime);
        return list;
    }

    @Override
    public void getDrbjListExcel(String fydm, String index, HttpServletResponse response){
        List<String> titleList= Arrays.asList("案号","案件名称","立案日期","结案日期","案件性质","审判程序");
        ExcelUtil.uploadExcelAboutUser(response,"aj.xlsx",titleList,getDrbjList(fydm,index));
    }

    @Override
    public void getDybjListExcel(String fydm, String index, HttpServletResponse response){
        List<String> titleList= Arrays.asList("案号","案件名称","立案日期","结案日期","案件性质","审判程序");
        ExcelUtil.uploadExcelAboutUser(response,"aj.xlsx",titleList,getDybjList(fydm,index));
    }

    @Override
    public void getDrlaListExcel(String fydm, String index, HttpServletResponse response){
        List<String> titleList= Arrays.asList("案号","案件名称","立案日期","结案日期","案件性质","审判程序");
        ExcelUtil.uploadExcelAboutUser(response,"aj.xlsx",titleList,getDrlaList(fydm,index));
    }

    @Override
    public void getDylaListExcel(String fydm, String index, HttpServletResponse response){
        List<String> titleList= Arrays.asList("案号","案件名称","立案日期","结案日期","案件性质","审判程序");
        ExcelUtil.uploadExcelAboutUser(response,"aj.xlsx",titleList,getDylaList(fydm,index));
    }

    @Override
    public void getDrfaListExcel(String fydm, String index, HttpServletResponse response){
        List<String> titleList= Arrays.asList("案号","案件名称","立案日期","结案日期","案件性质","审判程序");
        ExcelUtil.uploadExcelAboutUser(response,"aj.xlsx",titleList,getDrfaList(fydm,index));
    }

    @Override
    public void getDyfaListExcel(String fydm, String index, HttpServletResponse response){
        List<String> titleList= Arrays.asList("案号","案件名称","立案日期","结案日期","案件性质","审判程序");
        ExcelUtil.uploadExcelAboutUser(response,"aj.xlsx",titleList,getDyfaList(fydm,index));
    }

    @Override
    public void getDrktListExcel(String fydm, String index, HttpServletResponse response){
        List<String> titleList= Arrays.asList("案号","案件名称","立案日期","结案日期","案件性质","审判程序");
        ExcelUtil.uploadExcelAboutUser(response,"aj.xlsx",titleList,getDrktList(fydm,index));
    }

    @Override
    public void getDyktListExcel(String fydm, String index, HttpServletResponse response){
        List<String> titleList= Arrays.asList("案号","案件名称","立案日期","结案日期","案件性质","审判程序");
        ExcelUtil.uploadExcelAboutUser(response,"aj.xlsx",titleList,getDyktList(fydm,index));
    }

    @Override
    public void getDrtsListExcel(String fydm, String index, HttpServletResponse response){
        List<String> titleList= Arrays.asList("案号","案件名称","立案日期","结案日期","案件性质","审判程序");
        ExcelUtil.uploadExcelAboutUser(response,"aj.xlsx",titleList,getDrtsList(fydm,index));
    }

    @Override
    public void getDytsListExcel(String fydm, String index, HttpServletResponse response){
        List<String> titleList= Arrays.asList("案号","案件名称","立案日期","结案日期","案件性质","审判程序");
        ExcelUtil.uploadExcelAboutUser(response,"aj.xlsx",titleList,getDytsList(fydm,index));
    }

    @Override
    public void getDrsxListExcel(String fydm, String index, HttpServletResponse response){
        List<String> titleList= Arrays.asList("案号","案件名称","立案日期","结案日期","案件性质","审判程序");
        ExcelUtil.uploadExcelAboutUser(response,"aj.xlsx",titleList,getDrsxList(fydm,index));
    }

    @Override
    public void getDysxListExcel(String fydm, String index, HttpServletResponse response){
        List<String> titleList= Arrays.asList("案号","案件名称","立案日期","结案日期","案件性质","审判程序");
        ExcelUtil.uploadExcelAboutUser(response,"aj.xlsx",titleList,getDysxList(fydm,index));
    }

    @Override
    public void getDrjaListExcel(String fydm, String index, HttpServletResponse response){
        List<String> titleList= Arrays.asList("案号","案件名称","立案日期","结案日期","案件性质","审判程序");
        ExcelUtil.uploadExcelAboutUser(response,"aj.xlsx",titleList,getDrjaList(fydm,index));
    }

    @Override
    public void getDyjaListExcel(String fydm, String index, HttpServletResponse response){
        List<String> titleList= Arrays.asList("案号","案件名称","立案日期","结案日期","案件性质","审判程序");
        ExcelUtil.uploadExcelAboutUser(response,"aj.xlsx",titleList,getDyjaList(fydm,index));
    }

    @Override
    public void getDrgdListExcel(String fydm, String index, HttpServletResponse response){
        List<String> titleList= Arrays.asList("案号","案件名称","立案日期","结案日期","案件性质","审判程序");
        ExcelUtil.uploadExcelAboutUser(response,"aj.xlsx",titleList,getDrgdList(fydm,index));
    }

    @Override
    public void getDygdListExcel(String fydm, String index, HttpServletResponse response){
        List<String> titleList= Arrays.asList("案号","案件名称","立案日期","结案日期","案件性质","审判程序");
        ExcelUtil.uploadExcelAboutUser(response,"aj.xlsx",titleList,getDygdList(fydm,index));
    }

    @Override
    public void getDycqjaListExcel(String fydm, String index, HttpServletResponse response){
        List<String> titleList= Arrays.asList("案号","案件名称","立案日期","结案日期","案件性质","审判程序");
        ExcelUtil.uploadExcelAboutUser(response,"aj.xlsx",titleList,getDycqjaList(fydm,index));
    }

    @Override
    public void exportWord(HttpServletResponse response,String beginTime,String endTime,String fydm){
        List<String> list=new ArrayList<>();
        List<List<KvModel>> models=new ArrayList<>();
        List<KvModel> str=getDrla(fydm);
        List<KvModel> str1=getDyla(fydm);
        List<KvModel> str2=getDrfa(fydm);
        List<KvModel> str3=getDyfa(fydm);
        List<KvModel> str4=getDrkt(fydm);
        List<KvModel> str5=getDykt(fydm);
        List<KvModel> str6=getDrts(fydm);
        List<KvModel> str7=getDyts(fydm);
        List<KvModel> str8=getDrsx(fydm);
        List<KvModel> str9=getDysx(fydm);
        List<KvModel> str10=getDrbj(fydm);
        List<KvModel> str11=getDybj(fydm);
        List<KvModel> str12=getDrja(fydm);
        List<KvModel> str13=getDyja(fydm);
        List<KvModel> str14=getDrgd(fydm);
        List<KvModel> str15=getDygd(fydm);
        models.add(str);models.add(str1);models.add(str2);models.add(str3);models.add(str4);
        models.add(str5);models.add(str6);models.add(str7);models.add(str8);models.add(str9);
        models.add(str10);models.add(str11);models.add(str12);models.add(str13);models.add(str14);
        models.add(str15);
        List<String> name=Arrays.asList("当日立案统计","当月立案统计","当日分案统计","当月分案统计","当日开庭统计","当月开庭统计",
                "当日审限延长申请统计","当月审限延长申请统计","当日审限中止申请统计","当月审限中止申请统计","当日报结统计","当月报结统计",
                "当日结案统计","当月结案统计","当日归档统计","当月归档统计");
        list=addStr(name,models);
        String str16=ajService.getBarDes(fydm,getDycqja(fydm),"17、当月超期未结案（结案）统计");
        list.add(str16);
        WordUtil.exportWord(response,"aj.docx","审判流程监督",list);

    }

    public List<String> addStr(List<String> name,List<List<KvModel>> models){
        List<String> list=new ArrayList<>();
        int num=0;

        for(List<KvModel> list1:models){
            String str1="";
            int count=0;
            for(KvModel model:list1){
                str1+=model.getName()+"为"+model.getValue()+"件，";
                count=count+model.getValue();
            }
            str1=(num+1)+"、"+name.get(num++)+"\n    "+"案件总数为"+count+"件，"+str1;
            str1=str1.substring(0,str1.length()-1)+"。\n";
            list.add(str1);
        }
        return list;
    }

//    public List<AjjbModel> getList(String beginTime, String endTime, String fydm, String index, List<AjjbModel> list){
//        String sjxx= "";
//        String sjxx2016="";
//        List<AjjbModel> result=new ArrayList<>();
//        if(fydm.equals("120242 22A")){
//            List<K2vModelBH> k2vModelBHList=new ArrayList<>();
//            List<K2vModelBH> k2vModelBHList1=new ArrayList<>();
//            List<K2vModelBH> k2vModelBHList2=new ArrayList<>();
//            for(AjjbModel model:list){
//                K2vModelBH k2vModelBH = new K2vModelBH(model.getAjxz() + model.getSpcx(), model.getFydm(), model.getAjxh());
//                k2vModelBHList2.add(k2vModelBH);
//                if(model.getLarq()==null || Integer.parseInt(model.getLarq().substring(0,2))>=16) {
//                    k2vModelBHList.add(k2vModelBH);
//                }
//                else{
//                    k2vModelBHList1.add(k2vModelBH);
//                }
//            }
//            sjxx=syjfMapper.getSJXXBH("SPCX","PUB_AJ_JB",fydm);
//            sjxx2016=syjfMapper.getSJXX2016BH("SPCX","PUB_AJ_JB",fydm);
//            HashMap<String, List<KvModel>> ajMap = new HashMap<>();
//            if (sjxx2016 != null) {
//                ajMap = SjxxUtils.getSjxx2016BH(sjxx,sjxx2016, fydm, beginTime, endTime,k2vModelBHList,k2vModelBHList1);
//            } else {
//                ajMap = SjxxUtils.getSjxxBH(sjxx, fydm, beginTime, endTime,k2vModelBHList2);
//            }
//            for(String str:ajMap.keySet()){
//                List<AjjbModel> res=splcjdMapper.getAjBH(str,ajMap.get(str));
//                result.addAll(res);
//            }
//            if(index.equals("其他案件数")||index.substring(0,2).equals("当月")){
//                for(AjjbModel model:result){
//                    model.setAjxz(splcjdMapper.getAjxzBH(model.getAjxz(),model.getFydm()));
//                    if(model.getLarq()!=null){
//                        model.setLarq(model.getLarq().substring(0,19));
//                    }
//                    if(model.getJarq()!=null) {
//                        model.setJarq(model.getJarq().substring(0, 19));
//                    }
//                }
//            }
//            else{
//                for(AjjbModel model:result){
//                    model.setAjxz(index.substring(0,2));
//                    if(model.getLarq()!=null){
//                        model.setLarq(model.getLarq().substring(0,19));
//                    }
//                    if(model.getJarq()!=null) {
//                        model.setJarq(model.getJarq().substring(0, 19));
//                    }
//                }
//            }
//            return result;
//        }
//        else{
//            List<KvModel> kvModelList=new ArrayList<>();
//            List<KvModel> kvModelList1=new ArrayList<>();
//            List<KvModel> kvModelList2=new ArrayList<>();
//            for(AjjbModel model:list){
//                KvModel kvModel = new KvModel(model.getAjxz() + model.getSpcx(), model.getAjxh());
//                kvModelList2.add(kvModel);
//                if(model.getLarq()==null || Integer.parseInt(model.getLarq().substring(0,2))>=16) {
//                    kvModelList.add(kvModel);
//                }
//                else{
//                    kvModelList1.add(kvModel);
//                }
//            }
//            sjxx=syjfMapper.getSJXX("SPCX","PUB_AJ_JB");
//            sjxx2016=syjfMapper.getSJXX2016("SPCX","PUB_AJ_JB");
//            HashMap<String, List<Integer>> ajMap = new HashMap<>();
//            if (sjxx2016 != null) {
//                ajMap = SjxxUtils.getSjxx2016(sjxx,sjxx2016, fydm, beginTime, endTime,kvModelList,kvModelList1);
//            } else {
//                ajMap = SjxxUtils.getSjxx(sjxx, fydm, beginTime, endTime,kvModelList2);
//            }
//            for(String str:ajMap.keySet()){
//                List<AjjbModel> res=splcjdMapper.getAj(str,ajMap.get(str));
//                result.addAll(res);
//            }
//            if(index.equals("其他案件数")||index.substring(0,2).equals("当月")){
//                for(AjjbModel model:result){
//                    model.setAjxz(splcjdMapper.getAjxz(model.getAjxz()));
//                    if(model.getLarq()!=null){
//                        model.setLarq(model.getLarq().substring(0,19));
//                    }
//                    if(model.getJarq()!=null) {
//                        model.setJarq(model.getJarq().substring(0, 19));
//                    }
//                }
//            }
//            else{
//                for(AjjbModel model:result){
//                    model.setAjxz(index.substring(0,2));
//                    if(model.getLarq()!=null){
//                        model.setLarq(model.getLarq().substring(0,19));
//                    }
//                    if(model.getJarq()!=null) {
//                        model.setJarq(model.getJarq().substring(0, 19));
//                    }
//                }
//            }
//            return result;
//
//        }
//    }

    public List<AjjbModel> getBjAjByAJXZ(String fydm,String index,String beginTime,String endTime){
        if(index.equals("刑事案件数")){
            return splcjdMapper.getBjXsList(beginTime,endTime,fydm);
        }
        if(index.equals("民事案件数")){
            return splcjdMapper.getBjMsList(beginTime,endTime,fydm);
        }
        if(index.equals("行政案件数")){
            return splcjdMapper.getBjXzList(beginTime,endTime,fydm);
        }
        if(index.equals("执行案件数")){
            return splcjdMapper.getBjZxList(beginTime,endTime,fydm);
        }
        if(index.equals("赔偿案件数")){
            return splcjdMapper.getBjPcList(beginTime,endTime,fydm);
        }
        if(index.equals("其他案件数")){
            return splcjdMapper.getBjQtList(beginTime,endTime,fydm);
        }
        return new ArrayList<>();
    }

    public List<AjjbModel> getFaAjByAJXZ(String fydm,String index,String beginTime,String endTime){
        if(index.equals("刑事案件数")){
            return splcjdMapper.getFaXsList(beginTime,endTime,fydm);
        }
        if(index.equals("民事案件数")){
            return splcjdMapper.getFaMsList(beginTime,endTime,fydm);
        }
        if(index.equals("行政案件数")){
            return splcjdMapper.getFaXzList(beginTime,endTime,fydm);
        }
        if(index.equals("执行案件数")){
            return splcjdMapper.getFaZxList(beginTime,endTime,fydm);
        }
        if(index.equals("赔偿案件数")){
            return splcjdMapper.getFaPcList(beginTime,endTime,fydm);
        }
        if(index.equals("其他案件数")){
            return splcjdMapper.getFaQtList(beginTime,endTime,fydm);
        }
        return new ArrayList<>();
    }

    public List<AjjbModel> getKtAjByAJXZ(String fydm,String index,String beginTime,String endTime){
        if(index.equals("刑事案件数")){
            return splcjdMapper.getKtXsList(beginTime,endTime,fydm);
        }
        if(index.equals("民事案件数")){
            return splcjdMapper.getKtMsList(beginTime,endTime,fydm);
        }
        if(index.equals("行政案件数")){
            return splcjdMapper.getKtXzList(beginTime,endTime,fydm);
        }
        if(index.equals("执行案件数")){
            return splcjdMapper.getKtZxList(beginTime,endTime,fydm);
        }
        if(index.equals("赔偿案件数")){
            return splcjdMapper.getKtPcList(beginTime,endTime,fydm);
        }
        if(index.equals("其他案件数")){
            return splcjdMapper.getKtQtList(beginTime,endTime,fydm);
        }
        return new ArrayList<>();
    }

    public List<AjjbModel> getTsAjByAJXZ(String fydm,String index,String beginTime,String endTime){
        if(index.equals("刑事案件数")){
            return splcjdMapper.getTsXsList(beginTime,endTime,fydm);
        }
        if(index.equals("民事案件数")){
            return splcjdMapper.getTsMsList(beginTime,endTime,fydm);
        }
        if(index.equals("行政案件数")){
            return splcjdMapper.getTsXzList(beginTime,endTime,fydm);
        }
        if(index.equals("执行案件数")){
            return splcjdMapper.getTsZxList(beginTime,endTime,fydm);
        }
        if(index.equals("赔偿案件数")){
            return splcjdMapper.getTsPcList(beginTime,endTime,fydm);
        }
        if(index.equals("其他案件数")){
            return splcjdMapper.getTsQtList(beginTime,endTime,fydm);
        }
        return new ArrayList<>();
    }

    public List<AjjbModel> getSxAjByAJXZ(String fydm,String index,String beginTime,String endTime){
        if(index.equals("刑事案件数")){
            return splcjdMapper.getSxXsList(beginTime,endTime,fydm);
        }
        if(index.equals("民事案件数")){
            return splcjdMapper.getSxMsList(beginTime,endTime,fydm);
        }
        if(index.equals("行政案件数")){
            return splcjdMapper.getSxXzList(beginTime,endTime,fydm);
        }
        if(index.equals("执行案件数")){
            return splcjdMapper.getSxZxList(beginTime,endTime,fydm);
        }
        if(index.equals("赔偿案件数")){
            return splcjdMapper.getSxPcList(beginTime,endTime,fydm);
        }
        if(index.equals("其他案件数")){
            return splcjdMapper.getSxQtList(beginTime,endTime,fydm);
        }
        return new ArrayList<>();
    }
    public List<AjjbModel> getJaAjByAJXZ(String fydm,String index,String beginTime,String endTime){
        if(index.equals("刑事案件数")){
            return splcjdMapper.getJaXsList(beginTime,endTime,fydm);
        }
        if(index.equals("民事案件数")){
            return splcjdMapper.getJaMsList(beginTime,endTime,fydm);
        }
        if(index.equals("行政案件数")){
            return splcjdMapper.getJaXzList(beginTime,endTime,fydm);
        }
        if(index.equals("执行案件数")){
            return splcjdMapper.getFaZxList(beginTime,endTime,fydm);
        }
        if(index.equals("赔偿案件数")){
            return splcjdMapper.getJaPcList(beginTime,endTime,fydm);
        }
        if(index.equals("其他案件数")){
            return splcjdMapper.getJaQtList(beginTime,endTime,fydm);
        }
        return new ArrayList<>();
    }

    public List<AjjbModel> getGdAjByAJXZ(String fydm,String index,String beginTime,String endTime){
        if(index.equals("刑事案件数")){
            return splcjdMapper.getGdXsList(beginTime,endTime,fydm);
        }
        if(index.equals("民事案件数")){
            return splcjdMapper.getGdMsList(beginTime,endTime,fydm);
        }
        if(index.equals("行政案件数")){
            return splcjdMapper.getGdXzList(beginTime,endTime,fydm);
        }
        if(index.equals("执行案件数")){
            return splcjdMapper.getGdZxList(beginTime,endTime,fydm);
        }
        if(index.equals("赔偿案件数")){
            return splcjdMapper.getGdPcList(beginTime,endTime,fydm);
        }
        if(index.equals("其他案件数")){
            return splcjdMapper.getGdQtList(beginTime,endTime,fydm);
        }
        return new ArrayList<>();
    }

    public List<AjjbModel> getLaAjByAJXZ(String fydm,String index,String beginTime,String endTime){
        if(index.equals("刑事案件数")){
            return splcjdMapper.getLaXsList(beginTime,endTime,fydm);
        }
        if(index.equals("民事案件数")){
            return splcjdMapper.getLaMsList(beginTime,endTime,fydm);
        }
        if(index.equals("行政案件数")){
            return splcjdMapper.getLaXzList(beginTime,endTime,fydm);
        }
        if(index.equals("执行案件数")){
            return splcjdMapper.getLaZxList(beginTime,endTime,fydm);
        }
        if(index.equals("赔偿案件数")){
            return splcjdMapper.getLaPcList(beginTime,endTime,fydm);
        }
        if(index.equals("其他案件数")){
            return splcjdMapper.getLaQtList(beginTime,endTime,fydm);
        }
        return new ArrayList<>();
    }

    public List<KvModel> classification(List<KvModel> res,int countPC){
        List<KvModel> list=new ArrayList<>();
        int count=0;
        int countXZPC=0;
        KvModel kvModel1=new KvModel("刑事案件数",0);
        KvModel kvModel2=new KvModel("民事案件数",0);
        KvModel kvModel3=new KvModel("行政案件数",0);
        KvModel kvModel4=new KvModel("执行案件数",0);
        KvModel kvModel5=new KvModel("赔偿案件数",0);
        for(KvModel kvModel:res){
            if(kvModel.getName().equals("1")){
                kvModel1.setValue(kvModel.getValue());
            }
            else if(kvModel.getName().equals("2")){
                kvModel2.setValue(kvModel.getValue());
            }
            else if(kvModel.getName().equals("8")){
                kvModel4.setValue(kvModel.getValue());
            }
            else if(kvModel.getName().equals("6")||kvModel.getName().equals("7")){
                countXZPC=countXZPC+kvModel.getValue();
            }
            else{
                count=count+kvModel.getValue();
            }
        }
        kvModel5.setValue(countPC);
        kvModel3.setValue(countXZPC-countPC);
        KvModel kvModel6 = new KvModel("其他案件数", count);
        list.add(kvModel1);list.add(kvModel2);list.add(kvModel3);list.add(kvModel4);list.add(kvModel5);list.add(kvModel6);
        return list;
    }

    public String now(){
        LocalDateTime dateTime=LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateDay=dateTime.format(dateTimeFormatter);
        return dateDay;
    }

    public String thisMonth(){
        LocalDateTime dateTime=LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateDay=dateTime.format(dateTimeFormatter);
        return dateDay.substring(0,8)+"01";
    }
    public String nextMonth(){
        String dateMonth=thisMonth();
        int nextMonth= Integer.parseInt(dateMonth.substring(5,7))+1;
        String endTime="";
        if(nextMonth>=10&&nextMonth<=12){
            endTime=dateMonth.substring(0,5)+nextMonth+"-01";
        }
        else if(nextMonth<10){
            endTime=dateMonth.substring(0,5)+"0"+nextMonth+"-01";
        }
        else{
            int nextYear=DateUtils.getCurYear()+1;
            endTime=nextYear+"-01-01";
        }
        return endTime;
    }
    public String tomorrow(){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        String tomorrow=format.format(new Date(new Date().getTime()+86400000));
        return tomorrow;
    }
}
