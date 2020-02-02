package com.school.manager.pojo.dto.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lizelong01
 * @description 教案详情响应对象
 */
@Data
public class LessonPlanInfoResp implements Serializable {
    private static final long serialVersionUID = 4225568798940521179L;
    @ApiModelProperty("主键id     primary key")
    private String id;

    @ApiModelProperty("用户年级学科关联关系id")
    private String relationId;

    @ApiModelProperty("在线文档内容")
    private String lessonPlanText;

    @ApiModelProperty("上传文件的ppt名字")
    private String pptName;

    @ApiModelProperty("ppt附件地址")
    private String pptUrl;

    @ApiModelProperty("上传后的ppt文件名")
    private String pptFileName;

    @ApiModelProperty("上传的练习题名字")
    private String exercisesName;

    @ApiModelProperty("练习题附件地址")
    private String exercisesUrl;

    @ApiModelProperty("上传后的练习题名字")
    private String exercisesFileName;

    @ApiModelProperty("版本")
    private Long version;

}
