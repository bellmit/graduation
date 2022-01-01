package com.software.controller;

import com.software.service.IndexService;
import com.software.model.AjjbModel;
import com.software.model.K2vModel;
import com.software.model.ResultModel;
import com.software.utils.ResultModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class IndexController {
    @Autowired
    private IndexService indexService;

    /**
     * 获取业务庭排名
     * @param request
     * @return
     */
    @RequestMapping("/getYwtpm")
    public ResultModel wytpm(HttpServletRequest request) {
        // 获得所有审判庭已结案件数

        // 获得所有审判庭未结案件数

        // 相互累加排序取前五

        // 然后将前五审判庭的已结案件数和未接案件数返回前端

        String fydm= (String) request.getSession().getAttribute("fydm");
        List<K2vModel> list=indexService.getYwtpm("2021-01-01","2021-06-01",fydm);
        return ResultModelUtil.success(list,"","");
    }
    //得到收案实时信息
    @RequestMapping("/getAjxxIntime")
    public ResultModel ajxxIntime(HttpServletRequest request) {
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<AjjbModel> ajjbModels=indexService.getAjxxIntime(fydm);
        return ResultModelUtil.success(ajjbModels,"","");
    }
}
