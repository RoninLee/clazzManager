package com.school.manager.dao;

import com.school.manager.pojo.UserPwd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author RoninLee
 * @description 用户密码
 */
public interface UserPwdDao extends JpaRepository<UserPwd, String>, JpaSpecificationExecutor<UserPwd> {
}
