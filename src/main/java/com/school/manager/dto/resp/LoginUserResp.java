package com.school.manager.dto.resp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author RoninLee
 * @description 登录人员信息
 */
@Data
@AllArgsConstructor
@ApiModel("登录用户信息")
public class LoginUserResp {
    private static final long serialVersionUID = 3282620031226162308L;

    @ApiModelProperty("主键id")
    private String id;

    @ApiModelProperty("名字")
    private String name;

    @ApiModelProperty("工号")
    private String jobNumber;

    @ApiModelProperty("状态")
    private Integer state;

    @ApiModelProperty("是否组长")
    private Boolean groupLeaderFlag;

    @ApiModelProperty("是否管理员")
    private Boolean adminFlag;


    @JsonIgnore
    private String pwd;

    public LoginUserResp(String id, String name, String jobNumber, String pwd) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.jobNumber = jobNumber;
    }
}
