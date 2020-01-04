package com.school.manager.pojo.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author RoninLee
 * @description 用户信息
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("用户请求对象")
public class UserUpdateReq extends UserSaveReq implements Serializable {
    private static final long serialVersionUID = 3000236649439495201L;

    @NotBlank(message = "未知用户")
    @ApiModelProperty("id")
    private String id;

    @NotNull(message = "未知版本")
    @ApiModelProperty("版本")
    private Long version;
}
