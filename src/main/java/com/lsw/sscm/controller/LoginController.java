package com.lsw.sscm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lsw.sscm.pojo.ScStudent;
import com.lsw.sscm.pojo.ScTeacher;
import com.lsw.sscm.service.ScTeacherService;
import com.lsw.sscm.service.impl.ScStudentServiceImpl;
import com.lsw.sscm.tool.JwtUtil;
import com.lsw.sscm.tool.Result;
import com.lsw.sscm.tool.ResultEnum;
import com.lsw.sscm.tool.TokenUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * type==0 学生登录
 * type==1 教师|管理员登录
 */

@CrossOrigin
@RestController
@Slf4j
@Api("login controller")
public class LoginController {

    @Autowired
    private ScStudentServiceImpl scStudentService;

    @Autowired
    private ScTeacherService scTeacherService;


    @ApiOperation("登录")
    @PostMapping("/api/login")
    public Result login(@ApiParam("账号") Integer id, @ApiParam("密码") String password, @ApiParam("类型") Integer type) {
        log.info("login");
        if (type == 0) {
            log.info("type"+type);
            QueryWrapper<ScStudent> scStudentQueryWrapper = new QueryWrapper<>();
            scStudentQueryWrapper.eq("student_id", id).eq("student_password", password);
            try {
                ScStudent scStudent = scStudentService.getOne(scStudentQueryWrapper);
                log.info(scStudent.toString());
                String token = JwtUtil.createToken(new TokenUser(id, password, type));
                return Result.ok(ResultEnum.LOGIN_SUCCESS, token);
            } catch (Exception e) {
                return Result.error(ResultEnum.LOGIN_FAIL);
            }
        } else if (type == 1) {
            log.info("type"+type);
            QueryWrapper<ScTeacher> scTeacherQueryWrapper = new QueryWrapper<>();
            scTeacherQueryWrapper
                    .eq("teacher_id", id)
                    .eq("teacher_password", password);
            try {
                ScTeacher scTeacher = scTeacherService.getOne(scTeacherQueryWrapper);
                log.info(scTeacher.toString());
                String token = JwtUtil.createToken(new TokenUser(id, password, type));
                log.info(token);
                return Result.ok(ResultEnum.LOGIN_SUCCESS, token);
            } catch (Exception e) {
                log.error(e.toString());
                return Result.error(ResultEnum.LOGIN_FAIL);
            }
        }
        return Result.error();
    }

}
