package com.school.manager.pojo.dto.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lizelong
 */
@Data
public class LessonStatisticsInfoResp implements Serializable {
    private static final long serialVersionUID = -5633969487156488543L;

    @ApiModelProperty("老师姓名")
    private String userName;

    @ApiModelProperty("老师账号")
    private String jobNumber;

    @ApiModelProperty("备课数量")
    private Integer count;
}
