package com.school.manager.jwt;

/**
 * @author RoninLee
 * @description 登录用户信息工具
 */
public class LoginUserUtil {

    /**
     * 获取token中存的登录人信息
     *
     * @return 登录人信息
     */
    public static LoginUserInfo getLoginUserInfo() {
        return JwtInterceptor.getLocalContext().orElse(null);
    }

    /**
     * 删除登录人信息
     */
    public static void loginout() {

        JwtInterceptor.remove();
    }

}
