package com.lsw.sscm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.lsw.sscm.pojo.*;
import com.lsw.sscm.service.ScDeptService;
import com.lsw.sscm.service.ScMajorService;
import com.lsw.sscm.service.impl.ScCourseServiceImpl;
import com.lsw.sscm.service.impl.ScStudentServiceImpl;
import com.lsw.sscm.service.impl.ScStuscoreServiceImpl;
import com.lsw.sscm.service.impl.ScTeacherServiceImpl;
import com.lsw.sscm.tool.Result;
import com.lsw.sscm.tool.ResultEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;


@CrossOrigin
@RestController
@RequestMapping("/sscm")
@Slf4j
@Api("info controller")
public class InfoController {
    @Autowired
    private ScStudentServiceImpl scStudentService;
    @Autowired
    private ScTeacherServiceImpl scTeacherService;

    /*
    获取个人信息
    type==0 学生
    type==1 教师
    屎山代码
     */

    @ApiOperation("获取个人信息")
    @GetMapping("/getinfo")
    public Result getStudentInfo(HttpServletRequest request) throws UnknownHostException {
        log.info("获取信息");
        Integer id = (Integer) request.getAttribute("id");
        Integer type = (Integer) request.getAttribute("type");
        switch (type) {
            case 0:
                log.info("获取学生信息");
                ScStudent studentById = scStudentService.getStudentById(id);
                return Result.ok(studentById);
            case 1:
                if (!scTeacherService.isAdmin(id)) {
                    log.info("获取老师信息ID:"+id);
                    ScTeacher teacherById = scTeacherService.getTeacherById(id);
                    return Result.ok(teacherById);
                }else {
                    log.info("获取老师信息");
                    ScTeacher teacherById = scTeacherService.getAdminById(id);
                    return Result.ok(teacherById);
                }
            default:
                return Result.error(ResultEnum.BAD_REQUEST);
        }


    }

    /*
    更新学生信息
     */
    @ApiOperation("更新学生信息")
    @PutMapping("/upStudentInfo")
    public Result upStudentInfo(HttpServletRequest request, @RequestBody ScStudent scStudent) {
        log.info("updateinfo");
        Integer id = (Integer) request.getAttribute("id");
        log.info("更新学生信息");
        try {
            UpdateWrapper<ScStudent> studentUpdateWrapper = new UpdateWrapper<>();
            log.info(scStudent.toString());
            studentUpdateWrapper
                    .eq("student_id", id)
                    .set("student_name", scStudent.getStudentName())
                    .set("student_sex", scStudent.getStudentSex())
                    .set("student_birthday", scStudent.getStudentBirthday())
                    .set("student_img", scStudent.getStudentImg());
            scStudentService.update(studentUpdateWrapper);
            return Result.ok(ResultEnum.OPERATION_SUCCESS);
        } catch (Exception e) {
            return Result.error(ResultEnum.OPERATION_FAIL);
        }
    }

    /*
    更新老师信息
     */
    @ApiOperation("更新老师信息")
    @PutMapping("/upTeacherInfo")
    public Result upTeacherInfo(HttpServletRequest request, @RequestBody ScTeacher scTeacher) {
        log.info("updateinfo");
        Integer id = (Integer) request.getAttribute("id");
        log.info("更新老师信息");
        try {
            UpdateWrapper<ScTeacher> teacherUpdateWrapper = new UpdateWrapper<>();
            log.info(scTeacher.toString());
            teacherUpdateWrapper
                    .eq("teacher_id", id)
                    .set("teacher_name", scTeacher.getTeacherName())
                    .set("teacher_sex", scTeacher.getTeacherSex())
                    .set("teacher_birthday", scTeacher.getTeacherBirthday())
                    .set("teacher_img", scTeacher.getTeacherImg());
            scTeacherService.update(teacherUpdateWrapper);
            return Result.ok(ResultEnum.OPERATION_SUCCESS);
        } catch (Exception e) {
            return Result.error(ResultEnum.OPERATION_FAIL);
        }
    }

    @ApiOperation("更新密码")
    @PutMapping("/upPassword/{password}")
    public Result upPassword(HttpServletRequest request,@PathVariable String password) {
        log.info("upPassword");
        log.info(password);
        Integer id = (Integer) request.getAttribute("id");
        Integer type = (Integer) request.getAttribute("type");

        switch (type) {
            case 0:
                log.info("更新学生密码");
                if (scStudentService.upPassword(id, password)) {
                    return Result.ok(ResultEnum.OPERATION_SUCCESS);
                } else {
                    return Result.error(ResultEnum.OPERATION_FAIL);
                }
            case 1:
                log.info("更新老师密码");
                if (scTeacherService.upPassword(id, password)) {
                    return Result.ok(ResultEnum.OPERATION_SUCCESS);
                } else {
                    return Result.error(ResultEnum.OPERATION_FAIL);
                }
        }

        return Result.error(ResultEnum.BAD_REQUEST);
    }
}
