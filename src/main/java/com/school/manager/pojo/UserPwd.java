package com.school.manager.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author RoninLee
 * @description 用户密码
 */
@Data
@Entity
@Table(name = "user_pwd")
public class UserPwd implements Serializable {
    private static final long serialVersionUID = -4170020575014480457L;
    @Id
    private String id;
    /**
     * 密文密码
     */
    private String password;
}
