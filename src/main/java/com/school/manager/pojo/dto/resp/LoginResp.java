package com.school.manager.pojo.dto.resp;

import com.school.manager.jwt.LoginUserInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author RoninLee
 * @description 登录返回对象
 */
@Data
@ApiModel("用户返回对象")
public class LoginResp implements Serializable {
    private static final long serialVersionUID = -759933130371054183L;
    @ApiModelProperty("用户信息")
    private LoginUserInfo loginUserInfo;
    @ApiModelProperty("token")
    private String token;
}
