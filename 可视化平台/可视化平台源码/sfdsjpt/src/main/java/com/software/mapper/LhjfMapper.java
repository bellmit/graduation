package com.software.mapper;

import com.software.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Mapper
public interface LhjfMapper extends AjMapper{

    List<ForDmmsModel> getForDmms(@Param("beginTime")String beginTime,@Param("endTime")String endTime,@Param("ay") String ay,@Param("fydm")String fydm);

    String getDmms(@Param("lbbh") String lbbh,@Param("dmbh")String dmbh,@Param("fydm")String fydm);

    ResultMapWithBlobs getSjxx(@Param("szb") String szb,@Param("szl")String szl);

    ResultMapWithBlobs getSjxx2016(@Param("szb") String szb,@Param("szl")String szl,@Param("fydm")String fydm);

    List<ResultMapWithBlobs> getSjxxList(@Param("szb") String szb, @Param("szl")String szl);

    List<ResultMapWithBlobs> getSjxx2016List(@Param("szb") String szb,@Param("szl")String szl);

    List<KvModel> getLhaj(@Param("beginTime")String beginTime, @Param("endTime")String endTime);
    List<KvModel> getLhajBefore(@Param("beginTime")String beginTime, @Param("endTime")String endTime);
    List<KvModel> getLhajAfter(@Param("beginTime")String beginTime, @Param("endTime")String endTime);
    List<K2vModelBH> getLhajBH(@Param("beginTime")String beginTime, @Param("endTime")String endTime);
    List<K2vModelBH> getLhajBHBefore(@Param("beginTime")String beginTime, @Param("endTime")String endTime);
    List<K2vModelBH> getLhajBHAfter(@Param("beginTime")String beginTime, @Param("endTime")String endTime);

}

