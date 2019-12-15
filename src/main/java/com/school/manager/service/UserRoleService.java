package com.school.manager.service;

import com.school.manager.dao.UserRoleDao;
import com.school.manager.pojo.UserRole;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author RoninLee
 * @description 用户角色管理
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
public class UserRoleService {
    private final UserRoleDao userRoleDao;

    public UserRoleService(UserRoleDao userRoleDao) {
        this.userRoleDao = userRoleDao;
    }

    /**
     * 批量新增用户角色关联关系
     *
     * @param userRoleList 用户角色关联关系列表
     */
    public void batchSave(List<UserRole> userRoleList) {
        userRoleDao.saveAll(userRoleList);
    }

    /**
     * 通过用户获取角色
     *
     * @return 角色列表
     */
    public List<UserRole> findUserRolesByUserId(Long userId) {
        return userRoleDao.findUserRolesByUserId(userId);
    }

    /**
     * 通过用户id删除用户角色关联关系
     *
     * @param userId 用户id
     */
    public void deleteUserRoleBuUserId(Long userId) {
        userRoleDao.deleteByUserId(userId);
    }

    /**
     * 批量根据用户id查询用户角色关联关系
     *
     * @param userIds 用户id
     * @return 用户角色关联关系
     */
    public List<UserRole> findUserRolesByUserIdIn(List<Long> userIds) {
        return userRoleDao.findUserRolesByUserIdIn(userIds);
    }

}
