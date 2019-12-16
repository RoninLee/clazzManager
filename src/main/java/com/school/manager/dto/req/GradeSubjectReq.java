package com.school.manager.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author RoninLee
 * @description 年级学科关联关系请求对象
 */
@Data
@ApiModel("年级学科关联关系")
public class GradeSubjectReq implements Serializable {
    private static final long serialVersionUID = -3737079155090328814L;
    @ApiModelProperty("年级id")
    private Long gradeId;
    @ApiModelProperty("学科id")
    private Long subjectId;
}
