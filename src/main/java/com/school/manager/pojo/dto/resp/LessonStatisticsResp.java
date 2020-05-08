package com.school.manager.pojo.dto.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author lizelong
 */
@Data
public class LessonStatisticsResp implements Serializable {
    private static final long serialVersionUID = -1086459949762772239L;

    @ApiModelProperty("组名")
    private String groupName;

    @ApiModelProperty("列表数据")
    private List<LessonStatisticsInfoResp> statisticsData;

}
