package com.school.manager.enums;

/**
 * @author RoninLee
 * @description 角色
 */
public enum RoleEnum {
    /**
     * 角色
     */
    ADMIN("-1", "admin"),
    GROUP_LEADER("1", "备课组长"),
    TEACHER("2", "教师"),
    ;
    private String code;
    private String desc;

    RoleEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
