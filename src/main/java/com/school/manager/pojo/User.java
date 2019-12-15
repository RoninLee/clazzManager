package com.school.manager.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author RoninLee
 * @description 用户
 */
@Data
@Entity
@Table(name = "user")
public class User implements Serializable {
    private static final long serialVersionUID = 7131032465227610963L;

    @Id
    private Long id;
    /**
     * 名字
     */
    private String name;
    /**
     * 工号
     */
    private String jobNumber;
    /**
     * 状态
     */
    private Integer state;
}
