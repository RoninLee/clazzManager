package com.school.manager.dao;

import com.school.manager.pojo.UserPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author RoninLee
 * @description 用户密码
 */
public interface UserPasswordDao extends JpaRepository<UserPassword, String>, JpaSpecificationExecutor<UserPassword> {
}
