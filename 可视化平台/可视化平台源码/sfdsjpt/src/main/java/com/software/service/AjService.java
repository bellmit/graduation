package com.software.service;

import com.software.mapper.AjMapper;
import com.software.model.K2vModel;
import com.software.model.K2vModelBH;
import com.software.model.K3vModel;
import com.software.model.KvModel;

import java.util.HashMap;
import java.util.List;

public interface AjService {
    /**
     * 各年案件量
     */
    List<KvModel> getAjCount(AjMapper ajMapper,String fydm);

    List<KvModel> getAreaDistribution(String beginTime,String endTime,String fydm,AjMapper ajMapper);

    List<KvModel> getBgrxb(String beginTime,String endTime,AjMapper ajMapper,String fydm);

    List<KvModel> getBgrsf(String beginTime,String endTime,AjMapper ajMapper,String fydm);

    List<KvModel> getBgrWhcd(String beginTime,String endTime,AjMapper ajMapper,String fydm);

    List<KvModel> getBrgage(String beginTime,String endTime,AjMapper ajMapper,String fydm);

    List<K3vModel> getBgXb(String beginTime,String endTime,AjMapper ajMapper,String fydm);

    List<K3vModel> getBgSf(String beginTime,String endTime,AjMapper ajMapper,String fydm);

    List<K3vModel> getBgWhcd(String beginTime,String endTime,AjMapper ajMapper,String fydm);

    List<K3vModel> getBgAge(String beginTime,String endTime,AjMapper ajMapper,String fydm);

    /**
     * 有期徒刑量刑
     */
    List<KvModel> getYqtxlx(String beginTime,String endTime,AjMapper ajMapper,String fydm);


    List<K3vModel> getAjsl(String beginTime,String endTime,AjMapper ajMapper,String fydm);
    List<K3vModel> getBmslLa(String beginTime, String endTime,AjMapper ajMapper, String fydm);
    List<K3vModel> getBmslJa(String beginTime,String endTime,AjMapper ajMapper,String fydm);
    List<K3vModel> getBmslAvg(String beginTime,String endTime,AjMapper ajMapper,String fydm);
    List<K3vModel> getDsrXb(String beginTime,String endTime,AjMapper ajMapper,String fydm);
    List<K3vModel> getDsrSf(String beginTime,String endTime,AjMapper ajMapper,String fydm);
    List<K3vModel> getDsrAge(String beginTime,String endTime,AjMapper ajMapper,String fydm);
    List<K3vModel> getDsrWhcd(String beginTime,String endTime,AjMapper ajMapper,String fydm);
       HashMap<String,List<KvModel>> getSjxx2016BH(String sjxx1, String sjxx2016, String fydm, String beginTime, String endTime, List<K2vModelBH> k2vModelBHList, List<K2vModelBH> k2vModelBHList1);
    HashMap<String,List<Integer>> getSjxx2016(String sjxx1,String sjxx2016,String fydm,String beginTime,String endTime,List<KvModel> list,List<KvModel> list1);
    HashMap<String,List<KvModel>> getSjxxBH(String sjxx,String fydm,String beginTime,String endTime,List<K2vModelBH> list);
    HashMap<String,List<Integer>> getSjxx(String sjxx, String fydm, String beginTime, String endTime, List<KvModel> list);

    List<K2vModel> getAjSj(String fydm,AjMapper ajMapper);

    List<K2vModel> getBgSsdw(String fydm,String beginTime,String endTime,AjMapper ajMapper);
    String getBarDes(String fydm,List<KvModel> list1,String name);
    String getBarDes1(String fydm,String beginTime,String endTime,List<KvModel> list1,String name);
    String getBarPerDes(String fydm,List<KvModel> list1,String name);
    String getBarPerDes1(String fydm,String beginTime,String endTime,List<KvModel> list1,String name);
    String getTwoBarsDes(String fydm,String beginTime,String endTime,String name,List<K2vModel> models);
    String getThreeBarsDes(String fydm,String beginTime,String endTime,String name,List<K3vModel> models);
    String getThreeBarsPerDes(String fydm,String beginTime,String endTime,String name,List<K3vModel> models);
    String getThreeBarsDes1(String fydm,String beginTime,String endTime,String name,List<K3vModel> models);
}
