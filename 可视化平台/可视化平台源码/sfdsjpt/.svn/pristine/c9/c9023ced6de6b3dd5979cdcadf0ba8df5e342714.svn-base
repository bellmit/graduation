package com.software.controller;

import com.software.model.*;
import com.software.service.AjDescriptionService;
import com.software.service.MinorFzService;
import com.software.service.WcnfzDescService;
import com.software.utils.DateUtils;
import com.software.utils.ResultModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/wcnfz")
public class MinorfzController {
    @Autowired
    MinorFzService minorFzService;

    @Autowired
    WcnfzDescService wcnfzDescService;
    @Autowired
    AjDescriptionService ads;


    @RequestMapping("/minorYearAjCount")
    public ResultModel minorYearAjCount(HttpServletRequest request) {
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<K2vModel> list=minorFzService.getAjSj(fydm);
        TwoBarModel barModel=new TwoBarModel();
        barModel.setName1("新收");
        barModel.setName2("已结");
        barModel.setK2vModelList(list);
        List<String> description=ads.getAjSj(list);
        return ResultModelUtil.success(barModel,"年案件量",description);
    }
    //最近两年的涉案罪名
    @RequestMapping("/minorSszm")
    public ResultModel minorSszm(HttpServletRequest request) {
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<K2vModel> list=minorFzService.getMinorSszm(fydm);
        TwoBarModel barModel=new TwoBarModel();
        int year=DateUtils.getCurYear();
        int lastyear=year-1;
        barModel.setName1(String.valueOf(lastyear));
        barModel.setName2(String.valueOf(year));
        barModel.setK2vModelList(list);
        return ResultModelUtil.success(barModel,"","");
    }
    //罪名分布

    @RequestMapping("/minorZmfb")
    public ResultModel minorZmfb(HttpServletRequest request,String beginTime,String endTime) {
        String fydm= (String) request.getSession().getAttribute("fydm");

        List<KvModel> list=minorFzService.getMinorZmfb(beginTime,endTime,fydm);
        List<String> description=wcnfzDescService.getMinorSszm(list);
        return ResultModelUtil.success(list,"涉诉罪名",description);
    }
    //地域分布
    @RequestMapping("/minorDyfb")
    public ResultModel minorDyfb(HttpServletRequest request,String beginTime,String endTime) {
        HttpSession session = request.getSession();
        /*这里的时间单位是s 登录俩小时有效*/
        String fydm=(String) session.getAttribute("fydm");
        List<KvModel> list=minorFzService.getMinorDyfb(fydm,beginTime,endTime);
        return ResultModelUtil.success(list,"地域分布","");
    }

    //被告人性别

    @RequestMapping("/minorBgrxb")
    public ResultModel minorBgrxb(HttpServletRequest request,String beginTime,String endTime) {
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<K3vModel> kvModels=minorFzService.getMinorBgrxb(beginTime,endTime,fydm);
        ThreeBarModel barModel=new ThreeBarModel();
        barModel.setName1("当期");
        barModel.setName2("同比");
        barModel.setName3("环比");
        barModel.setK3vModelList(kvModels);
        List<String> description=ads.getDsrDesc(kvModels);
        return ResultModelUtil.success(barModel,"当事人性别",description);
    }
    //各省外来未成年人犯罪情况

    @RequestMapping("/minorWlaj")
    public ResultModel minorWlaj(HttpServletRequest request,String beginTime,String endTime) {
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> list=minorFzService.getMinorWlaj(beginTime,endTime,fydm);
        return ResultModelUtil.success(list,"","");
    }
    //年龄分布
    @RequestMapping("/minorBgrAge")
    public ResultModel minorBgrAge(HttpServletRequest request,String beginTime,String endTime) {
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<K3vModel> kvModels=minorFzService.getMinorBgrAge(beginTime,endTime,fydm);
        ThreeBarModel barModel=new ThreeBarModel();
        barModel.setName1("当期");
        barModel.setName2("同比");
        barModel.setName3("环比");
        barModel.setK3vModelList(kvModels);
        List<String> description=ads.getDsrDesc(kvModels);
        return ResultModelUtil.success(barModel,"年龄分布",description);
    }
    //14-15周岁
    @RequestMapping("/minorSszmRange1")
    public ResultModel minorSszmRange1(HttpServletRequest request,String beginTime,String endTime) {
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> list=minorFzService.getMinorSszmRange1(beginTime,endTime,fydm);
        List<String> description=wcnfzDescService.getMinorSszmRange1(list);
        return ResultModelUtil.success(list,"14-15周岁犯罪情况",description);
    }
    //16-17周岁
    @RequestMapping("/minorSszmRange2")
    public ResultModel minorSszmRange2(HttpServletRequest request,String beginTime,String endTime) {
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> list=minorFzService.getMinorSszmRange2(beginTime,endTime,fydm);
        List<String> description=wcnfzDescService.getMinorSszmRange2(list);
        return ResultModelUtil.success(list,"16-17周岁犯罪情况",description);
    }

    //有期徒刑
    @RequestMapping("/yqtx")
    public ResultModel yqtx(HttpServletRequest request,String beginTime,String endTime) {
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> list=minorFzService.yqtx(beginTime,endTime,fydm);
        List<String> description=wcnfzDescService.yqtxDesc(list);
        return ResultModelUtil.success(list,"有期徒刑",description);
    }


    //文化程度

    @RequestMapping("/minorWhcd")
    public ResultModel minorSszmWhcd(HttpServletRequest request,String beginTime,String endTime) {
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<K3vModel> kvModels=minorFzService.getMinorWhcd(beginTime,endTime,fydm);
        ThreeBarModel barModel=new ThreeBarModel();
        barModel.setName1("当期");
        barModel.setName2("同比");
        barModel.setName3("环比");
        barModel.setK3vModelList(kvModels);
        List<String> description=ads.getDsrDesc(kvModels);
        return ResultModelUtil.success(barModel,"文化程度",description);
    }

    //外来人口

    @RequestMapping("/minorWlrk")
    public ResultModel minorSszmWlrk(HttpServletRequest request,String beginTime,String endTime) {
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<K3vModel> kvModels=minorFzService.getMinorWlrk(beginTime,endTime,fydm);
        ThreeBarModel barModel=new ThreeBarModel();
        barModel.setName1("当期");
        barModel.setName2("同比");
        barModel.setName3("环比");
        barModel.setK3vModelList(kvModels);
        List<String> description=ads.getDsrDesc(kvModels);
        return ResultModelUtil.success(barModel,"外来人口",description);
    }

    /**
     * 案件审理情况
     */
    @RequestMapping("/getAjsl")
    public ResultModel getAjsl(HttpServletRequest request,String beginTime,String endTime){
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<K3vModel> kvModels=minorFzService.getAjsl(beginTime,endTime,fydm);
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
        List<K3vModel> k3vModels=minorFzService.getBmslLa(beginTime,endTime,fydm);
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
        List<K3vModel> k3vModels=minorFzService.getBmslJa(beginTime,endTime,fydm);
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
        List<K3vModel> k3vModels=minorFzService.getBmslAvg(beginTime,endTime,fydm);
        ThreeBarModel model=new ThreeBarModel();
        model.setName1("当期");
        model.setName2("同比");
        model.setName3("环比");
        model.setK3vModelList(k3vModels);
        List<String> description=ads.getBmslAvg(k3vModels);
        return ResultModelUtil.success(model,"部门审理变化",description);
    }

    /**
     * 导出word
     */
    @RequestMapping("/word")
    public void exportWord(HttpServletRequest request, HttpServletResponse response, String beginTime, String endTime){
        String fydm= (String) request.getSession().getAttribute("fydm");
        minorFzService.exportWord(response, beginTime, endTime,fydm);
    }
}
