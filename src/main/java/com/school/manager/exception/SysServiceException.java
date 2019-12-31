package com.school.manager.exception;

import com.google.common.collect.Maps;
import com.school.manager.enums.StatusCode;
import lombok.Getter;

import java.util.Map;

/**
 * @author RoninLee
 * @description 业务异常
 */
@Getter
public class SysServiceException extends RuntimeException {
    private static final long serialVersionUID = -1056917194450002215L;
    private Integer code;
    private String message;
    private Map<String, String> data = Maps.newHashMap();

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public SysServiceException(String message) {
        this.code = StatusCode.ERROR.getCode();
        this.message = message;
    }

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public SysServiceException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public SysServiceException(Integer code, String message, Map<String, String> data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public SysServiceException(String message, Map<String, String> data) {
        this.code = StatusCode.ERROR.getCode();
        this.message = message;
        this.data = data;
    }
}
