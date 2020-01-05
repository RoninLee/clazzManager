package com.school.manager.pojo.dao;

import com.school.manager.pojo.dto.resp.GradeSubjectResp;
import com.school.manager.pojo.entity.Chapter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author RoninLee
 * @description 章节数据层
 */
@Mapper
public interface ChapterDao {
    /**
     * [新增]
     *
     * @author RoninLee
     * @date 2020/01/04
     **/
    int save(Chapter chapter);

    /**
     * [刪除]
     *
     * @author RoninLee
     * @date 2020/01/04
     **/
    int delete(@Param("id") String id);

    /**
     * [更新]
     *
     * @author RoninLee
     * @date 2020/01/04
     **/
    int update(Chapter chapter);

    /**
     * [查询] 根据主键 id 查询
     *
     * @author RoninLee
     * @date 2020/01/04
     **/
    Chapter info(@Param("id") String id);

    /**
     * 查询是当前节点否存在子节点
     *
     * @param id 当前节点id
     * @return 子节点数量
     */
    int childNodes(@Param("id") String id);

    List<GradeSubjectResp> gradeSubjectDropdownList(@Param("userId") String userId);
}
