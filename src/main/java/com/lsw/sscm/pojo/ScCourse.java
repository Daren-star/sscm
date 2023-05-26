package com.lsw.sscm.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @TableName sc_course
 */
@TableName(value ="sc_course")
@Data
@ApiModel("课程信息表")
public class ScCourse implements Serializable {
    @ApiModelProperty("课程id")
    @TableId(type = IdType.AUTO)
    private Integer courseId;
    @ApiModelProperty("教师id")
    private String teacherId;
    @ApiModelProperty("课程名字")
    private String courseName;
    @ApiModelProperty("学分")
    private Double courseCredit;
    @ApiModelProperty("教室")
    private Integer courseClass;
    @ApiModelProperty("课程介绍")
    private String courseDesc;
    @ApiModelProperty("课程总数量")
    private Integer courseNum;
    @ApiModelProperty("课程图片")
    private String courseImg;
    @ApiModelProperty("课程剩余数量")
    private Integer courseRenum;

    private static final long serialVersionUID = 1L;
}