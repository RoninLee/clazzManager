package com.school.manager.dto.req;

import java.io.Serializable;

/**
 * @author RoninLee
 * @description 登录请求对象
 */
public class LoginReq implements Serializable {
    private static final long serialVersionUID = -765120192311297838L;
    private String jobNumber;
    private String pwd;
    // TODO: 2019/12/25 后期将登录对象换成此对象
}
