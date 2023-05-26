package com.lsw.sscm;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lsw.sscm.mapper.ScCourseMapper;
import com.lsw.sscm.mapper.ScStudentMapper;
import com.lsw.sscm.mapper.ScStuscoreMapper;
import com.lsw.sscm.mapper.ScTeacherMapper;
import com.lsw.sscm.pojo.*;
import com.lsw.sscm.service.impl.ScDeptServiceImpl;
import com.lsw.sscm.service.impl.ScMajorServiceImpl;
import com.lsw.sscm.service.impl.ScStuscoreServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class SscmApplicationTests {

    @Autowired
    private ScStuscoreServiceImpl scStuscoreService;

    @Resource
    private ScStudentMapper scStudentMapper;
    @Resource
    private ScTeacherMapper scTeacherMapper;

    @Resource
    private ScCourseMapper scCourseMapper;

    @Resource
    private ScStuscoreMapper scStuscoreMapper;

    @Test
    void contextLoads() {

    }

    @Test
    void getStudent(){
        ScStudent studentById = scStudentMapper.getStudentById(10000003);
        System.out.println(studentById.toString());
    }
    @Test
    void getTeacher(){
        ScTeacher teacherById = scTeacherMapper.getAdminById(200000);
        boolean equals = teacherById.getTeacherPermission().equals("1");
        System.out.println(equals);
        System.out.println(teacherById.toString());
    }

    @Autowired
    private ScDeptServiceImpl scDeptService;
    @Test
    void getAllDept(){
        List<ScDept> list = scDeptService.list();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    @Test
    void getInfo(){
//        List<ScStuscore> listStu = scStuscoreMapper.getListStu(10000001);
//        for (int i = 0; i < listStu.size(); i++) {
//            System.out.println(listStu.get(i));
//        }
        ScStudent studentById = scStudentMapper.getStudentById(10000001);
        System.out.println(studentById.toString());
    }

    @Test
    void getCourse(){
//        Integer sumReNub = scStuscoreMapper.getSumReNub(3006);
        ScCourse course = scCourseMapper.getCourse(3006);
//        System.out.println(sumReNub);
        System.out.println(course.toString());
    }
    @Autowired
    private ScMajorServiceImpl scMajorService;
    @Test
    void getMajor(){
        List<ScMajor> allMajor = scMajorService.getAllMajor();
        for (int i = 0; i < allMajor.size(); i++) {
            System.out.println(allMajor.get(i).toString());
        }
    }
}
