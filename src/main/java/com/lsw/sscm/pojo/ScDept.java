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
 * @TableName sc_dept
 */
@TableName(value ="sc_dept")
@Data
@ApiModel("学院信息表")
public class ScDept implements Serializable {
    @TableId
    @ApiModelProperty("学院id")
    private Integer deptId;
    @ApiModelProperty("学院名称")
    private String deptName;
    @ApiModelProperty("院长名字")
    private String deptChairman;
    @ApiModelProperty("院长电话")
    private String deptTel;
    @ApiModelProperty("学院简介")
    private String deptDesc;
    @TableField(exist = false)
    private int sumTea;

    private static final long serialVersionUID = 1L;
}