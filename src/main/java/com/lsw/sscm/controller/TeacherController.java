package com.lsw.sscm.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lsw.sscm.pojo.ScStudent;
import com.lsw.sscm.pojo.ScStuscore;
import com.lsw.sscm.service.impl.ScStudentServiceImpl;
import com.lsw.sscm.service.impl.ScStuscoreServiceImpl;
import com.lsw.sscm.tool.Result;
import com.lsw.sscm.tool.ResultEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 老师操作：
 * 删除自己的学生
 * 获取所有学生信息
 */
@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/sscm")
public class TeacherController {
    @Autowired
    private ScStuscoreServiceImpl scStuscoreService;
    @Autowired
    private ScStudentServiceImpl scStudentService;
    /*
    分页获取学生成绩
     */
    @ApiOperation("分页获取学生成绩")
    @GetMapping("/getStuscore/{pageNum}/{pageSize}")
    public Result getStuscore(HttpServletRequest request,@PathVariable int pageNum,@PathVariable int pageSize) {
        log.info("分页获取学生成绩");
        Integer id = (Integer) request.getAttribute("id");
        IPage<ScStuscore> stuscorePageByTeacherId = scStuscoreService.getStuscorePageByTeacherId(pageNum, pageSize, id);
        if (stuscorePageByTeacherId != null) {
            log.info("查询到ipage对象");
            return Result.ok(stuscorePageByTeacherId);
        } else {
            log.info("没有查询到");
            return Result.error(ResultEnum.BAD_REQUEST);
        }
    }
    /*
    根据id删除学生
     */
    @ApiOperation("根据id删除学生")
    @DeleteMapping("/delStuscore/{id}")
    public Result delStuscore(@PathVariable int id) {
        log.info("删除学生"+id);
        int i = scStuscoreService.delStuscore(id);
        if (i == 1) {
            return Result.ok(ResultEnum.OPERATION_SUCCESS);
        } else {
            return Result.error(ResultEnum.OPERATION_FAIL);
        }
    }

    @ApiOperation("修改学生成绩")
    @PutMapping("/updateStuScore")
    public Result updateStuScore(@RequestBody ScStuscore scStuscore){
        log.info(scStuscore.toString());
        log.info("修改学生成绩");
        UpdateWrapper<ScStuscore> scStuscoreUpdateWrapper = new UpdateWrapper<>();
        scStuscoreUpdateWrapper
                .eq("student_id",scStuscore.getStudentId())
                .set("daily_score",scStuscore.getDailyScore())
                .set("final_grade",scStuscore.getFinalGrade())
                .set("total_score",(scStuscore.getDailyScore()*0.4)+(scStuscore.getFinalGrade()*0.6));
        boolean update = scStuscoreService.update(scStuscoreUpdateWrapper);
        if (update) {
            return Result.ok(ResultEnum.OPERATION_SUCCESS);
        }else{
            return Result.error(ResultEnum.OPERATION_FAIL);
        }
    }
}
