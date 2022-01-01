package com.software.service;

import com.software.model.AjjbModel;
import com.software.model.K2vModel;
import com.software.model.KvModel;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface LadsjService{
    List<KvModel> getDjlasj(String fydm);
    List<KvModel> getDjlasjLx(String fydm,String beginTime,String endTime);
    List<KvModel> getDjladsrsj(String fydm);
    List<KvModel> getDjladsrsjLx(String fydm,String index);
    List<KvModel> getDrlasj(String fydm);
    List<KvModel> getDylasj(String fydm);
    List<KvModel> getDjdlasj(String fydm);
    List<KvModel> getDnlasj(String fydm);
    List<KvModel> getSqtjsj(String fydm);
    List<K2vModel> getDrlasjSp(String fydm);
    List<K2vModel> getDylasjSp(String fydm);
    List<K2vModel> getDjdlasjSp(String fydm);
    List<K2vModel> getDnlasjSp(String fydm);
    List<AjjbModel> getDjlasssj(String fydm,String beginTime,String endTime);
    List<AjjbModel> getLasssj(String fydm,String beginTime,String endTime);
    List<AjjbModel> getSqtjsssj(String fydm,String beginTime,String endTime);
    List<KvModel> getDjlalzsj(String fydm,String beginTime,String endTime);
    List<KvModel> getLayssj(String fydm,String beginTime,String endTime);
    void exportWord(HttpServletResponse response,String beginTime,String endTime,String fydm);
}
