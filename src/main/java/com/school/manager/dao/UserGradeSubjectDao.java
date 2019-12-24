package com.school.manager.dao;

import com.school.manager.dto.resp.UserGradeSubjectResp;
import com.school.manager.pojo.UserGradeSubject;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @author RoninLee
 * @description 人员年级学科关联关系
 */
public interface UserGradeSubjectDao extends JpaRepository<UserGradeSubject, String>, JpaSpecificationExecutor<UserGradeSubject> {
    /**
     * 根据用户id批量删除
     *
     * @param userId 用户id
     */
    void deleteAllByUserId(String userId);

    /**
     * 模糊查询
     *
     * @param fuzzyName 模糊字段
     * @param pageable  分页对象
     * @return 关系列表
     */
    @Query(value = "select new com.school.manager.dto.resp.UserGradeSubjectResp(ugs.id,u.id,u.name,u.jobNumber,g.id,g.name,s.id,s.name) from UserGradeSubject ugs join User u on ugs.userId = u.id join Grade g on ugs.gradeId = g.id join Subject s on ugs.subjectId = s.id where u.name like concat(:fuzzyName,'%') or u.jobNumber like concat(:fuzzyName,'%')")
    Page<UserGradeSubjectResp> fuzzyQueryList(@Param("fuzzyName") String fuzzyName, Pageable pageable);
}
