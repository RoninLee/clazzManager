package com.school.manager.service.impl;

import com.school.manager.dao.UserRoleDao;
import com.school.manager.pojo.UserRole;
import com.school.manager.service.UserRoleService;
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
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleDao userRoleDao;

    public UserRoleServiceImpl(UserRoleDao userRoleDao) {
        this.userRoleDao = userRoleDao;
    }

    /**
     * 新增用户角色关联关系
     *
     * @param role 用户角色关联关系
     */
    @Override
    public void save(UserRole role) {
        userRoleDao.save(role);
    }

    /**
     * 批量新增用户角色关联关系
     *
     * @param userRoleList 用户角色关联关系列表
     */
    @Override
    public void batchSave(List<UserRole> userRoleList) {
        userRoleDao.saveAll(userRoleList);
    }

    /**
     * 通过用户获取角色
     *
     * @return 角色列表
     */
    @Override
    public List<UserRole> findUserRolesByUserId(String userId) {
        return userRoleDao.findUserRolesByUserId(userId);
    }

    /**
     * 通过用户id删除用户角色关联关系
     *
     * @param userId 用户id
     */
    @Override
    public void deleteUserRoleBuUserId(String userId) {
        userRoleDao.deleteByUserId(userId);
    }

    /**
     * 批量根据用户id查询用户角色关联关系
     *
     * @param userIds 用户id
     * @return 用户角色关联关系
     */
    @Override
    public List<UserRole> findUserRolesByUserIdIn(List<String> userIds) {
        return userRoleDao.findUserRolesByUserIdIn(userIds);
    }

}
