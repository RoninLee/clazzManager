package com.school.manager.service;

import com.school.manager.pojo.dto.req.ChapterSaveReq;
import com.school.manager.pojo.dto.req.ChapterUpdateReq;
import com.school.manager.pojo.dto.resp.ChapterInfoResp;
import com.school.manager.pojo.dto.resp.GradeSubjectResp;

import java.util.List;

/**
 * @author RoninLee
 * @description 章节管理
 */
public interface ChapterService {
    /**
     * 新增
     *
     * @param request 章节对象
     * @return id
     */
    String save(ChapterSaveReq request);

    /**
     * 删除
     *
     * @param id 主键id
     */
    void delete(String id);

    /**
     * 更新
     *
     * @param request 章节id
     * @return 主键id
     */
    String update(ChapterUpdateReq request);

    /**
     * 根据主键 id 查询
     *
     * @param id 主键id
     * @return 返回对象
     */
    ChapterInfoResp info(String id);

    /**
     * 当前用户所绑定的年级学科下拉列表
     *
     * @return 当前用户所绑定的年级学科下拉列表
     */
    List<GradeSubjectResp> gradeSubjectDropdownList();

    // TODO: 2020/1/4 章节树接口 redis
}
