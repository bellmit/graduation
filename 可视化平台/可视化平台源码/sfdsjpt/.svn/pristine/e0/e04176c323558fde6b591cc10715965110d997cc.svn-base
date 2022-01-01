package com.software.mapper;

import com.software.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.ui.Model;

import java.util.List;

@Mapper
public interface SplcjdMapper {
    List<KvModel> getDrfa(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    int getDrfaPC(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    List<KvModel> getDrkt(@Param("beginTime")String beginTime, @Param("endTime")String endTime,@Param("fydm")String fydm);
    int getDrktPC(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    List<KvModel> getDrts(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    int getDrtsPC(@Param("beginTime")String beginTime, @Param("endTime")String endTime,@Param("fydm")String fydm);
    List<KvModel> getDrsx(@Param("beginTime")String beginTime, @Param("endTime")String endTime,@Param("fydm")String fydm);
    int getDrsxPC(@Param("beginTime")String beginTime, @Param("endTime")String endTime,@Param("fydm")String fydm);
    List<KvModel> getDrbj(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    int getDrbjPC(@Param("beginTime")String beginTime, @Param("endTime")String endTime, @Param("fydm")String fydm);
    List<KvModel> getDrja(@Param("beginTime")String beginTime, @Param("endTime")String endTime,@Param("fydm")String fydm);
    int getDrjaPC(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    List<KvModel> getDrgd(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    int getDrgdPC(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    List<DycqjaModel> getDycqja(@Param("beginTime")String beginTime, @Param("endTime") String endTime, @Param("fydm")String fydm);
    List<CqwgdModel> getDycqgd();
    List<AjjbModel> getDycqjaList(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    //报结
    List<AjjbModel> getBjXsList(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    List<AjjbModel> getBjMsList(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    List<AjjbModel> getBjZxList(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    List<AjjbModel> getBjPcList(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    List<AjjbModel> getBjXzList(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    List<AjjbModel> getBjQtList(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    //分案
    List<AjjbModel> getFaXsList(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    List<AjjbModel> getFaMsList(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    List<AjjbModel> getFaZxList(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    List<AjjbModel> getFaPcList(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    List<AjjbModel> getFaXzList(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    List<AjjbModel> getFaQtList(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    //开庭
    List<AjjbModel> getKtXsList(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    List<AjjbModel> getKtMsList(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    List<AjjbModel> getKtZxList(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    List<AjjbModel> getKtPcList(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    List<AjjbModel> getKtXzList(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    List<AjjbModel> getKtQtList(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    //庭审
    List<AjjbModel> getTsXsList(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    List<AjjbModel> getTsMsList(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    List<AjjbModel> getTsZxList(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    List<AjjbModel> getTsPcList(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    List<AjjbModel> getTsXzList(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    List<AjjbModel> getTsQtList(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    //审讯
    List<AjjbModel> getSxXsList(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    List<AjjbModel> getSxMsList(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    List<AjjbModel> getSxZxList(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    List<AjjbModel> getSxPcList(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    List<AjjbModel> getSxXzList(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    List<AjjbModel> getSxQtList(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    //结案
    List<AjjbModel> getJaXsList(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    List<AjjbModel> getJaMsList(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    List<AjjbModel> getJaZxList(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    List<AjjbModel> getJaPcList(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    List<AjjbModel> getJaXzList(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    List<AjjbModel> getJaQtList(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    //归档
    List<AjjbModel> getGdXsList(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    List<AjjbModel> getGdMsList(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    List<AjjbModel> getGdZxList(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    List<AjjbModel> getGdPcList(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    List<AjjbModel> getGdXzList(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    List<AjjbModel> getGdQtList(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    //立案
    List<AjjbModel> getLaXsList(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    List<AjjbModel> getLaMsList(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    List<AjjbModel> getLaZxList(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    List<AjjbModel> getLaPcList(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    List<AjjbModel> getLaXzList(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);
    List<AjjbModel> getLaQtList(@Param("beginTime")String beginTime,@Param("endTime")String endTime, @Param("fydm")String fydm);

    List<AjjbModel> getAjBH(@Param("lbbh") String lbbh, @Param("ajList")List<KvModel> ajList);
    List<AjjbModel> getAj(@Param("lbbh") String lbbh, @Param("ajList")List<Integer> ajList);
    String getAjxzBH(@Param("ajxz") String ajxz,@Param("fydm")String fydm);
    String getAjxz(@Param("ajxz") String ajxz);
}
