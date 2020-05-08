package com.school.manager.pojo.dao;

import com.school.manager.pojo.dto.common.BaseDTO;
import com.school.manager.pojo.dto.resp.LessonPlanListResp;
import com.school.manager.pojo.dto.resp.LessonStatisticsInfoResp;
import com.school.manager.pojo.entity.LessonPlan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author RoninLee
 * @description 教案
 */
@Mapper
public interface LessonPlanDao {

    /**
     * [新增]
     *
     * @author RoninLee
     * @date 2020/01/04
     **/
    int save(LessonPlan lessonPlan);

    /**
     * [刪除]
     *
     * @author RoninLee
     * @date 2020/01/04
     **/
    int delete(String id);

    /**
     * [更新]
     *
     * @author RoninLee
     * @date 2020/01/04
     **/
    int update(LessonPlan lessonPlan);

    /**
     * [查询] 根据主键 id 查询
     *
     * @author RoninLee
     * @date 2020/01/04
     **/
    LessonPlan info(String id);

    /**
     * [查询] 分页查询
     *
     * @author RoninLee
     * @date 2020/01/04
     **/
    List<LessonPlanListResp> pageList(@Param("relationId") String relationId, @Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);

    /**
     * [查询] 分页查询 count
     *
     * @author RoninLee
     * @date 2020/01/04
     **/
    Long pageListCount(@Param("relationId") String relationId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    /**
     * 根据用户id查询年级学科
     *
     * @param userId 用户id
     * @return <关联关系id,年级+学科>
     */
    List<BaseDTO<String>> gradeSubList(String userId);

    /**
     * 根据绑定关系查询教案
     *
     * @param relationId
     * @return
     */
    Integer getByRelationId(@Param("relationId") String relationId);

    /**
     * 根据用户ID查询教案
     *
     * @param userId
     * @return
     */
    Integer getByUserId(@Param("userId") String userId);

    /**
     * 统计各组员备课情况
     *
     * @param userIds 组员ID
     * @return 备课情况
     */
    List<LessonStatisticsInfoResp> statistics(@Param("userIds") Set<String> userIds);
}
