package com.software.mapper;

import com.software.model.AjjbModel;
import com.software.model.K2vModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface IndexMapper {
    //首页的业务审判庭数据排名
    List<K2vModel> getYwtsj(@Param("beginTime")String beginTime, @Param("endTime")String endTime,@Param("fydm")String fydm);
    //首页的实时立案数据 当天的
    List<AjjbModel> getAjxxIntime(@Param("beginTime")String beginTime, @Param("endTime")String endTime,@Param("fydm")String fydm);
}
