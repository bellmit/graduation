package com.software.service;

import com.software.mapper.AjMapper;
import com.software.model.AjVO;
import com.software.model.K2vModel;
import com.software.model.K3vModel;
import com.software.model.KvModel;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface SyjfService {
    public List<K3vModel> getQyajsl(String fydm);
    public List<K3vModel> getBmajsl(String fydm,String beginTime,String endTime);
    public List<KvModel> getBgxb(String fydm,String beginTime,String endTime);
    public List<KvModel> getYgxb(String fydm,String beginTime,String endTime);
    public List<KvModel> getBgsf(String fydm,String beginTime,String endTime);
    public List<KvModel> getYgsf(String fydm,String beginTime,String endTime);
    public List<KvModel> getBgAge(String fydm,String beginTime,String endTime);
    public List<KvModel> getYgAge(String fydm,String beginTime,String endTime);
    public List<KvModel> getResult(String fydm,String beginTime,String endTime);
    public List<AjVO> getQyajJaList(String index,String fydm);
    public List<AjVO> getQyajLaList(String index,String fydm);
    public List<AjVO> getBmajJaList(String index,String fydm,String beginTime,String endTime);
    public List<AjVO> getBmajLaList(String index,String fydm,String beginTime,String endTime);

    public List<K3vModel> getAjsl(String beginTime, String endTime, String fydm);
    public List<K3vModel> getBmslLa(String beginTime, String endTime, String fydm);
    public List<K3vModel> getBmslJa(String beginTime,String endTime,String fydm);
    public List<K3vModel> getBmslAvg(String beginTime,String endTime,String fydm);
    public List<K2vModel> getAjSj(String fydm);
    List<K3vModel> getBgSf(String beginTime,String endTime,String fydm);
    List<K3vModel> getPjjg(String beginTime,String endTime,String fydm);
    void exportWord(HttpServletResponse response, String beginTime, String endTime,String fydm);

}
