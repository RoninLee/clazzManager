package com.school.manager.service;

import com.school.manager.pojo.entity.UserPassword;

/**
 * @author RoninLee
 * @description 用户密码管理
 */
public interface UserPasswordService {

    /**
     * 新增或更新密码
     *
     * @param userPassword 用户密码
     */
    void saveOrUpdate(UserPassword userPassword);

    /**
     * 删除密码
     *
     * @param id 用户ID
     */
    void delete(String id);

    /**
     * 根据id查询密码
     *
     * @param id 用户id
     * @return 密码
     */
    String findById(String id);
}
