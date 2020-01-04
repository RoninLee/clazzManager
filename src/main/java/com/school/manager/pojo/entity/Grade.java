package com.school.manager.pojo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author RoninLee
 * @description 年级
 */
@Data
public class Grade implements Serializable {
    private static final long serialVersionUID = -6145718171115830691L;

    /**
     * 主键id     primary key
     */
    private String id;

    /**
     * 年级名称
     */
    private String name;

    /**
     * 版本
     */
    private Long version;
}
