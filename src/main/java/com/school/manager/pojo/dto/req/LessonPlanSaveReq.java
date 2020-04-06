package com.school.manager.pojo.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author lizelong01
 * @description 新增教案请求对象
 */
@Data
public class LessonPlanSaveReq implements Serializable {
    private static final long serialVersionUID = 4225568798940521179L;

    @NotBlank(message = "未知课程")
    @ApiModelProperty("用户年级学科关联关系id")
    private String relationId;

    @NotBlank(message = "未知课程")
    @ApiModelProperty("用户年级学科关系name")
    private String relationName;

    @NotBlank(message = "未知章名称")
    @ApiModelProperty("章名称")
    private String chapter;

    @NotBlank(message = "未知节名称")
    @ApiModelProperty("节名称")
    private String section;

    @ApiModelProperty("在线文档内容")
    private String lessonPlanText;

    @ApiModelProperty("ppt附件名")
    private String pptName;

    @ApiModelProperty("ppt附件地址")
    private String pptUrl;

    @ApiModelProperty("练习题附件名")
    private String exercisesName;

    @ApiModelProperty("练习题附件地址")
    private String exercisesUrl;

}
