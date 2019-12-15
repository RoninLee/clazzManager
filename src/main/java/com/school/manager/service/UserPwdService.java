package com.school.manager.service;

import com.school.manager.dao.UserPwdDao;
import com.school.manager.pojo.UserPwd;
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
public class UserPwdService {
    private final UserPwdDao userPwdDao;

    public UserPwdService(UserPwdDao userPwdDao) {
        this.userPwdDao = userPwdDao;
    }

    /**
     * 新增或更新密码
     *
     * @param userPwd 用户密码
     */
    public void saveOrUpdate(UserPwd userPwd) {
        userPwdDao.save(userPwd);
    }

}
