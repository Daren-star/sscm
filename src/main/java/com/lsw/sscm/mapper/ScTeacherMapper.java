package com.lsw.sscm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsw.sscm.pojo.ScStudent;
import com.lsw.sscm.pojo.ScTeacher;
import org.apache.ibatis.annotations.Select;

/**
* @author Administrator
* @description 针对表【sc_teacher】的数据库操作Mapper
* @createDate 2023-05-13 15:41:31
* @Entity com.lsw.sscm.pojo.ScTeacher
*/
public interface ScTeacherMapper extends BaseMapper<ScTeacher> {
   ScTeacher getTeacherById(int id);
   ScTeacher getAdminById(int id);

   /**
    * @param page
    * @return
    */
   IPage<ScTeacher> getTeacherPage(Page<ScTeacher> page);

   @Select("select COUNT(*) from sscm.sc_teacher where dept_id = #{id}")
   int sumTea(int id);
}




