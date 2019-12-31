package com.school.manager.common.resp;

import com.school.manager.enums.StatusCode;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Objects;


/**
 * 前后交互对象
 *
 * @author RoninLee
 */
@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -1686054524991742104L;
    protected Integer code = StatusCode.SUCCESS.getCode();
    protected String msg = StatusCode.SUCCESS.getDesc();
    protected T data;

    public Result() {
        super();
    }

    public Result(Integer code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public static <T> Result<T> error(String msg) {
        return new Result<>(StatusCode.ERROR.getCode(), msg);
    }

    public static <T> Result<T> error(String msg, T data) {
        Result<T> result = new Result<>(StatusCode.ERROR.getCode(), msg);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> error(Integer code, String msg, T data) {
        Result<T> result = new Result<>(code, msg);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> success() {
        return success(StatusCode.SUCCESS.getDesc(), null);
    }

    public static <T> Result<T> success(String msg) {
        return success(msg, null);
    }

    public static <T> Result<T> success(T data) {
        return success(StatusCode.SUCCESS.getDesc(), data);
    }

    public static <T> Result<T> success(String msg, T data) {
        Result<T> result = new Result<>();
        if (StringUtils.isNotBlank(msg)) {
            result.setMsg(msg);
            result.setCode(StatusCode.SUCCESS.getCode());
        }
        result.setData(data);
        return result;
    }

    /**
     * 判断结果集是否返回成功
     */
    public boolean isSuccess() {
        return Objects.equals(this.code, StatusCode.SUCCESS.getCode());
    }
}
