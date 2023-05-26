package com.lsw.sscm.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.lsw.sscm.pojo.ScDept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author Administrator
* @description 针对表【sc_dept】的数据库操作Mapper
* @createDate 2023-05-13 15:41:31
* @Entity com.lsw.sscm.pojo.ScDept
*/
public interface ScDeptMapper extends BaseMapper<ScDept> {
    @Select("select * from sscm.sc_dept where dept_id = #{id}")
    ScDept getDept(int id);

    List<ScDept> getAlltDept();
}




