package com.school.manager.pojo.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lizelong
 */
@Data
public class GroupInfoBO implements Serializable {
    private static final long serialVersionUID = -2894808162289809157L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 组名称
     */
    private String name;

    /**
     * 版本
     */
    private Long version;

    /**
     * 人员ID
     */
    private String userId;

    /**
     * 人员名称
     */
    private String userName;

    /**
     * 年级ID
     */
    private String gradeId;

    /**
     * 学科ID
     */
    private String subjectId;

    /**
     * 课程名称
     */
    private String lessonName;

    /**
     * 组长标志
     */
    private Boolean groupLeaderFlag;
}
