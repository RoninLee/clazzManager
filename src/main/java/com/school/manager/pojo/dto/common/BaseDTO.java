package com.school.manager.pojo.dto.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author RoninLee
 * @description 基础相应对象
 */
@Data
@ApiModel("基础响应对象")
public class BaseDTO<T> implements Serializable {
    private static final long serialVersionUID = 8046797262421130147L;

    @ApiModelProperty("主键id")
    private T id;

    @ApiModelProperty("名字")
    private String name;
}
