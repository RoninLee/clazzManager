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
 * @description 学科请求对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("学科")
public class SubjectUpdateReq extends SubjectSaveReq implements Serializable {
    private static final long serialVersionUID = -4848400876441460846L;

    @NotBlank(message = "未知学科")
    @ApiModelProperty("主键id")
    private String id;

    @NotNull(message = "未知版本")
    @ApiModelProperty("id")
    private Long version;
}
