package com.software.service;

import com.software.model.KvModel;

import java.util.List;

public interface WxjszDescService {
    List<String> getTypeOfVehicle(List<KvModel> kvModels);
}
