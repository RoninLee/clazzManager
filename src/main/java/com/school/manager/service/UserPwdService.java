package com.school.manager.service;

import com.school.manager.pojo.UserPwd;

/**
 * @author RoninLee
 * @description 用户密码管理
 */
public interface UserPwdService {

    /**
     * 新增或更新密码
     *
     * @param userPwd 用户密码
     */
    void saveOrUpdate(UserPwd userPwd);

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
