package com.school.manager.enums;

/**
 * @author RoninLee
 * @description 状态
 */
public enum StateEnum {
    /**
     * 状态枚举
     */
    invalid(0, "失效"),
    valid(1, "有效");

    private Integer code;
    private String desc;

    StateEnum(Integer code, String desc) {
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
