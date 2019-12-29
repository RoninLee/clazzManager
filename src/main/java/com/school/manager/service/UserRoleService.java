package com.school.manager.service;

import com.school.manager.pojo.UserRole;

import java.util.List;

/**
 * @author RoninLee
 * @description 用户角色管理
 */
public interface UserRoleService {

    /**
     * 新增用户角色关联关系
     *
     * @param role 用户角色关联关系
     */
    void save(UserRole role);

    /**
     * 批量新增用户角色关联关系
     *
     * @param userRoleList 用户角色关联关系列表
     */
    void batchSave(List<UserRole> userRoleList);

    /**
     * 通过用户获取角色
     *
     * @return 角色列表
     */
    List<UserRole> findUserRolesByUserId(String userId);

    /**
     * 通过用户id删除用户角色关联关系
     *
     * @param userId 用户id
     */
    void deleteUserRoleBuUserId(String userId);

    /**
     * 批量根据用户id查询用户角色关联关系
     *
     * @param userIds 用户id
     * @return 用户角色关联关系
     */
    List<UserRole> findUserRolesByUserIdIn(List<String> userIds);

}
