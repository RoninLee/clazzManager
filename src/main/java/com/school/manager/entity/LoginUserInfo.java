package com.school.manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.school.manager.enums.RoleEnum;
import com.school.manager.pojo.UserGradeSubject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * @author RoninLee
 * @description 登录用户信息
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LoginUserInfo extends UserInfo implements UserDetails {
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

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new HashSet<>();

        // 管理员else普通教师
        if (adminFlag) {
            collection.add(new SimpleGrantedAuthority(RoleEnum.ADMIN.getDesc()));
        } else {
            collection.add(new SimpleGrantedAuthority(RoleEnum.TEACHER.getDesc()));
        }
        // 备课组组长
        if (super.getGroupLeaderFlag()) {
            collection.add(new SimpleGrantedAuthority(RoleEnum.GROUP_LEADER.getDesc()));
        }
        return collection;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
