package com.school.manager.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
    private Long id;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("密码")
    private String pwd;
    @ApiModelProperty("工号")
    private String jobNumber;
    //@ApiModelProperty("角色")
    //private List<Long> role;
    @ApiModelProperty("状态")
    private Integer state;
}
