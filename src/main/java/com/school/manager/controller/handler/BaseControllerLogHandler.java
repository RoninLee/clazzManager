package com.school.manager.controller.handler;

import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 切面输出日志
 *
 * @author RoninLee
 */
@Log4j2
@Aspect
@Component
public class BaseControllerLogHandler {

    @Pointcut("execution (* com.school.manager.controller.*Controller.*(..))")
    private void methodUrl() {
    }

    @Before("methodUrl()")
    public void logBefore(JoinPoint point) {
        Object[] arrObject = point.getArgs();
        log.info("{} >>> req={}", getApiOperationValue(point), arrObject);
    }

    @AfterReturning(pointcut = "methodUrl()", returning = "returnValue")
    public void logAfter(JoinPoint point, Object returnValue) {
        if (String.valueOf(returnValue).length() > 300) {
            returnValue = String.valueOf(returnValue).substring(0, 300) + "...";
        }
        log.info("{}操作成功 <<< resp={}", getApiOperationValue(point), returnValue);
    }

    @AfterThrowing(pointcut = "methodUrl()", throwing = "exc")
    public void serviceExceptionLog(JoinPoint point, ServiceException exc) {
        log.error("{}操作异常 <<<! msg={}", getApiOperationValue(point), exc.getMessage());
        throw exc;
    }

    /**
     * 获取Api名称
     */
    private String getApiOperationValue(JoinPoint point) {
        return Arrays.stream(point.getTarget().getClass().getDeclaredMethods()).filter(method -> StringUtils
                .equals(method.getName(), point.getSignature().getName()))
                .findFirst().map(method -> method.getAnnotation(ApiOperation.class).value()).orElse(point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName());
    }
}
