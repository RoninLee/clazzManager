package com.school.manager.dao;

import com.school.manager.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author RoninLee
 * @description 用户
 */
public interface UserDao extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
    /**
     * 通过工号查人员
     *
     * @param jobNumber 工号
     * @return 人员信息
     */
    User findUserByJobNumber(String jobNumber);
}
