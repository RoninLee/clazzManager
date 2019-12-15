package com.school.manager.dto.resp;

import com.school.manager.pojo.User;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author RoninLee
 * @description 用户信息
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("用户返回对象")
public class UserResp extends User implements Serializable {
    private static final long serialVersionUID = -6673938853219935607L;

}
