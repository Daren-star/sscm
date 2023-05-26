package com.lsw.sscm.mapper;

import com.lsw.sscm.pojo.ScCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsw.sscm.pojo.ScStudent;

import java.util.List;

/**
* @author Administrator
* @description 针对表【sc_course】的数据库操作Mapper
* @createDate 2023-05-13 15:41:31
* @Entity com.lsw.sscm.pojo.ScCourse
*/
public interface ScCourseMapper extends BaseMapper<ScCourse> {
    ScCourse getCourse(int id);
    List<ScCourse> getListCou(int id);
}




