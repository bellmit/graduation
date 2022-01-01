package com.software.service;

import com.software.model.K2vModel;
import com.software.model.K3vModel;
import com.software.model.KvModel;

import java.util.List;

public interface ShfzDescService {
    List<String> getBgrGlzm(List<KvModel> kvModels);

    List<String> getBrgage(List<KvModel> kvModels);

    List<String> getAjslDesc(List<K3vModel> k3vModels);

    List<String> getBmslLa(List<K3vModel> k3vModels);
    List<String> getBmslJa(List<K3vModel> k3vModels);
    List<String> getBmslAvg(List<K3vModel> k3vModels);
}
