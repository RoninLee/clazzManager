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

    WRONG_PASSWORD(9995, "密码错误"),

    LOGIN_FAILURE(9996, "用户名密码错误"),

    NO_LOGIN_INFO(9997, "无登录信息"),

    INVALID_TOKEN(9998, "无效token"),

    NO_PERMISSION(9999, "无权限"),

    REQUEST_IS_NULL(100000, "参数为空!"),

    DATA_NOT_EXIST(100001, "信息不存在"),

    DATA_EXIST(100002, "数据重复"),

    PARENT_NOT_EXIST(100003, "父节点不存在"),

    EXIST_CHILD_NODE(100008, "当前节点存在子节点"),

    SAVE_FAILED(100004, "新增失败"),

    UPDATE_FAILED(100005, "更新失败"),

    DELETE_FAILED(100006, "删除失败"),

    DATA_CHANGED(100007, "数据内容发生变化，请刷新数据后再行提交"),

    FILE_EMPTY(200000, "文件为空"),

    FILE_UPLOAD_FAILURE(200001, "文件上传失败"),

    FILE_NO_SUFFIX(200002, "文件没后后缀名"),

    JOB_NUMBER_EXIST(300000, "工号已存在"),

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
