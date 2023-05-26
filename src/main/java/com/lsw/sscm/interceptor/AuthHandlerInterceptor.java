package com.lsw.sscm.interceptor;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.interfaces.Claim;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lsw.sscm.pojo.ScStudent;
import com.lsw.sscm.pojo.ScTeacher;
import com.lsw.sscm.service.ScStudentService;
import com.lsw.sscm.service.ScTeacherService;
import com.lsw.sscm.tool.JwtUtil;
import com.lsw.sscm.tool.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
@Slf4j
public class AuthHandlerInterceptor implements HandlerInterceptor {

    @Autowired
    private ScStudentService scStudentService;

    @Autowired
    private ScTeacherService scTeacherService;
 
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("token拦截器");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Type","text/html;charset=utf-8");
        String token = request.getHeader("token");

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers","*");
        response.setHeader("Access-Control-Allow-Methods","*");
        response.setHeader("Access-Control-Allow-Credentials","true");
        response.setHeader("Access-Control-Max-Age","3600");
        if (null == token) {
            response.getWriter().println(JSON.toJSONString(Result.error("token为空")));
            return false;  // 这里一般都是抛出自定义异常给全局异常处理，这里为了简化不做扩展说明
        }
        Map<String, Claim> auth = JwtUtil.verifyToken(token);


        if (auth == null) {
            response.getWriter().println(JSON.toJSONString(Result.error("token不合法")));
            return false;
        }
        Integer id = auth.get("id").asInt();
        String password = auth.get("password").asString();
        Integer type = auth.get("type").asInt();
        log.info(id+" "+password+" "+type);
        if (type == 0) {
            try {
                log.info("学生token拦截器");
                QueryWrapper<ScStudent> studentQueryWrapper = new QueryWrapper<>();
                studentQueryWrapper.select("student_password").eq("student_id",id);
                ScStudent scStudent = scStudentService.getOne(studentQueryWrapper);
                log.info(scStudent.toString());
                if (scStudent.getStudentPassword().equals(password)) {
                    log.info("学生 "+auth.get("id").asInt());
                    request.setAttribute("id",auth.get("id").asInt());
                    request.setAttribute("type",auth.get("type").asInt());
                    return true;
                }else {
                    response.getWriter().println(JSON.toJSONString(Result.error("token已过期")));
                    return false;
                }
            }catch (Exception e){
                response.getWriter().println(JSON.toJSONString(Result.error("token已过期")));
                return false;
            }
        }else if (type == 1) {
            try {
                log.info("老师token拦截器");
                QueryWrapper<ScTeacher> studentQueryWrapper = new QueryWrapper<>();
                studentQueryWrapper.select("teacher_password").eq("teacher_id",id);
                ScTeacher scTeacher = scTeacherService.getOne(studentQueryWrapper);
                log.info(scTeacher.toString());
                if (scTeacher.getTeacherPassword().equals(password)) {
                    log.info("教师 "+auth.get("id").asInt());
                    request.setAttribute("id",auth.get("id").asInt());
                    request.setAttribute("type",auth.get("type").asInt());
                    return true;
                }else {
                    response.getWriter().println(JSON.toJSONString(Result.error("token已过期")));
                    return false;
                }
            }catch (Exception e){
                response.getWriter().println(JSON.toJSONString(Result.error("token已过期")));
                return false;
            }
        }
        response.getWriter().println(JSON.toJSONString(Result.error("token不合法")));
        return false;

    }
 
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
 
    }
 
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
 
    }
}