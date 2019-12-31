package com.school.manager.enums;

/**
 * @author RoninLee
 * @description 状态码
 */
public enum StatusCode {
    /**
     * 状态码
     */
    ERROR(-200, "异常"),
    SUCCESS(200, "成功"),
    NO_LOGIN_INFO(9997, "无登录信息"),
    INVALID_TOKEN(9998, "无效token"),
    NO_PERMISSION(9999, "无权限"),
    LOGIN_FAILURE(100000, "用户名密码错误"),
    DATA_NOT_EXIST(100001, "信息不存在"),
    DATA_EXIST(100002, "数据重复"),
    REQUEST_IS_NULL(100003, "参数为空!"),
    FILE_EMPTY(200000, "文件为空"),
    FILE_UPLOAD_FAILURE(200001, "文件上传失败"),
    FILE_NO_SUFFIX(200002, "文件没后后缀名"),
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
