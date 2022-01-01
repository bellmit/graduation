package com.software.service;


import com.software.model.AjjbModel;
import com.software.model.K2vModel;

import java.util.List;

//用于处理首页的数据展示
public interface IndexService {
    //得到业务庭的当月结案数和当月未结数 业务庭排名
   List<K2vModel> getYwtpm(String beginTime, String endTime,String fydm);

    //得到当天的实时数据并展示。每一小时更新一次。
   List<AjjbModel> getAjxxIntime(String fydm);


}
