package com.school.manager.pojo.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author RoninLee
 * @description 人员年级学科绑定请求对象
 */
@Data
@ApiModel("人员年级学科关联关系")
public class UserGradeSubjectSaveReq implements Serializable {
    private static final long serialVersionUID = 3398608512030202555L;

    @NotBlank(message = "未知用户")
    @ApiModelProperty("用户id")
    private String userId;

    @NotNull(message = "未知年级")
    @ApiModelProperty("年级id")
    private String gradeId;

    @NotNull(message = "未知学科")
    @ApiModelProperty("学科id")
    private String subjectId;
}
