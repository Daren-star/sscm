package com.lsw.sscm.tool;

import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理自定义异常
     *
     */
    @ExceptionHandler(value = DefinitionException.class)
    @ResponseBody
    public Result bizExceptionHandler(DefinitionException e) {
        return Result.error(e.errorCode,e.errorMsg);
    }

    @ExceptionHandler(value = FileSizeLimitExceededException.class)
    @ResponseBody
    public Result fileSizeLimitExceededException(FileSizeLimitExceededException e){
        return Result.error("上传的文件太大");
    }
    /**
     * 处理其他异常
     *
     */
//    @ExceptionHandler(value = Exception.class)
//    @ResponseBody
//    public Result exceptionHandler( Exception e) {
//        return Result.error();
//    }
}