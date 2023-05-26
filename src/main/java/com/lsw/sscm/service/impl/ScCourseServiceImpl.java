package com.lsw.sscm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsw.sscm.pojo.ScCourse;
import com.lsw.sscm.pojo.ScTeacher;
import com.lsw.sscm.service.ScCourseService;
import com.lsw.sscm.mapper.ScCourseMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Administrator
 * @description 针对表【sc_course】的数据库操作Service实现
 * @createDate 2023-05-10 19:19:49
 */
@Service
public class ScCourseServiceImpl extends ServiceImpl<ScCourseMapper, ScCourse>
        implements ScCourseService {


    @Resource
    private ScCourseMapper scCourseMapper;


    /*
    分页查询
     */
    public IPage<ScCourse> getCoursePage(int pageNum, int pageSize) {
        Page<ScCourse> page = new Page<>(pageNum, pageSize); // 创建分页对象

        QueryWrapper<ScCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("course_id");

        IPage<ScCourse> resultPage = scCourseMapper.selectPage(page, queryWrapper); // 执行分页查询

        return resultPage;
    }

    /*
    添加课程信息
     */
    public int addCourse(ScCourse scCourses) {
        scCourses.setCourseRenum(scCourses.getCourseNum());
        int insert = scCourseMapper.insert(scCourses);
        return insert;
    }

    /*
    根据id删除课程
     */
    public int delCourse(Integer id) {
        return scCourseMapper.deleteById(id);
    }

    /*
    更新课程信息
     */
    public int upCourse(ScCourse scCourse) {
        return scCourseMapper.updateById(scCourse);
    }

    /*
    更具id获取课程信息
     */
    public ScCourse getCourseById(Integer id) {
        return scCourseMapper.selectById(id);
    }


    /*
    剩余数量自减
     */
    public int upCourseNum(Integer couresId) {
        UpdateWrapper<ScCourse> scCourseUpdateWrapper = new UpdateWrapper<>();
        scCourseUpdateWrapper.eq("course_id", couresId).gt("course_renum", 0);
        scCourseUpdateWrapper.setSql("course_renum = course_renum - 1");
        return scCourseMapper.update(null, scCourseUpdateWrapper);
    }


    /*
    教师不上课了
     */
    public int upCouTea(ScTeacher scTeacher) {
        UpdateWrapper<ScCourse> scCourseUpdateWrapper = new UpdateWrapper<>();
        if (scTeacher.getCourseId() == 0) {
            scCourseUpdateWrapper
                    .eq("teacher_id", scTeacher.getTeacherId())
                    .set("teacher_id", 0);
            return scCourseMapper.update(null, scCourseUpdateWrapper);
        } else {
            scCourseUpdateWrapper
                    .eq("course_id", scTeacher.getCourseId())
                    .set("teacher_id", scTeacher.getTeacherId());
            return scCourseMapper.update(null, scCourseUpdateWrapper);
        }
    }


    /*
    获取所有可选课程信息
     */
    public List<ScCourse> getAllCourse() {
        QueryWrapper<ScCourse> scCourseQueryWrapper = new QueryWrapper<>();
        scCourseQueryWrapper.gt("course_renum", 0);
        return scCourseMapper.selectList(scCourseQueryWrapper);
    }


    /*
    判断是否还有课
     */

    public boolean isHaveCou(int courseId) {
        ScCourse scCourse = scCourseMapper.selectById(courseId);
        if (scCourse.getCourseRenum() > 0) {
            return true;
        } else {
            return false;
        }

    }

}




