package com.school.manager.controller.handler;

import com.school.manager.common.Result;
import com.school.manager.enums.StatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author RoninLee
 * @description 异常拦截
 */
@RestControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result exception(Exception e) {
        e.printStackTrace();
        return Result.error(StatusCode.error.getCode(), e.getMessage(), null);
    }
}
