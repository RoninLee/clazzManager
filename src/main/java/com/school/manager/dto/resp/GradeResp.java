package com.school.manager.dto.resp;

import com.school.manager.pojo.Grade;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author RoninLee
 * @description 年级响应对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("年级")
public class GradeResp extends Grade {
    private static final long serialVersionUID = 3412583975545943613L;
}
