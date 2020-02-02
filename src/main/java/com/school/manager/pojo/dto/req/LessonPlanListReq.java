package com.school.manager.pojo.dto.req;

import com.school.manager.pojo.dto.common.PageReq;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author lizelong01
 * @description
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LessonPlanListReq extends PageReq {
    private static final long serialVersionUID = 2300110963267845727L;

    @ApiModelProperty("关联关系id")
    private String relationId;

    @ApiModelProperty("创建时间区间")
    private List<Date> createTime;
}
