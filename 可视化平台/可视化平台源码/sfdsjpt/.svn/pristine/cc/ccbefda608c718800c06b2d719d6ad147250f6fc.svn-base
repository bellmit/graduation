package com.software.controller;


import com.software.datasource.DataSourceRouter;
import com.software.mapper.PubXtglYhbMapper;
import com.software.model.Fy;
import com.software.model.FyModel;
import com.software.model.YhInfoModel;
import com.software.model.ResultModel;
import com.software.utils.ResultModelUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    PubXtglYhbMapper yhbMapper;
    @Autowired
    Fy fy;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    @ResponseBody
    public ResultModel login(HttpServletRequest request, @Param("fydm") String fydm, @Param("yhdm") String yhdm, @Param("yhkl")String yhkl) {
        System.out.println("fydm"+fydm+" "+yhdm+" "+yhkl);
        // todo 如果是滨海就把fydm传进去
        if (fydm==null||yhdm==null||yhkl==null||fydm.trim().length()==0||
                yhdm.trim().length()==0||yhkl.trim().length()==0||fydm.equals("test")){
            return ResultModelUtil.failed("法院 用户名 密码均不能为空！");
        }
        DataSourceRouter.routerTo(fydm);
        YhInfoModel yhInfoModel= yhbMapper.selectByYhkl("",yhdm,yhkl);
        if (yhInfoModel!=null){
            request.getSession().setAttribute("fydm",fydm);
            return ResultModelUtil.success("登陆成功");
        }
        return ResultModelUtil.failed("用户名或密码错误！");
    }

    @RequestMapping(value = "/getFy",method = RequestMethod.GET)
    @ResponseBody
    public List<FyModel> getFy(){
        Map<String,String> maps=fy.getMaps();
        List<FyModel> list=new ArrayList<>();
        for(String key:maps.keySet()){
            FyModel fyModel=new FyModel(key,maps.get(key));
            list.add(fyModel);
        }
        return list;
    }

}
