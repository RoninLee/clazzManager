package com.school.manager.pojo.dao;

import com.school.manager.pojo.entity.UserPassword;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author RoninLee
 * @description 用户密码
 */
@Mapper
public interface UserPasswordDao {

    /**
     * 保存密码
     *
     * @param userPassword 密码信息
     */
    void save(UserPassword userPassword);

    /**
     * 删除密码
     *
     * @param id 密码id
     */
    void delete(String id);

    /**
     * 查询密码
     *
     * @param id 密码id
     * @return 密码信息
     */
    UserPassword findById(String id);
}
