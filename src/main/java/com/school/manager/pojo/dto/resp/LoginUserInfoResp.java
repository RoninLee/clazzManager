package com.school.manager.pojo.dto.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author lizelong
 */
@ApiModel("当前登录用户信息")
@Data
public class LoginUserInfoResp implements Serializable {
    private static final long serialVersionUID = 1544170036464699110L;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("工号")
    private String jobNumber;

    @ApiModelProperty("角色")
    private List<String> roleList;
}
