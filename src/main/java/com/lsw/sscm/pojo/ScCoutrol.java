package com.lsw.sscm.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName sc_coutrol
 */
@TableName(value ="sc_coutrol")
@Data
public class ScCoutrol implements Serializable {
    private String ifTakeCourse;

    private String ifInputGrade;

    private static final long serialVersionUID = 1L;
}