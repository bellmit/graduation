package com.software.mapper;

import com.software.model.AyModel;
import com.software.model.DsrModel;
import com.software.model.K2vModel;
import com.software.model.KvModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ZscqqqMapper extends AjMapper{
    List<DsrModel> getDsrType(@Param("beginTime")String beginTime, @Param("endTime")String endTime,@Param("fydm")String fydm);

    List<AyModel> getAyType(@Param("fydm")String fydm);

    Integer getAjCountByAy(@Param("beginTime")String beginTime, @Param("endTime")String endTime, @Param("dmbh")String dmbh,@Param("fydm")String fydm);

    Integer getAjAvgDayByAy(@Param("beginTime")String beginTime, @Param("endTime")String endTime,@Param("dmbh") String dmbh,@Param("fydm")String fydm);

    List<Integer> getBgCount(@Param("beginTime")String beginTime, @Param("endTime")String endTime,@Param("fydm")String fydm);

    List<KvModel> getDsrDw(@Param("beginTime")String beginTime, @Param("endTime")String endTime,@Param("fydm")String fydm);

    List<KvModel> getDsrJg(@Param("beginTime")String beginTime, @Param("endTime")String endTime,@Param("fydm")String fydm);

    List<KvModel> getAy(@Param("beginTime")String beginTime, @Param("endTime")String endTime,@Param("fydm")String fydm);

    List<KvModel> getBgDw(@Param("dsrList") List<K2vModel> dsrList);
    List<KvModel> getBgDwBH(@Param("dsrList") List<K2vModel> dsrList);
}
