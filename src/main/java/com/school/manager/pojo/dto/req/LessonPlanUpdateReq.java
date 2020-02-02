package com.school.manager.pojo.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lizelong01
 * @description 更新教案请求对象
 */
@Data
public class LessonPlanUpdateReq implements Serializable {
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
