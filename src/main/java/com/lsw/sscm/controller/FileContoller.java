package com.lsw.sscm.controller;

import com.lsw.sscm.tool.FileResult;
import com.lsw.sscm.tool.FileuploadUtil;
import com.lsw.sscm.tool.Result;
import com.lsw.sscm.tool.ResultEnum;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin
@Slf4j
@Api("文件上传controller")
public class FileContoller {
    @PostMapping("/uploadimg")
    public Result upLoadImg(MultipartFile file, int type) throws IOException {
        log.info("文件上传");
        switch (type) {
            case 0:
                return Result.ok(FileuploadUtil.saveImage(file, "stu", "jpg,png"));
            case 1:
                return Result.ok(FileuploadUtil.saveImage(file, "tea", "jpg,png"));
            case 2:
                return Result.ok(FileuploadUtil.saveImage(file, "cou", "jpg,png"));
            default:
                return Result.error(ResultEnum.BAD_REQUEST);
        }
    }
}
