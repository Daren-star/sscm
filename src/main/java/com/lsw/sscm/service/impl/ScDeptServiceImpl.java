package com.lsw.sscm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsw.sscm.pojo.ScDept;
import com.lsw.sscm.service.ScDeptService;
import com.lsw.sscm.mapper.ScDeptMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Administrator
 * @description 针对表【sc_dept】的数据库操作Service实现
 * @createDate 2023-05-13 15:41:31
 */
@Service
public class ScDeptServiceImpl extends ServiceImpl<ScDeptMapper, ScDept>
        implements ScDeptService {
    @Resource
    private ScDeptMapper scDeptMapper;

    public List<ScDept> getAllDept(){
        return scDeptMapper.getAlltDept();
    }
}




