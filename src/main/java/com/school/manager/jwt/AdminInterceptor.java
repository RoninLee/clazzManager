package com.school.manager.jwt;

import com.school.manager.entity.LoginUserInfo;
import com.school.manager.enums.StatusCode;
import com.school.manager.exception.SysServiceException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @author RoninLee
 * @description 管理员拦截实现
 */
@Component
public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LoginUserInfo loginUserInfo = LoginUserUtil.getLoginUserInfo();
        if (Objects.nonNull(loginUserInfo)) {
            if (!loginUserInfo.getAdminFlag()) {
                throw new SysServiceException(StatusCode.NO_PERMISSION.getCode(), StatusCode.NO_PERMISSION.getDesc());
            }
        } else {
            throw new SysServiceException(StatusCode.NO_PERMISSION.getCode(), StatusCode.NO_PERMISSION.getDesc());
        }
        return true;
    }
}
