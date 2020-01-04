package com.school.manager.pojo.dto.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author RoninLee
 * @description 用户信息
 */
@Data
@ApiModel("用户返回对象")
public class UserResp implements Serializable {
    private static final long serialVersionUID = -6673938853219935607L;

    @ApiModelProperty("主键id     primary key")
    private String id;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("工号")
    private String jobNumber;

    @ApiModelProperty("是否为组长1是 0否")
    private boolean groupLeaderFlag;

    @ApiModelProperty("版本")
    private Long version;

    @ApiModelProperty("是否管理员,默认否")
    private Boolean adminFlag;

    public UserResp() {
        this.adminFlag = Boolean.FALSE;
    }
}
