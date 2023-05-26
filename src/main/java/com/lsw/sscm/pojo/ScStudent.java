package com.lsw.sscm.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @TableName sc_student
 */
@TableName(value ="sc_student")
@Data
@ApiModel("学生信息表")
public class ScStudent implements Serializable {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("学生id")
    private Integer studentId;
    @ApiModelProperty("专业id")
    private String majorId;
    @ApiModelProperty("学生名字")
    private String studentName;
    @ApiModelProperty("学生性别")
    private String studentSex;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("学生生日")
    private Date studentBirthday;
    @ApiModelProperty("学生密码")
    private String studentPassword;
    @ApiModelProperty("学生头像")
    private String studentImg;
    @TableField(exist = false)
    private ScMajor scMajor;
    @TableField(exist = false)
    private List<ScStuscore> scStuscore;
    @TableField(exist = false)
    private List<ScCourse> scCourse;


    private static final long serialVersionUID = 1L;
}