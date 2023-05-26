package com.lsw.sscm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lsw.sscm.pojo.ScCourse;
import com.lsw.sscm.pojo.ScStudent;
import com.lsw.sscm.pojo.ScStuscore;
import com.lsw.sscm.pojo.ScTeacher;
import com.lsw.sscm.service.impl.*;
import com.lsw.sscm.tool.Result;
import com.lsw.sscm.tool.ResultEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


/**
 * 管理员操作
 * 对课程信息的crud
 * 对老师信息的crud
 * 对学生信息的crud
 * 能够对学生成绩表进行crud
 */
@CrossOrigin
@RestController
@Slf4j
@RequestMapping("sscm/admin/")
@Api("管理员操作")
public class AdminController {

    @Autowired
    private ScTeacherServiceImpl scTeacherService;


    /*
    添加教师
     */
    @ApiOperation("新增老师")
    @PostMapping("/addTeacher")
    public Result addTeacher(@ApiParam("老师对象") @RequestBody ScTeacher scTeacher) {
        log.info("新增老师");
        log.info(scTeacher.toString());
        int i = scTeacherService.addTeacher(scTeacher);
        if (i == 1) {
            return Result.ok(ResultEnum.OPERATION_SUCCESS);
        } else {
            return Result.error(ResultEnum.OPERATION_FAIL);
        }
    }

    /*
    更新教师
     */

    @ApiOperation("更新老师信息")
    @Transactional
    @PutMapping("/upTeacher")
    public Result upTeacher(@ApiParam("老师对象") @RequestBody ScTeacher scTeacher) {
        log.info("更新教师信息");
        log.info("分配了课程id:"+scTeacher.getCourseId());
        int i1 = scCourseService.upCouTea(scTeacher);
        int i = scTeacherService.upTeacher(scTeacher);
        if (i == 1 && i1 == 1) {
            return Result.ok(ResultEnum.OPERATION_SUCCESS);
        } else {
            return Result.error(ResultEnum.OPERATION_FAIL);
        }
    }

    /*
    删除教师
     */
    @ApiOperation("删除老师")
    @DeleteMapping("/delTeacher/{id}")
    public Result delTeacher(@ApiParam("老师id") @PathVariable Integer id) {
        int i = scTeacherService.delTeacher(id);
        if (i == 1) {
            return Result.ok(ResultEnum.OPERATION_SUCCESS);
        } else {
            return Result.error(ResultEnum.OPERATION_FAIL);
        }
    }

    /*
    获取所有教师信息
    分页查询
    @pageNum 查询第几页
    @pageSize 查询几个
     */
    @ApiOperation("分页获取老师信息")
    @GetMapping("/getTeacher/{pageNum}/{pageSize}")
    public Result getTeacher(@ApiParam("第几页")@PathVariable int pageNum, @ApiParam("一页几个")@PathVariable int pageSize) {
        log.info("分页获取老师信息");
        IPage<ScTeacher> teacherPage = scTeacherService.getTeacherPage(pageNum, pageSize);
        if (teacherPage != null) {
            return Result.ok(teacherPage);
        } else {
            return Result.error(ResultEnum.OPERATION_FAIL);
        }

    }

    /*
    根据id查询老师
     */
    @ApiOperation("根据id查询老师信息")
    @GetMapping("/getTeacher/{id}")
    public Result getTeacher(@ApiParam("老师id") @PathVariable Integer id) {
        ScTeacher teacherById = scTeacherService.getTeacherById(id);
        if (teacherById != null) {
            return Result.ok(teacherById);
        } else {
            return Result.error(ResultEnum.OPERATION_FAIL);
        }
    }

    /*
    获取没有课程的老师
     */
    @ApiOperation("获取没有课程的老师")
    @GetMapping("/getNoCouTea")
    public Result getNoCouTea(){
        log.info("获取没有课程的老师");
        QueryWrapper<ScTeacher> scTeacherQueryWrapper = new QueryWrapper<>();
        scTeacherQueryWrapper.eq("course_id", 0);
        return Result.ok(scTeacherService.list(scTeacherQueryWrapper));
    }


    @Autowired
    private ScCourseServiceImpl scCourseService;


    /*
    添加课程
     */
    @ApiOperation("添加课程")
    @PostMapping("/addCourse")
    public Result addCourse(@ApiParam("课程对象") @RequestBody ScCourse scCourse) {
        int i = scCourseService.addCourse(scCourse);
        if (i == 1) {
            return Result.ok(ResultEnum.OPERATION_SUCCESS);
        } else {
            return Result.error(ResultEnum.OPERATION_FAIL);
        }
    }

    /*
    更新课程信息
     */
    @ApiOperation("更新课程信息")
    @PutMapping("/upCourse")
    public Result upCourse(@ApiParam("课程对象") @RequestBody ScCourse scCourse) {
        int i = scCourseService.upCourse(scCourse);
        if (i == 1) {
            return Result.ok(ResultEnum.OPERATION_SUCCESS);
        } else {
            return Result.error(ResultEnum.OPERATION_FAIL);
        }
    }

    /*
    删除课程
     */
    @ApiOperation("根据id删除课程信息")
    @DeleteMapping("/delCourse/{id}")
    public Result delCourse(@ApiParam("课程id") @PathVariable Integer id) {
        int i = scCourseService.delCourse(id);
        if (i == 1) {
            return Result.ok(ResultEnum.OPERATION_SUCCESS);
        } else {
            return Result.error(ResultEnum.OPERATION_FAIL);
        }
    }

    /*
    获取所有课程信息
    分页查询
    @pageNum 查询第几页
    @pageSize 查询几个
     */
    @ApiOperation("查询所有课程信息")
    @GetMapping("/getCourse")
    public Result getCourse() {
        return Result.ok(scCourseService.list());
    }

    /*
    根据id查询课程信息
     */
    @ApiOperation("根据id查询课程信息")
    @GetMapping("/getCourse/{id}")
    public Result getCourse(@PathVariable Integer id) {
        ScCourse course = scCourseService.getCourseById(id);
        if (course != null) {
            return Result.ok(course);
        } else {
            return Result.error(ResultEnum.OPERATION_FAIL);
        }
    }


    /*
   获取没有老师的课程
    */
    @ApiOperation("获取没有老师的课程")
    @GetMapping("/getNoTeaCou")
    public Result getNoTeaCou(){
        log.info("获取没有老师的课程");
        QueryWrapper<ScCourse> scCourseQueryWrapper = new QueryWrapper<>();
        scCourseQueryWrapper
                .eq("course_id",0)
                .or(scCourseQueryWrapper1 -> scCourseQueryWrapper1.eq("teacher_id",0));
        return Result.ok(scCourseService.list(scCourseQueryWrapper));
    }

    @Autowired
    private ScStudentServiceImpl scStudentService;


    /*
    添加学生
     */
    @ApiOperation("添加学生")
    @PostMapping("/addStudent")
    public Result addStudent(@RequestBody ScStudent scStudent) {
        log.info("添加学生");
        log.info(scStudent.toString());
        int i = scStudentService.addStudent(scStudent);
        if (i == 1) {
            return Result.ok(ResultEnum.OPERATION_SUCCESS);
        } else {
            return Result.error(ResultEnum.OPERATION_FAIL);
        }
    }

    /*
    更新学生信息
     */
    @ApiOperation("更新学生信息")
    @PutMapping("/upStudent")
    public Result upStudent(@RequestBody ScStudent scStudent) {
        log.info("更新学生信息");
        log.info(scStudent.toString());
        int i = scStudentService.upStudent(scStudent);
        if (i == 1) {
            return Result.ok(ResultEnum.OPERATION_SUCCESS);
        } else {
            return Result.error(ResultEnum.OPERATION_FAIL);
        }
    }

    /*
    删除学生
     */
    @ApiOperation("删除学生")
    @DeleteMapping("/delStudent/{id}")
    public Result delStudent(@PathVariable Integer id) {
        int i = scStudentService.delStudent(id);
        if (i == 1) {
            return Result.ok(ResultEnum.OPERATION_SUCCESS);
        } else {
            return Result.error(ResultEnum.OPERATION_FAIL);
        }
    }

    /*
    获取所有学生信息
    分页查询
    @pageNum 查询第几页
    @pageSize 查询几个
     */
    @ApiOperation("获取所有学生信息")
    @GetMapping("/getStudent/{pageNum}/{pageSize}")
    public Result getStudent(@PathVariable int pageNum,@PathVariable int pageSize) {
        log.info("获取所有学生信息");
        IPage<ScStudent> studentPage = scStudentService.getStudentPage(pageNum, pageSize);
        if (studentPage != null) {
            return Result.ok(studentPage);
        } else {
            return Result.error(ResultEnum.OPERATION_FAIL);
        }

    }

    /*
    根据id查询学生信息
     */
    @ApiOperation("根据id查询学生信息")
    @GetMapping("/getStudent/{id}")
    public Result getStudent(@PathVariable Integer id) {
        log.info("获取单个学生信息");
        ScStudent scStudent = scStudentService.getStudentById(id);
        log.info(scStudent.toString());
        if (scStudent != null) {
            return Result.ok(scStudent);
        } else {
            return Result.error(ResultEnum.OPERATION_FAIL);
        }
    }


    /**
     * 选课成绩的增删改查
     */


    @Autowired
    private ScStuscoreServiceImpl scStuscoreService;

    /*
        根据课程id获取成绩信息
    */
//    @ApiOperation("根据课程id获取成绩信息")
//    @GetMapping("/getStuScore/{id}")
//    public Result getStuScore(@PathVariable Integer id, Integer pageNum, Integer pageSize) {
//        IPage<ScStuscore> stuscorePageByCourseId = scStuscoreService.getStuscorePageByCourseId(pageNum, pageSize, id);
//        if (stuscorePageByCourseId == null) {
//            return Result.ok(stuscorePageByCourseId);
//        }
//        return Result.error(ResultEnum.OPERATION_FAIL);
//    }

    /*
    添加选课信息
     */

    @PostMapping("/addStuScore")
    public Result addStuScore(@RequestBody ScStuscore scStuscore) {
        int i = scStuscoreService.addStuscore(scStuscore);
        if (i == 1) {
            return Result.ok(ResultEnum.OPERATION_SUCCESS);
        } else {
            return Result.error(ResultEnum.OPERATION_FAIL);
        }
    }

    /*
    删除选课信息
     */
    @ApiOperation("删除选课信息")
    @DeleteMapping("/delStuScore/{id}")
    public Result delStuScore(@PathVariable Integer id) {
        int i = scStuscoreService.delStuscore(id);
        if (i == 1) {
            return Result.ok(ResultEnum.OPERATION_SUCCESS);
        } else {
            return Result.error(ResultEnum.OPERATION_FAIL);
        }
    }

    /*
    更新选课信息包括成绩
     */
    @ApiOperation("更新选课信息包括成绩")
    @PutMapping("/upStuScore")
    public Result upStuScore(@RequestBody ScStuscore scStuscore) {
        int i = scStuscoreService.upStuscore(scStuscore);
        if (i == 1) {
            return Result.ok(ResultEnum.OPERATION_SUCCESS);
        } else {
            return Result.error(ResultEnum.OPERATION_FAIL);
        }
    }


    @Autowired
    private ScMajorServiceImpl scMajorService;

    @GetMapping("/getAllMajor")
    public Result getAllMajor(){
        log.info("获取所有专业信息");
        return Result.ok(scMajorService.getAllMajor());
    }

    @Autowired
    private ScDeptServiceImpl scDeptService;

    @ApiOperation("获取所有学院信息")
    @GetMapping("/getAllDept")
    public Result getAllDept(){
        log.info("获取所有学院信息");
        return Result.ok(scDeptService.getAllDept());
    }
}
