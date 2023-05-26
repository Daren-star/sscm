package com.lsw.sscm.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsw.sscm.pojo.ScMajor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsw.sscm.pojo.ScStudent;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;
import java.util.List;

/**
* @author Administrator
* @description 针对表【sc_major】的数据库操作Mapper
* @createDate 2023-05-13 15:41:31
* @Entity com.lsw.sscm.pojo.ScMajor
*/
public interface ScMajorMapper extends BaseMapper<ScMajor> {
    ScMajor getMajor(int id);
    /**
     * @return
     */
    List<ScMajor> getAllMajor();
}




