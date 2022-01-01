package com.software.mapper;

import com.software.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Time;
import java.util.List;

@Mapper
public interface ShfzMapper extends AjMapper{

    //被告人关联罪名
    List<KvModel> getBgrGlzm(@Param("beginTime")String beginTime,@Param("endTime")String endTime,@Param("fydm")String fydm);
//    int getAjslLa(@Param("beginTime")String beginTime,@Param("endTime")String endTime,@Param("fydm")String fydm);
//    int getAjslJa(@Param("beginTime")String beginTime,@Param("endTime")String endTime,@Param("fydm")String fydm);
//    List<TimeModel> getAjslAvg(@Param("beginTime")String beginTime,@Param("endTime")String endTime,@Param("fydm")String fydm);
//    List<KvModel> getBmajslJa(@Param("fydm") String fydm,@Param("beginTime")String beginTime,@Param("endTime")String endTime);
//    List<KvModel> getBmajslLa(@Param("fydm") String fydm,@Param("beginTime")String beginTime,@Param("endTime")String endTime);
//    List<TimeModel> getBmajslavg(@Param("fydm") String fydm,@Param("beginTime")String beginTime,@Param("endTime")String endTime);


}

