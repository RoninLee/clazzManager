package com.school.manager.pojo.dao;

import com.school.manager.pojo.bo.GroupInfoBO;
import com.school.manager.pojo.bo.GroupLeaderInfoBO;
import com.school.manager.pojo.entity.Group;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @author lizelong
 * @description 组管理
 */
@Mapper
public interface GroupDao {

    /**
     * 添加组
     *
     * @param lessonGroup 组
     */
    void add(@Param("lessonGroup") Group lessonGroup);

    /**
     * 组详情
     *
     * @param id 组id
     * @return 组信息
     */
    Group info(@Param("id") String id);

    /**
     * 组信息 包含组长
     *
     * @param id 组ID
     * @return 组信息
     */
    GroupLeaderInfoBO infoWithLeader(@Param("id") String id);

    /**
     * 更新
     *
     * @param group
     */
    void update(Group group);

    /**
     * 删除
     *
     * @param id 组ID
     */
    void delete(@Param("id") String id);

    /**
     * 根据学科查询
     *
     * @param subjectId
     * @return
     */
    Integer getBySubjectId(@Param("subjectId") String subjectId);

    /**
     * 根据年级ID查询
     *
     * @param gradeId
     * @return
     */
    Integer getByGradeId(@Param("gradeId") String gradeId);

    /**
     * 姓名和工号模糊查询组
     *
     * @param name
     * @param id
     * @return
     */
    List<GroupInfoBO> getByIdOrFuzzyName(@Param("name") String name, @Param("id") String id);

    /**
     * 根据组长id查询所有组员id
     *
     * @param userId 组长ID
     * @return 组员列表
     */
    Set<String> getByLeaderId(@Param("userId") String userId);

    /**
     * 根据用户ID查询组信息
     *
     * @param userId 用户ID
     * @return 组信息
     */
    Group getByUserId(@Param("userId") String userId);
}
