package com.school.manager.pojo.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author lizelong
 */
@Data
public class AttachmentDownloadReq implements Serializable {

    private static final long serialVersionUID = -2517642936957214938L;

    @NotNull(message = "id不能为空")
    @ApiModelProperty("id")
    private String id;

    @NotNull
    @ApiModelProperty("附件类型：1:ppt;2:exercises")
    private int fileType;

}
