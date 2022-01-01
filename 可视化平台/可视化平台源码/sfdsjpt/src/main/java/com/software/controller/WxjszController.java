package com.software.controller;

import com.software.model.*;
import com.software.service.AjDescriptionService;
import com.software.service.WxjszDescService;
import com.software.service.WxjszService;
import com.software.utils.ResultModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.EntityField;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * 危险驾驶罪接口
 */
@RestController
@RequestMapping("/wxjsz")
public class WxjszController {
    @Autowired
    WxjszService wxjszService;
    @Autowired
    AjDescriptionService ads;

    @Autowired
    WxjszDescService wxd;

    /**
     * 各年案件量(近五年)
     */
    @RequestMapping("/getAjCount")
    public ResultModel getAjCount(HttpServletRequest request,String beginTime,String endTime){
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<K2vModel> list=wxjszService.getAjSj(fydm);
        TwoBarModel barModel=new TwoBarModel();
        barModel.setName1("新收");
        barModel.setName2("已结");
        barModel.setK2vModelList(list);
        List<String> description=ads.getAjSj(list);
        return ResultModelUtil.success(barModel,"年案件量",description);
    }

    @RequestMapping("/getBgrWhcd")
    public ResultModel getBgrWhcd(HttpServletRequest request,String beginTime,String endTime) {
        System.out.println(beginTime+"---------"+ endTime);
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> kvModels=wxjszService.getBgrWhcd(beginTime,endTime,fydm);
        List<String> description=ads.getBgrWhcdDesc(kvModels);
        return ResultModelUtil.success(kvModels,"文化程度",description);
    }
    @RequestMapping("/getBgrsf")
    public ResultModel getBgrsf(HttpServletRequest request,String beginTime,String endTime) {
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> kvModels=wxjszService.getBgrsf(beginTime,endTime,fydm);
        List<String> description=ads.getBgrsfDesc(kvModels);
        return ResultModelUtil.success(kvModels,"当事人身份",description);
    }
    @RequestMapping("/getBgrxb")
    public ResultModel getBgrxb(HttpServletRequest request,String beginTime,String endTime) {
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> kvModels=wxjszService.getBgrxb(beginTime,endTime,fydm);
        List<String> description=ads.getDsrxbDesc(kvModels);
        return ResultModelUtil.success(kvModels,"当事人性别",description);
    }
    @RequestMapping("/getBgrAge")
    public ResultModel getBgrAge(HttpServletRequest request,String beginTime,String endTime) {
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> kvModels=wxjszService.getBrgage(beginTime,endTime,fydm);
        List<String> description=ads.getBgrAgeDesc(kvModels);
        return ResultModelUtil.success(kvModels,"当事人年龄",description);
    }
    /**
     * 案件地域分布（天津地区）
     */
    @RequestMapping("/getAreaDistribution")
    public ResultModel getAreaDistribution(HttpServletRequest request,String beginTime,String endTime){
        String fydm= (String) request.getSession().getAttribute("fydm");
        return ResultModelUtil.success(wxjszService.getAreaDistribution(beginTime,endTime,fydm),"","");
    }
    /**
     * 有期徒刑量刑
     */
    @RequestMapping("/getYqtxlx")
    public ResultModel getYqtxlx(HttpServletRequest request,String beginTime,String endTime){
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> kvModels=wxjszService.getYqtxlx(beginTime,endTime,fydm);
        List<String> description=ads.getYqtxlxDesc(kvModels);
        return ResultModelUtil.success(kvModels,"有期徒刑量刑",description);
    }

    /**
     * 机动车类型
     */
    @RequestMapping("/getTypeOfVehicle")
    public ResultModel getTypeOfVehicle(HttpServletRequest request,String beginTime,String endTime){
        try {
            String fydm= (String) request.getSession().getAttribute("fydm");
            List<KvModel> kvModels=wxjszService.getTypeOfVehicle(beginTime,endTime,fydm);
            List<String> description=wxd.getTypeOfVehicle(kvModels);
            return ResultModelUtil.success(kvModels,"机动车类型",description);
        }catch (Exception e){
            return ResultModelUtil.failed(e.getMessage());
        }
    }
    /**
     * 案件审理情况
     */
    @RequestMapping("/getAjsl")
    public ResultModel getAjsl(HttpServletRequest request,String beginTime,String endTime){
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<K3vModel> kvModels=wxjszService.getAjsl(beginTime,endTime,fydm);
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
        List<K3vModel> k3vModels=wxjszService.getBmslLa(beginTime,endTime,fydm);
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
        List<K3vModel> k3vModels=wxjszService.getBmslJa(beginTime,endTime,fydm);
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
        List<K3vModel> k3vModels=wxjszService.getBmslAvg(beginTime,endTime,fydm);
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
        List<K3vModel> kvModels=wxjszService.getBgXb(beginTime,endTime,fydm);
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
        List<K3vModel> kvModels=wxjszService.getBgWhcd(beginTime,endTime,fydm);
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
        List<K3vModel> kvModels=wxjszService.getBgAge(beginTime,endTime,fydm);
        ThreeBarModel barModel=new ThreeBarModel();
        barModel.setName1("当期");
        barModel.setName2("同比");
        barModel.setName3("环比");
        barModel.setK3vModelList(kvModels);
        List<String> description=ads.getBgABDesc(kvModels);
        return ResultModelUtil.success(barModel,"年龄分布",description);
    }

    /**
     * 导出word
     */
    @RequestMapping("/word")
    public void exportWord(HttpServletRequest request, HttpServletResponse response, String beginTime, String endTime){
        String fydm= (String) request.getSession().getAttribute("fydm");
        wxjszService.exportWord(response, beginTime, endTime,fydm);
    }


}
