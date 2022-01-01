package com.software.controller;

import com.software.model.*;
import com.software.service.*;
import com.software.utils.ResultModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * 立案大数据接口
 */
@RestController
@RequestMapping("/ladsj")
public class LadsjController {
    @Autowired
    LadsjService ladsjService;
    @Autowired
    LadsjDescService ladsjDescService;
    @Autowired
    AjDescriptionService ads;


    /**
     * 导出word
     */
    @RequestMapping("/word")
    public void exportWord(HttpServletRequest request, HttpServletResponse response, String beginTime, String endTime){
        String fydm= (String) request.getSession().getAttribute("fydm");
        ladsjService.exportWord(response, beginTime, endTime,fydm);
    }
    /**
     * 登记立案实时数据
     */
    @RequestMapping("/getDjlasssj")
    public ResultModel getDjlasssj(HttpServletRequest request,String beginTime,String endTime){
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<AjjbModel> list=ladsjService.getDjlasssj(fydm,beginTime,endTime);
        return ResultModelUtil.success(list,"","");
    }
    /**
     * 立案实时数据
     */
    @RequestMapping("/getLasssj")
    public ResultModel getLasssj(HttpServletRequest request,String beginTime,String endTime){
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<AjjbModel> list=ladsjService.getLasssj(fydm,beginTime,endTime);
        return ResultModelUtil.success(list,"","");
    }

    /**
     * 诉前调解实时数据
     */
    @RequestMapping("/getSqtjsssj")
    public ResultModel getSqtjsssj(HttpServletRequest request,String beginTime,String endTime){
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<AjjbModel> list=ladsjService.getSqtjsssj(fydm,beginTime,endTime);
        return ResultModelUtil.success(list,"","");
    }

    /**
     * 登记立案流转数据
     */
    @RequestMapping("/getDjlalzsj")
    public ResultModel getDjlalzsj(HttpServletRequest request,String beginTime,String endTime){
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> kvModels=ladsjService.getDjlalzsj(fydm,beginTime,endTime);
        return ResultModelUtil.success(kvModels,"","");
    }

    /**
     * 立案移送数据
     */
    @RequestMapping("/getLayssj")
    public ResultModel getLayssj(HttpServletRequest request,String beginTime,String endTime){
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> kvModels=ladsjService.getLayssj(fydm,beginTime,endTime);
        return ResultModelUtil.success(kvModels,"","");
    }

    /**
     * 登记立案数据
     */
    @RequestMapping("/getDjlasj")
    public ResultModel getDjlasj(HttpServletRequest request,String beginTime,String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> kvModels=ladsjService.getDjlasj(fydm);
        List<String> description=ladsjDescService.getDjlasj(kvModels);
        return ResultModelUtil.success(kvModels,"案件数量",description);
    }
    /**
     * 登记立案数据类型
     */
    @RequestMapping("/getDjlasjLx")
    public ResultModel getDjlasjLx(HttpServletRequest request,String beginTime,String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> kvModels=ladsjService.getDjlasjLx(fydm,beginTime,endTime);
        List<String> description=ladsjDescService.getDjlasjLx(kvModels);
        return ResultModelUtil.success(kvModels,"案件数量",description);
    }

    /**
     * 登记立案当事人数据
     */
    @RequestMapping("/getDjladsrsj")
    public ResultModel getDjladsrsj(HttpServletRequest request,String beginTime,String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> kvModels=ladsjService.getDjladsrsj(fydm);
        return ResultModelUtil.success(kvModels,"这里写什么？？","");
    }

    /**
     * 登记立案人数据类型
     */
    @RequestMapping("/getDjladsrsjLx")
    public ResultModel getDjladsrsjLx(HttpServletRequest request,String index){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        System.out.println(index+"--------------------");
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> kvModels=ladsjService.getDjladsrsjLx(fydm,index);
        return ResultModelUtil.success(kvModels,"","");
    }
    /**
     * 当日立案数据
     */
    @RequestMapping("/getDrlasj")
    public ResultModel getDrlasj(HttpServletRequest request,String beginTime,String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> kvModels=ladsjService.getDrlasj(fydm);
        return ResultModelUtil.success(kvModels,"","");
    }
    /**
     * 当日立案数据审判
     */
    @RequestMapping("/getDrlasjSp")
    public ResultModel getDrlasjSp(HttpServletRequest request,String beginTime,String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<K2vModel> kvModels=ladsjService.getDrlasjSp(fydm);
        TwoBarModel barModel=new TwoBarModel();
        barModel.setName1("当日立案数量");
        barModel.setName2("移送审判庭数量");
        barModel.setK2vModelList(kvModels);
        return ResultModelUtil.success(barModel,"","");
    }
    /**
     * 当月立案数据
     */
    @RequestMapping("/getDylasj")
    public ResultModel getDylasj(HttpServletRequest request,String beginTime,String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> kvModels=ladsjService.getDylasj(fydm);
        return ResultModelUtil.success(kvModels,"","");
    }
    /**
     * 当月立案数据审判
     */
    @RequestMapping("/getDylasjSp")
    public ResultModel getDylasjSp(HttpServletRequest request,String beginTime,String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<K2vModel> kvModels=ladsjService.getDylasjSp(fydm);
        TwoBarModel barModel=new TwoBarModel();
        barModel.setName1("当月立案数量");
        barModel.setName2("移送审判庭数量");
        barModel.setK2vModelList(kvModels);
        return ResultModelUtil.success(barModel,"","");
    }
    /**
     * 当季度立案数据
     */
    @RequestMapping("/getDjdlasj")
    public ResultModel getDjdlasj(HttpServletRequest request,String beginTime,String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> kvModels=ladsjService.getDjdlasj(fydm);
        return ResultModelUtil.success(kvModels,"","");
    }
    /**
     * 当季度立案数据审判
     */
    @RequestMapping("/getDjdlasjSp")
    public ResultModel getDjdlasjSp(HttpServletRequest request,String beginTime,String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<K2vModel> kvModels=ladsjService.getDjdlasjSp(fydm);
        TwoBarModel barModel=new TwoBarModel();
        barModel.setName1("当季度立案数量");
        barModel.setName2("移送审判庭数量");
        barModel.setK2vModelList(kvModels);
        return ResultModelUtil.success(barModel,"","");
    }
    /**
     * 当年立案数据
     */
    @RequestMapping("/getDnlasj")
    public ResultModel getDnlasj(HttpServletRequest request,String beginTime,String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> kvModels=ladsjService.getDnlasj(fydm);
        return ResultModelUtil.success(kvModels,"","");
    }
    /**
     * 当年立案数据审判
     */
    @RequestMapping("/getDnlasjSp")
    public ResultModel getDnlasjSp(HttpServletRequest request,String beginTime,String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<K2vModel> kvModels=ladsjService.getDnlasjSp(fydm);
        TwoBarModel barModel=new TwoBarModel();
        barModel.setName1("当年立案数量");
        barModel.setName2("移送审判庭数量");
        barModel.setK2vModelList(kvModels);
        return ResultModelUtil.success(barModel,"","");
    }
    /**
     * 诉前调解数据
     */
    @RequestMapping("/getSqtjsj")
    public ResultModel getSqtjsj(HttpServletRequest request,String beginTime,String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> kvModels=ladsjService.getSqtjsj(fydm);
        List<String> description=ladsjDescService.getSqtjsjDesc(kvModels);
        return ResultModelUtil.success(kvModels,"",description);
    }

}

