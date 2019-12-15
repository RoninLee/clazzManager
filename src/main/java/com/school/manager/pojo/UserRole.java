package com.school.manager.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author RoninLee
 * @description 用户角色
 */
@Data
@Entity
@Table(name = "user_role")
public class UserRole implements Serializable {
    private static final long serialVersionUID = 7257929951689423884L;

    @Id
    private Long id;
    /**
     * 人员ID
     */
    private Long userId;
    /**
     * 角色ID
     */
    private Long roleId;
}
