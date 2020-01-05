package com.school.manager.service.impl;

import com.school.manager.pojo.dao.UserPasswordDao;
import com.school.manager.pojo.entity.UserPassword;
import com.school.manager.service.UserPasswordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author RoninLee
 * @description 用户密码管理
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
public class UserPasswordServiceImpl implements UserPasswordService {

    @Resource
    private UserPasswordDao userPasswordDao;

    /**
     * 新增或更新密码
     *
     * @param userPassword 用户密码
     */
    @Override
    public void save(UserPassword userPassword) {
        userPasswordDao.save(userPassword);
    }

    /**
     * 新增或更新密码
     *
     * @param userPassword 用户密码
     */
    @Override
    public void update(UserPassword userPassword) {
        userPasswordDao.update(userPassword);
    }

    /**
     * 删除密码
     *
     * @param id 用户ID
     */
    @Override
    public void delete(String id) {
        userPasswordDao.delete(id);
    }

    /**
     * 根据id查询密码
     *
     * @param id 用户id
     * @return 密码
     */
    @Override
    public String findById(String id) {
        return Optional.ofNullable(userPasswordDao.findById(id)).map(UserPassword::getPassword).orElse(null);
    }
}
