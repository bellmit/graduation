package com.software.mapper;

import com.software.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface SyjfMapper extends AjMapper{
    List<KvModel> getQyajslJa(@Param("fydm") String fydm,@Param("beginTime")String beginTime,@Param("endTime")String endTime);
    List<KvModel> getQyajslLa(@Param("fydm") String fydm,@Param("beginTime")String beginTime,@Param("endTime")String endTime);
    List<TimeModel> getQyajslavg(@Param("fydm") String fydm,@Param("beginTime")String beginTime,@Param("endTime")String endTime);

    String getSJXX(@Param("szl")String szl,@Param("szb")String szb);
    String getSJXXBH(@Param("szl")String szl,@Param("szb")String szb,@Param("fydm")String fydm);
    String getSJXX2016(@Param("szl")String szl,@Param("szb")String szb);
    String getSJXX2016BH(@Param("szl")String szl,@Param("szb")String szb,@Param("fydm")String fydm);
    List<KvModel> getSyaj(@Param("beginTime")String beginTime, @Param("endTime")String endTime);
    List<KvModel> getSyajBefore(@Param("beginTime")String beginTime, @Param("endTime")String endTime);
    List<KvModel> getSyajAfter(@Param("beginTime")String beginTime, @Param("endTime")String endTime);
    List<K2vModelBH> getSyajBH(@Param("beginTime")String beginTime, @Param("endTime")String endTime);
    List<K2vModelBH> getSyajBHBefore(@Param("beginTime")String beginTime, @Param("endTime")String endTime);
    List<K2vModelBH> getSyajBHAfter(@Param("beginTime")String beginTime, @Param("endTime")String endTime);
    List<K2vModel> getBgDSR(@Param("lbbh") String lbbh, @Param("ajList")List<Integer> ajList);
    List<K2vModel> getYgDSR(@Param("lbbh") String lbbh, @Param("ajList")List<Integer> ajList);
    List<K2vModel> getBgDSRBH(@Param("lbbh") String lbbh, @Param("ajList")List<KvModel> ajList);
    List<K2vModel> getYgDSRBH(@Param("lbbh") String lbbh, @Param("ajList")List<KvModel> ajList);
    List<KvModel> getXb(@Param("dsrList") List<K2vModel> dsrList);
    List<KvModel> getSf(@Param("dsrList") List<K2vModel> dsrList);
    List<BgrxxModel> getAge(@Param("dsrList") List<K2vModel> dsrList);
    List<KvModel> getXbBH(@Param("dsrList") List<K2vModel> dsrList);
    List<KvModel> getSfBH(@Param("dsrList") List<K2vModel> dsrList);
    List<KvModel> getWhcd(@Param("dsrList") List<K2vModel> dsrList);
    List<KvModel> getWhcdBH(@Param("dsrList") List<K2vModel> dsrList);
    List<BgrxxModel> getAgeBH(@Param("dsrList") List<K2vModel> dsrList);
    List<KvModel> getResult(@Param("lbbh") String lbbh, @Param("ajList")List<Integer> ajList);
    List<KvModel> getResultBH(@Param("lbbh") String lbbh, @Param("ajList")List<KvModel> ajList);
    String getXgdm(@Param("lbbh")String lbbh,@Param("ajxh")int ajxh);
    String getXgdmBH(@Param("lbbh")String lbbh,@Param("ajxh")int ajxh,@Param("fydm")String fydm);
    String getAjxzSpcx(@Param("ajxh")int ajxh,@Param("fydm")String fydm);
    String getAjxzSpcxSpcxdz(@Param("ajxh")int ajxh,@Param("fydm")String fydm);
    List<AjModel> getQyajJaList(@Param("fydm") String fydm,@Param("beginTime")String beginTime,@Param("endTime")String endTime);
    List<AjModel> getQyajLaList(@Param("fydm") String fydm,@Param("beginTime")String beginTime,@Param("endTime")String endTime);
    List<AjModel> getBmajJaList(@Param("dmms")String dmms,@Param("fydm") String fydm,@Param("beginTime")String beginTime,@Param("endTime")String endTime);
    List<AjModel> getBmajLaList(@Param("dmms")String dmms,@Param("fydm") String fydm,@Param("beginTime")String beginTime,@Param("endTime")String endTime);
}
