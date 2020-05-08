package com.school.manager.pojo.dto.resp;

import com.school.manager.pojo.dto.common.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author lizelong
 * @description 组列表响应对象
 */
@ApiModel("组列表响应对象")
@Data
public class GroupListInfoResp implements Serializable {
    private static final long serialVersionUID = 470637959772864669L;

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("组名称")
    private String name;

    @ApiModelProperty("组长ID")
    private String leaderId;

    @ApiModelProperty("组长名称")
    private String leaderName;

    @ApiModelProperty("课程ID")
    private String gradeId;

    @ApiModelProperty("课程ID")
    private String subjectId;

    @ApiModelProperty("课程名称")
    private String lessonName;

    @ApiModelProperty("组员列表")
    private List<BaseDTO<String>> members;

}
