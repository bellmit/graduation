package com.software.service.impl;

import com.software.model.KvModel;
import com.software.service.AjDescriptionService;
import com.software.service.XykzpDescService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XykzpDescServiceImpl implements XykzpDescService {
    @Autowired
    AjDescriptionService ajd;


    /**
     * 当年月案件量
     * @return
     */
    @Override
    public List<String> getAjMonthCount(List<KvModel> kvModels) {
        if (kvModels==null) return null;
        return ajd.getAjCountDesc(kvModels);
    }
}
