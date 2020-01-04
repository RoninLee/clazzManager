package com.school.manager.pojo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author RoninLee
 * @description 学科
 */
@Data
public class Subject implements Serializable {
    private static final long serialVersionUID = 3478258385162336751L;

    /**
     * 主键id     primary key
     */
    private String id;

    /**
     * 名字
     */
    private String name;

    /**
     * 版本
     */
    private Long version;
}
