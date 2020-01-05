package com.school.manager.pojo.dto.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author RoninLee
 * @description 用户关联关系响应对象
 */
@Data
@ApiModel("用户关联关系相应对象")
public class UserGradeSubjectResp implements Serializable {
    private static final long serialVersionUID = -2327072156583767788L;

    @ApiModelProperty("主键id")
    private String id;

    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("名称")
    private String username;

    @ApiModelProperty("工号")
    private String jobNumber;

    @ApiModelProperty("年级id")
    private String gradeId;

    @ApiModelProperty("年级名称")
    private String gradeName;

    @ApiModelProperty("学科id")
    private String subjectId;

    @ApiModelProperty("学科名称")
    private String subjectName;
}
