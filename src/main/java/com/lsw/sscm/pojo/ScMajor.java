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
 * @TableName sc_major
 */
@TableName(value ="sc_major")
@Data
@ApiModel("专业信息表")
public class ScMajor implements Serializable {
    @TableId
    @ApiModelProperty("专业id")
    private Integer majorId;
    @ApiModelProperty("学院id")
    private String deptId;
    @ApiModelProperty("专业名字")
    private String majorName;
    @ApiModelProperty("专业主任")
    private String majorAssistant;
    @ApiModelProperty("主任电话")
    private String majorTel;
    @ApiModelProperty("专业简介")
    private String majorDesc;
    @TableField(exist = false)
    private Integer sumStu;
    private static final long serialVersionUID = 1L;
}