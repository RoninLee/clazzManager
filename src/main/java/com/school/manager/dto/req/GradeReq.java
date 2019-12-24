package com.school.manager.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author RoninLee
 * @description 年级请求对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("年级")
public class GradeReq extends PageReq {
    private static final long serialVersionUID = 7547307610574457880L;
    @ApiModelProperty("id")
    private String id;
    @ApiModelProperty("年级名称")
    private String name;
}
