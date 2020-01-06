package com.school.manager.service;

import com.school.manager.jwt.LoginUserInfo;
import com.school.manager.pojo.dto.common.BaseDTO;
import com.school.manager.pojo.dto.common.FuzzyQueryReq;
import com.school.manager.pojo.dto.common.PageResult;
import com.school.manager.pojo.dto.req.LoginReq;
import com.school.manager.pojo.dto.req.UserSaveReq;
import com.school.manager.pojo.dto.req.UserUpdateReq;
import com.school.manager.pojo.dto.resp.UserDropdownListResp;
import com.school.manager.pojo.dto.resp.UserResp;

import java.util.List;

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
    PageResult<List<BaseDTO<String>>> list(FuzzyQueryReq request);

    /**
     * 通过id查人员
     *
     * @param id id
     * @return 人员信息
     */
    UserResp info(String id);

    /**
     * 新增用户
     *
     * @param request 用户信息
     * @return 用户id
     */
    String save(UserSaveReq request);

    /**
     * 更新用户
     *
     * @param request 用户信息
     * @return 用户id
     */
    String update(UserUpdateReq request);

    /**
     * 删除用户
     *
     * @param id 用户id
     */
    void delete(String id);

    /**
     * 查询用户详情
     *
     * @param jobNumber 工号
     * @return 用户详细信息
     */
    LoginUserInfo loginUserInfo(String jobNumber);

    /**
     * 修改密码
     *
     * @param oldPassword 原始密码
     * @param newPassword 新密码
     */
    void updatePassword(String oldPassword, String newPassword);

    /**
     * 用户下拉列表
     *
     * @param name 模糊查询字段
     * @return 用户列表
     */
    List<UserDropdownListResp> userList(String name);
}
