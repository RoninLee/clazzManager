package com.school.manager.pojo.dao;

import com.school.manager.pojo.dto.resp.GroupLessonListResp;
import com.school.manager.pojo.dto.resp.UserGradeSubjectResp;
import com.school.manager.pojo.entity.UserGradeSubject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author RoninLee
 * @description 人员年级学科关联关系
 */
@Mapper
public interface UserGradeSubjectDao {
    /**
     * 根据用户id批量删除
     *
     * @param userId 用户id
     */
    void deleteAllByUserId(String userId);

    /**
     * [新增]
     *
     * @author RoninLee
     * @date 2020/01/05
     **/
    int save(UserGradeSubject userGradeSubject);

    /**
     * [刪除]
     *
     * @author RoninLee
     * @date 2020/01/05
     **/
    int delete(String id);

    /**
     * [更新]
     *
     * @author RoninLee
     * @date 2020/01/05
     **/
    int update(UserGradeSubject userGradeSubject);

    /**
     * [查询] 根据主键 id 查询
     *
     * @author RoninLee
     * @date 2020/01/05
     **/
    UserGradeSubjectResp info(String id);

    /**
     * [查询] 分页查询
     *
     * @author RoninLee
     * @date 2020/01/05
     **/
    List<UserGradeSubject> pageList(int pageIndex, int pageSize);

    /**
     * [查询] 分页查询 count
     *
     * @author RoninLee
     * @date 2020/01/05
     **/
    int pageListCount(int pageIndex, int pageSize);

    List<UserGradeSubject> listByUserId(String userId);

    /**
     * 根据id查询
     *
     * @param id 主键ID
     * @return 关联关系
     */
    UserGradeSubject getById(String id);

    /**
     * 查询列表
     *
     * @param fuzzyName 用户名和工号模糊查询
     * @param index     开始
     * @param pageSize  数量
     * @return 列表
     */
    List<UserGradeSubjectResp> list(@Param("fuzzyName") String fuzzyName, @Param("index") Integer index, @Param("pageSize") Integer pageSize);


    /**
     * 查询课程列表
     *
     * @param leaderId 组长ID
     * @return 课程列表
     */
    List<GroupLessonListResp> lessonList(@Param("leaderId") String leaderId);

    /**
     * 根据年级查询关联关系
     *
     * @param gradeId 年级ID
     * @return
     */
    Integer getByGradeId(@Param("gradeId") String gradeId);

    /**
     * 根据用户ID查询关联关系
     *
     * @param userId
     * @return
     */
    Integer getByUserId(@Param("userId") String userId);

    /**
     * 根据学科ID查询
     *
     * @param subjectId
     * @return
     */
    Integer getBySubjectId(@Param("subjectId") String subjectId);
}
