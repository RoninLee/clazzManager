package com.school.manager.dao;

import com.school.manager.pojo.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author RoninLee
 * @description 用户角色
 */
public interface UserRoleDao extends JpaRepository<UserRole, Long>, JpaSpecificationExecutor<UserRole> {
    /**
     * 通过用户id查询用户角色
     *
     * @param userId 用户id
     * @return 用户角色列表
     */
    List<UserRole> findUserRolesByUserId(Long userId);

    /**
     * 通过用户id删除用户角色关联关系
     *
     * @param userId 用户id
     */
    void deleteByUserId(Long userId);

    /**
     * 批量查询用户们的角色关联关系
     *
     * @param userIds 用户id
     * @return 用户角色关联关系
     */
    List<UserRole> findUserRolesByUserIdIn(List<Long> userIds);
}
