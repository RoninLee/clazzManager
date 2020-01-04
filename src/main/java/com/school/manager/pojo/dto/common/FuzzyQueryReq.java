package com.school.manager.pojo.dto.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author RoninLee
 * @description 模糊查询请求对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("模糊查询请求对象")
public class FuzzyQueryReq extends PageReq implements Serializable {
    private static final long serialVersionUID = -5071577326810129940L;
    @ApiModelProperty("模糊查询")
    private String name;
}
