package com.software.service.impl;

import com.software.mapper.WxjszMapper;
import com.software.model.KvModel;
import com.software.service.WxjszDescService;
import com.software.service.WxjszService;
import com.software.utils.DescriptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WxjszDescServiceImpl implements WxjszDescService {


    @Override
    public List<String> getTypeOfVehicle(List<KvModel> kvModels) {
        if (kvModels==null) return null;
        return DescriptionUtils.getMaxBlDesc(kvModels,"机动车类型");
    }
}
