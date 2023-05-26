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
 * @TableName sc_stuscore
 */
@TableName(value ="sc_stuscore")
@Data
@ApiModel("学生成绩表")
public class ScStuscore implements Serializable {
    @ApiModelProperty("id")
    @TableId(type = IdType.AUTO)
    private Integer stuscoreId;
    @ApiModelProperty("学生id")
    private Integer studentId;
    @ApiModelProperty("课程id")
    private Integer courseId;
    @ApiModelProperty("老师id")
    private Integer teacherId;
    @ApiModelProperty("平时成绩")
    private Double dailyScore;
    @ApiModelProperty("期末成绩")
    private Double finalGrade;
    @ApiModelProperty("总成绩")
    private Double totalScore;
    @TableField(exist = false)
    private ScStudent scStudent;
    @TableField(exist = false)
    private ScCourse scCourse;

    private static final long serialVersionUID = 1L;
}