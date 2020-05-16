package com.school.manager.pojo.dto.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lizelong
 * @description 组模块课程列表响应对象
 */
@ApiModel("组模块课程列表响应对象")
@Data
public class GroupLessonListResp implements Serializable {
    private static final long serialVersionUID = -4338482090849276682L;

    @ApiModelProperty("前端要求唯一ID：年级ID+学科ID")
    private String lessonId;

    @ApiModelProperty(value = "年级ID")
    private String gradeId;

    @ApiModelProperty(value = "学科ID")
    private String subjectId;

    @ApiModelProperty(value = "课程名称", example = "八年级语文")
    private String lessonName;
}
