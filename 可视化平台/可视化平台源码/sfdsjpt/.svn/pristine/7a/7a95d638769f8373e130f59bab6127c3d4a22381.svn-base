package com.software.service.impl;

import com.software.model.K2vModel;
import com.software.model.K3vModel;
import com.software.service.AjService;
import com.software.service.XykzpService;
import com.software.mapper.XykzpMapper;
import com.software.model.KvModel;
import com.software.utils.DateUtils;
import com.software.utils.WordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class XykzpServiceImpl implements XykzpService {
    @Autowired
    XykzpMapper xykzpMapper;
    @Autowired
    AjService ajService;
    @Override
    public List<KvModel> getAjCount(String fydm) {
        return ajService.getAjCount(xykzpMapper,fydm);
    }

    @Override
    public List<KvModel> getAreaDistribution(String beginTime, String endTime, String fydm) {
        return ajService.getAreaDistribution(beginTime,endTime,fydm,xykzpMapper);
    }

    @Override
    public List<KvModel> getBgrxb(String beginTime, String endTime, String fydm) {
        return ajService.getBgrxb(beginTime,endTime,xykzpMapper,fydm);
    }

    @Override
    public List<KvModel> getBgrsf(String beginTime, String endTime, String fydm) {
        return ajService.getBgrsf(beginTime,endTime,xykzpMapper,fydm);
    }

    @Override
    public List<KvModel> getBgrWhcd(String beginTime, String endTime, String fydm) {
        return ajService.getBgrWhcd(beginTime,endTime,xykzpMapper,fydm);
    }

    @Override
    public List<KvModel> getBrgage(String beginTime, String endTime, String fydm) {
        return ajService.getBrgage(beginTime,endTime,xykzpMapper,fydm);
    }

    @Override
    public List<K3vModel> getBgXb(String beginTime, String endTime, String fydm) {
        return ajService.getBgXb(beginTime,endTime,xykzpMapper,fydm);
    }

    @Override
    public List<K3vModel> getBgSf(String beginTime, String endTime, String fydm) {

        return ajService.getBgSf(beginTime,endTime,xykzpMapper,fydm);
    }

    @Override
    public List<K3vModel> getBgWhcd(String beginTime, String endTime, String fydm) {

        return ajService.getBgWhcd(beginTime,endTime,xykzpMapper,fydm);
    }

    @Override
    public List<K3vModel> getBgAge(String beginTime, String endTime, String fydm) {

        return ajService.getBgAge(beginTime,endTime,xykzpMapper,fydm);
    }

    @Override
    public List<KvModel> getYqtxlx(String beginTime, String endTime, String fydm) {
        return ajService.getYqtxlx(beginTime,endTime,xykzpMapper,fydm);
    }
    /**
     * 当年各月案件量统计
     */
    @Override
    public List<KvModel> getAjMonthCount(String fydm) {
        int year= DateUtils.getCurYear();
        Date first=DateUtils.getYearFirst(year);
        String firstDay=DateUtils.formatDate(first);
        String cur=DateUtils.formatDate(new Date());
        // 返回的月份为实际月份-1
        int month=DateUtils.getCurMonth();
        List<KvModel> list=new ArrayList<>(month+1);
        for(int i=0;i<=month;i++){
            list.add(new KvModel(i+1+"月",0));
        }
        List<KvModel> kvs=xykzpMapper.getAjMonthCount(firstDay,cur,fydm);
        kvs.stream().forEach((kv)->{
            int m=Integer.parseInt(kv.getName());
            list.get(m-1).setValue(kv.getValue());
        });
        boolean isNull=true;
        for (KvModel k:kvs){
            if (k.getValue()!=0){
                isNull=false;
                break;
            }
        }


        return isNull?null:list;
    }

    @Override
    public List<K3vModel> getAjsl(String beginTime, String endTime, String fydm){
        return ajService.getAjsl(beginTime,endTime,xykzpMapper,fydm);
    }
    @Override
    public List<K3vModel> getBmslLa(String beginTime, String endTime, String fydm){
        return ajService.getBmslLa(beginTime,endTime,xykzpMapper,fydm);
    }
    @Override
    public List<K3vModel> getBmslJa(String beginTime,String endTime,String fydm){
        return ajService.getBmslJa(beginTime,endTime,xykzpMapper,fydm);
    }
    @Override
    public List<K3vModel> getBmslAvg(String beginTime,String endTime,String fydm){
        return ajService.getBmslAvg(beginTime,endTime,xykzpMapper,fydm);
    }

    @Override
    public List<K2vModel> getAjSj(String fydm){
        return ajService.getAjSj(fydm,xykzpMapper);
    }

    @Override
    public void exportWord(HttpServletResponse response, String beginTime, String endTime, String fydm) {
        List<String> list=new ArrayList<>();
        list.add(ajService.getTwoBarsDes(fydm,beginTime,endTime,"1、案件收结统计",getAjSj(fydm)));
        list.add(ajService.getThreeBarsDes(fydm,beginTime,endTime,"2、案件审理统计",getAjsl(beginTime,endTime,fydm)));
        list.add(ajService.getThreeBarsDes(fydm,beginTime,endTime,"3、部门审理统计（立案数）",getBmslLa(beginTime,endTime,fydm)));
        list.add(ajService.getThreeBarsDes(fydm,beginTime,endTime,"4、部门审理统计（结案数）",getBmslJa(beginTime,endTime,fydm)));
        list.add(ajService.getThreeBarsDes1(fydm,beginTime,endTime,"5、部门审理统计（平均审理天数）",getBmslAvg(beginTime,endTime,fydm)));
        list.add(ajService.getBarDes(fydm,getAjMonthCount(fydm),"6、案件审结情况-当年月案件量"));
        list.add(ajService.getBarDes1(fydm,beginTime,endTime,getAreaDistribution(beginTime,endTime,fydm),"7、案件地域分布"));
        list.add(ajService.getBarPerDes1(fydm,beginTime,endTime,getBgrxb(beginTime,endTime,fydm),"8、当事人特征统计（性别）"));
        list.add(ajService.getBarPerDes1(fydm,beginTime,endTime,getBgrWhcd(beginTime,endTime,fydm),"9、当事人特征统计（文化程度）"));
        list.add(ajService.getBarPerDes1(fydm,beginTime,endTime,getBgrsf(beginTime,endTime,fydm),"10、当事人特征统计（身份）"));
        list.add(ajService.getBarPerDes1(fydm,beginTime,endTime,getBrgage(beginTime,endTime,fydm),"11、当事人特征统计（年龄）"));
        list.add(ajService.getBarPerDes1(fydm,beginTime,endTime,getYqtxlx(beginTime,endTime,fydm),"12、当事人特征统计（量刑）"));
        list.add(ajService.getThreeBarsPerDes(fydm,beginTime,endTime,"13、被告人特征统计（性别）",getBgXb(beginTime,endTime,fydm)));
        list.add(ajService.getThreeBarsPerDes(fydm,beginTime,endTime,"14、被告人特征统计（年龄）",getBgAge(beginTime,endTime,fydm)));
        list.add(ajService.getThreeBarsPerDes(fydm,beginTime,endTime,"15、被告人特征统计（文化程度）",getBgWhcd(beginTime,endTime,fydm)));
        list.add(ajService.getThreeBarsPerDes(fydm,beginTime,endTime,"16、被告人特征统计（身份）",getBgSf(beginTime,endTime,fydm)));
        WordUtil.exportWord(response,"aj.docx","信用卡诈骗案件专题分析",list);
    }
}
