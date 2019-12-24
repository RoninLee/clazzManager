package com.school.manager.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * @author RoninLee
 * @description 用户信息
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("用户请求对象")
public class UserReq extends PageReq {
    private static final long serialVersionUID = 3000236649439495201L;
    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("名称")
    @NotBlank(message = "名称不能为空")
    private String name;

    @ApiModelProperty("密码")
    private String pwd;

    @ApiModelProperty("工号")
    @NotBlank(message = "工号不能为空")
    private String jobNumber;

    @ApiModelProperty("是否为组长")
    private Boolean isGroupLeader;

    @ApiModelProperty("状态")
    private Integer state;
}
