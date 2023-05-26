package com.lsw.sscm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsw.sscm.pojo.ScStuscore;
import com.lsw.sscm.service.ScStuscoreService;
import com.lsw.sscm.mapper.ScStuscoreMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author Administrator
* @description 针对表【sc_stuscore】的数据库操作Service实现
* @createDate 2023-05-10 19:19:49
*/
@Service
public class ScStuscoreServiceImpl extends ServiceImpl<ScStuscoreMapper, ScStuscore>
    implements ScStuscoreService{

    @Resource
    private ScStuscoreMapper scStuscoreMapper;

    /*
    新增选课信息
     */
    public int addStuscore(ScStuscore scStuscore){
        return scStuscoreMapper.insert(scStuscore);
    }

    /*
    删除选课信息
     */
    public int delStuscore(Integer id){
        QueryWrapper<ScStuscore> scStuscoreQueryWrapper = new QueryWrapper<>();
        scStuscoreQueryWrapper.eq("student_id",id);
        return scStuscoreMapper.delete(scStuscoreQueryWrapper);
    }

    /*
    根据id修改选课信息
     */
    public int upStuscore(ScStuscore scStuscore){
        return scStuscoreMapper.updateById(scStuscore);
    }

    /*
    查询所有选课信息
    分页查询
     */
    public IPage<ScStuscore> getStuscorePage(int pageNum, int pageSize) {
        Page<ScStuscore> page = new Page<>(pageNum, pageSize); // 创建分页对象

        QueryWrapper<ScStuscore> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("stuscore_id");

        IPage<ScStuscore> resultPage = scStuscoreMapper.selectPage(page, queryWrapper); // 执行分页查询

        return resultPage;
    }

    /*
    查询某个老师下的所有选课学生成绩信息
    分页查询
     */
//    public IPage<ScStuscore> getStuscorePageByCourseId(int pageNum, int pageSize,int courseId) {
//        Page<ScStuscore> page = new Page<>(pageNum, pageSize); // 创建分页对象
//        IPage<ScStuscore> stuscorePageByCourseId = scStuscoreMapper.getStuscorePageByCourseId(page, courseId);
//        return stuscorePageByCourseId;
//    }

    /*
   查询某个老师下的所有选课学生成绩信息
   分页查询
    */
    public IPage<ScStuscore> getStuscorePageByTeacherId(int num,int size,int teacherId) {
        Page<ScStuscore> page = new Page<>(num, size);
        IPage<ScStuscore> stuscorePageByTeacherId = scStuscoreMapper.getStuscorePageByTeacherId(page, teacherId);
        return stuscorePageByTeacherId;
    }




}




