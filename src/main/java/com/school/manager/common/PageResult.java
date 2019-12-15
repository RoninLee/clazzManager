package com.school.manager.common;

import com.school.manager.enums.StatusCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author RoninLee
 * @description 分页返回对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PageResult<T> extends Result<T> {
    private static final long serialVersionUID = -6819637503391799995L;
    private Long total;

    public PageResult() {
        super();
    }

    public PageResult(Integer code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public static <T> PageResult<T> error(String msg) {
        return new PageResult<T>(StatusCode.error.getCode(), msg);
    }

    public static <T> PageResult<T> success(T result, Long total) {
        PageResult<T> pageResult = new PageResult<T>();
        pageResult.setTotal(total);
        pageResult.setData(result);
        return pageResult;
    }

}
