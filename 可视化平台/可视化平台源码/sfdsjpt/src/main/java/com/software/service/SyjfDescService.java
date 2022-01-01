package com.software.service;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import com.software.model.K3vModel;
import com.software.model.KvModel;

import javax.swing.text.JTextComponent;
import java.util.List;

public interface SyjfDescService {
    public List<String> getSyjfAjCount(List<K3vModel> k3vModels);
    public List<String> getSyjfBmAjCount(List<K3vModel> k3vModels);
    public List<String> getYgrxbDesc(List<KvModel> kvModels);
    public List<String> getYgrsfDesc(List<KvModel> kvModels);
    public List<String> getYgrAgeDesc(List<KvModel> kvModels);
    public List<String> getResultDesc(List<KvModel> kvModels);
}
