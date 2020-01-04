package com.school.manager.pojo.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author RoninLee
 * @description 用户信息
 */
@Data
@ApiModel("新增用户请求对象")
public class UserSaveReq implements Serializable {
    private static final long serialVersionUID = 3000236649439495201L;

    @ApiModelProperty("名称")
    @NotBlank(message = "名称不能为空")
    private String name;


    @ApiModelProperty("工号")
    @NotBlank(message = "工号不能为空")
    private String jobNumber;

    @ApiModelProperty("是否为组长")
    private Boolean groupLeaderFlag;
}
