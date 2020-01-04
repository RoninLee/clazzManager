package com.school.manager.pojo.dto.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author RoninLee
 * @description 分页请求对象
 */
@Data
@ApiModel("分页对象")
public class PageReq implements Serializable {
    private static final long serialVersionUID = 1666723749279768316L;

    @NotNull(message = "页码不能为空")
    @Min(1)
    @ApiModelProperty("页码")
    private Integer pageIndex;

    @NotNull(message = "每页容量不能为空")
    @Min(1)
    @Max(20)
    @ApiModelProperty("每页数量")
    private Integer pageSize;
}
