package com.software.controller;

import com.software.model.*;
import com.software.service.SplcjdDescService;
import com.software.service.SplcjdService;
import com.software.utils.ResultModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
@RequestMapping("/splcjd")
public class SplcjdController {
    @Autowired
    SplcjdService splcjdService;
    @Autowired
    SplcjdDescService splcjdDescService;

    /**
     * 当日立案情况
     */
    @RequestMapping("/getDrla")
    public ResultModel getDrla(HttpServletRequest request, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> kvModels=splcjdService.getDrla(fydm);
        List<String> description=splcjdDescService.getDrajDesc(kvModels);
        return ResultModelUtil.success(kvModels,"案件数量",description);
    }

    /**
     * 当月立案情况
     */
    @RequestMapping("/getDyla")
    public ResultModel getDyla(HttpServletRequest request, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> kvModels=splcjdService.getDyla(fydm);
        List<String> description=splcjdDescService.getDrajDesc(kvModels);
        return ResultModelUtil.success(kvModels,"案件数量",description);
    }

    /**
     * 当日分案情况
     */
    @RequestMapping("/getDrfa")
    public ResultModel getDrfa(HttpServletRequest request, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> kvModels=splcjdService.getDrfa(fydm);
        List<String> description=splcjdDescService.getDrajDesc(kvModels);
        return ResultModelUtil.success(kvModels,"案件数量",description);
    }

    /**
     * 当月分案情况
     */
    @RequestMapping("/getDyfa")
    public ResultModel getDyfa(HttpServletRequest request, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> kvModels=splcjdService.getDyfa(fydm);
        List<String> description=splcjdDescService.getDrajDesc(kvModels);
        return ResultModelUtil.success(kvModels,"案件数量",description);
    }
    /**
     * 当日开庭
     */
    @RequestMapping("/getDrkt")
    public ResultModel getDrkt(HttpServletRequest request, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> kvModels=splcjdService.getDrkt(fydm);
        List<String> description=splcjdDescService.getDrajDesc(kvModels);
        return ResultModelUtil.success(kvModels,"案件数量",description);
    }

    /**
     * 当月开庭
     */
    @RequestMapping("/getDykt")
    public ResultModel getDykt(HttpServletRequest request, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> kvModels=splcjdService.getDykt(fydm);
        List<String> description=splcjdDescService.getDrajDesc(kvModels);
        return ResultModelUtil.success(kvModels,"案件数量",description);
    }

    /**
     * 当日审限延长申请情况
     */
    @RequestMapping("/getDrts")
    public ResultModel getDrts(HttpServletRequest request, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> kvModels=splcjdService.getDrts(fydm);
        List<String> description=splcjdDescService.getDrajDesc(kvModels);
        return ResultModelUtil.success(kvModels,"案件数量",description);
    }

    /**
     * 当月审限延长申请情况
     */
    @RequestMapping("/getDyts")
    public ResultModel getDyts(HttpServletRequest request, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> kvModels=splcjdService.getDyts(fydm);
        List<String> description=splcjdDescService.getDrajDesc(kvModels);
        return ResultModelUtil.success(kvModels,"案件数量",description);
    }

    /**
     * 当日审限中止申请情况
     */
    @RequestMapping("/getDrsx")
    public ResultModel getDrsx(HttpServletRequest request, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> kvModels=splcjdService.getDrsx(fydm);
        List<String> description=splcjdDescService.getDrajDesc(kvModels);
        return ResultModelUtil.success(kvModels,"案件数量",description);
    }

    /**
     * 当月审限中止申请情况
     */
    @RequestMapping("/getDysx")
    public ResultModel getDysx(HttpServletRequest request, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> kvModels=splcjdService.getDysx(fydm);
        List<String> description=splcjdDescService.getDrajDesc(kvModels);
        return ResultModelUtil.success(kvModels,"案件数量",description);
    }

    /**
     * 当日报结情况
     */
    @RequestMapping("/getDrbj")
    public ResultModel getDrbj(HttpServletRequest request, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> kvModels=splcjdService.getDrbj(fydm);
        List<String> description=splcjdDescService.getDrajDesc(kvModels);
        return ResultModelUtil.success(kvModels,"案件数量",description);
    }

    /**
     * 当月报结情况
     */
    @RequestMapping("/getDybj")
    public ResultModel getDybj(HttpServletRequest request, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> kvModels=splcjdService.getDybj(fydm);
        List<String> description=splcjdDescService.getDrajDesc(kvModels);
        return ResultModelUtil.success(kvModels,"案件数量",description);
    }

    /**
     * 当日报结列表
     */
    @RequestMapping("/getDrbjList")
    public List<AjjbModel> getDrbjList(HttpServletRequest request, String index, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        return splcjdService.getDrbjList(fydm,index);
    }


    /**
     * 当月报结列表
     */
    @RequestMapping("/getDybjList")
    public List<AjjbModel> getDybjList(HttpServletRequest request,String index, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        return splcjdService.getDybjList(fydm,index);
    }

    /**
     * 当日分案列表
     */
    @RequestMapping("/getDrfaList")
    public List<AjjbModel> getDrfaList(HttpServletRequest request, String index, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        return splcjdService.getDrfaList(fydm,index);
    }


    /**
     * 当月分案列表
     */
    @RequestMapping("/getDyfaList")
    public List<AjjbModel> getDyfaList(HttpServletRequest request,String index, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        return splcjdService.getDyfaList(fydm,index);
    }

    /**
     * 当日开庭列表
     */
    @RequestMapping("/getDrktList")
    public List<AjjbModel> getDrktList(HttpServletRequest request, String index, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        return splcjdService.getDrktList(fydm,index);
    }


    /**
     * 当月开庭列表
     */
    @RequestMapping("/getDyktList")
    public List<AjjbModel> getDyktList(HttpServletRequest request,String index, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        return splcjdService.getDyktList(fydm,index);
    }

    /**
     * 当日庭审列表
     */
    @RequestMapping("/getDrtsList")
    public List<AjjbModel> getDrtsList(HttpServletRequest request, String index, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        return splcjdService.getDrtsList(fydm,index);
    }


    /**
     * 当月庭审列表
     */
    @RequestMapping("/getDytsList")
    public List<AjjbModel> getDytsList(HttpServletRequest request,String index, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        return splcjdService.getDytsList(fydm,index);
    }

    /**
     * 当日审讯列表
     */
    @RequestMapping("/getDrsxList")
    public List<AjjbModel> getDrsxList(HttpServletRequest request, String index, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        return splcjdService.getDrsxList(fydm,index);
    }


    /**
     * 当月审讯列表
     */
    @RequestMapping("/getDysxList")
    public List<AjjbModel> getDysxList(HttpServletRequest request,String index, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        return splcjdService.getDysxList(fydm,index);
    }

    /**
     * 当日结案列表
     */
    @RequestMapping("/getDrjaList")
    public List<AjjbModel> getDrjaList(HttpServletRequest request, String index, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        return splcjdService.getDrjaList(fydm,index);
    }


    /**
     * 当月结案列表
     */
    @RequestMapping("/getDyjaList")
    public List<AjjbModel> getDyjaList(HttpServletRequest request,String index, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        return splcjdService.getDyjaList(fydm,index);
    }

    /**
     * 当日归档列表
     */
    @RequestMapping("/getDrgdList")
    public List<AjjbModel> getDrgdList(HttpServletRequest request, String index, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        return splcjdService.getDrgdList(fydm,index);
    }


    /**
     * 当月归档列表
     */
    @RequestMapping("/getDygdList")
    public List<AjjbModel> getDygdList(HttpServletRequest request,String index, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        return splcjdService.getDygdList(fydm,index);
    }

    /**
     * 当日立案列表
     */
    @RequestMapping("/getDrlaList")
    public List<AjjbModel> getDrlaList(HttpServletRequest request, String index, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        return splcjdService.getDrlaList(fydm,index);
    }


    /**
     * 当月立案列表
     */
    @RequestMapping("/getDylaList")
    public List<AjjbModel> getDylaList(HttpServletRequest request,String index, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        return splcjdService.getDylaList(fydm,index);
    }

    /**
     * 当月超期未结案（结案）情况
     */
    @RequestMapping("/getDycqjaList")
    public List<AjjbModel> getDycqjaList(HttpServletRequest request,String index, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        return splcjdService.getDycqjaList(fydm,index);
    }

    /**
     * 当月超期未结案（结案）Excel
     */
    @RequestMapping(value="/getDycqjaListExcel",method = RequestMethod.GET,produces = "application/octet-stream")
    public void getDycqjaListExcel(HttpServletRequest request, HttpServletResponse response,String index, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        splcjdService.getDycqjaListExcel(fydm,index,response);
    }

    /**
     * 当日报结列表Excel
     */
    @RequestMapping(value="/getDrbjListExcel",method = RequestMethod.GET,produces = "application/octet-stream")
    public void getDrbjListExcel(HttpServletRequest request, HttpServletResponse response,String index, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        splcjdService.getDrbjListExcel(fydm,index,response);
    }

    /**
     * 当月报结列表Excel
     */
    @RequestMapping(value="/getDybjListExcel",method = RequestMethod.GET,produces = "application/octet-stream")
    public void getDybjListExcel(HttpServletRequest request, HttpServletResponse response,String index, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        splcjdService.getDybjListExcel(fydm,index,response);
    }

    /**
     * 当日立案列表Excel
     */
    @RequestMapping(value="/getDrlaListExcel",method = RequestMethod.GET,produces = "application/octet-stream")
    public void getDrlaListExcel(HttpServletRequest request, HttpServletResponse response,String index, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        splcjdService.getDrlaListExcel(fydm,index,response);
    }

    /**
     * 当月立案列表Excel
     */
    @RequestMapping(value="/getDylaListExcel",method = RequestMethod.GET,produces = "application/octet-stream")
    public void getDylaListExcel(HttpServletRequest request, HttpServletResponse response,String index, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        splcjdService.getDylaListExcel(fydm,index,response);
    }

    /**
     * 当日分案列表Excel
     */
    @RequestMapping(value="/getDrfaListExcel",method = RequestMethod.GET,produces = "application/octet-stream")
    public void getDrfaListExcel(HttpServletRequest request, HttpServletResponse response,String index, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        splcjdService.getDrfaListExcel(fydm,index,response);
    }

    /**
     * 当月分案列表Excel
     */
    @RequestMapping(value="/getDyfaListExcel",method = RequestMethod.GET,produces = "application/octet-stream")
    public void getDyfaListExcel(HttpServletRequest request, HttpServletResponse response,String index, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        splcjdService.getDyfaListExcel(fydm,index,response);
    }

    /**
     * 当日开庭列表Excel
     */
    @RequestMapping(value="/getDrktListExcel",method = RequestMethod.GET,produces = "application/octet-stream")
    public void getDrktListExcel(HttpServletRequest request, HttpServletResponse response,String index, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        splcjdService.getDrktListExcel(fydm,index,response);
    }

    /**
     * 当月开庭列表Excel
     */
    @RequestMapping(value="/getDyktListExcel",method = RequestMethod.GET,produces = "application/octet-stream")
    public void getDyktListExcel(HttpServletRequest request, HttpServletResponse response,String index, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        splcjdService.getDyktListExcel(fydm,index,response);
    }

    /**
     * 当日庭审列表Excel
     */
    @RequestMapping(value="/getDrtsListExcel",method = RequestMethod.GET,produces = "application/octet-stream")
    public void getDrtsListExcel(HttpServletRequest request, HttpServletResponse response,String index, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        splcjdService.getDrtsListExcel(fydm,index,response);
    }

    /**
     * 当月庭审列表Excel
     */
    @RequestMapping(value="/getDytsListExcel",method = RequestMethod.GET,produces = "application/octet-stream")
    public void getDytsListExcel(HttpServletRequest request, HttpServletResponse response,String index, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        splcjdService.getDytsListExcel(fydm,index,response);
    }

    /**
     * 当日审讯列表Excel
     */
    @RequestMapping(value="/getDrsxListExcel",method = RequestMethod.GET,produces = "application/octet-stream")
    public void getDrsxListExcel(HttpServletRequest request, HttpServletResponse response,String index, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        splcjdService.getDrsxListExcel(fydm,index,response);
    }

    /**
     * 当月审讯列表Excel
     */
    @RequestMapping(value="/getDysxListExcel",method = RequestMethod.GET,produces = "application/octet-stream")
    public void getDysxListExcel(HttpServletRequest request, HttpServletResponse response,String index, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        splcjdService.getDysxListExcel(fydm,index,response);
    }

    /**
     * 当日结案列表Excel
     */
    @RequestMapping(value="/getDrjaListExcel",method = RequestMethod.GET,produces = "application/octet-stream")
    public void getDrjaListExcel(HttpServletRequest request, HttpServletResponse response,String index, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        splcjdService.getDrjaListExcel(fydm,index,response);
    }

    /**
     * 当月结案列表Excel
     */
    @RequestMapping(value="/getDyjaListExcel",method = RequestMethod.GET,produces = "application/octet-stream")
    public void getDyjaListExcel(HttpServletRequest request, HttpServletResponse response,String index, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        splcjdService.getDyjaListExcel(fydm,index,response);
    }

    /**
     * 当日归档列表Excel
     */
    @RequestMapping(value="/getDrgdListExcel",method = RequestMethod.GET,produces = "application/octet-stream")
    public void getDrgdListExcel(HttpServletRequest request, HttpServletResponse response,String index, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        splcjdService.getDrgdListExcel(fydm,index,response);
    }

    /**
     * 当月归档列表Excel
     */
    @RequestMapping(value="/getDygdListExcel",method = RequestMethod.GET,produces = "application/octet-stream")
    public void getDygdListExcel(HttpServletRequest request, HttpServletResponse response,String index, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        splcjdService.getDygdListExcel(fydm,index,response);
    }

    /**
     * 当日结案
     */
    @RequestMapping("/getDrja")
    public ResultModel getDrja(HttpServletRequest request, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> kvModels=splcjdService.getDrja(fydm);
        List<String> description=splcjdDescService.getDrajDesc(kvModels);
        return ResultModelUtil.success(kvModels,"案件数量",description);
    }

    /**
     * 当月结案
     */
    @RequestMapping("/getDyja")
    public ResultModel getDyja(HttpServletRequest request, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> kvModels=splcjdService.getDyja(fydm);
        List<String> description=splcjdDescService.getDrajDesc(kvModels);
        return ResultModelUtil.success(kvModels,"案件数量",description);
    }

    /**
     * 当日归档
     */
    @RequestMapping("/getDrgd")
    public ResultModel getDrgd(HttpServletRequest request, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> kvModels=splcjdService.getDrgd(fydm);
        List<String> description=splcjdDescService.getDrajDesc(kvModels);
        return ResultModelUtil.success(kvModels,"案件数量",description);
    }

    /**
     * 当月归档
     */
    @RequestMapping("/getDygd")
    public ResultModel getDygd(HttpServletRequest request, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> kvModels=splcjdService.getDygd(fydm);
        List<String> description=splcjdDescService.getDrajDesc(kvModels);
        return ResultModelUtil.success(kvModels,"案件数量",description);
    }

    /**
     * 当月超期未结案（结案）情况
     */
    @RequestMapping("/getDycqja")
    public ResultModel getDycqja(HttpServletRequest request, String beginTime, String endTime){
        // 处理请求数据，beginTime,endTime,fydm需要从session中获取
        String fydm= (String) request.getSession().getAttribute("fydm");
        List<KvModel> kvModels=splcjdService.getDycqja(fydm);
        List<String> description=splcjdDescService.getDycqjaDesc(kvModels);
        return ResultModelUtil.success(kvModels,"案件数量",description);
    }

    /**
     * 当月超期未归档情况
     */
    @RequestMapping("/getDycqgd")
    @ResponseBody
    public List<CqwgdVOModel> getDycqgd(){
        List<CqwgdVOModel> cqwgdModels=splcjdService.getDycqgd();
        return cqwgdModels;
    }

    /**
     * 导出word
     */
    @RequestMapping("/word")
    public void exportWord(HttpServletRequest request, HttpServletResponse response, String beginTime, String endTime){
        String fydm= (String) request.getSession().getAttribute("fydm");
        splcjdService.exportWord(response, beginTime, endTime,fydm);
    }
}
