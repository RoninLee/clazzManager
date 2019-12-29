package com.school.manager.dao;

import com.school.manager.dto.resp.LoginUserResp;
import com.school.manager.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @author RoninLee
 * @description 用户
 */
public interface UserDao extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
    /**
     * 根据工号和密码查询人员
     *
     * @param jobNumber 工号
     * @param pwd       密码
     * @return 人员信息
     */
    @Query(value = "select user.* from user,user_pwd where user.id = user_pwd.id and user.job_number = ? and user_pwd.pwd = ?", nativeQuery = true)
    User login(String jobNumber, String pwd);

    /**
     * 根据工号查询人员信息
     *
     * @param jobNumber 工号
     * @return 登录人员信息
     */
    @Query(value = "select new com.school.manager.dto.resp.LoginUserResp(u.id,u.username,u.jobNumber,up.password) from User u,UserPwd up where u.id = up.id and u.jobNumber = :jobNumber ")
    LoginUserResp loginUser(@Param("jobNumber") String jobNumber);

    /**
     * 通过工号查人员
     *
     * @param jobNumber 工号
     * @return 人员信息
     */
    User findUserByJobNumber(String jobNumber);
}
