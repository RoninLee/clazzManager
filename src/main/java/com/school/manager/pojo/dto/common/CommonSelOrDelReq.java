package com.school.manager.pojo.dto.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author RoninLee
 * @description 共用查询请求对象
 */
@Data
@ApiModel("共用请求对象")
public class CommonSelOrDelReq<T> {
    private static final long serialVersionUID = 1457401419885150422L;
    @NotNull(message = "id不能为空")
    @ApiModelProperty("id")
    private T id;
}
