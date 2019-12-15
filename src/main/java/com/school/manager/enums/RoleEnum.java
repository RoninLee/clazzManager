package com.school.manager.enums;

/**
 * @author RoninLee
 * @description 角色
 */
public enum RoleEnum {
    /**
     * 角色
     */
    group_leader(1L, "备课组长"),
    teacher(2L, "教师"),
    ;
    private Long code;
    private String desc;

    RoleEnum(Long code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Long getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
