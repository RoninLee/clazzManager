package com.school.manager.dao;

import com.school.manager.pojo.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author RoninLee
 * @description 学科管理
 */
public interface SubjectDao extends JpaRepository<Subject, String>, JpaSpecificationExecutor<Subject> {
    /**
     * 分页查询
     *
     * @param pageIndex 页码
     * @param pageSize  数量
     * @return 列表
     */
    @Query(value = "select * from subject limit ?1,?2", nativeQuery = true)
    List<Subject> list(Integer pageIndex, Integer pageSize);
}
