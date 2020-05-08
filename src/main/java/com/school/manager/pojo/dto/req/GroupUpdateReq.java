package com.school.manager.pojo.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author lizelong
 */
@ApiModel("更新组请求对象")
@EqualsAndHashCode(callSuper = true)
@Data
public class GroupUpdateReq extends GroupAddReq {
    private static final long serialVersionUID = 6170452133672392862L;

    @NotBlank(message = "未知组")
    @ApiModelProperty("主键ID")
    private String id;

    @NotNull(message = "未知版本")
    @ApiModelProperty("版本")
    private Long version;
}
