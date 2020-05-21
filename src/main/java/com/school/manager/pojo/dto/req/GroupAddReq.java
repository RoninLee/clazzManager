package com.school.manager.pojo.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;

/**
 * @author lizelong
 * @description 组列表响应对象
 */
@ApiModel("组列表响应对象")
@Data
public class GroupAddReq implements Serializable {
    private static final long serialVersionUID = -3692031489884869477L;

    @NotBlank(message = "未知组名")
    @ApiModelProperty("组名称")
    private String name;

    @NotBlank(message = "未知组长")
    @ApiModelProperty("组长ID")
    private String leaderId;

    @NotBlank(message = "未知年级")
    @ApiModelProperty("年级ID")
    private String gradeId;

    @NotBlank(message = "未知学科")
    @ApiModelProperty("学科ID")
    private String subjectId;

    @ApiModelProperty("组员ID列表")
    private Set<String> members;

}
