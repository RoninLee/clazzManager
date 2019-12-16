package com.school.manager.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author RoninLee
 * @description 人员角色年级学科绑定请求对象
 */
@Data
@ApiModel("人员角色年级学科关联关系")
public class UserRelationReq implements Serializable {
    private static final long serialVersionUID = 3398608512030202555L;
    @ApiModelProperty("用户id")
    private Long userId;
    @ApiModelProperty("角色id数组")
    private List<Long> roles;
    @ApiModelProperty("年级学科关联关系对象数组")
    private List<GradeSubjectReq> gradeSubject;
}
