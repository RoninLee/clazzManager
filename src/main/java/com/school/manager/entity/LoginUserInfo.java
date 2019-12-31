package com.school.manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.school.manager.pojo.UserGradeSubject;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author RoninLee
 * @description 登录用户信息
 */
@Data
public class LoginUserInfo implements Serializable {
    private static final long serialVersionUID = -4040824240078506101L;
    /**
     * 主键id
     */
    private String id;
    /**
     * 名字
     */
    private String name;
    /**
     * 工号
     */
    private String jobNumber;
    /**
     * 密码
     */
    @JsonIgnore
    private String password;
    /**
     * 状态
     */
    private Integer state;
    /**
     * 是否组长
     */
    private Boolean groupLeaderFlag;
    /**
     * 年级
     */
    private List<String> grades;

    /**
     * 学科
     */
    private List<String> subjects;

    /**
     * 用户学科年级绑定关系
     */
    private List<UserGradeSubject> userGradeSubjects;

    /**
     * 是否管理员 是true 否false
     */
    private Boolean adminFlag;
}
