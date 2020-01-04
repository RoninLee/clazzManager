package com.school.manager.pojo.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author RoninLee
 * @description 章节管理请求对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("章节更新请求对象")
public class ChapterUpdateReq extends ChapterSaveReq implements Serializable {
    private static final long serialVersionUID = 4855975464389675517L;

    @NotBlank(message = "未知id")
    @ApiModelProperty("主键id     primary key")
    private String id;

    @ApiModelProperty("根节点id")
    private String rootId;

    @NotNull(message = "未知版本")
    @ApiModelProperty("版本")
    private Long version;
}
