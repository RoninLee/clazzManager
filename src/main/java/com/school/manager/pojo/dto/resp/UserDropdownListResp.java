package com.school.manager.pojo.dto.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author RoninLee
 * @description 用户下拉列表响应对象
 */
@Data
@ApiModel("用户下拉列表响应对象")
public class UserDropdownListResp implements Serializable {
    private static final long serialVersionUID = 4033410387368550506L;

    @ApiModelProperty("主键id")
    private String id;
    @ApiModelProperty("名字")
    private String name;
    @ApiModelProperty("工号")
    private String jobNumber;
}
