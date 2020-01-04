package com.school.manager.pojo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author RoninLee
 * @description 教案
 */
@Data
public class LessonPlan implements Serializable {
    private static final long serialVersionUID = -4704148842993975785L;

    /**
     * 主键id     primary key
     */
    private String id;

    /**
     * 章节id
     */
    private String sectionId;

    /**
     * 人员id
     */
    private String userId;

    /**
     * 在线文档内容
     */
    private String lessonPlanText;

    /**
     * 上传文件的ppt名字
     */
    private String pptName;

    /**
     * ppt附件地址
     */
    private String pptUrl;

    /**
     * 上传后的ppt文件名
     */
    private String pptFileName;

    /**
     * 上传的练习题名字
     */
    private String exercisesName;

    /**
     * 练习题附件地址
     */
    private String exercisesUrl;

    /**
     * 上传后的练习题名字
     */
    private String exercisesFileName;

    /**
     * 版本
     */
    private Long version;
}
