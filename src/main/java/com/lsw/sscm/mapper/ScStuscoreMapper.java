package com.lsw.sscm.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsw.sscm.pojo.ScStuscore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author Administrator
* @description 针对表【sc_stuscore】的数据库操作Mapper
* @createDate 2023-05-10 19:19:49
* @Entity com.lsw.sscm.pojo.ScStuscore
*/
public interface ScStuscoreMapper extends BaseMapper<ScStuscore> {
    /**
     * 根据老师id查询选课成绩信息
     * @param page
     * @param techerId 老师id
     * @return
     */
    IPage<ScStuscore> getStuscorePageByTeacherId(Page<ScStuscore> page,int techerId);

    List<ScStuscore> getListStu(int id);

}




