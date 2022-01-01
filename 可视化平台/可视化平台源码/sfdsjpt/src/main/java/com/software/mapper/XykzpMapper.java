package com.software.mapper;

import com.software.model.KvModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface XykzpMapper extends AjMapper{
    List<KvModel>  getAjMonthCount(@Param("beginTime")String beginTime, @Param("endTime")String endTime,@Param("fydm")String fydm);
}
