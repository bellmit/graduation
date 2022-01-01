package com.software.mapper;

import com.software.model.K2vModel;
import com.software.model.KvModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DxwlzpMapper extends AjMapper{
    List<K2vModel> getBgDSR(@Param("lbbh") String lbbh, @Param("ajList")List<Integer> ajList);
    List<K2vModel> getBgDSRBH(@Param("lbbh") String lbbh, @Param("ajList")List<KvModel> ajList);
}
