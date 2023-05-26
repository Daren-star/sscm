package com.lsw.sscm.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsw.sscm.pojo.ScStudent;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsw.sscm.pojo.ScStuscore;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;
import java.util.List;

/**
* @author Administrator
* @description 针对表【sc_student】的数据库操作Mapper
* @createDate 2023-05-13 15:41:31
* @Entity com.lsw.sscm.pojo.ScStudent
*/
public interface ScStudentMapper extends BaseMapper<ScStudent> {
    ScStudent getStudentById(int id);
    ScStudent getStudentNoPass(int id);
    /**
     * @param page
     * @return
     */
    IPage<ScStudent> getStudentPage(Page<ScStudent> page);

    @Select("select COUNT(*) from sscm.sc_student where major_id = #{majorId}")
    int sumStu(int majorId);
}




