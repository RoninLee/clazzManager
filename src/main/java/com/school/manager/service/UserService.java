package com.school.manager.service;

import com.school.manager.dto.req.LoginReq;
import com.school.manager.dto.req.UserReq;
import com.school.manager.dto.resp.UserResp;
import com.school.manager.entity.LoginUserInfo;
import com.school.manager.pojo.User;
import org.springframework.data.domain.Page;

/**
 * @author RoninLee
 * @description 用户管理
 */
public interface UserService {


    /**
     * 登录
     *
     * @param request 请求对象
     * @return 用户信息
     */
    LoginUserInfo login(LoginReq request);

    /**
     * 分页查询用户列表
     *
     * @param request 请求对象
     * @return 人员列表
     */
    Page<User> findValidList(UserReq request);

    /**
     * 通过id查人员
     *
     * @param id id
     * @return 人员信息
     */
    UserResp findById(String id);

    /**
     * 新增用户
     *
     * @param request 用户信息
     * @return 用户id
     */
    String save(UserReq request);

    /**
     * 更新用户
     *
     * @param request 用户信息
     * @return 用户id
     */
    String update(UserReq request);

    /**
     * 删除用户
     *
     * @param id 用户id
     */
    void removeById(String id);

    /**
     * 查询用户详情
     *
     * @param jobNumber 工号
     * @return 用户详细信息
     */
    com.school.manager.entity.LoginUserInfo info(String jobNumber);
}
