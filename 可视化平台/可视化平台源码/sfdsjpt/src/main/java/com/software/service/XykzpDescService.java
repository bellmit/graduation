package com.software.service;

import com.software.model.KvModel;

import java.util.List;

public interface XykzpDescService {
    /**
     * 当年月案件量
     * @return
     */
    List<String> getAjMonthCount(List<KvModel> kvModels);
}
