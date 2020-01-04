package com.school.manager.pojo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author RoninLee
 * @description 用户密码
 */
@Data
public class UserPassword implements Serializable {
    private static final long serialVersionUID = -4170020575014480457L;

    /**
     * 共用user表id     primary key
     */
    private String id;

    /**
     * 密文密码
     */
    private String password;
}
