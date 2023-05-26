package com.lsw.sscm.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @TableName sc_teacher
 */
@TableName(value ="sc_teacher")
@Data
@ApiModel("老师信息表")
public class ScTeacher implements Serializable {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("老师id")
    private Integer teacherId;
    @ApiModelProperty("学院id")
    private String deptId;
    @ApiModelProperty("老师名字")
    private String teacherName;
    @ApiModelProperty("老师性别")
    private String teacherSex;
    @ApiModelProperty("老师生日")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date teacherBirthday;
    @ApiModelProperty("老师称呼")
    private String teacherTitle;
    @ApiModelProperty("老师密码")
    private String teacherPassword;
    @ApiModelProperty("老师权限")
    private String teacherPermission;
    @ApiModelProperty("老师头像")
    private String teacherImg;
    private Integer courseId;

    @TableField(exist = false)
    private ScCourse scCourse;

    @TableField(exist = false)
    private ScDept scDept;



    private static final long serialVersionUID = 1L;
}