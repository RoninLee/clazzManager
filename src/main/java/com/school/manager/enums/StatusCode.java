package com.school.manager.enums;

/**
 * @author RoninLee
 * @description 状态码
 */
public enum StatusCode {
    /**
     * 状态码
     */
    success(200, "成功"),
    error(-200, "异常"),
    DATA_NOT_EXIST(100001, "信息不存在"),
    ;
    private Integer code;
    private String desc;

    StatusCode(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
