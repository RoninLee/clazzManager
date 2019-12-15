package com.school.manager.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author RoninLee
 * @description 教案
 */
@Data
@Entity
@Table(name = "lesson_plan")
public class LessonPlan implements Serializable {
    private static final long serialVersionUID = -4704148842993975785L;
    @Id
    private Long id;
    /**
     * 章节id
     */
    private Long chapterId;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 在线文档内容
     */
    private String lessonPlanText;
    /**
     * ppt附件地址
     */
    private String pptUrl;
    /**
     * 练习题教案地址
     */
    private String exercisesUrl;
}
