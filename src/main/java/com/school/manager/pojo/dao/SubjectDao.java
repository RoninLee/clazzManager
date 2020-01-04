package com.school.manager.pojo.dao;

import com.school.manager.pojo.dto.common.BaseDTO;
import com.school.manager.pojo.dto.resp.SubjectResp;
import com.school.manager.pojo.entity.Subject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author RoninLee
 * @description 学科管理
 */
@Mapper
public interface SubjectDao {

    /**
     * [新增]
     *
     * @author RoninLee
     * @date 2020/01/04
     **/
    int save(Subject subject);

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
    int update(Subject subject);

    /**
     * [查询] 根据主键 id 查询
     *
     * @author RoninLee
     * @date 2020/01/04
     **/
    Subject info(String id);

    /**
     * [查询] 分页查询
     *
     * @author RoninLee
     * @date 2020/01/04
     **/
    List<Subject> pageList(int pageIndex, int pageSize);

    /**
     * [查询] 分页查询 count
     *
     * @author RoninLee
     * @date 2020/01/04
     **/
    int pageListCount();

    /**
     * 学科列表
     *
     * @return 学科列表
     */
    List<SubjectResp> list();

    /**
     * 学科下拉列表
     *
     * @param name 模糊查询
     * @return 学科下拉列表
     */
    List<BaseDTO<String>> dropdownList(String name);
}
