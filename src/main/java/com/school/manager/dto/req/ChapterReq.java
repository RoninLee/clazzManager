package com.school.manager.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author RoninLee
 * @description 章节管理请求对象
 */
@Data
@ApiModel("章节管理请求对象")
public class ChapterReq implements Serializable {
    private static final long serialVersionUID = 4855975464389675517L;
    @ApiModelProperty("年级id")
    @NotBlank(message = "未知年级")
    private String gradeId;

    @ApiModelProperty("学科id")
    @NotBlank(message = "未知学科")
    private String subjectId;

    @ApiModelProperty("章节名称")
    private String name;

    // TODO: 2020/1/1 待定要不要树状结构 存parentId
}
