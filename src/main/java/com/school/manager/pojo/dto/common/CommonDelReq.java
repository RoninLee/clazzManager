package com.school.manager.pojo.dto.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author lizelong
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommonDelReq<T> extends CommonIdReq<T> {
    private static final long serialVersionUID = 3986942117984004921L;

    @NotNull(message = "未知版本")
    @ApiModelProperty("版本")
    private Long version;
}
