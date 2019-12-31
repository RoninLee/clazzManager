package com.school.manager.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author RoninLee
 * @description 登录请求对象
 */
@Data
@ApiModel("登录请求对象")
public class LoginReq implements Serializable {
    private static final long serialVersionUID = 5992111510622867998L;

    @NotBlank(message = "登录账号不能为空")
    @ApiModelProperty("登录账号")
    private String jobNumber;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty("用户密码")
    private String password;
}
