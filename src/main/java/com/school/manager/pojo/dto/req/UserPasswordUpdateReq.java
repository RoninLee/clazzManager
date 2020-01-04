package com.school.manager.pojo.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author RoninLee
 * @description 修改密码请求对象
 */
@Data
@ApiModel("修改密码请求对象")
public class UserPasswordUpdateReq implements Serializable {
    private static final long serialVersionUID = -3756826801112555934L;

    @NotBlank(message = "原密码不能为空")
    @ApiModelProperty("原始密码")
    private String oldPassword;

    @NotBlank(message = "新密码不能为空")
    @ApiModelProperty("新密码")
    private String newPassword;
}
