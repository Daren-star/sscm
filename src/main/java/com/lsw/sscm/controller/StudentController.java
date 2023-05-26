package com.lsw.sscm.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lsw.sscm.pojo.ScCourse;
import com.lsw.sscm.pojo.ScStuscore;
import com.lsw.sscm.service.impl.ScCourseServiceImpl;
import com.lsw.sscm.service.impl.ScStuscoreServiceImpl;
import com.lsw.sscm.tool.Result;
import com.lsw.sscm.tool.ResultEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 学生操作：
 * 抢课
 */
@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/sscm")
@Api("学生controller")
public class StudentController {

    @Autowired
    private ScStuscoreServiceImpl scStuscoreService;

    @Autowired
    private ScCourseServiceImpl scCourseService;


    @ApiOperation("学生抢课操作")
    @PostMapping("/addStuscore")
    public Result addStuscore(HttpServletRequest request, @RequestBody ScStuscore scStuscore) {
        Integer id = (Integer) request.getAttribute("id");
        log.info("学生"+id+"选课");
        scStuscore.setStudentId(id);
        boolean haveCou = scCourseService.isHaveCou(scStuscore.getCourseId());
        if(haveCou){
            int i = scStuscoreService.addStuscore(scStuscore);
            if (i == 1) {
                return Result.ok(ResultEnum.OPERATION_SUCCESS);
            } else {
                return Result.error(ResultEnum.OPERATION_FAIL);
            }
        }else {
            return Result.error("课已抢完");
        }

    }

    @ApiOperation("获取所有课程")
    @GetMapping("/getAllScore")
    public Result getAllScore(){
        log.info("获取所有可选课程");
        return Result.ok(scCourseService.getAllCourse());
    }
}
