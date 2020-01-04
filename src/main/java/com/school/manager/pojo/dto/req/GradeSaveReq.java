package com.school.manager.pojo.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author RoninLee
 * @description 年级请求对象
 */
@Data
@ApiModel("年级保存请求对象")
public class GradeSaveReq implements Serializable {
    private static final long serialVersionUID = 7547307610574457880L;

    @NotBlank(message = "年级名称不能为空")
    @ApiModelProperty("年级名称")
    private String name;
}
