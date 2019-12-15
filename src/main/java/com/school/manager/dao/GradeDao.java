package com.school.manager.dao;

import com.school.manager.pojo.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author RoninLee
 * @description 年级管理
 */
public interface GradeDao extends JpaRepository<Grade, Long>, JpaSpecificationExecutor<Grade> {
}
