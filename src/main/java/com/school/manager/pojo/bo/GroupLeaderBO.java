package com.school.manager.pojo.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lizelong
 */
@Data
public class GroupLeaderBO implements Serializable {
    private static final long serialVersionUID = 6255662658101709438L;

    private String userName;
    private String groupName;
}
