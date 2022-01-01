package com.software.mapper;

import com.software.model.KvModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface WxjszMapper extends AjMapper{

    List<KvModel> getTypeOfVehicle(@Param("beginTime")String beginTime, @Param("endTime")String endTime,@Param("fydm")String fydm);
}
