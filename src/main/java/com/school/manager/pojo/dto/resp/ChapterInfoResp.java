package com.school.manager.pojo.dto.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author RoninLee
 * @description 章节详细信息响应对象
 */
@Data
@ApiModel("章节响应对象")
public class ChapterInfoResp implements Serializable {
    private static final long serialVersionUID = 1313015915783059546L;

    @ApiModelProperty("主键id     primary key")
    private String id;

    @ApiModelProperty("年级id")
    private String gradeId;

    @ApiModelProperty("学科id")
    private String subjectId;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("父节点id")
    private String parentId;

    @ApiModelProperty("根节点id")
    private String rootId;

    @ApiModelProperty("版本")
    private Long version;
}
