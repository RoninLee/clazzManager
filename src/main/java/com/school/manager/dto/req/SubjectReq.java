package com.school.manager.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author RoninLee
 * @description 学科请求对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("学科")
public class SubjectReq extends PageReq {
    private static final long serialVersionUID = -4848400876441460846L;
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("学科名称")
    private String name;
}
