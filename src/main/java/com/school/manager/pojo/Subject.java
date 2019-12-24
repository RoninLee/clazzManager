package com.school.manager.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author RoninLee
 * @description 学科
 */
@Data
@Entity
@Table(name = "subject")
public class Subject implements Serializable {
    private static final long serialVersionUID = 3478258385162336751L;
    @Id
    private String id;
    /**
     * 学科名称
     */
    private String name;
}
