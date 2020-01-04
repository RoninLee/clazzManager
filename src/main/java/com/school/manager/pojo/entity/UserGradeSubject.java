package com.school.manager.pojo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author RoninLee
 * @description 用户年级学科关联关系
 */
@Data
public class UserGradeSubject implements Serializable {
    private static final long serialVersionUID = 2725028113680219719L;
    
    /**
     * 主键id     primary key
     */
    private String id;

    /**
     * 人员id
     */
    private String userId;

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
