package com.school.manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * @author RoninLee
 * @description 用户信息
 */
@Data
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 5280530276619957149L;
    /**
     * 主键id
     */
    private String id;
    /**
     * 名字
     */
    private String username;
    /**
     * 工号
     */
    private String jobNumber;
    /**
     * 密码
     */
    @JsonIgnore
    private String password;
    /**
     * 状态
     */
    private Integer state;
    /**
     * 是否组长
     */
    private Boolean groupLeaderFlag;

}
