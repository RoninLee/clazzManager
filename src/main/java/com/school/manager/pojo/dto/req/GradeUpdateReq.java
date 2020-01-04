package com.school.manager.pojo.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author RoninLee
 * @description 年级请求对象
 */
@Data
@ApiModel("年级更新请求对象")
public class GradeUpdateReq implements Serializable {
    private static final long serialVersionUID = 7547307610574457880L;

    @NotBlank(message = "未知年级")
    @ApiModelProperty("主键id")
    private String id;

    @NotNull(message = "未知版本")
    @ApiModelProperty("版本")
    private Long version;
}
