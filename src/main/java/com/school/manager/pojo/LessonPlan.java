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
    private String id;
    /**
     * 章节id
     */
    private String chapterId;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 在线文档内容
     */
    private String lessonPlanText;
    /**
     * ppt文件名
     */
    private String pptName;
    /**
     * ppt附件地址
     */
    private String pptUrl;
    /**
     * ppt存储的文件名
     */
    private String pptFileName;
    /**
     * 练习题文件名
     */
    private String exercisesName;
    /**
     * 练习题教案地址
     */
    private String exercisesUrl;
    /**
     * 练习题存储的文件名字
     */
    private String exercisesFileName;
}
