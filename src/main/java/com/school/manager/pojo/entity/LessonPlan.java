package com.school.manager.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

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
     * 用户年级学科关系id
     */
    private String relationId;

    /**
     * 用户年级学科关系name
     */
    private String relationName;

    /**
     * 人员id
     */
    private String userId;

    /**
     * 章名称
     */
    private String chapter;

    /**
     * 节名称
     */
    private String section;

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

    /**
     * 创建时间
     */
    private Date createTime;

}
