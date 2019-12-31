package com.school.manager.jwt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.school.manager.common.constant.RequestConstant;
import com.school.manager.entity.LoginUserInfo;
import com.school.manager.enums.StatusCode;
import com.school.manager.exception.SysServiceException;
import com.school.manager.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * @author RoninLee
 * @description JWT拦截器
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {
    private static final InheritableThreadLocal<LoginUserInfo> LOCAL_CONTEXT = new InheritableThreadLocal<>();

    @Autowired
    private JwtUtil jwtUtil;

    private String loginToken;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // TODO: 2019/12/30 无论如何都放行，只是单纯验证token是否合法
        String header = request.getHeader(RequestConstant.AUTHORIZATION);
        // 验证是否存在token
        if (StringUtils.isNotBlank(header)) {
            // 验证是否token是否存在约定的开头
            if (header.startsWith(RequestConstant.BEARER)) {
                String token = header.substring(RequestConstant.BEARER.length());
                loginToken = token;
                try {
                    // 根据token获取用户信息
                    Claims claims = jwtUtil.parseJwt(token);
                    LoginUserInfo loginUserInfo = JSON.parseObject(JSON.toJSONString(claims.get(RequestConstant.LOGIN_USER_INFO)), new TypeReference<LoginUserInfo>() {});
                    setLocalContext(loginUserInfo);
                } catch (Exception e) {
                    throw new SysServiceException(StatusCode.INVALID_TOKEN.getDesc());
                }
            } else {
                throw new SysServiceException(StatusCode.NO_PERMISSION.getDesc());
            }
        } else {
            throw new SysServiceException(StatusCode.NO_PERMISSION.getDesc());
        }
        return true;
    }

    private static void setLocalContext(LoginUserInfo loginUserInfo) {
        Optional.ofNullable(loginUserInfo).ifPresent(LOCAL_CONTEXT::set);
    }

    static Optional<LoginUserInfo> getLocalContext() {
        return Optional.ofNullable(LOCAL_CONTEXT.get());
    }

    static void remove() {
        LOCAL_CONTEXT.remove();
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        remove();
    }
}
