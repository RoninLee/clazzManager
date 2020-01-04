package com.school.manager.pojo.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author RoninLee
 * @description 学科请求对象
 */
@Data
@ApiModel("学科")
public class SubjectSaveReq implements Serializable {
    private static final long serialVersionUID = -4848400876441460846L;

    @NotBlank(message = "未知学科名称")
    @ApiModelProperty("学科名称")
    private String name;
}
