package com.software.controller;

import com.software.model.*;
import com.software.service.AjDescriptionService;
import com.software.service.SyjfDescService;
import com.software.service.SyjfService;
import com.software.utils.ResultModelUtil;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 赡养纠纷案件接口
 */
@RestController
@RequestMapping("/syjfaj")
public class SyjfajController {
    @Autowired
    SyjfService syjfService;
    @Autowired
    SyjfDescService syjfDescService;
    @Autowired
    AjDescriptionService ads;


    @RequestMapping("/getAjCount")
    public ResultModel getLhjfAjCount(javax.servlet.http.HttpServletRequest request) {
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<K2vModel> list=syjfService.getAjSj(fydm);
        TwoBarModel barModel=new TwoBarModel();
        barModel.setName1("新收");
        barModel.setName2("已结");
        barModel.setK2vModelList(list);
        List<String> description=ads.getAjSj(list);
        return ResultModelUtil.success(barModel,"年案件量",description);
    }
    /**
     * 案件审理情况
     */
    @RequestMapping("/getAjsl")
    public ResultModel getAjsl(javax.servlet.http.HttpServletRequest request, String beginTime, String endTime){
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<K3vModel> kvModels=syjfService.getAjsl(beginTime,endTime,fydm);
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
    public ResultModel getBmslLa(javax.servlet.http.HttpServletRequest request, String beginTime, String endTime){
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<K3vModel> k3vModels=syjfService.getBmslLa(beginTime,endTime,fydm);
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
    public ResultModel getBmslJa(javax.servlet.http.HttpServletRequest request, String beginTime, String endTime){
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<K3vModel> k3vModels=syjfService.getBmslJa(beginTime,endTime,fydm);
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
    public ResultModel getBmslAvg(javax.servlet.http.HttpServletRequest request, String beginTime, String endTime){
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<K3vModel> k3vModels=syjfService.getBmslAvg(beginTime,endTime,fydm);
        ThreeBarModel model=new ThreeBarModel();
        model.setName1("当期");
        model.setName2("同比");
        model.setName3("环比");
        model.setK3vModelList(k3vModels);
        List<String> description=ads.getBmslAvg(k3vModels);
        return ResultModelUtil.success(model,"部门审理变化",description);
    }

    /**
     * 被告性别特征
     */
    @RequestMapping("/getBgxb")
    public ResultModel getBgxb(HttpServletRequest request,String beginTime,String endTime){
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> kvModels=syjfService.getBgxb(fydm,beginTime,endTime);
        List<String> description=ads.getBgrxbDesc(kvModels);
        return ResultModelUtil.success(kvModels,"被告人性别",description);
    }
    /**
     * 原告性别特征
     */
    @RequestMapping("/getYgxb")
    public ResultModel getYgxb(HttpServletRequest request,String beginTime,String endTime){
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> kvModels=syjfService.getYgxb(fydm,beginTime,endTime);
        List<String> description=syjfDescService.getYgrxbDesc(kvModels);
        return ResultModelUtil.success(kvModels,"原告人性别",description);
    }
    /**
     * 被告身份特征
     */
    @RequestMapping("/getBgsf")
    public ResultModel getBgsf(HttpServletRequest request,String beginTime,String endTime){
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> kvModels=syjfService.getBgsf(fydm,beginTime,endTime);
        List<String> description=ads.getBgrsfDesc(kvModels);
        return ResultModelUtil.success(kvModels,"被告人身份",description);
    }
    /**
     * 原告身份特征
     */
    @RequestMapping("/getYgsf")
    public ResultModel getYgsf(HttpServletRequest request,String beginTime,String endTime){
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> kvModels=syjfService.getYgsf(fydm,beginTime,endTime);
        List<String> description=syjfDescService.getYgrsfDesc(kvModels);
        return ResultModelUtil.success(kvModels,"原告人身份",description);
    }
    /**
     * 被告年龄特征
     */
    @RequestMapping("/getBgnl")
    public ResultModel getBgnl(HttpServletRequest request,String beginTime,String endTime){
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> kvModels=syjfService.getBgAge(fydm,beginTime,endTime);
        List<String> description=ads.getBgrAgeDesc(kvModels);
        return ResultModelUtil.success(kvModels,"被告人年龄",description);
    }
    /**
     * 原告年龄特征
     */
    @RequestMapping("/getYgnl")
    public ResultModel getYgnl(HttpServletRequest request,String beginTime,String endTime){
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> kvModels=syjfService.getYgAge(fydm,beginTime,endTime);
        List<String> description=syjfDescService.getYgrAgeDesc(kvModels);
        return ResultModelUtil.success(kvModels,"原告人年龄",description);
    }

    @RequestMapping("/getBgSf")
    public ResultModel bgSf(javax.servlet.http.HttpServletRequest request, String beginTime, String endTime) {
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<K3vModel> kvModels=syjfService.getBgSf(beginTime,endTime,fydm);
        ThreeBarModel barModel=new ThreeBarModel();
        barModel.setName1("当期");
        barModel.setName2("同比");
        barModel.setName3("环比");
        barModel.setK3vModelList(kvModels);
        List<String> description=ads.getBgABDesc(kvModels);
        return ResultModelUtil.success(barModel,"被告人身份",description);
    }
    /**
     * 案件判决结果
     */
    @RequestMapping("getResult")
    public ResultModel getResult(HttpServletRequest request,String beginTime,String endTime){
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> kvModels=syjfService.getResult(fydm,beginTime,endTime);
        List<String> description=syjfDescService.getResultDesc(kvModels);
        return ResultModelUtil.success(kvModels,"判决结果",description);
    }

    @RequestMapping("getPjjg")
    public ResultModel getPjjg(HttpServletRequest request,String beginTime,String endTime){
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<K3vModel> kvModels=syjfService.getPjjg(beginTime,endTime,fydm);
        ThreeBarModel barModel=new ThreeBarModel();
        barModel.setName1("当期");
        barModel.setName2("同比");
        barModel.setName3("环比");
        barModel.setK3vModelList(kvModels);
        List<String> description=ads.getPjjgDesc(kvModels);
        return ResultModelUtil.success(barModel,"判决结果",description);
    }

    /**
     * 导出word
     */
    @RequestMapping("/word")
    public void exportWord(HttpServletRequest request, HttpServletResponse response, String beginTime, String endTime){
        String fydm= (String) request.getSession().getAttribute("fydm");
        syjfService.exportWord(response, beginTime, endTime,fydm);
    }
}

