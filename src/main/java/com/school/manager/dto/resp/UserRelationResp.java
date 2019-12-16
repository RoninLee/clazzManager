package com.school.manager.dto.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author RoninLee
 * @description 用户关联关系响应对象
 */
@Data
@ApiModel("用户关联关系相应对象")
public class UserRelationResp implements Serializable {
    private static final long serialVersionUID = -2327072156583767788L;
    @ApiModelProperty("用户id")
    private Long userId;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("工号")
    private String jobNumber;
    @ApiModelProperty("角色id数组")
    private List<Long> roles;
    @ApiModelProperty("年级学科对象数组")
    private List<GradeSubjectResp> gradeSubject;
}
