package com.lsw.sscm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsw.sscm.pojo.ScMajor;
import com.lsw.sscm.service.ScMajorService;
import com.lsw.sscm.mapper.ScMajorMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Administrator
 * @description 针对表【sc_major】的数据库操作Service实现
 * @createDate 2023-05-13 15:41:31
 */
@Service
public class ScMajorServiceImpl extends ServiceImpl<ScMajorMapper, ScMajor>
        implements ScMajorService {

    @Resource
    private ScMajorMapper scMajorMapper;

    public List<ScMajor> getAllMajor() {
        return scMajorMapper.getAllMajor();
    }
}




