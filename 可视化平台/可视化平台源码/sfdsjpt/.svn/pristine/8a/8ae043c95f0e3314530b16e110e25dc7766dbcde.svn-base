package com.software.controller;

import com.software.model.*;
import com.software.service.AjDescriptionService;
import com.software.service.ZscqqqDescService;
import com.software.service.ZscqqqService;
import com.software.utils.ResultModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 知识产权侵权接口
 */
@RestController
@RequestMapping("/zscqqq")
public class ZscqqqController {
    @Autowired
    ZscqqqService zscqqqService;
    @Autowired
    AjDescriptionService ads;
    @Autowired
    ZscqqqDescService zds;


    /**
     * 各年案件量(近五年)
     */
    @RequestMapping("/getAjCount")
    public ResultModel getAjCount(HttpServletRequest request){
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<K2vModel> list=zscqqqService.getAjSj(fydm);
        TwoBarModel barModel=new TwoBarModel();
        barModel.setName1("新收");
        barModel.setName2("已结");
        barModel.setK2vModelList(list);
        List<String> description=ads.getAjSj(list);
        return ResultModelUtil.success(barModel,"年案件量",description);
    }
    /**
     * 案件地域分布（天津地区）
     */
    @RequestMapping("/getAreaDistribution")
    public ResultModel getAreaDistribution(HttpServletRequest request,String beginTime,String endTime){
        String fydm= (String) request.getSession().getAttribute("fydm");
        return ResultModelUtil.success(zscqqqService.getAreaDistribution(beginTime,endTime,fydm),"案件地域分布","");
    }
    /**
     * 案由分布情况
     */
    @RequestMapping("/getAyDistribution")
    public ResultModel getAyDistribution(HttpServletRequest request,String beginTime,String endTime){
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> kvModels=zscqqqService.getAyDistribution(beginTime,endTime,fydm);
        List<String> description=zds.getAyDistribution(kvModels);
        return ResultModelUtil.success(kvModels,"案由分布情况",description);
    }
    /**
     * 当事人类型
     */
    @RequestMapping("/getDsrType")
    public ResultModel getDsrType(HttpServletRequest request,String beginTime,String endTime){
        String fydm= (String) request.getSession().getAttribute("fydm");
        int[][] kvmodels=zscqqqService.getDsrType(beginTime,endTime,fydm);
        List<String> description=zds.getDsrType(kvmodels);
        return ResultModelUtil.success(kvmodels,"当事人类型",description);
    }
    /**
     * 单位性质
     */
    @RequestMapping("/getDwNature")
    public ResultModel getDwNature(HttpServletRequest request,String beginTime,String endTime){
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> kvModels=zscqqqService.getDwNature(beginTime,endTime,fydm);
        List<String> description=zds.getDwNature(kvModels);
        return ResultModelUtil.success(kvModels,"单位性质",description);
    }

    /**
     * 被告个数
     */
    @RequestMapping("/getBgCount")
    public ResultModel getBgCount(HttpServletRequest request,String beginTime,String endTime) {
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> kvModels=zscqqqService.getBgCount(beginTime,endTime,fydm);
        List<String> description=zds.getBgCount(kvModels);
        return ResultModelUtil.success(kvModels,"被告个数",description);
    }
    /**
     * 被告身份
     */
    @RequestMapping("/getBgrsf")
    public ResultModel getBgrsf(HttpServletRequest request,String beginTime,String endTime) {
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> kvModels=zscqqqService.getBgrsf(beginTime,endTime,fydm);
        List<String> description=ads.getBgrsfDesc(kvModels);
        return ResultModelUtil.success(kvModels,"当事人身份",description);
    }
    /**
     * 审理周期信息
     */
    @RequestMapping("/getProcessingCycle")
    public ResultModel getProcessingCycle(HttpServletRequest request,String beginTime,String endTime){
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> kvModels=zscqqqService.getProcessingCycle(beginTime,endTime,fydm);
        List<String> description = zds.getProcessingCycle(kvModels);
        return ResultModelUtil.success(kvModels,"审理周期信息",description);
    }

    /**
     * 案件审理情况
     */
    @RequestMapping("/getAjsl")
    public ResultModel getAjsl(HttpServletRequest request,String beginTime,String endTime){
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<K3vModel> kvModels=zscqqqService.getAjsl(beginTime,endTime,fydm);
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
        List<K3vModel> k3vModels=zscqqqService.getBmslLa(beginTime,endTime,fydm);
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
        List<K3vModel> k3vModels=zscqqqService.getBmslJa(beginTime,endTime,fydm);
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
        List<K3vModel> k3vModels=zscqqqService.getBmslAvg(beginTime,endTime,fydm);
        ThreeBarModel model=new ThreeBarModel();
        model.setName1("当期");
        model.setName2("同比");
        model.setName3("环比");
        model.setK3vModelList(k3vModels);
        List<String> description=ads.getBmslAvg(k3vModels);
        return ResultModelUtil.success(model,"部门审理变化",description);
    }

    @RequestMapping("/getDsrsf")
    public ResultModel bgrsf(HttpServletRequest request,String beginTime,String endTime) {
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<K3vModel> kvModels=zscqqqService.getDsrSf(beginTime,endTime,fydm);
        ThreeBarModel barModel=new ThreeBarModel();
        barModel.setName1("当期");
        barModel.setName2("同比");
        barModel.setName3("环比");
        barModel.setK3vModelList(kvModels);
        List<String> description=ads.getDsrDesc(kvModels);
        return ResultModelUtil.success(barModel,"当事人身份",description);
    }

    @RequestMapping("/getBgNum")
    public ResultModel getBgNum(HttpServletRequest request,String beginTime,String endTime) {
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<K3vModel> kvModels=zscqqqService.getBgNum(beginTime,endTime,fydm);
        ThreeBarModel barModel=new ThreeBarModel();
        barModel.setName1("当期");
        barModel.setName2("同比");
        barModel.setName3("环比");
        barModel.setK3vModelList(kvModels);
        List<String> description=ads.getBgNumDesc(kvModels);
        return ResultModelUtil.success(barModel,"被告个数",description);
    }

    @RequestMapping("/getAy")
    public ResultModel getAy(HttpServletRequest request,String beginTime,String endTime) {
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<K3vModel> kvModels=zscqqqService.getAy(beginTime,endTime,fydm);
        ThreeBarModel barModel=new ThreeBarModel();
        barModel.setName1("当期");
        barModel.setName2("同比");
        barModel.setName3("环比");
        barModel.setK3vModelList(kvModels);
        List<String> description=ads.getBgNumDesc(kvModels);
        return ResultModelUtil.success(barModel,"案件案由",description);
    }
    @RequestMapping("/getBgDw")
    public ResultModel getBgDw(HttpServletRequest request,String beginTime,String endTime) {
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<K3vModel> kvModels=zscqqqService.getBgDw(beginTime,endTime,fydm);
        ThreeBarModel barModel=new ThreeBarModel();
        barModel.setName1("当期");
        barModel.setName2("同比");
        barModel.setName3("环比");
        barModel.setK3vModelList(kvModels);
        List<String> description=ads.getBgABDesc(kvModels);
        return ResultModelUtil.success(barModel,"被告单位",description);
    }

    /**
     * 导出word
     */
    @RequestMapping("/word")
    public void exportWord(HttpServletRequest request, HttpServletResponse response, String beginTime, String endTime){
        String fydm= (String) request.getSession().getAttribute("fydm");
        zscqqqService.exportWord(response, beginTime, endTime,fydm);
    }
}
