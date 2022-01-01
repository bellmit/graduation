package com.software.config;

import com.software.datasource.DataSourceRouter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 拦截器
 */
@Component
public class AccessInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        /*这里的时间单位是s 登录俩小时有效*/
        session.setMaxInactiveInterval(60*60*2);
        // 后期自行定制
        if (session.getAttribute("fydm") != null) {
            String fydm = (String) session.getAttribute("fydm");
            // 选择数据源
            System.out.println(request.getRequestURI()+"放行了");
            DataSourceRouter.routerTo(fydm);
            return true;
        }else{
            System.out.println(request.getRequestURI()+"拦截了");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }


}
