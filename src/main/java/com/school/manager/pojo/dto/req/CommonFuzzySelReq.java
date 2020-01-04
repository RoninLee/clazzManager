package com.school.manager.pojo.dto.req;

import com.school.manager.pojo.dto.common.PageReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author RoninLee
 * @description 共用模糊查询对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("共用模糊查询对象")
public class CommonFuzzySelReq extends PageReq {
    private static final long serialVersionUID = -7815115271343897573L;
    @ApiModelProperty("模糊查询")
    private String name;
}
