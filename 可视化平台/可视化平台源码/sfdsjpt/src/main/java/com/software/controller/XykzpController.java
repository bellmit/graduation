package com.software.controller;

import com.software.model.*;
import com.software.service.AjDescriptionService;
import com.software.service.XykzpDescService;
import com.software.service.XykzpService;
import com.software.utils.ResultModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 信用卡诈骗罪接口
 */
@RestController
@RequestMapping("/xykzp")
public class XykzpController {
    @Autowired
    XykzpService xykzpService;
    @Autowired
    AjDescriptionService ads;

    @Autowired
    XykzpDescService xkd;

    /**
     * 各年案件量(近五年)
     */
    @RequestMapping("/getAjCount")
    public ResultModel getAjCount(HttpServletRequest request){
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<K2vModel> list=xykzpService.getAjSj(fydm);
        TwoBarModel barModel=new TwoBarModel();
        barModel.setName1("新收");
        barModel.setName2("已结");
        barModel.setK2vModelList(list);
        List<String> description=ads.getAjSj(list);
        return ResultModelUtil.success(barModel,"年案件量",description);
    }
    /**
     * 当年各月的案件量
     */
    @RequestMapping("/getAjMonthCount")
    public ResultModel getAjMonthCount(HttpServletRequest request,String beginTime,String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        // 目前写死获取近五年
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> kvModels=xykzpService.getAjMonthCount(fydm);
        List<String> description=xkd.getAjMonthCount(kvModels);
        return ResultModelUtil.success(kvModels,"月案件量",description);
    }
    /**
     * 案件地域分布（天津地区）
     */
    @RequestMapping("/getAreaDistribution")
    public ResultModel getAreaDistribution(HttpServletRequest request,String beginTime,String endTime){
        String fydm= (String) request.getSession().getAttribute("fydm");
        return ResultModelUtil.success(xykzpService.getAreaDistribution(beginTime,endTime,fydm),"","");
    }
    @RequestMapping("/getBgrWhcd")
    public ResultModel getBgrWhcd(HttpServletRequest request,String beginTime,String endTime) {
        String fydm= (String) request.getSession().getAttribute("fydm");

        List<KvModel> kvModels=xykzpService.getBgrWhcd(beginTime,endTime,fydm);
        List<String> description=ads.getBgrWhcdDesc(kvModels);
        return ResultModelUtil.success(kvModels,"当事人文化程度",description);
    }
    @RequestMapping("/getBgrsf")
    public ResultModel getBgrsf(HttpServletRequest request,String beginTime,String endTime) {
        String fydm= (String) request.getSession().getAttribute("fydm");

        List<KvModel> kvModels=xykzpService.getBgrsf(beginTime,endTime,fydm);
        List<String> description=ads.getBgrsfDesc(kvModels);
        return ResultModelUtil.success(kvModels,"当事人身份",description);
    }
    @RequestMapping("/getBgrxb")
    public ResultModel getBgrxb(HttpServletRequest request,String beginTime,String endTime) {
        String fydm= (String) request.getSession().getAttribute("fydm");

        List<KvModel> kvModels=xykzpService.getBgrxb(beginTime,endTime,fydm);
        List<String> description=ads.getDsrxbDesc(kvModels);
        return ResultModelUtil.success(kvModels,"当事人性别",description);
    }
    @RequestMapping("/getBgrAge")
    public ResultModel getBgrAge(HttpServletRequest request,String beginTime,String endTime) {
        String fydm= (String) request.getSession().getAttribute("fydm");

        List<KvModel> kvModels=xykzpService.getBrgage(beginTime,endTime,fydm);
        List<String> description=ads.getBgrAgeDesc(kvModels);
        return ResultModelUtil.success(kvModels,"年龄分布",description);
    }
    @RequestMapping("/getYqtxlx")
    public ResultModel getYqtxlx(HttpServletRequest request,String beginTime,String endTime){
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> kvModels=xykzpService.getYqtxlx(beginTime,endTime,fydm);
        List<String> description=ads.getAjCountDesc(kvModels);
        return ResultModelUtil.success(kvModels,"有期徒刑量刑",description);
    }

    /**
     * 案件审理情况
     */
    @RequestMapping("/getAjsl")
    public ResultModel getAjsl(HttpServletRequest request,String beginTime,String endTime){
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<K3vModel> kvModels=xykzpService.getAjsl(beginTime,endTime,fydm);
        ThreeBarModel barModel=new ThreeBarModel();
        barModel.setName1("当期");
        barModel.setName2("同比");
        barModel.setName3("环比");
        barModel.setK3vModelList(kvModels);
        List<String> description=ads.getAjslDesc(kvModels);
        return ResultModelUtil.success(barModel,"案件审理变化",description);
    }
    /**
     * 部门审理情况(立案)
     */
    @RequestMapping("/getBmslLa")
    public ResultModel getBmslLa(HttpServletRequest request,String beginTime,String endTime){
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<K3vModel> k3vModels=xykzpService.getBmslLa(beginTime,endTime,fydm);
        ThreeBarModel model=new ThreeBarModel();
        model.setName1("当期");
        model.setName2("同比");
        model.setName3("环比");
        model.setK3vModelList(k3vModels);
        List<String> description=ads.getBmslLa(k3vModels);
        return ResultModelUtil.success(model,"部门审理变化",description);
    }
    /**
     * 部门审理情况(结案)
     */
    @RequestMapping("/getBmslJa")
    public ResultModel getBmslJa(HttpServletRequest request,String beginTime,String endTime){
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<K3vModel> k3vModels=xykzpService.getBmslJa(beginTime,endTime,fydm);
        ThreeBarModel model=new ThreeBarModel();
        model.setName1("当期");
        model.setName2("同比");
        model.setName3("环比");
        model.setK3vModelList(k3vModels);
        List<String> description=ads.getBmslJa(k3vModels);
        return ResultModelUtil.success(model,"部门审理变化",description);
    }
    /**
     * 部门审理情况(平均审理天数)
     */
    @RequestMapping("/getBmslAvg")
    public ResultModel getBmslAvg(HttpServletRequest request,String beginTime,String endTime){
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<K3vModel> k3vModels=xykzpService.getBmslAvg(beginTime,endTime,fydm);
        ThreeBarModel model=new ThreeBarModel();
        model.setName1("当期");
        model.setName2("同比");
        model.setName3("环比");
        model.setK3vModelList(k3vModels);
        List<String> description=ads.getBmslAvg(k3vModels);
        return ResultModelUtil.success(model,"部门审理变化",description);
    }

    @RequestMapping("/getBgxb")
    public ResultModel bgxb(HttpServletRequest request,String beginTime,String endTime) {
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<K3vModel> kvModels=xykzpService.getBgXb(beginTime,endTime,fydm);
        ThreeBarModel barModel=new ThreeBarModel();
        barModel.setName1("当期");
        barModel.setName2("同比");
        barModel.setName3("环比");
        barModel.setK3vModelList(kvModels);
        List<String> description=ads.getDsrDesc(kvModels);
        return ResultModelUtil.success(barModel,"被告人性别",description);
    }

    @RequestMapping("/getBgWhcd")
    public ResultModel bgWhcd(HttpServletRequest request,String beginTime,String endTime) {
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<K3vModel> kvModels=xykzpService.getBgWhcd(beginTime,endTime,fydm);
        ThreeBarModel barModel=new ThreeBarModel();
        barModel.setName1("当期");
        barModel.setName2("同比");
        barModel.setName3("环比");
        barModel.setK3vModelList(kvModels);
        List<String> description=ads.getBgABDesc(kvModels);
        return ResultModelUtil.success(barModel,"文化程度",description);
    }

    @RequestMapping("/getBgAge")
    public ResultModel glBgage(HttpServletRequest request,String beginTime,String endTime) {
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<K3vModel> kvModels=xykzpService.getBgAge(beginTime,endTime,fydm);
        ThreeBarModel barModel=new ThreeBarModel();
        barModel.setName1("当期");
        barModel.setName2("同比");
        barModel.setName3("环比");
        barModel.setK3vModelList(kvModels);
        List<String> description=ads.getBgABDesc(kvModels);
        return ResultModelUtil.success(barModel,"年龄分布",description);
    }

    @RequestMapping("/getBgSf")
    public ResultModel bgSf(HttpServletRequest request,String beginTime,String endTime) {
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<K3vModel> kvModels=xykzpService.getBgSf(beginTime,endTime,fydm);
        ThreeBarModel barModel=new ThreeBarModel();
        barModel.setName1("当期");
        barModel.setName2("同比");
        barModel.setName3("环比");
        barModel.setK3vModelList(kvModels);
        List<String> description=ads.getBgABDesc(kvModels);
        return ResultModelUtil.success(barModel,"被告人身份",description);
    }

    /**
     * 导出word
     */
    @RequestMapping("/word")
    public void exportWord(HttpServletRequest request, HttpServletResponse response, String beginTime, String endTime){
        String fydm= (String) request.getSession().getAttribute("fydm");
        xykzpService.exportWord(response, beginTime, endTime,fydm);
    }
}
