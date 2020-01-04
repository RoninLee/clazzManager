package com.school.manager.pojo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author RoninLee
 * @description 用户
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 7131032465227610963L;

    /**
     * 主键id     primary key
     */
    private String id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 工号
     */
    private String jobNumber;

    /**
     * 是否为组长1是 0否
     */
    private boolean groupLeaderFlag;

    /**
     * 版本
     */
    private Long version;
}
