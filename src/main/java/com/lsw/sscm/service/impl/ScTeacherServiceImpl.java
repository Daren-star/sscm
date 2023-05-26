package com.lsw.sscm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsw.sscm.pojo.ScTeacher;
import com.lsw.sscm.service.ScTeacherService;
import com.lsw.sscm.mapper.ScTeacherMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author Administrator
* @description 针对表【sc_teacher】的数据库操作Service实现
* @createDate 2023-05-10 19:19:49
*/
@Slf4j
@Service
public class ScTeacherServiceImpl extends ServiceImpl<ScTeacherMapper, ScTeacher>
    implements ScTeacherService{

    @Resource
    private ScTeacherMapper scTeacherMapper;

    /*
    判断老师是不是管理员
     */

    public Boolean isAdmin(Integer id){
        QueryWrapper<ScTeacher> scTeacherQueryWrapper = new QueryWrapper<>();
        scTeacherQueryWrapper.select("teacher_permission").eq("teacher_id",id);
        ScTeacher scTeacher = scTeacherMapper.getAdminById(id);
        if (Integer.parseInt(scTeacher.getTeacherPermission())==1){
            return true;
        }else {
            return false;
        }
    }

    /*
    分页查询
     */
    public IPage<ScTeacher> getTeacherPage(int pageNum, int pageSize) {
        Page<ScTeacher> page = new Page<>(pageNum, pageSize); // 创建分页对象

        IPage<ScTeacher> teacherPage = scTeacherMapper.getTeacherPage(page);// 执行分页查询

        return teacherPage;
    }

    /*
    添加老师信息
     */
    public int addTeacher(ScTeacher scTeacher){
        int insert = scTeacherMapper.insert(scTeacher);
        return insert;
    }

    /*
    根据id删除老师
     */
    public int delTeacher(Integer id){

        return scTeacherMapper.deleteById(id);
    }

    /*
    更新老师信息
     */
    public int upTeacher(ScTeacher scTeacher){
        return scTeacherMapper.updateById(scTeacher);
    }

    /*
    根据id获取老师信息
     */
    public ScTeacher getTeacherById(Integer id){
        return scTeacherMapper.getTeacherById(id);
    }

    public ScTeacher getAdminById(int id){
        return scTeacherMapper.getAdminById(id);
    }


    /*
    更该密码
     */
    public boolean upPassword(Integer id, String password){
        UpdateWrapper<ScTeacher> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("teacher_password", password)
                .eq("teacher_id", id);
        return update(updateWrapper);
    }




}




