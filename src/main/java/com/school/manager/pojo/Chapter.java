package com.school.manager.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author RoninLee
 * @description 章节
 */
@Data
@Entity
@Table(name = "chapter")
public class Chapter implements Serializable {
    private static final long serialVersionUID = 3469919845137476832L;
    @Id
    private Long id;
    /**
     * 章节名称
     */
    private String name;
}
