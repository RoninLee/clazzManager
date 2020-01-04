package com.school.manager.pojo.dto.req;

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
public class ChapterSaveReq implements Serializable {
    private static final long serialVersionUID = 4855975464389675517L;

    @NotBlank(message = "未知年级")
    @ApiModelProperty("年级id")
    private String gradeId;

    @NotBlank(message = "未知学科")
    @ApiModelProperty("学科id")
    private String subjectId;

    @NotBlank(message = "未知章节名称")
    @ApiModelProperty("名称")
    private String name;

    @NotBlank(message = "未知上级节点")
    @ApiModelProperty("父节点id")
    private String parentId;

}
