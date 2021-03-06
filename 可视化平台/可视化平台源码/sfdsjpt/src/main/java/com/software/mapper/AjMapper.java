package com.software.mapper;

import com.software.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AjMapper {
    Integer getAjCount(@Param("beginTime")String beginTime, @Param("endTime")String endTime,@Param("fydm")String fydm);

    List<KvModel> getBgrxb(@Param("beginTime")String beginTime, @Param("endTime")String endTime,@Param("fydm")String fydm);

    List<BgrxxModel> getBgrAge(@Param("beginTime")String beginTime, @Param("endTime") String endTime,@Param("fydm")String fydm);

    List<KvModel> getBgrWhcd(@Param("beginTime")String beginTime,@Param("endTime")String endTime,@Param("fydm")String fydm);

    List<KvModel> getBgrsf(@Param("beginTime") String beginTime,@Param("endTime")String endTime,@Param("fydm")String fydm);

    List<KvModel> getYqtxlx(@Param("beginTime") String beginTime,@Param("endTime")String endTime,@Param("fydm")String fydm);
    int getAjslLa(@Param("beginTime")String beginTime,@Param("endTime")String endTime,@Param("fydm")String fydm);
    int getAjslJa(@Param("beginTime")String beginTime,@Param("endTime")String endTime,@Param("fydm")String fydm);
    List<TimeModel> getAjslAvg(@Param("beginTime")String beginTime, @Param("endTime")String endTime, @Param("fydm")String fydm);
    List<KvModel> getBmajslJa(@Param("fydm") String fydm,@Param("beginTime")String beginTime,@Param("endTime")String endTime);
    List<KvModel> getBmajslLa(@Param("fydm") String fydm,@Param("beginTime")String beginTime,@Param("endTime")String endTime);
    List<TimeModel> getBmajslavg(@Param("fydm") String fydm,@Param("beginTime")String beginTime,@Param("endTime")String endTime);
    List<KvModel> getDsrxb(@Param("beginTime")String beginTime,@Param("endTime")String endTime,@Param("fydm")String fydm);
    List<KvModel> getDsrAge(@Param("beginTime")String beginTime,@Param("endTime")String endTime,@Param("fydm")String fydm);
    List<KvModel> getDsrWhcd(@Param("beginTime")String beginTime,@Param("endTime")String endTime,@Param("fydm")String fydm);
    List<KvModel> getDsrSf(@Param("beginTime")String beginTime,@Param("endTime")String endTime,@Param("fydm")String fydm);
    List<KvModel> getSjCount(@Param("beginTime")String beginTime,@Param("endTime")String endTime,@Param("fydm")String fydm);

    List<KvModel> getYjCount(@Param("beginTime")String beginTime,@Param("endTime")String endTime,@Param("fydm")String fydm);

    List<KvModel> getAj(@Param("beginTime")String beginTime, @Param("endTime")String endTime);
    List<KvModel> getAjBefore(@Param("beginTime")String beginTime, @Param("endTime")String endTime);
    List<KvModel> getAjAfter(@Param("beginTime")String beginTime, @Param("endTime")String endTime);
    List<K2vModelBH> getAjBH(@Param("beginTime")String beginTime, @Param("endTime")String endTime);
    List<K2vModelBH> getAjBHBefore(@Param("beginTime")String beginTime, @Param("endTime")String endTime);
    List<K2vModelBH> getAjBHAfter(@Param("beginTime")String beginTime, @Param("endTime")String endTime);
}
