package com.school.manager.service.impl;

import com.school.manager.dao.UserPwdDao;
import com.school.manager.pojo.UserPwd;
import com.school.manager.service.UserPwdService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author RoninLee
 * @description 用户密码管理
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
public class UserPwdServiceImpl implements UserPwdService {
    private final UserPwdDao userPwdDao;

    public UserPwdServiceImpl(UserPwdDao userPwdDao) {
        this.userPwdDao = userPwdDao;
    }

    /**
     * 新增或更新密码
     *
     * @param userPwd 用户密码
     */
    @Override
    public void saveOrUpdate(UserPwd userPwd) {
        userPwdDao.save(userPwd);
    }

    /**
     * 删除密码
     *
     * @param id 用户ID
     */
    @Override
    public void delete(String id) {
        userPwdDao.deleteById(id);
    }

    /**
     * 根据id查询密码
     *
     * @param id 用户id
     * @return 密码
     */
    @Override
    public String findById(String id) {
        return userPwdDao.findById(id).map(UserPwd::getPassword).orElse(null);
    }
}
