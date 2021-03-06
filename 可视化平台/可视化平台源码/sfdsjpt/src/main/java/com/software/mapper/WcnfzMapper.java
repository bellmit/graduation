package com.software.mapper;

import com.software.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Mapper
@Primary
public interface WcnfzMapper extends AjMapper{
    //未成年人罪名分布
    List<KvModel> getMinorZmfb(@Param("beginTime")String beginTime,@Param("endTime")String endTime,@Param("fydm")String fydm);

    //未成年人年新收案件量
    int getMinorXsajCount(@Param("beginTime")String beginTime,@Param("endTime")String endTime,@Param("fydm")String fydm );
    //未成年人根据案由得到案件量
    int getMinorajCountByAy(@Param("beginTime")String beginTime,@Param("endTime")String endTime,@Param("ay")String ay,@Param("fydm")String fydm  );
    //未成年人已结案件量。这个也可以用于计算辖区内未成年人犯罪情况对应省案件量分布
    int getMinorYjajCount(@Param("beginTime")String beginTime,@Param("endTime")String endTime,@Param("fydm")String fydm );

    //未成年人被告性别
    List<KvModel> getMinorBgrxb(@Param("beginTime")String beginTime,@Param("endTime")String endTime,@Param("fydm")String fydm);

    //未成年人犯罪各省外来案件
    List<KvModel> getMinorWlaj(@Param("beginTime")String beginTime,@Param("endTime")String endTime,@Param("fydm")String fydm);
    //未成年人犯罪各省外来案件
    List<KvModel> getMinorBgrAge(@Param("beginTime")String beginTime,@Param("endTime")String endTime,@Param("fydm")String fydm);
    //14-15周岁案件分布
    List<KvModel> getMinorSszmRange1(@Param("beginTime")String beginTime,@Param("endTime")String endTime,@Param("fydm")String fydm);
    //16-17周岁案件分布
    List<KvModel> getMinorSszmRange2(@Param("beginTime")String beginTime,@Param("endTime")String endTime,@Param("fydm")String fydm);
    List<K3vModel> yqtx(@Param("beginTime")String beginTime,@Param("endTime")String endTime,@Param("fydm")String fydm);
    //未成年人文化程度
    List<KvModel> getMinorWhcd(@Param("beginTime")String beginTime,@Param("endTime")String endTime,@Param("fydm")String fydm);
    //未成年人外来人口
    K2vModel getMinorWlrk(@Param("beginTime")String beginTime,@Param("endTime")String endTime,@Param("fydm")String fydm);
}

