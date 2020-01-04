package com.school.manager.pojo.dto.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author RoninLee
 * @description 年级响应对象
 */
@Data
@ApiModel("年级响应对象")
public class GradeResp implements Serializable {
    private static final long serialVersionUID = 3412583975545943613L;

    @NotBlank(message = "未知年级")
    @ApiModelProperty("主键id")
    private String id;

    @NotBlank(message = "年级名称不能为空")
    @ApiModelProperty("年级名称")
    private String name;

    @NotNull(message = "未知版本")
    @ApiModelProperty("版本")
    private Long version;
}
