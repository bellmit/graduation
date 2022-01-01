package com.software.service.impl;

import com.mysql.cj.protocol.ReadAheadInputStream;
import com.software.model.K2vModel;
import com.software.model.K3vModel;
import com.software.service.AjService;
import com.software.service.WxjszService;
import com.software.mapper.WxjszMapper;
import com.software.model.KvModel;
import com.software.utils.DateUtils;
import com.software.utils.WordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class WxjszServiceImpl implements WxjszService {
    @Autowired
    WxjszMapper wxjszMapper;

    @Autowired
    AjService ajService;
    @Override
    public List<KvModel> getAjCount(String fydm) {
        return ajService.getAjCount(wxjszMapper,fydm);
    }

    @Override
    public List<KvModel> getAreaDistribution(String beginTime, String endTime, String fydm) {
        return ajService.getAreaDistribution(beginTime,endTime,fydm,wxjszMapper);
    }

    @Override
    public List<KvModel> getBgrxb(String beginTime, String endTime, String fydm) {
        return ajService.getBgrxb(beginTime,endTime,wxjszMapper,fydm);
    }

    @Override
    public List<KvModel> getBgrsf(String beginTime, String endTime, String fydm) {
        return ajService.getBgrsf(beginTime,endTime,wxjszMapper,fydm);
    }

    @Override
    public List<KvModel> getBgrWhcd(String beginTime, String endTime, String fydm) {
        return ajService.getBgrWhcd(beginTime,endTime,wxjszMapper,fydm);
    }

    @Override
    public List<KvModel> getBrgage(String beginTime, String endTime, String fydm) {
        return ajService.getBrgage(beginTime,endTime,wxjszMapper,fydm);
    }

    @Override
    public List<K3vModel> getBgXb(String beginTime, String endTime, String fydm) {
        return ajService.getBgXb(beginTime,endTime,wxjszMapper,fydm);
    }

    @Override
    public List<K3vModel> getBgSf(String beginTime, String endTime, String fydm) {
        return null;
    }

    @Override
    public List<K3vModel> getBgWhcd(String beginTime, String endTime, String fydm) {
        return ajService.getBgWhcd(beginTime,endTime,wxjszMapper,fydm);
    }

    @Override
    public List<K3vModel> getBgAge(String beginTime, String endTime, String fydm) {
        return ajService.getBgAge(beginTime,endTime,wxjszMapper,fydm);
    }

    @Override
    public List<KvModel> getYqtxlx(String beginTime, String endTime, String fydm) {
        return ajService.getYqtxlx(beginTime,endTime,wxjszMapper,fydm);
    }

    @Override
    public List<KvModel> getTypeOfVehicle(String beginTime, String endTime, String fydm) {
        return wxjszMapper.getTypeOfVehicle(beginTime,endTime,fydm);
    }

    @Override
    public List<K3vModel> getAjsl(String beginTime, String endTime, String fydm){
        return ajService.getAjsl(beginTime,endTime,wxjszMapper,fydm);
    }
    @Override
    public List<K3vModel> getBmslLa(String beginTime, String endTime, String fydm){
        return ajService.getBmslLa(beginTime,endTime,wxjszMapper,fydm);
    }
    @Override
    public List<K3vModel> getBmslJa(String beginTime,String endTime,String fydm){
        return ajService.getBmslJa(beginTime,endTime,wxjszMapper,fydm);
    }
    @Override
    public List<K3vModel> getBmslAvg(String beginTime,String endTime,String fydm){
        return ajService.getBmslAvg(beginTime,endTime,wxjszMapper,fydm);
    }

    @Override
    public List<K2vModel> getAjSj(String fydm){
        List<K2vModel> res=new ArrayList<>();
        int curYear= DateUtils.getCurYear();
        String begin=(curYear-5)+"-01-01";
        String end=curYear+"-01-01";
        List<KvModel> list1=wxjszMapper.getSjCount(begin,end,fydm);
        List<KvModel> list2=wxjszMapper.getYjCount(begin,end,fydm);
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
    public void exportWord(HttpServletResponse response, String beginTime, String endTime, String fydm) {
        List<String> list=new ArrayList<>();
        list.add(ajService.getTwoBarsDes(fydm,beginTime,endTime,"1、案件收结统计",getAjSj(fydm)));
        list.add(ajService.getThreeBarsDes(fydm,beginTime,endTime,"2、案件审理统计",getAjsl(beginTime,endTime,fydm)));
        list.add(ajService.getThreeBarsDes(fydm,beginTime,endTime,"3、部门审理统计（立案数）",getBmslLa(beginTime,endTime,fydm)));
        list.add(ajService.getThreeBarsDes(fydm,beginTime,endTime,"4、部门审理统计（结案数）",getBmslJa(beginTime,endTime,fydm)));
        list.add(ajService.getThreeBarsDes1(fydm,beginTime,endTime,"5、部门审理统计（平均审理天数）",getBmslAvg(beginTime,endTime,fydm)));
        list.add(ajService.getBarDes1(fydm,beginTime,endTime,getAreaDistribution(beginTime,endTime,fydm),"6、案件地域分布"));
        list.add(ajService.getBarPerDes1(fydm,beginTime,endTime,getBgrWhcd(beginTime,endTime,fydm),"7、当事人特征（文化程度）"));
        list.add(ajService.getBarPerDes1(fydm,beginTime,endTime,getBgrsf(beginTime,endTime,fydm),"8、当事人特征（身份）"));
        list.add(ajService.getBarPerDes1(fydm,beginTime,endTime,getBgrxb(beginTime,endTime,fydm),"9、当事人特征（性别）"));
        list.add(ajService.getBarPerDes1(fydm,beginTime,endTime,getBrgage(beginTime,endTime,fydm),"10、当事人特征（年龄）"));
        list.add(ajService.getBarPerDes1(fydm,beginTime,endTime,getYqtxlx(beginTime,endTime,fydm),"11、当事人特征（量刑）"));
        list.add(ajService.getBarPerDes1(fydm,beginTime,endTime,getTypeOfVehicle(beginTime,endTime,fydm),"12、当事人特征（机动车类型）"));
        list.add(ajService.getThreeBarsPerDes(fydm,beginTime,endTime,"13、被告人特征统计（性别）",getBgXb(beginTime,endTime,fydm)));
        list.add(ajService.getThreeBarsPerDes(fydm,beginTime,endTime,"14、被告人特征统计（年龄）",getBgAge(beginTime,endTime,fydm)));
        list.add(ajService.getThreeBarsPerDes(fydm,beginTime,endTime,"15、被告人特征统计（文化程度）",getBgWhcd(beginTime,endTime,fydm)));
        WordUtil.exportWord(response,"aj.docx","道路交通案件专题分析",list);
    }
}
