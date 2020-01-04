package com.school.manager.pojo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author RoninLee
 * @description 用户角色
 */
@Data
public class UserRole implements Serializable {
    private static final long serialVersionUID = 7257929951689423884L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 人员ID
     */
    private String userId;

    /**
     * 角色ID
     */
    private String roleId;
}
