package com.software.service;

import com.software.model.K3vModel;

import java.util.List;

public interface DxwlzpService extends CommonService{
    List<K3vModel> getBgNum(String beginTime, String endTime, String fydm);
}
