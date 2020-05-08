package com.school.manager.pojo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lizelong
 * @description 用户组关系表
 */
@Data
public class GroupUser implements Serializable {
    private static final long serialVersionUID = 2495085589040548339L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 组ID
     */
    private String groupId;
}
