package com.school.manager.pojo.dao;

import com.school.manager.pojo.entity.Chapter;
import org.apache.ibatis.annotations.Mapper;

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
    int delete(String id);

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
    Chapter info(String id);
}
