package com.school.manager.pojo.dao;

import com.school.manager.pojo.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author RoninLee
 * @description 用户角色
 */
@Mapper
public interface UserRoleDao {
    /**
     * 通过用户id查询用户角色
     *
     * @param userId 用户id
     * @return 用户角色列表
     */
    List<UserRole> findUserRolesByUserId(String userId);

    /**
     * 通过用户id删除用户角色关联关系
     *
     * @param userId 用户id
     */
    void deleteByUserId(String userId);

    /**
     * 保存用户角色关系
     *
     * @param role 关系信息
     */
    void save(UserRole role);

    /**
     * 批量保存用户角色 关系
     *
     * @param userRoleList 关系信息列表
     */
    void saveAll(List<UserRole> userRoleList);
}
