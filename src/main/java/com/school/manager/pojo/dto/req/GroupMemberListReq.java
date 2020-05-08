package com.school.manager.pojo.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author lizelong
 * @description 组模块组员列表下拉请求对象
 */
@ApiModel("组模块组员列表下拉请求对象")
@Data
public class GroupMemberListReq implements Serializable {
    private static final long serialVersionUID = 1238214381402752073L;

    @NotBlank(message = "未知年级")
    @ApiModelProperty("年级ID")
    private String gradeId;

    @NotBlank(message = "未知学科")
    @ApiModelProperty("学科ID")
    private String subjectId;
}
