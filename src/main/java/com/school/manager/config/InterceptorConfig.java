package com.school.manager.config;

import com.school.manager.jwt.AdminInterceptor;
import com.school.manager.jwt.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author RoninLee
 * @description 拦截器配置
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {
    @Autowired
    private JwtInterceptor jwtInterceptor;
    @Autowired
    private AdminInterceptor adminInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器要声明拦截器对象和拦截器的请求
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login");

        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/user/**")
                .addPathPatterns("/grade/**")
                .addPathPatterns("/subject/**")
                .addPathPatterns("/userRelation/**")
                .excludePathPatterns("/user/login");
    }
}