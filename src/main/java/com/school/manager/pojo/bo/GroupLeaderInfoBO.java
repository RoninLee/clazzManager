package com.school.manager.pojo.bo;

import com.school.manager.pojo.entity.Group;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lizelong
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GroupLeaderInfoBO extends Group {
    private static final long serialVersionUID = 2808855575142055821L;

    private String leaderId;
}
