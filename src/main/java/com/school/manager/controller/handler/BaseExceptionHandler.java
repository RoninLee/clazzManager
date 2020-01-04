package com.school.manager.controller.handler;

import com.school.manager.pojo.dto.common.Result;
import com.school.manager.enums.StatusCode;
import com.school.manager.exception.SysServiceException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author RoninLee
 * @description 异常拦截
 */
@RestControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result<Object> exception(Exception e) {
        e.printStackTrace();
        if (e instanceof DataIntegrityViolationException) {
            return Result.error(StatusCode.DATA_EXIST.getCode(), StatusCode.DATA_EXIST.getDesc(), null);
        } else if (e instanceof SysServiceException) {

            return Result.error(((SysServiceException) e).getCode(), e.getMessage(), ((SysServiceException) e).getData());
        }
        return Result.error(StatusCode.ERROR.getCode(), e.getMessage(), null);
    }
}
