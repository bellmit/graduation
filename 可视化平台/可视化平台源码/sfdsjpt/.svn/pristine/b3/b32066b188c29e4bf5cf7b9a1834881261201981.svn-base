package com.software.controller;

import com.software.model.*;
import com.software.service.AjDescriptionService;
import com.software.service.LhjfDescService;
import com.software.service.LhjfService;
import com.software.utils.ResultModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/lhjf")
public class LhjfController {

    @Autowired
    private LhjfService lhjfService;
    @Autowired
    private LhjfDescService lhjfDescService;
    @Autowired
    private AjDescriptionService ads;

    @RequestMapping("/getAjCount")
    public ResultModel getLhjfAjCount(HttpServletRequest request) {
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<K2vModel> list=lhjfService.getAjSj(fydm);
        TwoBarModel barModel=new TwoBarModel();
        barModel.setName1("新收");
        barModel.setName2("已结");
        barModel.setK2vModelList(list);
        List<String> description=ads.getAjSj(list);
        return ResultModelUtil.success(barModel,"年案件量",description);
    }
    @RequestMapping("/getAreaDistribution")
    public ResultModel getLhjfTjdqAjCount(HttpServletRequest request,String beginTime,String endTime) {
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> list=lhjfService.getAreaDistribution(beginTime,endTime,fydm);
        return ResultModelUtil.success(list,"地域分布","");
    }
    @RequestMapping("/getPjjg")
    public ResultModel getLhjfPjjg(HttpServletRequest request,String beginTime,String endTime) {
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<K3vModel> kvModels=lhjfService.getLhjfPjjg(beginTime,endTime,fydm);
        ThreeBarModel barModel=new ThreeBarModel();
        barModel.setName1("当期");
        barModel.setName2("同比");
        barModel.setName3("环比");
        barModel.setK3vModelList(kvModels);
        List<String> description=lhjfDescService.getAjDesc(kvModels);
        return ResultModelUtil.success(barModel,"判决结果",description);
    }


    /**
     * 案件审理情况
     */
    @RequestMapping("/getAjsl")
    public ResultModel getAjsl(HttpServletRequest request,String beginTime,String endTime){
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<K3vModel> kvModels=lhjfService.getAjsl(beginTime,endTime,fydm);
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
        List<K3vModel> k3vModels=lhjfService.getBmslLa(beginTime,endTime,fydm);
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
        List<K3vModel> k3vModels=lhjfService.getBmslJa(beginTime,endTime,fydm);
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
        List<K3vModel> k3vModels=lhjfService.getBmslAvg(beginTime,endTime,fydm);
        ThreeBarModel model=new ThreeBarModel();
        model.setName1("当期");
        model.setName2("同比");
        model.setName3("环比");
        model.setK3vModelList(k3vModels);
        List<String> description=ads.getBmslAvg(k3vModels);
        return ResultModelUtil.success(model,"部门审理变化",description);
    }

    @RequestMapping("/getDsrAge")
    public ResultModel glBrgage(HttpServletRequest request,String beginTime,String endTime) {
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<K3vModel> kvModels=lhjfService.getDsrAge(beginTime,endTime,fydm);
        ThreeBarModel barModel=new ThreeBarModel();
        barModel.setName1("当期");
        barModel.setName2("同比");
        barModel.setName3("环比");
        barModel.setK3vModelList(kvModels);
        List<String> description=ads.getDsrABDesc(kvModels);
        return ResultModelUtil.success(barModel,"年龄分布",description);
    }

    @RequestMapping("/getWhcd")
    public ResultModel bgrWhcd(HttpServletRequest request,String beginTime,String endTime) {
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<K3vModel> kvModels=lhjfService.getDsrWhcd(beginTime,endTime,fydm);
        ThreeBarModel barModel=new ThreeBarModel();
        barModel.setName1("当期");
        barModel.setName2("同比");
        barModel.setName3("环比");
        barModel.setK3vModelList(kvModels);
        List<String> description=ads.getDsrABDesc(kvModels);
        return ResultModelUtil.success(barModel,"文化程度",description);
    }

    /**
     * 导出word
     */
    @RequestMapping("/word")
    public void exportWord(HttpServletRequest request, HttpServletResponse response, String beginTime, String endTime){
        String fydm= (String) request.getSession().getAttribute("fydm");
        lhjfService.exportWord(response, beginTime, endTime,fydm);
    }
}
