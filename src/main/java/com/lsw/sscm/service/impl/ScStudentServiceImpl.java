package com.lsw.sscm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsw.sscm.pojo.ScStudent;
import com.lsw.sscm.pojo.ScStuscore;
import com.lsw.sscm.service.ScStudentService;
import com.lsw.sscm.mapper.ScStudentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author Administrator
* @description 针对表【sc_student】的数据库操作Service实现
* @createDate 2023-05-10 19:19:49
*/
@Service
public class ScStudentServiceImpl extends ServiceImpl<ScStudentMapper, ScStudent>
    implements ScStudentService{



    @Resource
    private ScStudentMapper scStudentMapper;

    /*
   分页查询
    */
    public IPage<ScStudent> getStudentPage(int pageNum, int pageSize) {
        Page<ScStudent> page = new Page<>(pageNum, pageSize); // 创建分页对象
        IPage<ScStudent> studentPage = scStudentMapper.getStudentPage(page);
        return studentPage;
    }

    /*
    添加学生
     */
    public int addStudent(ScStudent scStudent){
        int insert = scStudentMapper.insert(scStudent);
        return insert;
    }

    /*
    根据id删除学生
     */
    public int delStudent(Integer id){
        return scStudentMapper.deleteById(id);
    }

    /*
    更新学生信息
     */
    public int upStudent(ScStudent scStudent){
        return scStudentMapper.updateById(scStudent);
    }

    /*
    根据id获取学生信息
     */
    public ScStudent getStudentById(Integer id){
        return scStudentMapper.getStudentById(id);
    }


    /*
更该密码
 */
    public boolean upPassword(Integer id, String password){
        UpdateWrapper<ScStudent> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("student_password", password)
                .eq("student_id", id);
        return update(updateWrapper);
    }
    /*
  条件分页查询
   */
    public IPage<ScStudent> getStudentPage(int pageNum, int pageSize,int teacherid) {
        Page<ScStudent> page = new Page<>(pageNum, pageSize); // 创建分页对象
        QueryWrapper<ScStudent> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("student_id");
        IPage<ScStudent> resultPage = scStudentMapper.selectPage(page, queryWrapper); // 执行分页查询
        return resultPage;
    }


}




