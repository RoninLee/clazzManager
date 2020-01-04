package com.school.manager.pojo.dao;

import com.school.manager.pojo.entity.LessonPlan;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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
    LessonPlan info(int id);

    /**
     * [查询] 分页查询
     *
     * @author RoninLee
     * @date 2020/01/04
     **/
    List<LessonPlan> pageList(int offset, int pageSize);

    /**
     * [查询] 分页查询 count
     *
     * @author RoninLee
     * @date 2020/01/04
     **/
    int pageListCount(int offset, int pagesize);
}
