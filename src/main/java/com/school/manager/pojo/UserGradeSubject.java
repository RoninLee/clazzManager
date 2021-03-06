package com.school.manager.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author RoninLee
 * @description 用户年级学科关联关系
 */
@Data
@Entity
@Table(name = "user_grade_subject")
public class UserGradeSubject implements Serializable {
    private static final long serialVersionUID = 2725028113680219719L;
    @Id
    private String id;
    /**
     * 用户id
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
}
