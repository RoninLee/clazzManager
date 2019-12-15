package com.school.manager.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author RoninLee
 * @description 分页请求对象
 */
@Data
@ApiModel("分页对象")
public class PageReq implements Serializable {
    private static final long serialVersionUID = 1666723749279768316L;
    @ApiModelProperty("页码")
    private Integer pageIndex;
    @ApiModelProperty("每页数量")
    private Integer pageSize;
}
