package com.school.manager.pojo.dto.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lizelong01
 * @description 教案列表响应对象
 */
@Data
public class LessonPlanListResp implements Serializable {
    private static final long serialVersionUID = 4225568798940521179L;
    @ApiModelProperty("主键id     primary key")
    private String id;

    @ApiModelProperty("用户年级学科关系id")
    private String relationId;

    @ApiModelProperty("用户年级学科关系name")
    private String relationName;

    @ApiModelProperty("章名称")
    private String chapter;

    @ApiModelProperty("节名称")
    private String section;

    @ApiModelProperty("ppt附件名")
    private String pptName;

    @ApiModelProperty("练习题附件名")
    private String exercisesName;

    @ApiModelProperty("创建时间")
    private Date createTime;

}
