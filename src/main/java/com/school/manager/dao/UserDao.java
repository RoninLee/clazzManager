package com.school.manager.dao;

import com.school.manager.pojo.User;
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
     * 通过工号查人员
     *
     * @param jobNumber 工号
     * @return 人员信息
     */
    User findUserByJobNumber(String jobNumber);
}
