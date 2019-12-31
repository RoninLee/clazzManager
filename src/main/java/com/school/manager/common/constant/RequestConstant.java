package com.school.manager.common.constant;

/**
 * @author RoninLee
 * @description 请求常量类
 */
public interface RequestConstant {
    /**
     * 请求头中存放token的属性名
     */
    String AUTHORIZATION = "Authorization";
    /**
     * token以Bearer 开头
     */
    String BEARER = "Bearer ";
    /**
     * 用户信息自定义属性
     */
    String LOGIN_USER_INFO = "loginUserInfo";
}
