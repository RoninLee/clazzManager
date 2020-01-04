package com.school.manager.pojo.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author RoninLee
 * @description 人员年级学科绑定请求对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("人员年级学科关联关系")
public class UserGradeSubjectUpdateSaveReq extends UserGradeSubjectSaveReq implements Serializable {
    private static final long serialVersionUID = 3398608512030202555L;

    @NotBlank(message = "未知关系")
    @ApiModelProperty("主键id")
    private String id;

    @NotNull(message = "未知版本")
    @ApiModelProperty("版本")
    private Long version;
}
