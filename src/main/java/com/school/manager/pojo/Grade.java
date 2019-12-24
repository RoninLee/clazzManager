package com.school.manager.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author RoninLee
 * @description 年级
 */
@Data
@Entity
@Table(name = "grade")
public class Grade implements Serializable {
    private static final long serialVersionUID = -6145718171115830691L;
    @Id
    private String id;
    /**
     * 年级名称
     */
    private String name;
}
