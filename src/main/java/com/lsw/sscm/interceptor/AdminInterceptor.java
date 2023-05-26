package com.lsw.sscm.interceptor;

import com.alibaba.fastjson.JSON;
import com.lsw.sscm.service.impl.ScTeacherServiceImpl;
import com.lsw.sscm.tool.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class AdminInterceptor implements HandlerInterceptor {

    @Autowired
    private ScTeacherServiceImpl scTeacherService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Type","text/html;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers","*");
        response.setHeader("Access-Control-Allow-Methods","*");
        response.setHeader("Access-Control-Allow-Credentials","true");
        response.setHeader("Access-Control-Max-Age","3600");

        log.info("管理员拦截器");
        // 获取用户类型
        Integer id = (Integer) request.getAttribute("id");
        // 判断用户类型是否是管理员
        if (scTeacherService.isAdmin(id)) {
            // 用户是管理员，继续处理请求
            return true;
        } else {
            // 用户不是管理员，返回无权限提示
            response.getWriter().println(JSON.toJSONString(Result.error("无管理员权限")));
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 在处理完请求后进行调用，但是在视图被渲染之前（Controller方法调用之后）
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 在整个请求结束之后被调用，也就是在DispatcherServlet渲染了对应的视图之后执行
    }
}
