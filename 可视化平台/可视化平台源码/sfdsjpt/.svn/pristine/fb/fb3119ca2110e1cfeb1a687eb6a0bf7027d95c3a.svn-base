package com.software.service;

import com.software.model.K2vModel;
import com.software.model.K3vModel;
import com.software.model.KvModel;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface CommonService {
    /**
     * 各年案件量
     */
    List<KvModel> getAjCount(String fydm);

    List<KvModel> getAreaDistribution(String beginTime,String endTime,String fydm);

    List<KvModel> getBgrxb(String beginTime,String endTime,String fydm);

    List<KvModel> getBgrsf(String beginTime,String endTime,String fydm);

    List<KvModel> getBgrWhcd(String beginTime,String endTime,String fydm);

    List<KvModel> getBrgage(String beginTime,String endTime,String fydm);


    List<K3vModel> getBgXb(String beginTime,String endTime,String fydm);

    List<K3vModel> getBgSf(String beginTime,String endTime,String fydm);

    List<K3vModel> getBgWhcd(String beginTime,String endTime,String fydm);

    List<K3vModel> getBgAge(String beginTime,String endTime,String fydm);

    /**
     * 有期徒刑量刑
     */
    List<KvModel> getYqtxlx(String beginTime,String endTime,String fydm);

    List<K3vModel> getAjsl(String beginTime, String endTime, String fydm);
    List<K3vModel> getBmslLa(String beginTime,String endTime,String fydm);
    List<K3vModel> getBmslJa(String beginTime,String endTime,String fydm);
    List<K3vModel> getBmslAvg(String beginTime,String endTime,String fydm);

    List<K2vModel> getAjSj(String fydm);

    void exportWord(HttpServletResponse response, String beginTime, String endTime, String fydm);
}
