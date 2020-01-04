package com.school.manager.pojo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author RoninLee
 * @description 章节
 */
@Data
public class Chapter implements Serializable {
    private static final long serialVersionUID = 3469919845137476832L;
    /**
     * 主键id     primary key
     */
    private String id;

    /**
     * 年级id
     */
    private String gradeId;

    /**
     * 学科id
     */
    private String subjectId;

    /**
     * 名称
     */
    private String name;

    /**
     * 父节点id
     */
    private String parentId;

    /**
     * 根节点id
     */
    private String rootId;

    /**
     * 版本
     */
    private Long version;
}
