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

    @ApiModelProperty("上传文件的ppt名字")
    private String pptName;

    @ApiModelProperty("上传后的ppt文件名")
    private String pptFileName;

    @ApiModelProperty("上传的练习题名字")
    private String exercisesName;

    @ApiModelProperty("上传后的练习题名字")
    private String exercisesFileName;

    @ApiModelProperty("创建时间")
    private Date createTime;

}
