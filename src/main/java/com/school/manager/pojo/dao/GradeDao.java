package com.school.manager.pojo.dao;

import com.school.manager.pojo.dto.common.BaseDTO;
import com.school.manager.pojo.dto.resp.GradeResp;
import com.school.manager.pojo.entity.Grade;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author RoninLee
 * @description 年级管理
 */
@Mapper
public interface GradeDao {
    /**
     * [新增]
     *
     * @param grade 年级信息
     * @return 成功数量
     **/
    int save(Grade grade);

    /**
     * [刪除]
     *
     * @param id 年级id
     * @return 成功数量
     **/
    int delete(String id);

    /**
     * [更新]
     *
     * @param grade 年级信息
     * @return 成功数量
     **/
    int update(Grade grade);

    /**
     * [查询] 根据主键 id 查询
     *
     * @param id 年级id
     * @return 年级信息
     **/
    Grade info(String id);

    /**
     * 年级列表
     *
     * @return 年级列表
     */
    List<GradeResp> list();

    /**
     * 年级下拉列表
     *
     * @return 年级列表
     */
    List<BaseDTO<String>> dropdownList();
}
