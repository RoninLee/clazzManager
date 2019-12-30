package com.school.manager.entity;

import com.school.manager.pojo.UserGradeSubject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author RoninLee
 * @description 登录用户信息
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LoginUserInfo extends UserInfo {
    private static final long serialVersionUID = -5333648458446313599L;

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
     * 是否管理员
     */
    private Boolean adminFlag;
}
