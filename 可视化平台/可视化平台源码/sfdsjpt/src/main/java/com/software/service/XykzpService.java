package com.software.service;

import com.software.model.KvModel;

import java.util.List;

public interface XykzpService extends CommonService{

    /**
     * 当年月案件量
     */
    List<KvModel> getAjMonthCount(String fydm);
}
