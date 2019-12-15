package com.school.manager.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author RoninLee
 * @description 共用查询请求对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("共用请求对象")
public class CommonSelOrDelReq<T> extends PageReq {
    private static final long serialVersionUID = 1457401419885150422L;
    @ApiModelProperty("id")
    private T id;
}
