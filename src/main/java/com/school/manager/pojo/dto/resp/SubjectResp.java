package com.school.manager.pojo.dto.resp;

import com.school.manager.pojo.entity.Subject;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author RoninLee
 * @description 学科响应对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("学科")
public class SubjectResp extends Subject {
    private static final long serialVersionUID = 6933404768460116260L;
}
