package com.school.manager.pojo.entity;

import lombok.Data;

import java.io.Serializable;


/**
 * @author lizelong
 * @description 组
 */
@Data
public class Group implements Serializable {
    private static final long serialVersionUID = 4699999070975506249L;

    /**
     * 主键     primary key
     */
    private String id;

    /**
     * 组名
     */
    private String name;

    /**
     * 年级id
     */
    private String gradeId;

    /**
     * 学科id
     */
    private String subjectId;

    /**
     * 版本
     */
    private Long version;
}
